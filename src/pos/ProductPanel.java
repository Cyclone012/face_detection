package pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class ProductPanel extends JPanel {

    private SqlConnector con = new SqlConnector();
    private TableDark jTableDark;
    private int productTotalPages;
    private int totalItems;
    private int tableHeaderSize = 44;
    private int productCurrentPage = 1;
    private int productItemsPerPage = 9;

    private JPanel tableHeader;
    private JPanel pLabel;
    private JPanel pPageCount;
    private JPanel pActionBtn;
    private JPanel pHeaderProduct;
    private JPanel pBodyProduct;
    private JPanel pContianerProduct;
    private JPanel pTableProduct;
    private JPanel pPagination;
    private JPanel productEllipsis;
    private JLabel productPageCount;
    private JPanel pProductNextPage;
    private JPanel pProductPreviousPage;
    private JPanel productPreviousBtn;
    private JPanel productNextBtn;
    private JPanel pContainNextPage;
    private JPanel pContainPrevPage;
    private JPanel pEllipsis;
    
    private JLabel labelProduct;
    private JLabel labelPrev;
    private JLabel labelNext;
    private JLabel iconLabelPrev;
    private JLabel iconLabelNext;

    public ProductPanel() {
        setLayout(new BorderLayout());
        initComponents();
        initDatabaseConnection();
        calculateProductTotalPages();
        updatePagination();
        tblProduct(productCurrentPage, productItemsPerPage);
    }

    private void initComponents() {
        // Initialize panels
        Font titleFont = new Font("Segoe UI", Font.BOLD, 18);
        Font pageCountFont = new Font("Segoe UI", Font.BOLD, 14);
        
        tableHeader = new JPanel(new BorderLayout());
        pLabel = new JPanel(new BorderLayout());
        pPageCount = new JPanel(new BorderLayout());
        pActionBtn = new JPanel(new BorderLayout());
        pHeaderProduct = new JPanel(new BorderLayout());
        pBodyProduct = new JPanel(new BorderLayout());
        pTableProduct = new JPanel(new BorderLayout());
        pContianerProduct = new JPanel(new BorderLayout());
        pTableProduct = new JPanel(new BorderLayout());
        pPagination = new JPanel(new BorderLayout());
        pContainNextPage = new JPanel(null);
        pContainPrevPage = new JPanel(null);
        productEllipsis = new JPanel(new GridBagLayout());
        productPageCount = new JLabel();
        pProductNextPage = new JPanel(new BorderLayout());
        pProductPreviousPage = new JPanel(new BorderLayout());
        productPreviousBtn = new JPanel(new BorderLayout());
        productNextBtn = new JPanel(new BorderLayout());
        pEllipsis = new JPanel(new BorderLayout());

        labelProduct = new JLabel();
        labelPrev = new JLabel();
        labelNext = new JLabel();
        iconLabelPrev = new JLabel();
        iconLabelNext = new JLabel();

        labelProduct.setFont(titleFont);
        productPageCount.setFont(pageCountFont);
       
        labelProduct.setText("Product");
        labelPrev.setText("Previous");
        labelNext.setText("Next");
        labelNext.setHorizontalAlignment(labelNext.TRAILING);
        
        productPageCount.setVerticalAlignment(SwingConstants.CENTER);
        productPageCount.setVerticalAlignment(SwingConstants.CENTER);
        productPageCount.setHorizontalAlignment(SwingConstants.CENTER);
        productPageCount.setHorizontalAlignment(SwingConstants.CENTER);
        
        iconLabelPrev.setVerticalAlignment(SwingConstants.CENTER);
        iconLabelNext.setVerticalAlignment(SwingConstants.CENTER);
        iconLabelPrev.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabelNext.setHorizontalAlignment(SwingConstants.CENTER);
        
        iconLabelPrev.setIcon(new ImageIcon(getClass().getResource("/img/previous-24.png")));
        iconLabelNext.setIcon(new ImageIcon(getClass().getResource("/img/next-24.png")));

        // Add components to the panel
        add(pHeaderProduct, BorderLayout.NORTH);
        add(pBodyProduct, BorderLayout.CENTER);
        pBodyProduct.add(pContianerProduct, BorderLayout.CENTER);
        pContianerProduct.add(pTableProduct, BorderLayout.CENTER);
        pContianerProduct.add(pPagination, BorderLayout.SOUTH);
        pHeaderProduct.add(tableHeader,BorderLayout.CENTER);
        tableHeader.add(pLabel, BorderLayout.WEST);
        pLabel.add(labelProduct);
        
        tableHeader.add(pPageCount,BorderLayout.CENTER);
        pPageCount.add(productPageCount);
        
        tableHeader.add(pActionBtn,BorderLayout.EAST);

        // Setup navigation buttons
        pPagination.add(pContainPrevPage, BorderLayout.WEST);
        pPagination.add(pContainNextPage,BorderLayout.EAST);
        pPagination.add(pEllipsis,BorderLayout.CENTER);
        pEllipsis.add(productEllipsis);

        pContainPrevPage.add(pProductPreviousPage);
        pContainNextPage.add(pProductNextPage);
        pProductPreviousPage.add(productPreviousBtn);
        pProductNextPage.add(productNextBtn);
        pContainPrevPage.add(labelPrev, BorderLayout.CENTER);
        pContainNextPage.add(labelNext, BorderLayout.CENTER);
        productPreviousBtn.add(iconLabelPrev);
        productNextBtn.add(iconLabelNext);

        pLabel.setPreferredSize(new Dimension(200, 50));
        pActionBtn.setPreferredSize(new Dimension(250, 50));
        pHeaderProduct.setPreferredSize(new Dimension(getWidth(), 50));
        pPagination.setPreferredSize(new Dimension(getWidth(), 30));
        pContainPrevPage.setPreferredSize(new Dimension(100, 30));
        pContainNextPage.setPreferredSize(new Dimension(100, 30));
        productPreviousBtn.setPreferredSize(new Dimension(50, 30));
        productNextBtn.setPreferredSize(new Dimension(50, 30));
        labelPrev.setPreferredSize(new Dimension(50, 30));
        labelNext.setPreferredSize(new Dimension(50, 30));
        
        pProductPreviousPage.setBounds(0, 0, 50, 30);
        labelPrev.setBounds(50, 0, 50, 30);
        pProductNextPage.setBounds(50, 0, 50, 30);
        labelNext.setBounds(0, 0, 50, 30);

        pHeaderProduct.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
        pContianerProduct.setBorder(new EmptyBorder(20, 20, 20, 20));
        tableHeader.setBorder(new EmptyBorder(0,20,0,20));

        pHeaderProduct.setBackground(new Color(35, 35, 35));
        pPageCount.setBackground(new Color(35, 35, 35));
        pActionBtn.setBackground(new Color(35, 35, 35));
        pLabel.setBackground(new Color(35, 35, 35));
        productPageCount.setForeground(new Color(202,202,202));
        labelProduct.setForeground(Color.WHITE);
        labelPrev.setForeground(Color.WHITE);
        labelNext.setForeground(Color.WHITE);
        pContianerProduct.setBackground(new Color(35, 35, 35));
        tableHeader.setBackground(new Color(35, 35, 35));
        pPagination.setBackground(new Color(77, 77, 77));
        pContainPrevPage.setBackground(new Color(77, 77, 77));
        pContainNextPage.setBackground(new Color(77, 77, 77));
        pProductPreviousPage.setBackground(new Color(77, 77, 77));
        pProductNextPage.setBackground(new Color(77, 77, 77));
        productPreviousBtn.setBackground(new Color(77, 77, 77));
        productNextBtn.setBackground(new Color(77, 77, 77));
        productEllipsis.setBackground(new Color(77, 77, 77));

        // Add listeners to navigation buttons
        productPreviousBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productPreviousBtnMouseClicked(evt);
            }
        });
        productNextBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productNextBtnMouseClicked(evt);
            }
        });
    }

    private void initDatabaseConnection() {
        try {
            con.ConnectionDB("localhost:3306", "clothesshop", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateProductTotalPages() {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.dataCon.createStatement();
            String sql = "SELECT COUNT(*) FROM tblproduct";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                totalItems = rs.getInt(1);
            }
            productTotalPages = (int) Math.ceil((double) totalItems / productItemsPerPage);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void tblProduct(int page, int limit) {
        jTableDark = new TableDark();
        String[] columnNames = {"ID", "Name", "Category", "Description"};
        NonEditableTableModel data = new NonEditableTableModel();
        data.setColumnIdentifiers(columnNames);

        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.dataCon.createStatement();
            int offset = (page - 1) * limit;

            String sql = "SELECT * FROM tblproduct " + " LIMIT " + limit + " OFFSET " + offset;

            rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int categoryId = rs.getInt("category_id");
                String description = rs.getString("description");

                data.addRow(new Object[]{id, name, categoryId, description});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        jTableDark.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDark.setModel(data);

        JScrollPane newScrollPane = new JScrollPane(jTableDark);
        newScrollPane.setVerticalScrollBar(new ScrollBarCustom());
        newScrollPane.setHorizontalScrollBar(new ScrollBarCustom());

        jTableDark.fixTable(newScrollPane);
        jTableDark.getTableHeader().setPreferredSize(new Dimension(0, tableHeaderSize));

        // Configure column alignments and widths
        configureTableColumns();

        pTableProduct.removeAll();
        pTableProduct.add(newScrollPane, BorderLayout.CENTER);
        pTableProduct.revalidate();
        pTableProduct.repaint();
    }

    private void configureTableColumns() {
        jTableDark.setColumnAlignment(0, JLabel.CENTER);
        jTableDark.setCellAlignment(0, JLabel.CENTER);
        jTableDark.setColumnWidth(0, 50);

        jTableDark.setColumnWidth(1, 250);

        jTableDark.setColumnAlignment(2, JLabel.CENTER);
        jTableDark.setCellAlignment(2, JLabel.CENTER);
        jTableDark.setColumnWidth(2, 100);
    }

    private void updatePagination() {
        productEllipsis.removeAll();
        tblProduct(productCurrentPage, productItemsPerPage);
        productPageCount.setText("Page " + productCurrentPage + " of " + productTotalPages);

        if (productTotalPages > 1) {
            addButton("1");

            if (productCurrentPage > 4) {
                addEllipsis();
            }

            int start = Math.max(2, productCurrentPage - 2);
            int end = Math.min(productTotalPages - 1, productCurrentPage + 2);

            for (int i = start; i <= end; i++) {
                addButton(String.valueOf(i));
            }

            if (productCurrentPage < productTotalPages - 3) {
                addEllipsis();
            }
            addButton(String.valueOf(productTotalPages));

            productEllipsis.revalidate();
            productEllipsis.repaint();
        }
        updateNavigationButtons();
    }

    private void addButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(77, 77, 77));
        button.setForeground(new Color(255, 255, 255));
        button.setBorder(new LineBorder(Color.WHITE, 1, true));
        button.setPreferredSize(new Dimension(50, 24));
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(10));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productCurrentPage = Integer.parseInt(text);
                updatePagination();
            }
        });
        productEllipsis.add(button);
    }

    private void addEllipsis() {
        JLabel ellipsisLabel = new JLabel("...");
        ellipsisLabel.setForeground(Color.WHITE);
        productEllipsis.add(ellipsisLabel);
    }

    private void updateNavigationButtons() {
        productPreviousBtn.setEnabled(productCurrentPage > 1);
        productNextBtn.setEnabled(productCurrentPage < productTotalPages);
    }

    private void productPreviousBtnMouseClicked(java.awt.event.MouseEvent evt) {
        if (productCurrentPage > 1) {
            productCurrentPage--;
            updatePagination();
        }
    }

    private void productNextBtnMouseClicked(java.awt.event.MouseEvent evt) {
        if (productCurrentPage < productTotalPages) {
            productCurrentPage++;
            updatePagination();
        }
    }

    private class NonEditableTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
