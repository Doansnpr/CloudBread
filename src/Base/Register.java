
package Base;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;


public class Register extends javax.swing.JFrame {

    Connection con;
    Statement stm;
    PreparedStatement pst;
    ResultSet st;

    
    public Register() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
    }
    
    private void reset() {
        
        nama.setText("");
        username.setText("");
        password.setText("");
        no_telp.setText("");
        alamat.setText("");
        level.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        nama = new javax.swing.JTextField();
        no_telp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        register = new javax.swing.JButton();
        level = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        RadButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setBorder(null);
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 226, 210, 27));

        password.setBorder(null);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 283, 210, 28));

        nama.setBorder(null);
        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 169, 210, 27));

        no_telp.setBorder(null);
        no_telp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_telpActionPerformed(evt);
            }
        });
        getContentPane().add(no_telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 368, 210, 27));

        alamat.setColumns(20);
        alamat.setRows(5);
        alamat.setBorder(null);
        jScrollPane1.setViewportView(alamat);
        alamat.getAccessibleContext().setAccessibleParent(alamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(576, 479, 220, 100));

        register.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        register.setForeground(new java.awt.Color(242, 242, 242));
        register.setContentAreaFilled(false);
        register.setBorderPainted(false);
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        getContentPane().add(register, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 600, 100, 30));

        level.setEditable(false);
        level.setText("Kasir");
        level.setBorder(null);
        level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                levelActionPerformed(evt);
            }
        });
        getContentPane().add(level, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 424, 210, 28));

        kembali.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        kembali.setForeground(new java.awt.Color(242, 242, 242));
        kembali.setBorder(null);
        kembali.setContentAreaFilled(false);
        kembali.setBorderPainted(false);
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 600, 94, 30));

        RadButton.setBackground(new java.awt.Color(230, 210, 172));
        RadButton.setText("Show Password");
        RadButton.setToolTipText("");
        RadButton.setBorder(null);
        RadButton.setBorderPainted(true);
        RadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadButtonActionPerformed(evt);
            }
        });
        getContentPane().add(RadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 318, -1, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/FormRegister.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void no_telpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_telpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_telpActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_levelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_levelActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        try {
           String sqlID = "SELECT id_user FROM user ORDER BY id_user DESC LIMIT 1";
           pst = con.prepareStatement(sqlID);
           ResultSet rs = pst.executeQuery(); 
           
           String newID = "U01"; 
           if (rs.next()) {
            String IDterakhir = rs.getString("id_user");
            int angka = Integer.parseInt(IDterakhir.substring(1)) + 1;
            newID = String.format("U%02d", angka);
           }
           
           rs.close();
           pst.close();
            
           String sql = "INSERT INTO user (id_user, nama, username, password, alamat, no_telp, level) VALUES (?, ?, ?, ?, ?, ?, ?)";

           pst = con.prepareStatement(sql);

           pst.setString(1, newID);
           pst.setString(2, nama.getText());
           pst.setString(3, username.getText());
           pst.setString(4, password.getText());
           pst.setString(5, alamat.getText());
           pst.setString(6, no_telp.getText());
           pst.setString(7, level.getText());

           pst.execute();

           JOptionPane.showMessageDialog(null, "Register Sukses!");
           reset();
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }

    }//GEN-LAST:event_registerActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
     Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    private void RadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadButtonActionPerformed
        if(RadButton.isSelected()){
            password.setEchoChar((char)0);
        } else{
            password.setEchoChar('•');
        }
    }//GEN-LAST:event_RadButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadButton;
    private javax.swing.JTextArea alamat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField level;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField no_telp;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton register;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
