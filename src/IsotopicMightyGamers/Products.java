/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javaswingdev.GradientDropdownMenu;
import javaswingdev.MenuEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Poindexter
 */
public class Products extends javax.swing.JFrame {

    int count = 0;
    
    /**
     * Creates new form Products
     */
    public Products() {
        initComponents();
        
        ProductTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        ProductTable.getTableHeader().setOpaque(false);
        ProductTable.getTableHeader().setBackground(new Color(32,136,203));
        ProductTable.getTableHeader().setForeground(new Color(0, 0, 0));
        ProductTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Delete");
        GDM.addItem("Quantity Sorting","ASC","DESC");
        GDM.addItem("Back","Home","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Data")
            {
                DefaultTableModel m = (DefaultTableModel)ProductTable.getModel();
                m.setRowCount(0);
                String q1 = "Select * from product";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Delete")
            {
                try{
                    DefaultTableModel m1 = (DefaultTableModel)ProductTable.getModel();
                    int i = ProductTable.getSelectedRow();
                    TableModel m = ProductTable.getModel();
                    String a = m.getValueAt(i, 0).toString();
                    int b = Integer.parseInt(a);
                    m1.setRowCount(0);
                    String q3 = "delete from Product where Product_ID = "+b+" select * from product" ;
                    show_user(q3);
                    if(count == 0)
                    {
                      JOptionPane.showMessageDialog(null, "Product Data deleted!");  
                    }
                    else if(count == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Product Data could not be deleted!");  
                    }
                    }catch(ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a product first!");
                    }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "ASC")
            {
                DefaultTableModel m = (DefaultTableModel)ProductTable.getModel();
                m.setRowCount(0);
                String q1 = "Select * From Product Order by quantity ASC";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "DESC")
            {
                DefaultTableModel m = (DefaultTableModel)ProductTable.getModel();
                m.setRowCount(0);
                String q1 = "Select * From Product Order by quantity DESC";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Home")
            {
                Home H = new Home();
                H.setVisible(true);
                dispose();
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Exit")
            {
                JFrame frame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?","EXIT",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
                {
                    System.exit(0);
                }
            }
        }
    }
});
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        pnlTop.setOpaque(true);
        pnlTop.setBackground(new Color(0,0,0,0));
        
    }
    
    int xx,xy;
    
    public ArrayList<Prod> userList(String query1)
    {
        ArrayList<Prod> userList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Prod prod;
            while(rs.next())
            {
                prod = new Prod(rs.getInt("Product_ID"),rs.getString("Product_Name"),rs.getInt("Price"), rs.getString("Brand"), rs.getInt("Warranty"), rs.getDate("Shipping_Date"), rs.getString("Country"), rs.getInt("PurchaseCost"), rs.getInt("quantity"));
                userList.add(prod);
            }
        }catch(SQLException ex)
        {
            int a = ex.getErrorCode();
            if(a == 547)
            {
                count = 1;
                JOptionPane.showMessageDialog(null, "The product has not been delivered yet!");
            }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList;
    }
    
    public void show_user(String query2)
    {
        ArrayList<Prod> list = userList(query2);
        DefaultTableModel model = (DefaultTableModel)ProductTable.getModel();
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getProduct_ID();
            row[1] = list.get(i).getProduct_Name();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getBrand();
            row[4] = list.get(i).getWarranty();
            row[5] = list.get(i).getShipping_Date();
            row[6] = list.get(i).getCountry();
            row[7] = list.get(i).getCosting();
            row[8] = list.get(i).getQuantity();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        pnlTop = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        ProdSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Enter");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 110, 40));

        pnlTop.setBackground(new java.awt.Color(255, 102, 102));
        pnlTop.setPreferredSize(new java.awt.Dimension(1024, 30));
        pnlTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopMouseDragged(evt);
            }
        });
        pnlTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTopMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopMousePressed(evt);
            }
        });
        pnlTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 50));

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Product Table");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 360, 70));

        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "Name", "Price", "Brand", "Warranty", "Shipping_Date", "Country", "Costing", "quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ProductTable.setFocusable(false);
        ProductTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        ProductTable.setRowHeight(50);
        ProductTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        ProductTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ProductTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 930, 320));

        ProdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProdSearchActionPerformed(evt);
            }
        });
        getContentPane().add(ProdSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 450, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Search Product ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProdSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdSearchActionPerformed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (Products.this.getExtendedState() == MAXIMIZED_BOTH) {
                Products.this.setExtendedState(JFrame.NORMAL);
            } else {
                Products.this.setExtendedState(MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlTopMouseClicked

    private void pnlTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlTopMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            this.setState(1);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        DefaultTableModel m = (DefaultTableModel)ProductTable.getModel();
        m.setRowCount(0);
        String id_srch = ProdSearch.getText();
        if(id_srch.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter a Product's id!");
        }
        else
        {
            int a = Integer.parseInt(id_srch);
            String q2 = "Select * from Product where Product_ID = "+a+"";

            show_user(q2);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Products.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Products().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ProdSearch;
    private javax.swing.JTable ProductTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
