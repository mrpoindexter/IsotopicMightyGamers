/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Poindexter
 */
public class InsertOrder extends javax.swing.JFrame {
    
    public static float points;
    public static int quantity;
    Connection con = null;
    PreparedStatement pst = null;

    /**
     * Creates new form InsertOrder
     */
    public InsertOrder() {
        initComponents();
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        pnlTop.setOpaque(true);
        pnlTop.setBackground(new Color(0,0,0,0));
    }

    int xx,xy;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        P_ID = new javax.swing.JTextField();
        O_ID = new javax.swing.JTextField();
        C_ID = new javax.swing.JTextField();
        D_Charge = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        OrderCalen = new com.toedter.calendar.JDateChooser();
        DeliverCalen = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        D_ID = new javax.swing.JTextField();
        pnlTop = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(93, 173, 226));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setText("Enter Order Detail");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 74, 343, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("ProductID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 148, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("OrderID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 226, 98, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("CustomerID");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 295, 98, 34));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("OrderDate");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 373, 98, 39));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Delivery Charge");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 454, -1, 39));

        P_ID.setForeground(new java.awt.Color(153, 153, 153));
        P_ID.setText("2000");
        P_ID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                P_IDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                P_IDFocusLost(evt);
            }
        });
        P_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P_IDActionPerformed(evt);
            }
        });
        jPanel1.add(P_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 145, 324, 33));

        O_ID.setForeground(new java.awt.Color(153, 153, 153));
        O_ID.setText("3000");
        O_ID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                O_IDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                O_IDFocusLost(evt);
            }
        });
        O_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                O_IDActionPerformed(evt);
            }
        });
        jPanel1.add(O_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 224, 324, 31));

        C_ID.setForeground(new java.awt.Color(153, 153, 153));
        C_ID.setText("1000");
        C_ID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                C_IDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                C_IDFocusLost(evt);
            }
        });
        jPanel1.add(C_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 295, 324, 38));
        jPanel1.add(D_Charge, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 462, 324, 31));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Deliveryman ID");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 534, -1, 70));
        jPanel1.add(OrderCalen, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 373, 208, 39));
        jPanel1.add(DeliverCalen, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 646, 227, 38));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Delivery Date");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 630, -1, 70));

        D_ID.setForeground(new java.awt.Color(153, 153, 153));
        D_ID.setText("5000");
        D_ID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                D_IDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                D_IDFocusLost(evt);
            }
        });
        D_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D_IDActionPerformed(evt);
            }
        });
        jPanel1.add(D_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 554, 330, 35));

        pnlTop.setBackground(new java.awt.Color(153, 204, 255));
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

        jButton7.setBackground(new java.awt.Color(204, 204, 255));
        jButton7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jButton7.setText("-");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pnlTop.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 50, -1));

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(204, 204, 204));
        jButton9.setText("X");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pnlTop.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 50, -1));

        jPanel1.add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 611, 64));

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 740, 114, 47));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Confirm");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 738, 114, 47));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void P_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_P_IDActionPerformed

    private void D_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_D_IDActionPerformed

    private void O_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_O_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_O_IDActionPerformed

    private void P_IDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_P_IDFocusGained
        if(P_ID.getText().equals("2000"))
        {
            P_ID.setText("");
            P_ID.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_P_IDFocusGained

    private void P_IDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_P_IDFocusLost
        if(P_ID.getText().equals(""))
        {
            P_ID.setText("2000");
            P_ID.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_P_IDFocusLost

    private void O_IDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_O_IDFocusGained
        if(O_ID.getText().equals("3000"))
        {
            O_ID.setText("");
            O_ID.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_O_IDFocusGained

    private void O_IDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_O_IDFocusLost
        if(O_ID.getText().equals(""))
        {
            O_ID.setText("3000");
            O_ID.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_O_IDFocusLost

    private void C_IDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_C_IDFocusGained
        if(C_ID.getText().equals("1000"))
        {
            C_ID.setText("");
            C_ID.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_C_IDFocusGained

    private void C_IDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_C_IDFocusLost
        if(C_ID.getText().equals(""))
        {
            C_ID.setText("1000");
            C_ID.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_C_IDFocusLost

    private void D_IDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_D_IDFocusGained
        if(D_ID.getText().equals("5000"))
        {
            D_ID.setText("");
            D_ID.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_D_IDFocusGained

    private void D_IDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_D_IDFocusLost
       if(D_ID.getText().equals(""))
        {
            D_ID.setText("5000");
            D_ID.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_D_IDFocusLost

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.setState(1);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JFrame frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?","EXIT",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (InsertOrder.this.getExtendedState() == MAXIMIZED_BOTH) {
                InsertOrder.this.setExtendedState(JFrame.NORMAL);
            } else {
                InsertOrder.this.setExtendedState(MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlTopMouseClicked

    private void pnlTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlTopMousePressed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        try{
            String query = "INSERT INTO Orders(Product_ID,order_id,Customer_ID,Order_Date,Delivery_Date,DeliveryCharge, D_ID, points) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String query1 = "Select * from Product where Product_ID = "+P_ID.getText()+"";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;selectMethod=cursor", "sa", "p@ssword13");
            Statement st = con.createStatement();
            pst = con.prepareStatement(query);
              
            Statement ST = con.createStatement();
            ResultSet RS = ST.executeQuery(query1);
            
            while(RS.next())
            {
                float P = RS.getInt("Price");
                quantity = RS.getInt("quantity");
                P *= 0.190104002;
                points = P;
                pst.setFloat(8, points);
            }

            if(P_ID.getText().equals("2000"))
            {
                JOptionPane.showMessageDialog(null, "Product ID is missing!");
            }
            else if(P_ID.getText().toString().charAt(0) != '2')
            {
                JOptionPane.showMessageDialog(null, "Product ID must follow the pattern 2###");
            }
            else if(quantity - 1 < 0)
            {
                JOptionPane.showMessageDialog(null, "Product is out of stock!");
            }
            else
            {
                pst.setString(1, P_ID.getText());
            }
 
            if(O_ID.getText().equals("3000"))
            {
                JOptionPane.showMessageDialog(null, "Order ID is missing!");
            }
            else if(O_ID.getText().toString().charAt(0) != '3')
            {  
                JOptionPane.showMessageDialog(null, "Order ID must follow the pattern 3###!");
            }
            else
            {
                pst.setString(2, O_ID.getText());
            }
             
            if(C_ID.getText().equals("1000"))
            {
                JOptionPane.showMessageDialog(null, "Customer ID is missing!");
            }
            else if(C_ID.getText().toString().charAt(0) != '1')
            {  
                JOptionPane.showMessageDialog(null, "Customer ID must follow the pattern 1###!");
            }
            else
            {
                pst.setString(3, C_ID.getText());
            }
                try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String deliveryDate = sdf.format(OrderCalen.getDate());
                pst.setString(4, deliveryDate);
                
                }catch(NullPointerException ex)
                {
                    JOptionPane.showMessageDialog(null, "Order date is missing!");
                }
                
            
                try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String deliveryDate = sdf.format(DeliverCalen.getDate());
                pst.setString(5, deliveryDate);
                
                }catch(NullPointerException ex)
                {
                    JOptionPane.showMessageDialog(null, "Delivery date is missing!");
                }
            
            
            if(D_Charge.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Delivery cost is missing!");
            }
            else
            {  
                pst.setString(6, D_Charge.getText());
            }
         
            if(D_ID.getText().equals("5000"))
            {
                JOptionPane.showMessageDialog(null, "Employee ID is missing!");
            }
            else if(D_ID.getText().toString().charAt(0) != '5')
            {
                JOptionPane.showMessageDialog(null, "Product ID must follow the pattern 5###");
            }
            else
            {
                pst.setString(7, D_ID.getText());
            }
            
            pst.executeUpdate();
            
            quantity--;
            
            String query3 = "Update Product set quantity = "+quantity+" where Product_ID = "+P_ID.getText()+"";
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;selectMethod=cursor", "sa", "p@ssword13");
            PreparedStatement pst3 = con.prepareStatement(query3);
            pst3.executeUpdate();

            JOptionPane.showMessageDialog(null, "Order has been added!!");

            Orders obj = new Orders();

            obj.setVisible(true);
            dispose();

        }catch(SQLException ex){
            int a = ex.getErrorCode();
            
            if(a == 547)
            {
                JOptionPane.showMessageDialog(null, "Check customer id, product id and deliveryman id!");
            }
            else if(a == 2627)
              {
                  JOptionPane.showMessageDialog(null, "An order with the same id exists in the database!");
              }
            else if(a == 0)
            {
                JOptionPane.showMessageDialog(null, "Please fill up all the necessary data!");
            }
            else
                JOptionPane.showMessageDialog(null, ex);
            
        } catch (Exception ex) {
            Logger.getLogger(InsertOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Home H = new Home();
        H.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(InsertOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField C_ID;
    private javax.swing.JTextField D_Charge;
    private javax.swing.JTextField D_ID;
    private com.toedter.calendar.JDateChooser DeliverCalen;
    private javax.swing.JTextField O_ID;
    private com.toedter.calendar.JDateChooser OrderCalen;
    private javax.swing.JTextField P_ID;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
