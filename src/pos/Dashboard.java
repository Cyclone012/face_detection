/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author CycloneTG
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    SqlConnector con = new SqlConnector();
    HeaderCustom headerCustom = new HeaderCustom();

    private int staffId;
    private String staffName;
    private boolean isMaximized = false;
    private int productTotalPages;
    private int totalItems;
    private int tableHeaderSize = 44;
    private int productCurrentPage = 1;
    private int productItemsPerPage = 9;
    private int productMaxPageEquivalent;
    private int productMinPageEquivalent;

    public Dashboard(int staffId) {
        initComponents();

        this.staffId = staffId;
        headerCustom.moveFrame(Header, isMaximized);
        
        
        ProductPanel productPanel = new ProductPanel();
        Product.add(productPanel);
        
        InventoryPanel inventoryPanel = new InventoryPanel();
        Inventory.add(inventoryPanel);

        
        
        Content.setLayout(new CardLayout());
        Content.removeAll();
        Content.add(Home, "Home");
        Content.add(Product, "Product");
        Content.add(Inventory, "Inventory");
        Content.add(History, "History");
        // Show Home by default
        CardLayout cl = (CardLayout) (Content.getLayout());
        cl.show(Content, "Home");

        setHoverAndClickEffects(pHome);
        setHoverAndClickEffects(pProduct);
        setHoverAndClickEffects(pInventory);
        setHoverAndClickEffects(pHistory);

        setActivePanel(pHome, pHome.getBackground(), new Color(29, 86, 127), new Color(25, 25, 25));

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
    
    private JPanel selectedPanel;

    private void setHoverAndClickEffects(JPanel panel) {
        // Original background color
        Color originalColor = panel.getBackground();
        Color hoverColor = new Color(33, 103, 153);
        Color activeColor = new Color(29, 86, 127);
        Color borderColor = new Color(250, 250, 250);

        // Hover effect
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (panel != selectedPanel) {
                    panel.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (panel != selectedPanel) {
                    panel.setBackground(originalColor);
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Set the active panel
                setActivePanel(panel, originalColor, activeColor, borderColor);
            }
        });
    }

    private void setActivePanel(JPanel panel, Color originalColor, Color activeColor, Color borderColor) {
        // Reset the previously selected panel to its original color
        if (selectedPanel != null) {
            selectedPanel.setBackground(originalColor);
            selectedPanel.setBorder(null);
        }

        // Set the new panel as active
        selectedPanel = panel;
        selectedPanel.setBackground(activeColor);
        selectedPanel.setBorder(new MatteBorder(1, 0, 1, 0, new Color(250, 255, 255)));
    }

    public void changeColor(JPanel panelName, Color color) {
        panelName.setBackground(color);
    }

    private void showPanel(JPanel panelToShow) {
        for (Component comp : Content.getComponents()) {
            comp.setVisible(false); // Hide all panels
        }
        panelToShow.setVisible(true);
        panelToShow.revalidate();
        panelToShow.repaint();
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
        Navbar = new javax.swing.JPanel();
        NavContianer = new javax.swing.JPanel();
        pHome = new javax.swing.JPanel();
        jLabelProduct1 = new javax.swing.JLabel();
        pProduct = new javax.swing.JPanel();
        jLabelProduct = new javax.swing.JLabel();
        pInventory = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pHistory = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        SignOut = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        Content = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        Product = new javax.swing.JPanel();
        productPanel1 = new pos.ProductPanel();
        Inventory = new javax.swing.JPanel();
        inventoryPanel1 = new pos.InventoryPanel();
        History = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        Border.setBackground(new java.awt.Color(35, 35, 35));
        Border.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        Border.setLayout(new java.awt.BorderLayout());

        Header.setBackground(new java.awt.Color(25, 25, 25));
        Header.setPreferredSize(new java.awt.Dimension(798, 40));
        Header.setLayout(new java.awt.BorderLayout());

        iconMinMaxClose.setBackground(new java.awt.Color(25, 25, 25));
        iconMinMaxClose.setPreferredSize(new java.awt.Dimension(200, 40));
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
        iconLogoTitle.setPreferredSize(new java.awt.Dimension(200, 40));
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

        Navbar.setBackground(new java.awt.Color(35, 35, 35));
        Navbar.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 10, 0, new java.awt.Color(77, 77, 77)));
        Navbar.setPreferredSize(new java.awt.Dimension(50, 458));
        Navbar.setLayout(new java.awt.BorderLayout());

        NavContianer.setBackground(new java.awt.Color(35, 35, 35));
        NavContianer.setPreferredSize(new java.awt.Dimension(50, 400));
        NavContianer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pHome.setBackground(new java.awt.Color(35, 35, 35));
        pHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pHomeMouseClicked(evt);
            }
        });
        pHome.setLayout(new java.awt.BorderLayout());

        jLabelProduct1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home-24.png"))); // NOI18N
        pHome.add(jLabelProduct1, java.awt.BorderLayout.CENTER);

        NavContianer.add(pHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        pProduct.setBackground(new java.awt.Color(35, 35, 35));
        pProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pProductMouseClicked(evt);
            }
        });
        pProduct.setLayout(new java.awt.BorderLayout());

        jLabelProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product-24.png"))); // NOI18N
        pProduct.add(jLabelProduct, java.awt.BorderLayout.CENTER);

        NavContianer.add(pProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 50, 50));

        pInventory.setBackground(new java.awt.Color(35, 35, 35));
        pInventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pInventoryMouseClicked(evt);
            }
        });
        pInventory.setLayout(new java.awt.BorderLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stock-24.png"))); // NOI18N
        pInventory.add(jLabel8, java.awt.BorderLayout.CENTER);

        NavContianer.add(pInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 50, 50));

        pHistory.setBackground(new java.awt.Color(35, 35, 35));
        pHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pHistoryMouseClicked(evt);
            }
        });
        pHistory.setLayout(new java.awt.BorderLayout());

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/history-24.png"))); // NOI18N
        pHistory.add(jLabel11, java.awt.BorderLayout.CENTER);

        NavContianer.add(pHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 50, 50));

        Navbar.add(NavContianer, java.awt.BorderLayout.PAGE_START);

        SignOut.setBackground(new java.awt.Color(35, 35, 35));
        SignOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignOut.setPreferredSize(new java.awt.Dimension(50, 50));
        SignOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignOutMouseClicked(evt);
            }
        });
        SignOut.setLayout(new java.awt.BorderLayout());

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log-out-24.png"))); // NOI18N
        SignOut.add(jLabel10, java.awt.BorderLayout.CENTER);

        Navbar.add(SignOut, java.awt.BorderLayout.PAGE_END);

        Border.add(Navbar, java.awt.BorderLayout.LINE_START);

        Content.setBackground(new java.awt.Color(45, 45, 45));
        Content.setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(75, 75, 75));
        Home.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Home.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(25, 25, 25));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel13.setPreferredSize(new java.awt.Dimension(748, 50));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel14.setBackground(new java.awt.Color(35, 35, 35));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("  Home");
        jPanel13.add(jLabel14, java.awt.BorderLayout.CENTER);

        Home.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel16.setBackground(new java.awt.Color(35, 35, 35));
        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel16.setLayout(new java.awt.BorderLayout());
        Home.add(jPanel16, java.awt.BorderLayout.CENTER);

        Content.add(Home, "card1");

        Product.setBackground(new java.awt.Color(75, 75, 75));
        Product.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Product.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Product.setLayout(new java.awt.BorderLayout());
        Product.add(productPanel1, java.awt.BorderLayout.CENTER);

        Content.add(Product, "card2");

        Inventory.setBackground(new java.awt.Color(75, 75, 75));
        Inventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Inventory.setLayout(new java.awt.BorderLayout());
        Inventory.add(inventoryPanel1, java.awt.BorderLayout.CENTER);

        Content.add(Inventory, "card1");

        History.setBackground(new java.awt.Color(75, 75, 75));
        History.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        History.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        History.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(25, 25, 25));
        jPanel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel21.setPreferredSize(new java.awt.Dimension(748, 50));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel18.setBackground(new java.awt.Color(35, 35, 35));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("  History");
        jPanel21.add(jLabel18, java.awt.BorderLayout.CENTER);

        History.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel22.setBackground(new java.awt.Color(35, 35, 35));
        jPanel22.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel22.setLayout(new java.awt.BorderLayout());
        History.add(jPanel22, java.awt.BorderLayout.CENTER);

        Content.add(History, "card3");

        Border.add(Content, java.awt.BorderLayout.CENTER);

        getContentPane().add(Border, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(800, 500));
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
            productItemsPerPage = 27;

            productMaxPageEquivalent = (productCurrentPage + 2) / 3;
            productCurrentPage = productMaxPageEquivalent;
//            tblProduct(productCurrentPage, productItemsPerPage);
//            updatePagination();

            this.setBounds(0, 0, screenWidth, adjustedHeight);
            isMaximized = true;
        } else {
            // Restore to normal size
            productItemsPerPage = 9;

            int productMinPageEquivalent = (productCurrentPage - 1) * 3 + 1;
            productCurrentPage = productMinPageEquivalent;
//            tblProduct(productCurrentPage, productItemsPerPage);
//            updatePagination();

            this.setSize(800, 500);
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

    private void pProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pProductMouseClicked
        // TODO add your handling code here:
        showPanel(Product);
    }//GEN-LAST:event_pProductMouseClicked

    private void pInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pInventoryMouseClicked
        // TODO add your handling code here:
        showPanel(Inventory);
    }//GEN-LAST:event_pInventoryMouseClicked

    private void SignOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignOutMouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to sign out?",
                "Confirm Sign Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // Check the user's response
        if (response == JOptionPane.YES_OPTION) {
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_SignOutMouseClicked

    private void pHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pHistoryMouseClicked
        // TODO add your handling code here:
        showPanel(History);
    }//GEN-LAST:event_pHistoryMouseClicked

    private void pHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pHomeMouseClicked
        // TODO add your handling code here:\
        showPanel(pHome);
    }//GEN-LAST:event_pHomeMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel Content;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel History;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Inventory;
    private javax.swing.JPanel NavContianer;
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel Product;
    private javax.swing.JPanel SignOut;
    private javax.swing.JPanel iconLogoTitle;
    private javax.swing.JPanel iconMinMaxClose;
    private pos.InventoryPanel inventoryPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelProduct;
    private javax.swing.JLabel jLabelProduct1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel pClose;
    private javax.swing.JPanel pHistory;
    private javax.swing.JPanel pHome;
    private javax.swing.JPanel pInventory;
    private javax.swing.JPanel pMax;
    private javax.swing.JPanel pMin;
    private javax.swing.JPanel pProduct;
    private pos.ProductPanel productPanel1;
    private javax.swing.JPanel titleFrame;
    // End of variables declaration//GEN-END:variables
}