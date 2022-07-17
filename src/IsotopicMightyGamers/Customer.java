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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Customer extends javax.swing.JFrame {

    public static String C_Name,P_Name,Email, OrDate;
    public static int OID, C_ID,P_ID,price,warranty;
    public static int verificationCode;
    public static float points;
    public static float TotalPoints;
    
    public static int counter = 0;
    
    Connection con = null;
    PreparedStatement pst = null;
    ArrayList<Cust> userList1 = new ArrayList<>();
    ArrayList<Prod> userList2 = new ArrayList<>();
    ArrayList<Ord> userList3 = new ArrayList<>();
    /**
     * Creates new form Customer
     */
    public Customer() {
        initComponents();
     
        MixedTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        MixedTable.getTableHeader().setOpaque(false);
        MixedTable.getTableHeader().setBackground(new Color(32,136,203));
        MixedTable.getTableHeader().setForeground(new Color(0, 0, 0));
        MixedTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Mail Receipt");
        GDM.addItem("Generate Receipt");
        GDM.addItem("Back","Orders","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Mail Receipt")
            {
                {
                LocalDateTime Obj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String Date = Obj.format(myFormatObj);

                Random R = new Random();
                verificationCode = R.nextInt(100000000);

                DefaultTableModel m1 = (DefaultTableModel)MixedTable.getModel();
                int selection = MixedTable.getSelectedRow();
                TableModel m = MixedTable.getModel();
                try{
                points = Float.parseFloat(m.getValueAt(selection, 9).toString());
                OrDate = m.getValueAt(selection, 8).toString();
                OID = Integer.parseInt(m.getValueAt(selection, 7).toString());
                P_ID = Integer.parseInt(m.getValueAt(selection, 6).toString());
                warranty = Integer.parseInt(m.getValueAt(selection, 5).toString());
                price = Integer.parseInt(m.getValueAt(selection, 4).toString());
                P_Name = m.getValueAt(selection, 3).toString();
                Email = m.getValueAt(selection, 2).toString();
                C_Name = m.getValueAt(selection, 1).toString();
                C_ID = Integer.parseInt(m.getValueAt(selection, 0).toString());
                }catch(ArrayIndexOutOfBoundsException ex)
                {
                    JOptionPane.showMessageDialog(null, "Please check all inputs!");
                }
                try{
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
                    Connection con = DriverManager.getConnection(url);
                    String query1 = "Select SUM(points) as 'Total Points' from Customer join Orders on Customer.Customer_ID = Orders.Customer_ID where Customer.Customer_ID = "+C_ID+"";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query1);
                    while(rs.next())
                    {
                        TotalPoints = rs.getFloat(1);
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Error has occured");
                }

                m1.setRowCount(0);
                try {
                    String Subject = "Product Receipt";
                    String htmlCode ="<div style=\"display: grid; grid-template-columns: repeat(2, 1fr);\">"
                            +"<div> <img src=\"https://i.ibb.co/NFgR8pw/default.png\" "+"width=\"150px\" height=\"150px\""+" alt=\"default\" border=\"0\"> </div>"
                            +"<div> <p style=\"text-align: right; padding: 50px 80px\">DATE : "+Date+"</p></div> </div> <div style=\"width: 60%; margin: auto; \">"
                            +"<h1>Hello "+C_Name+" ,</h1>"
                            +"<h3>Thank you for purchasing a "+P_Name+" from Isotopic Mighty Gamers. Your purchase make our day. Hope our product will make yours.</h3>"
                            +"<br>"
                            +"<hr>"
                            +"<div style=\"padding-left: 50px;\">"
                            +"<br>"
                            +"<h4>Order Date : "+OrDate+"</h4>"
                            +"<h4>Order ID : "+OID+"</h4>"
                            +"<h4>Product price : "+price+"</h4>"
                            + " <h4>Warranty: "+warranty+" years</h4>"
                            + " <h4>Points earned: "+points+" (50 points = 1BDT)</h4>"
                            + " <h4>Your total points: "+TotalPoints+"</h4>"
                            + "<h1>Product Verfication Code: "+verificationCode+" </h1></br>"
                            + "<br>"
                            + "</div>"
                            + "<hr>"
                            + "<br>"
                            + "<div style=\"  display: flex\"; align-items: center; justify-content: center;\">"
                            + "<img src=\"https://i.ibb.co/L5FxgZN/ihr-qr-code-ohne-logo.png\" alt=\"ihr-qr-code-ohne-logo\""+"width=\"10%\" height=\"10%\""+" alt=\"default\" border=\"0\">"
                            + "</div>"
                            + "<p> We hope to meet you again. Have a good day. </p>";

                    Voucher V = new Voucher();
                    V.sendMail(Email,htmlCode, Subject);

                
                }catch(NullPointerException ex)
                {
                    JOptionPane.showMessageDialog(null, "Try again!");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Generate Receipt")
            {
                try{
                    DefaultTableModel m1 = (DefaultTableModel)MixedTable.getModel();
                    int selection = MixedTable.getSelectedRow();
                    TableModel m = MixedTable.getModel();

                    points = Float.parseFloat(m.getValueAt(selection, 9).toString());
                    OrDate = m.getValueAt(selection, 8).toString();
                    OID = Integer.parseInt(m.getValueAt(selection, 7).toString());
                    P_ID = Integer.parseInt(m.getValueAt(selection, 6).toString());
                    warranty = Integer.parseInt(m.getValueAt(selection, 5).toString());
                    price = Integer.parseInt(m.getValueAt(selection, 4).toString());
                    P_Name = m.getValueAt(selection, 3).toString();
                    Email = m.getValueAt(selection, 2).toString();
                    C_Name = m.getValueAt(selection, 1).toString();
                    C_ID = Integer.parseInt(m.getValueAt(selection, 0).toString());

                    m1.setRowCount(0);

                   Receipt R = new Receipt();
                   R.setVisible(true);
                   dispose();

                   }catch(ArrayIndexOutOfBoundsException ex)
                   {
                       JOptionPane.showMessageDialog(null, "Please select a customer first!");
                   }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Orders")
            {
                Orders O = new Orders();
                O.setVisible(true);
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
            else if(GDM.getMenuNameAt(index, subIndex) == "Data")
            {
                if(counter == 0)
                {
                    String query1 = "Select Customer.Customer_ID, C_Name, C_Email, Product_Name, Price, Warranty, Product.Product_ID, order_id, Order_Date, Orders.points From Customer Right join Orders on Customer.Customer_ID = Orders.Customer_ID Left Join Product on Product.Product_ID = Orders.Product_ID group by Customer.Customer_ID, Customer.C_Name, Customer.C_Email, Product_Name, Price, Warranty, Product.Product_ID, order_id, Order_Date, Orders.points ORDER BY Customer.Customer_ID;";
                    show_userJoin(query1);
                    counter++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Already entered!");
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
    
    public ArrayList<Prod> userListProd(String query1)
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Prod prod;
            while(rs.next())
            {
                prod = new Prod(rs.getString("Product_Name"),rs.getInt("Price"),rs.getInt("Warranty"),rs.getInt("Product_ID"));
                userList2.add(prod);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList2;
    }
    
    public ArrayList<Cust> userListCust(String query1)
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Cust cust;
            while(rs.next())
            {
                cust = new Cust(rs.getInt("Customer_ID"),rs.getString("C_name"),rs.getString("C_Email"));
                userList1.add(cust);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList1;
    }
    
    public ArrayList<Ord> userListOrd(String query1)
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Ord ord;
            while(rs.next())
            {
                ord = new Ord(rs.getInt("order_id"), rs.getDate("Order_Date"), rs.getFloat("points"));
                userList3.add(ord);
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        return userList3;
    }
    
    public void show_userJoin(String query2)
    {
        ArrayList<Cust> list = userListCust(query2);
        ArrayList<Prod> list2 = userListProd(query2);
        ArrayList<Ord> list3 = userListOrd(query2);
        DefaultTableModel model = (DefaultTableModel)MixedTable.getModel();
        Object[] row = new Object[10];
        for(int i = 0; i < list.size() && i < list2.size() && i < list3.size(); i++)
        {
            row[0] = list.get(i).getCustomer_ID();
            row[1] = list.get(i).getC_name();
            row[2] = list.get(i).getEmail();
            row[3] = list2.get(i).getProduct_Name();
            row[4] = list2.get(i).getPrice(); 
            row[5] = list2.get(i).getWarranty();
            row[6] = list2.get(i).getProduct_ID();
            row[7] = list3.get(i).getOrder_id();
            row[8] = list3.get(i).getOrder_date();
            row[9] = list3.get(i).getPoints();
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

        pnlTop = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MixedTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        MixedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CustomerID", "Name", "Email", "Product", "Price", "Warranty", "Product_ID", "OrderID", "Order date", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        MixedTable.setFocusable(false);
        MixedTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        MixedTable.setRowHeight(50);
        MixedTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        MixedTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(MixedTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 930, 390));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Customer Record");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 410, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (Customer.this.getExtendedState() == MAXIMIZED_BOTH) {
                Customer.this.setExtendedState(JFrame.NORMAL);
            } else {
                Customer.this.setExtendedState(MAXIMIZED_BOTH);
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
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MixedTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
