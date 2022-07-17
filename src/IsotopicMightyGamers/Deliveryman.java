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
public class Deliveryman extends javax.swing.JFrame {

    public static int counter = 0;
    int count = 0;
    /**
     * Creates new form Employee
     */
    public Deliveryman() {
        initComponents();
        
        EmployeeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        EmployeeTable.getTableHeader().setOpaque(false);
        EmployeeTable.getTableHeader().setBackground(new Color(32,136,203));
        EmployeeTable.getTableHeader().setForeground(new Color(0, 0, 0));
        EmployeeTable.setRowHeight(50);
        
        GradientDropdownMenu GDM = new GradientDropdownMenu();
        GDM.setMenuHeight(50);
        GDM.setGradientColor(Color.ORANGE, Color.RED);
        GDM.addItem("Data");
        GDM.addItem("Delete");
        GDM.addItem("Deliveries Sorting","ASC","DESC");
        GDM.addItem("Back","Home","Exit");
        GDM.applay(this);
        
        GDM.addEvent(new MenuEvent() {
    @Override
    public void selected(int index, int subIndex, boolean menuItem) {
        if (menuItem) {
            if(GDM.getMenuNameAt(index,subIndex) == "Data")
            {
                if(counter == 0)
                {
                    DefaultTableModel m = (DefaultTableModel)EmployeeTable.getModel();
                    m.setRowCount(0);
                    String q1 = "Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone ";
                    show_user(q1);
                    counter++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Already Entered!");
                }
                    
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "Delete")
            {
                try{
                    DefaultTableModel m1 = (DefaultTableModel)EmployeeTable.getModel();
                    int i = EmployeeTable.getSelectedRow();
                    TableModel m = EmployeeTable.getModel();
                    String a = m.getValueAt(i, 0).toString();
                    int b = Integer.parseInt(a);
                    m1.setRowCount(0);
                    String q3 = "delete from DeliveryMan where D_ID = "+b+"Select * from DeliveryMan" ;
                    show_user(q3);

                    if(count == 0)
                    {
                       JOptionPane.showMessageDialog(null, "Deliveryman Data deleted!");
                    }
                    else if(count == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Product Data could not be deleted!");  
                    }

                    }catch(ArrayIndexOutOfBoundsException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a deliveryman first!");
                    }
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "ASC")
            {
                DefaultTableModel m = (DefaultTableModel)EmployeeTable.getModel();
                m.setRowCount(0);
                String q1 = "Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone order by [deliveries] ASC";
                show_user(q1);
            }
            else if(GDM.getMenuNameAt(index,subIndex) == "DESC")
            {
                DefaultTableModel m = (DefaultTableModel)EmployeeTable.getModel();
                m.setRowCount(0);
                String q1 = "Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone order by [deliveries] DESC";
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
    
    public ArrayList<Delman> userList(String query1)
    {
        ArrayList<Delman> userList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MacroGameZone;user=sa;password=p@ssword13";
            Connection con = DriverManager.getConnection(url);
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Delman emp;
            while(rs.next())
            {
                emp = new Delman(rs.getInt("D_ID"),rs.getString("D_Name"),rs.getString("D_Addr"), rs.getInt("D_Phone"), rs.getInt("Salary"),rs.getString("D_email"), rs.getDate("JoiningDate"), rs.getFloat("NID"), rs.getInt("deliveries"));
                userList.add(emp);
            }
        }catch(SQLException ex)
        {
            int a = ex.getErrorCode();
            if(a == 547)
            {
                count = 1;
                JOptionPane.showMessageDialog(null, "The deliveryman has not delivered all his registered orders yet!");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Deliveryman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
    
    public void show_user(String query2)
    {
        ArrayList<Delman> list = userList(query2);
        DefaultTableModel model = (DefaultTableModel)EmployeeTable.getModel();
        Object[] row = new Object[9];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getD_ID();
            row[1] = list.get(i).getD_Name();
            row[2] = list.get(i).getD_Addr();
            row[3] = list.get(i).getD_Phone();
            row[4] = list.get(i).getSalary();
            row[5] = list.get(i).getD_email();
            row[6] = list.get(i).getJoiningDate();
            row[7] = list.get(i).getNID();
            row[8] = list.get(i).getDeliveries();
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
        Search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
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
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 220, 100, 40));

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
        getContentPane().add(pnlTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 50));

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 540, 40));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Search Deliveryman ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 250, 40));

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DeliverymanID", "Name", "Address", "Contact", "Salary", "D_email", "Joining Date", "NID", "Deliveries"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        EmployeeTable.setFocusable(false);
        EmployeeTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        EmployeeTable.setRowHeight(50);
        EmployeeTable.setSelectionBackground(new java.awt.Color(233, 150, 122));
        EmployeeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(EmployeeTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 1020, 290));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel2.setText("Deliveryman Table");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 410, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IsotopicMightyGamers/photo-1579546929518-9e396f3cc809.jpg"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 620));

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
            if (Deliveryman.this.getExtendedState() == MAXIMIZED_BOTH) {
                Deliveryman.this.setExtendedState(JFrame.NORMAL);
            } else {
                Deliveryman.this.setExtendedState(MAXIMIZED_BOTH);
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
       DefaultTableModel m = (DefaultTableModel)EmployeeTable.getModel();
        m.setRowCount(0);
        String id_srch = Search.getText();
        if(id_srch.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter a Deliveryman's id!");
        }
        else
        {
            int a = Integer.parseInt(id_srch);
            String q2 = "Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID where DeliveryMan.D_ID = "+a+" group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone";

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
            java.util.logging.Logger.getLogger(Deliveryman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Deliveryman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Deliveryman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Deliveryman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Deliveryman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
