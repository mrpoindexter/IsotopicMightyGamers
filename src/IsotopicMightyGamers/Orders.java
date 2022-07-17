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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class Orders extends javax.swing.JFrame {

    /**
     * Creates new form Orders
     */
    public Orders() {
        initComponents();
        
        OrderTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        OrderTable.getTableHeader().setOpaque(false);
        OrderTable.getTableHeader().setBackground(new Color(32,136,203));
        OrderTable.getTableHeader().setForeground(new Color(0, 0, 0));
        OrderTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Delete");
        GDM.addItem("Order Date Sorting","ASC","DESC");
        GDM.addItem("Mail","Confirmation","Delivery");
        GDM.addItem("Back","Home","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Data")
            {
                DefaultTableModel m = (DefaultTableModel)OrderTable.getModel();
                m.setRowCount(0);
                String q1 = "SELECT * FROM Orders";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Delete")
            {
                try{
                DefaultTableModel m1 = (DefaultTableModel)OrderTable.getModel();
                int i = OrderTable.getSelectedRow();
                TableModel m = OrderTable.getModel();
                String a = m.getValueAt(i, 1).toString();
                int b = Integer.parseInt(a);
                m1.setRowCount(0);
                String q3 = "delete from Orders where order_id = "+b+" select * from Orders" ;
                show_user(q3);
                JOptionPane.showMessageDialog(null, "Order has been deleted!");
                }catch(ArrayIndexOutOfBoundsException ex)
                {
                    JOptionPane.showMessageDialog(null,"Please select an order first!");
                }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "ASC")
            {
                DefaultTableModel m = (DefaultTableModel)OrderTable.getModel();
                m.setRowCount(0);
                String q1 = "Select * From Orders Order by order_date ASC";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "DESC")
            {
                DefaultTableModel m = (DefaultTableModel)OrderTable.getModel();
                m.setRowCount(0);
                String q1 = "Select * From Orders Order by order_date DESC";
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
            else if(GDM.getMenuNameAt(index,subIndex) == "Confirmation")
            {
                Customer C = new Customer();
                C.setVisible(true);
                dispose();
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Delivery")
            {
                Delivers D = new Delivers();
                D.setVisible(true);
                dispose();
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
    
    public ArrayList<Ord> userList(String query1)
    {
        ArrayList<Ord> userList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Ord ord;
            while(rs.next())
            {
                ord = new Ord(rs.getInt("product_id"),rs.getInt("order_id"),rs.getInt("customer_id"), rs.getDate("order_date"), rs.getDate("delivery_date"), rs.getInt("DeliveryCharge"),rs.getInt("D_ID"), rs.getFloat("points"));
                userList.add(ord);
            }
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getErrorCode());
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return userList;
    }
    
    public void show_user(String query2)
    {
        ArrayList<Ord> list = userList(query2);
        DefaultTableModel model = (DefaultTableModel)OrderTable.getModel();
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getProduct_id();
            row[1] = list.get(i).getOrder_id();
            row[2] = list.get(i).getCustomer_id();
            row[3] = list.get(i).getOrder_date();
            row[4] = list.get(i).getDelivery_date();
            row[5] = list.get(i).getDeliveryCharge();
            row[6] = list.get(i).getD_ID();
            row[7] = list.get(i).getPoints();
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
        SearchOrdDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        OrderTable = new javax.swing.JTable();
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
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 100, 40));

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
        getContentPane().add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 50));
        getContentPane().add(SearchOrdDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 350, 40));

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Order Table");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 330, 70));

        OrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "OrderID", "CustomerID", "OrderDate", "DeliveryDate", "Carriage", "Deliveryman ID", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        OrderTable.setFocusable(false);
        OrderTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        OrderTable.setRowHeight(50);
        OrderTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        OrderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(OrderTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 920, 350));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Search Order Date");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (Orders.this.getExtendedState() == MAXIMIZED_BOTH) {
                Orders.this.setExtendedState(JFrame.NORMAL);
            } else {
                Orders.this.setExtendedState(MAXIMIZED_BOTH);
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
        DefaultTableModel m = (DefaultTableModel)OrderTable.getModel();
        m.setRowCount(0);
       
        try{
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String D = sdf.format(SearchOrdDate.getDate());
            String date = "'"+D+"'";
            
            String q2 = "Select * from Orders where Order_Date = "+date+"";
            
            show_user(q2);
            
            }catch(NullPointerException ex)
                 {
                     JOptionPane.showMessageDialog(null, "Please enter a date!");
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
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Orders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable OrderTable;
    private com.toedter.calendar.JDateChooser SearchOrdDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
