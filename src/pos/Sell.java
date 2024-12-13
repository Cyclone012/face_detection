/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author CycloneTG
 */
public class Sell extends javax.swing.JFrame {

    /**
     * Creates new form Sell
     */
    SqlConnector con = new SqlConnector();
    HeaderCustom headerCustom = new HeaderCustom();
    
    private int staffId;
    private String staffName;
    private String position;
    private boolean isMaximized = false;
    
    public Sell(int staffId) {
        this.staffId = staffId;
        initComponents();
        
        headerCustom.moveFrame(Header, isMaximized);
        
        try {
            con.ConnectionDB("localhost:3306", "clothesshop", "root", "");
            GetData getData = new GetData(con);
            getData.staffChecker(staffId);
            String staffName = getData.getStaffName().toUpperCase();

            SwingUtilities.invokeLater(() -> jLabelName.setText(staffName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void staffChecker() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.dataCon.createStatement();

            String query = "SELECT * FROM tblStaff where id = '" + staffId + "'";
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                position = rs.getString("position").toLowerCase();
                position = position.substring(0, 1).toUpperCase() + position.substring(1);
                staffName = rs.getString("name");
                staffName = staffName.substring(0, 1).toUpperCase() + staffName.substring(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void changeColor(JPanel panelName, Color color) {
        panelName.setBackground(color);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Border = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        iconMinMaxClose = new javax.swing.JPanel();
        pClose = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pMax = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pMin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        iconLogoTitle = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        titleFrame = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Border.setBackground(new java.awt.Color(35, 35, 35));
        Border.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        Border.setLayout(new java.awt.BorderLayout());

        Header.setBackground(new java.awt.Color(25, 25, 25));
        Header.setPreferredSize(new java.awt.Dimension(798, 40));
        Header.setLayout(new java.awt.BorderLayout());

        iconMinMaxClose.setBackground(new java.awt.Color(25, 25, 25));
        iconMinMaxClose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pClose.setBackground(new java.awt.Color(25, 25, 25));
        pClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pCloseMouseExited(evt);
            }
        });
        pClose.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close-24.png"))); // NOI18N
        pClose.add(jLabel1, java.awt.BorderLayout.CENTER);

        iconMinMaxClose.add(pClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 50, 40));

        pMax.setBackground(new java.awt.Color(25, 25, 25));
        pMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMaxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pMaxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pMaxMouseExited(evt);
            }
        });
        pMax.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/maximize-24.png"))); // NOI18N
        pMax.add(jLabel2, java.awt.BorderLayout.CENTER);

        iconMinMaxClose.add(pMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, 40));

        pMin.setBackground(new java.awt.Color(25, 25, 25));
        pMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pMinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pMinMouseExited(evt);
            }
        });
        pMin.setLayout(new java.awt.BorderLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimize-24.png"))); // NOI18N
        pMin.add(jLabel3, java.awt.BorderLayout.CENTER);

        iconMinMaxClose.add(pMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 50, 40));

        Header.add(iconMinMaxClose, java.awt.BorderLayout.LINE_END);

        iconLogoTitle.setBackground(new java.awt.Color(25, 25, 25));
        iconLogoTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-24.png"))); // NOI18N
        jPanel1.add(jLabel4, java.awt.BorderLayout.CENTER);

        iconLogoTitle.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 40));

        jPanel2.setBackground(new java.awt.Color(25, 25, 25));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabelName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelName.setText("Admin");
        jPanel2.add(jLabelName, java.awt.BorderLayout.CENTER);

        iconLogoTitle.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 150, 40));

        Header.add(iconLogoTitle, java.awt.BorderLayout.LINE_START);

        titleFrame.setBackground(new java.awt.Color(25, 25, 25));
        titleFrame.setLayout(new java.awt.BorderLayout());

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Dashboard");
        titleFrame.add(jLabel5, java.awt.BorderLayout.CENTER);

        Header.add(titleFrame, java.awt.BorderLayout.CENTER);

        Border.add(Header, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(35, 35, 35));
        jPanel4.setPreferredSize(new java.awt.Dimension(1078, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(77, 77, 77));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        Border.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(Border, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1080, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCloseMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_pCloseMouseClicked

    private void pCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCloseMouseEntered
        // TODO add your handling code here:
        changeColor(pClose, new Color(255, 0, 0));
    }//GEN-LAST:event_pCloseMouseEntered

    private void pCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCloseMouseExited
        // TODO add your handling code here:
        changeColor(pClose, new Color(25, 25, 25));
    }//GEN-LAST:event_pCloseMouseExited

    private void pMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMaxMouseClicked
        // TODO add your handling code here:
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Adjust size to not cover the taskbar
        int taskbarHeight = toolkit.getScreenInsets(this.getGraphicsConfiguration()).bottom;
        int adjustedHeight = screenHeight - taskbarHeight;

        if (!isMaximized) {
            this.setBounds(0, 0, screenWidth, adjustedHeight);
            isMaximized = true;
        } else {
            // Restore to normal size
            this.setSize(1080, 720);
            this.setLocationRelativeTo(null);
            isMaximized = false;
        }
        headerCustom.moveFrame(Header, isMaximized);
    }//GEN-LAST:event_pMaxMouseClicked

    private void pMaxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMaxMouseEntered
        // TODO add your handling code here:
        changeColor(pMax, new Color(102, 102, 102));
    }//GEN-LAST:event_pMaxMouseEntered

    private void pMaxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMaxMouseExited
        // TODO add your handling code here:
        changeColor(pMax, new Color(25, 25, 25));
    }//GEN-LAST:event_pMaxMouseExited

    private void pMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMinMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(HIDE_ON_CLOSE);
    }//GEN-LAST:event_pMinMouseClicked

    private void pMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMinMouseEntered
        // TODO add your handling code here:
        changeColor(pMin, new Color(102, 102, 102));
    }//GEN-LAST:event_pMinMouseEntered

    private void pMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMinMouseExited
        // TODO add your handling code here:
        changeColor(pMin, new Color(25, 25, 25));
    }//GEN-LAST:event_pMinMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Border;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel iconLogoTitle;
    private javax.swing.JPanel iconMinMaxClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel pClose;
    private javax.swing.JPanel pMax;
    private javax.swing.JPanel pMin;
    private javax.swing.JPanel titleFrame;
    // End of variables declaration//GEN-END:variables
}
