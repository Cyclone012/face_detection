/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CycloneTG
 */
public class HeaderCustom extends JPanel { 
    
    Point initialClick;
    
    public void moveFrame(JPanel panelName, boolean isMaximized) {
        // Remove existing listeners if not maximized
        if (!isMaximized) {
            // Remove all existing mouse listeners
            for (MouseListener ml : panelName.getMouseListeners()) {
                panelName.removeMouseListener(ml);
            }
            for (MouseMotionListener mml : panelName.getMouseMotionListeners()) {
                panelName.removeMouseMotionListener(mml);
            }

            // Add new MouseListener for mouse press
            panelName.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                    getParentFrame(panelName).setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    getParentFrame(panelName).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            });

            // Add MouseMotionListener for dragging the frame
            panelName.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    // Get the frame and move it
                    JFrame parentFrame = getParentFrame(panelName);
                    if (parentFrame != null) {
                        int mouseXOnScreen = e.getXOnScreen();
                        int mouseYOnScreen = e.getYOnScreen();

                        int newX = mouseXOnScreen - initialClick.x;
                        int newY = mouseYOnScreen - initialClick.y;

                        parentFrame.setLocation(newX, newY); // Move the frame
                    }
                }
            });
        } else {
            // Remove all listeners to disable dragging if maximized
            for (MouseListener ml : panelName.getMouseListeners()) {
                panelName.removeMouseListener(ml);
            }
            for (MouseMotionListener mml : panelName.getMouseMotionListeners()) {
                panelName.removeMouseMotionListener(mml);
            }
        }
    }

    // Helper method to find the parent JFrame
    private JFrame getParentFrame(Component component) {
        while (component.getParent() != null) {
            component = component.getParent();
            if (component instanceof JFrame) {
                return (JFrame) component;
            }
        }
        return null;
    }
}
