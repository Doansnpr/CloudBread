
package Base;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Login extends javax.swing.JFrame {

    Connection con;
    Koneksi DB = new Koneksi();
    
    public Login() {
        initComponents();
        
        DB.config();
        con = DB.con;
        
    }
    
    public static String activeUserId; // ID user aktif
    public static String activeUsername; // Nama user aktif (opsional)
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        logButton = new javax.swing.JButton();
        register_p = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        RadButton = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username.setBorder(null);
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 260, 200, 27));

        logButton.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        logButton.setForeground(new java.awt.Color(242, 242, 242));
        logButton.setContentAreaFilled(false);
        logButton.setBorderPainted(false);
        logButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logButtonActionPerformed(evt);
            }
        });
        getContentPane().add(logButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 375, 200, 37));

        register_p.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        register_p.setForeground(new java.awt.Color(242, 242, 242));
        register_p.setLabel("");
        register_p.setContentAreaFilled(false);
        register_p.setBorderPainted(false);
        register_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register_pActionPerformed(evt);
            }
        });
        getContentPane().add(register_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 460, 200, 37));

        password.setBorder(null);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 322, 200, 26));

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
        getContentPane().add(RadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 353, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/FormLogin.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1360, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void logButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logButtonActionPerformed
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
     try {
         PreparedStatement st = con.prepareStatement(sql);
         st.setString(1, username.getText());
         st.setString(2, new String(password.getPassword()));

         ResultSet rs = st.executeQuery();
         if (rs.next()) {
             String level = rs.getString("level");
             activeUserId = rs.getString("id_user"); 
             activeUsername = rs.getString("username");
             JOptionPane.showMessageDialog(null, "Login Sukses!");

             if ("Admin".equalsIgnoreCase(level)) {
                 new DashAdmin().setVisible(true); 
             } else if ("Kasir".equalsIgnoreCase(level)) {
                 new DashKasir().setVisible(true);
             }

             dispose();
         } else {
             JOptionPane.showMessageDialog(null, "Username atau password salah");
         }
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
     }
    }//GEN-LAST:event_logButtonActionPerformed

    private void register_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_register_pActionPerformed
        // TODO add your handling code here:
        Register register = new Register();
        register.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_register_pActionPerformed

    private void RadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadButtonActionPerformed
        if(RadButton.isSelected()){
            password.setEchoChar((char)0);
        } else{
            password.setEchoChar('•');
        }
    }//GEN-LAST:event_RadButtonActionPerformed

 
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton register_p;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
