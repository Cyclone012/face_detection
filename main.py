import os
import sys
import cv2
import numpy as np
import face_recognition
import math
from datetime import datetime


def face_confidence(face_distance, face_match_threshold=0.6):
    range = (1.0 - face_match_threshold)
    linear_val = (1.0 - face_distance) / (range * 2.0)

    if face_distance > face_match_threshold:
        return str(round(linear_val * 100, 2)) + '%'
    else:
        value = (linear_val + ((1.0 - linear_val) * math.pow((linear_val - 0.5) * 2, 0.2))) * 100
        return str(round(value, 2)) + '%'


class faceRecognition:
    face_locations = []
    face_encodings = []
    face_names = []
    known_face_encodings = []
    known_face_names = []
    process_current_frame = True
    checked_in_faces = set()

    def __init__(self):
        pass

    # Encode known faces
    def encode_face(self):
        for image in os.listdir('images'):
            face_image = face_recognition.load_image_file(f'images/{image}')
            face_encoding = face_recognition.face_encodings(face_image)[0]

            self.known_face_encodings.append(face_encoding)
            self.known_face_names.append(os.path.splitext(image)[0])  # Store base name without extension

        print(self.known_face_names)

    # Log check-in times
    def log_checkin(self, name, late_hours=None, late_minutes=None):
        now = datetime.now().strftime('%d/%b/%Y %H:%M:%S')
        if late_hours is not None and late_minutes is not None:
            hour_str = "hour" if late_hours == 1 else "hours"
            minute_str = "minute" if late_minutes == 1 else "minutes"
            with open('checkin.txt', 'a') as file:
                file.write(
                    f"{name} checked in at {now} (Late by {int(late_hours)} {hour_str} and {int(late_minutes)} {minute_str})\n")
        else:
            with open('checkin.txt', 'a') as file:
                file.write(f"{name} checked in at {now}\n")

    # Calculate late hours if checked in after 7:30 AM
    def calculate_late_hours(self, checkin_time):
        late_hours = None
        late_minutes = None
        checkin_hour = checkin_time.hour
        checkin_minute = checkin_time.minute

        if checkin_hour < 7 or (checkin_hour == 7 and checkin_minute <= 30):
            return late_hours, late_minutes

        # Calculate the number of hours late
        checkin_datetime = datetime.now().replace(hour=checkin_hour, minute=checkin_minute, second=0, microsecond=0)
        late_time = datetime.now().replace(hour=7, minute=30, second=0, microsecond=0)
        if checkin_datetime > late_time:
            late_delta = checkin_datetime - late_time
            late_hours = late_delta.seconds // 3600
            late_minutes = (late_delta.seconds // 60) % 60

        return late_hours, late_minutes

    # Check if a person has already checked in today
    def has_checked_in_today(self, name):
        today_str = datetime.now().strftime('%d/%b/%Y')
        if os.path.exists('checkin.txt'):
            with open('checkin.txt', 'r') as file:
                for line in file:
                    if name in line and today_str in line:
                        return True
        return False

    # Run face recognition using DroidCam
    def run_recognition(self, video_source=0):
        video_capture = cv2.VideoCapture(video_source)

        if not video_capture.isOpened():
            sys.exit('DroidCam not found. Please check your connection.')

        while True:
            ret, frame = video_capture.read()

            if self.process_current_frame:
                # Resize frame and convert to RGB
                small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
                rgb_small_frame = small_frame[:, :, ::-1]

                # Find all faces in the current frame
                self.face_locations = face_recognition.face_locations(rgb_small_frame)
                self.face_encodings = face_recognition.face_encodings(rgb_small_frame, self.face_locations)

                self.face_names = []
                for face_encoding in self.face_encodings:
                    matches = face_recognition.compare_faces(self.known_face_encodings, face_encoding)
                    name = 'Unknown'
                    confidence = '0%'

                    face_distances = face_recognition.face_distance(self.known_face_encodings, face_encoding)
                    best_match_index = np.argmin(face_distances)

                    check_in_status = "Unknown Person"

                    if matches[best_match_index]:
                        confidence = face_confidence(face_distances[best_match_index])
                        confidence_value = float(confidence.rstrip('%'))

                        if confidence_value >= 95:
                            name = self.known_face_names[best_match_index]
                            if name != 'Unknown':
                                if self.has_checked_in_today(name):
                                    check_in_status = "Already Check in"
                                else:
                                    self.checked_in_faces.add(name)
                                    checking_time: datetime = datetime.now()
                                    late_hours, late_minutes = self.calculate_late_hours(checking_time)
                                    self.log_checkin(name, late_hours, late_minutes)

                                    if late_hours is not None and late_minutes is not None:
                                        check_in_status = f"Check in done (Late by {int(late_hours)} hours and {int(late_minutes)} minutes)"
                                    else:
                                        check_in_status = "Check in done"
                        else:
                            confidence = "0%"
                            check_in_status = "Unknown Person"

                    self.face_names.append((name, confidence, check_in_status))

            self.process_current_frame = not self.process_current_frame

            # Display annotations
            for (top, right, bottom, left), (name, confidence, check_in_status) in zip(self.face_locations,
                                                                                       self.face_names):
                top *= 4
                right *= 4
                bottom *= 4
                left *= 4

                # Draw rectangle around the face
                cv2.rectangle(frame, (left, top), (right, bottom + 60), (0, 0, 255), 2)
                # Draw background rectangle for name and confidence
                cv2.rectangle(frame, (left, bottom - 5), (right, bottom + 60), (0, 0, 255), -1)

                # Adjust font scale to fit the text inside the frame
                name_conf_text = f"{name} ({confidence})"
                (text_width, text_height), _ = cv2.getTextSize(name_conf_text, cv2.FONT_HERSHEY_DUPLEX, 1, 1)
                box_width = right - left
                font_scale = min((box_width - 10) / text_width, 1.0)

                # name and confidence
                cv2.putText(frame, name_conf_text, (left + 6, bottom + 25), cv2.FONT_HERSHEY_DUPLEX, font_scale,
                            (255, 255, 255), 1)
                # check-in status
                cv2.putText(frame, check_in_status, (left + 6, bottom + 50), cv2.FONT_HERSHEY_DUPLEX, font_scale,
                            (255, 255, 255), 1)

            cv2.imshow('Face Recognition', frame)

            if cv2.waitKey(1) == ord('q'):
                break

        video_capture.release()
        cv2.destroyAllWindows()


if __name__ == '__main__':
    fr = faceRecognition()
    fr.encode_face()  # Encode known faces
    fr.run_recognition(video_source=1)  # Change video_source to the correct index for DroidCam
