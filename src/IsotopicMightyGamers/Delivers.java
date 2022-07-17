/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Poindexter
 */
public class Delivers extends javax.swing.JFrame {
    
    
    public static int ver;
    public static int count = 0;
    public Toolkit toolkit = getToolkit();
    
    Connection con = null;
    PreparedStatement pst = null;
    ArrayList<Cust> userList1 = new ArrayList<>();
    ArrayList<Prod> userList2 = new ArrayList<>();
    ArrayList<Ord> userList3 = new ArrayList<>();
    ArrayList<Delman> userList4 = new ArrayList<>();

    /**
     * Creates new form Delivers
     */
    public Delivers() {
        initComponents();
        
        DeliversTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        DeliversTable.getTableHeader().setOpaque(false);
        DeliversTable.getTableHeader().setBackground(new Color(32,136,203));
        DeliversTable.getTableHeader().setForeground(new Color(0, 0, 0));
        DeliversTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Mail");
        GDM.addItem("Back","Orders","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Mail")
            {
               try{ 
                LocalDateTime Obj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String Date = Obj.format(myFormatObj);

                DefaultTableModel m1 = (DefaultTableModel)DeliversTable.getModel();
                int selection = DeliversTable.getSelectedRow();
                TableModel m = DeliversTable.getModel();

                String Name,Destination,DeliveryDate,Email,Del_Name;
                int contact,orderID,DeliveryCost,Price,Del_ID;

                Customer C = new Customer();
                ver = C.verificationCode;
                
                Del_Name = m.getValueAt(selection, 9).toString();
                Email = m.getValueAt(selection, 8).toString();
                Del_ID = Integer.parseInt(m.getValueAt(selection, 7).toString());
                Price = Integer.parseInt(m.getValueAt(selection, 6).toString());
                DeliveryCost = Integer.parseInt(m.getValueAt(selection, 5).toString());
                Destination = m.getValueAt(selection, 4).toString();
                DeliveryDate = m.getValueAt(selection, 3).toString();
                orderID = Integer.parseInt(m.getValueAt(selection, 2).toString());
                contact = Integer.parseInt(m.getValueAt(selection, 1).toString());
                Name = m.getValueAt(selection, 0).toString();

                int Total = Integer.parseInt(m.getValueAt(selection, 6).toString()) + Integer.parseInt(m.getValueAt(selection, 5).toString());
                
                m1.setRowCount(0);
                try {
                    String Subject = "Product Receipt";
                    String htmlCode ="<div style=\"display: grid; grid-template-columns: repeat(2, 1fr);\">"
                    +"<div> <img src=\"https://i.ibb.co/NFgR8pw/default.png\" "+"width=\"150px\" height=\"150px\""+" alt=\"default\" border=\"0\"> </div>"
                    +"<div> <p style=\"text-align: right; padding: 50px 80px\">DATE : "+Date+"</p></div> </div> <div style=\"width: 60%; margin: auto; \">"
                    +"<h1>Hello "+Del_Name+" ,</h1>"
                    +"<h3>Please deliver the product to Mr/Mrs "+Name+"</h3>"
                    +"<br>"
                    +"<hr>"
                    +"<div style=\"padding-left: 50px;\">"
                    +"<br>"
                    +"<h4>Product Price : "+Price+" BDT</h4>"
                    + " <h4>Delivery Charge: "+DeliveryCost+" BDT</h4>"
                    + "<h4>Contact: "+'+'+880+contact+"</h4></br></br></br> "
                    + "<h4>Address: "+Destination+" </h4></br>"
                    + "<h4>Verification Code: "+ver+" </h4></br>"
                    + "</br>"
                    + "<h1>Total Price : "+Total+" BDT</h1></br>"
                    + "<br>"
                    + "</div>"
                    + "<hr>"
                    + "<br>"
                    + "<div style=\"  display: flex\"; align-items: center; justify-content: center;\">"
                    + "<img src=\"https://i.ibb.co/L5FxgZN/ihr-qr-code-ohne-logo.png\" alt=\"ihr-qr-code-ohne-logo\""+"width=\"10%\" height=\"10%\""+" alt=\"default\" border=\"0\">"
                    + "</div>"
                    + "<p> Please ensure to check the verification code. </p>";

                    Voucher.sendMail(Email,htmlCode, Subject);
                    
                    count = 0;

                }catch( Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
               }catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Please select an order first!");
                }
            }
            
            else if(GDM.getMenuNameAt(index,subIndex) == "Exit")
            {
                JFrame frame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?","EXIT",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
                {
                    System.exit(0);
                }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Orders")
            {
                Orders O = new Orders();
                O.setVisible(true);
                dispose();
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Data")
            {
                if(count == 0)
                {
                    String query1 = "SELECT C_Name,C_contact,C_address,order_id,Delivery_Date,DeliveryCharge,DeliveryMan.D_ID,Price,D_email,D_Name FROM Customer Right JOIN Orders ON Customer.Customer_ID = Orders.Customer_ID Left JOIN Product ON Orders.Product_ID = Product.Product_ID Left JOIN DeliveryMan ON Orders.D_ID = DeliveryMan.D_ID ORDER BY Orders.Delivery_Date asc;";
                    show_userJoin(query1);
                    count++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Already entered!");
                }
            }
        }
    }
});
        
        
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        pnlTop.setOpaque(true);
        pnlTop.setBackground(new Color(0,0,0,0));
}
    
    static class ImagePanel extends JPanel {
        private Image image;
        ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image,0,0,getWidth(),getHeight(),this);
        }
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
                prod = new Prod(rs.getInt("Price"));
                userList2.add(prod);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList2;
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
                ord = new Ord(rs.getInt("order_ID"),rs.getDate("delivery_date"),rs.getInt("DeliveryCharge"));
                userList3.add(ord);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList3;
    }
    
    public ArrayList<Delman> userListEmp(String query1)
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Delman emp;
            while(rs.next())
            {
                emp = new Delman(rs.getString("D_Name"),rs.getInt("D_ID"),rs.getString("D_email"));
                userList4.add(emp);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList4;
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
                cust = new Cust(rs.getString("C_name"),rs.getInt("C_Contact"),rs.getString("C_address"));
                userList1.add(cust);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList1;
    }
    
    public void show_userJoin(String query2)
    {
        ArrayList<Cust> list = userListCust(query2);
        ArrayList<Prod> list2 = userListProd(query2);
        ArrayList<Ord> list3 = userListOrd(query2);
        ArrayList<Delman> list4 = userListEmp(query2);
        DefaultTableModel model = (DefaultTableModel)DeliversTable.getModel();
        Object[] row = new Object[10];
        for(int i = 0; i < list.size() && i < list2.size() && i < list3.size() && i < list4.size(); i++)
        {
            row[0] = list.get(i).getC_name();
            row[1] = list.get(i).getC_contact();
            row[2] = list3.get(i).getOrder_id();
            row[3] = list3.get(i).getDelivery_date();
            row[4] = list.get(i).getC_Address();
            row[5] = list3.get(i).getDeliveryCharge();
            row[6] = list2.get(i).getPrice();
            row[7] = list4.get(i).getD_ID();
            row[8] = list4.get(i).getD_email();
            row[9] = list4.get(i).getD_Name();
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
        DeliversTable = new javax.swing.JTable();
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
        getContentPane().add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 40));

        DeliversTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Contact", "OrderID", "Delivery Date", "Destination", "Carriage", "Price", "D_ID", "D_email", "D_Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        DeliversTable.setRowHeight(50);
        DeliversTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        DeliversTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(DeliversTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 930, 390));

        jLabel2.setFont(new java.awt.Font("Cambria", 2, 48)); // NOI18N
        jLabel2.setText("Delivery Record");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 380, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (Delivers.this.getExtendedState() == MAXIMIZED_BOTH) {
                Delivers.this.setExtendedState(JFrame.NORMAL);
            } else {
                Delivers.this.setExtendedState(MAXIMIZED_BOTH);
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
            java.util.logging.Logger.getLogger(Delivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Delivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Delivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Delivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Delivers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DeliversTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
