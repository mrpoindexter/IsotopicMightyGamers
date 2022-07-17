/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class CustomerInfo extends javax.swing.JFrame  {
  
    public static String points;
    Connection con = null;
    PreparedStatement pst = null;
    int count = 0;
    
    
    public CustomerInfo() {
        initComponents();
        
        CustomerTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        CustomerTable.getTableHeader().setOpaque(false);
        CustomerTable.getTableHeader().setBackground(new Color(32,136,203));
        CustomerTable.getTableHeader().setForeground(new Color(0, 0, 0));
        CustomerTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Delete");
        GDM.addItem("Points Sorting","ASC","DESC");
        GDM.addItem("Back","Home","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Data")
            {
                DefaultTableModel m = (DefaultTableModel)CustomerTable.getModel();
                m.setRowCount(0);
                String q1 = "Select Customer.*,SUM(points) as 'Total Points' from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID, Customer.C_address, Customer.C_contact,Customer.C_Name, Customer.C_Email";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Delete")
            {
                try{
                    DefaultTableModel m1 = (DefaultTableModel)CustomerTable.getModel();
                    int i = CustomerTable.getSelectedRow();
                    TableModel m = CustomerTable.getModel();
                    String a = m.getValueAt(i, 0).toString();
                    int b = Integer.parseInt(a);
                    m1.setRowCount(0);
                    String q3 = "delete from Customer where Customer_ID = "+b+" select * from customer" ;
                    show_user(q3);
                    if(count == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Customer Data deleted!");
                    }
                    else if(count == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Customer Data could not be deleted!");
                    }
                    }catch(ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a customer first!");
                    }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "ASC")
            {
                DefaultTableModel m = (DefaultTableModel)CustomerTable.getModel();
                m.setRowCount(0);
                String q1 = "Select Customer.*,SUM(points) as 'Total Points' from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID, Customer.C_address, Customer.C_contact,Customer.C_Name, Customer.C_Email Order by [Total Points] ASC";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "DESC")
            {
                DefaultTableModel m = (DefaultTableModel)CustomerTable.getModel();
                m.setRowCount(0);
                String q1 = "Select Customer.*,SUM(points) as 'Total Points' from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID, Customer.C_address, Customer.C_contact,Customer.C_Name, Customer.C_Email Order by [Total Points] DESC";
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
    
    public static int xx = 0;
    public static int xy = 0;
    
    
    public ArrayList<Cust> userList(String query1)
    { 
        ArrayList<Cust> userList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Cust cust;
            while(rs.next())
            {
                cust = new Cust(rs.getInt("Customer_ID"),rs.getString("C_name"),rs.getString("C_Address"),rs.getInt("C_contact"),rs.getString("C_Email"),rs.getFloat("Total Points"));
                userList.add(cust);
            }
        }catch(SQLException ex)
        {
            int a = ex.getErrorCode();
            if(a == 547)
            {
                count = 1;
                JOptionPane.showMessageDialog(null, "Order is pending for the current customer!");
            }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList;
    }
    
    public void show_user(String query2)
    {
        ArrayList<Cust> list = userList(query2);
        DefaultTableModel model = (DefaultTableModel)CustomerTable.getModel();
        
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getCustomer_ID();
            row[1] = list.get(i).getC_name();
            row[2] = list.get(i).getC_Address();
            row[3] = list.get(i).getC_contact();
            row[4] = list.get(i).getEmail();
            row[5] = list.get(i).getTotalPoints();
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
        CustomerTable = new javax.swing.JTable();
        Search = new javax.swing.JTextField();
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
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 160, 120, 50));

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
        getContentPane().add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 40));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel2.setText("Customer Table");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 330, 70));

        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "Name", "Address", "Contact", "Email", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        CustomerTable.setFocusable(false);
        CustomerTable.setRowHeight(50);
        CustomerTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        CustomerTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(CustomerTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 930, 290));

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 450, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Search Customer ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (CustomerInfo.this.getExtendedState() == MAXIMIZED_BOTH) {
                CustomerInfo.this.setExtendedState(JFrame.NORMAL);
            } else {
                CustomerInfo.this.setExtendedState(MAXIMIZED_BOTH);
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
        DefaultTableModel m = (DefaultTableModel)CustomerTable.getModel();
        m.setRowCount(0);
        String id_srch = Search.getText();
        if(id_srch.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter a customer's id!");
        }
        else
        {
            int a = Integer.parseInt(id_srch);
            String q2 = "Select Customer.*,SUM(points) as 'Total Points' from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID where Customer.Customer_ID = "+a+" group by Customer.Customer_ID, Customer.C_address, Customer.C_contact,Customer.C_Name, Customer.C_Email";
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
            java.util.logging.Logger.getLogger(CustomerInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CustomerTable;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
