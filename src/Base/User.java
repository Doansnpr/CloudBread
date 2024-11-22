
package Base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User extends javax.swing.JFrame {
Connection con;
Statement stm;
ResultSet rs;
PreparedStatement pst;

private String selectID;

    public User() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stm = DB.stm;
        load_table();
        
        ubah_u.setEnabled(false);
        hapus_u.setEnabled(false);
    }
    private void reset() {
        tambah_u.setEnabled(true);
        ubah_u.setEnabled(false);
        hapus_u.setEnabled(false);
        
        nama_u.setText("");
        username_u.setText("");
        password_u.setText("");
        alamat_u.setText("");
        notlp_u.setText("");
        level_u.setText("");

    }
    
private void load_table() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id_user");
        model.addColumn("Nama");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("No telp");
        model.addColumn("Alamat");
        model.addColumn("Level");

        try {
            String sql = "select * from user";

            rs = stm.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
            tabel_u.setModel(model);
        } catch (Exception e) {
        
        }
    
}
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kembali_u = new javax.swing.JButton();
        nama_u = new javax.swing.JTextField();
        username_u = new javax.swing.JTextField();
        password_u = new javax.swing.JTextField();
        notlp_u = new javax.swing.JTextField();
        level_u = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_u = new javax.swing.JTable();
        tambah_u = new javax.swing.JButton();
        ubah_u = new javax.swing.JButton();
        hapus_u = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat_u = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kembali_u.setContentAreaFilled(false);
        kembali_u.setBorderPainted(false);
        kembali_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali_uActionPerformed(evt);
            }
        });
        getContentPane().add(kembali_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 200, 50));

        nama_u.setBorder(null);
        nama_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_uActionPerformed(evt);
            }
        });
        getContentPane().add(nama_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 140, 216, 28));

        username_u.setBorder(null);
        username_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_uActionPerformed(evt);
            }
        });
        getContentPane().add(username_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 190, 216, 29));

        password_u.setBorder(null);
        password_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_uActionPerformed(evt);
            }
        });
        getContentPane().add(password_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 241, 216, 29));

        notlp_u.setBorder(null);
        notlp_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notlp_uActionPerformed(evt);
            }
        });
        getContentPane().add(notlp_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 291, 216, 28));

        level_u.setBorder(null);
        getContentPane().add(level_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 295, 225, 29));

        tabel_u.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_user", "Nama", "Username", "Password", "Alamat", "No Telp", "Level"
            }
        ));
        tabel_u.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_uMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_u);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 358, 1036, 358));

        tambah_u.setContentAreaFilled(false);
        tambah_u.setBorderPainted(false);
        tambah_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_uActionPerformed(evt);
            }
        });
        getContentPane().add(tambah_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(1195, 145, 115, 30));

        ubah_u.setContentAreaFilled(false);
        ubah_u.setBorderPainted(false);
        ubah_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_uActionPerformed(evt);
            }
        });
        getContentPane().add(ubah_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(1195, 200, 115, 32));

        hapus_u.setContentAreaFilled(false);
        hapus_u.setBorderPainted(false);
        hapus_u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_uActionPerformed(evt);
            }
        });
        getContentPane().add(hapus_u, new org.netbeans.lib.awtextra.AbsoluteConstraints(1194, 256, 115, 33));

        alamat_u.setColumns(20);
        alamat_u.setRows(5);
        alamat_u.setBorder(null);
        jScrollPane2.setViewportView(alamat_u);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 138, 231, 136));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/User.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nama_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_uActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_uActionPerformed

    private void username_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_uActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_uActionPerformed

    private void password_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_uActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_uActionPerformed

    private void notlp_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notlp_uActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notlp_uActionPerformed

    private void tambah_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_uActionPerformed
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
        pst.setString(2, nama_u.getText());
        pst.setString(3, username_u.getText());
        pst.setString(4, password_u.getText());
        pst.setString(6, alamat_u.getText());
        pst.setString(5, notlp_u.getText());
        pst.setString(7, level_u.getText());

        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Sukses!");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal!");
        }

        reset();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    } 
    load_table();

    }//GEN-LAST:event_tambah_uActionPerformed

    private void ubah_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_uActionPerformed
       
        try {
        String sql = "UPDATE user SET Nama=?, Username=?, Password=?, No_telp=?, Alamat=?, Level=? WHERE id_user=?";
    
        pst = con.prepareStatement(sql);

        pst.setString(1, nama_u.getText());  
        pst.setString(2, username_u.getText());
        pst.setString(3, password_u.getText());
        pst.setString(4, notlp_u.getText());
        pst.setString(5, alamat_u.getText());
        pst.setString(6, level_u.getText());
        pst.setString(7, selectID);
        
        int rowsAffected = pst.executeUpdate(); 
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Sukses!");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal!");
        }

        reset();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        load_table();

    }//GEN-LAST:event_ubah_uActionPerformed

    private void kembali_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali_uActionPerformed
        DashAdmin nw_dashadmin = new DashAdmin();
        nw_dashadmin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembali_uActionPerformed

    private void tabel_uMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_uMouseClicked
        tambah_u.setEnabled(false);
        ubah_u.setEnabled(true);
        hapus_u.setEnabled(true);

        int baris = tabel_u.rowAtPoint(evt.getPoint());

        selectID = tabel_u.getValueAt(baris, 0).toString();

        String nama = tabel_u.getValueAt(baris,1).toString(); 
        nama_u.setText(nama);

        String username = tabel_u.getValueAt(baris, 2).toString(); 
        username_u.setText(username);

        String password = tabel_u.getValueAt(baris, 3).toString(); 
        password_u.setText(password);

        String no_telp = tabel_u.getValueAt(baris, 4).toString();
        notlp_u.setText(no_telp);
        
        String alamat = tabel_u.getValueAt(baris, 5).toString();
        alamat_u.setText(alamat);
        
        String level = tabel_u.getValueAt(baris, 6).toString();
        level_u.setText(level);
    }//GEN-LAST:event_tabel_uMouseClicked

    private void hapus_uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_uActionPerformed
        int ok=JOptionPane.showConfirmDialog(null, "Ingin menghapus data ini?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok==0){
            try{
                String sql="DELETE FROM user WHERE id_user='"+selectID+"'";
                PreparedStatement st = con.prepareStatement(sql);
                int rowsAffected = st.executeUpdate(); 
        
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Sukses!");
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal!");
                }
                reset();
                load_table();
            } catch (SQLException e){
                  JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                
            }
        }
    }//GEN-LAST:event_hapus_uActionPerformed

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_u;
    private javax.swing.JButton hapus_u;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton kembali_u;
    private javax.swing.JTextField level_u;
    private javax.swing.JTextField nama_u;
    private javax.swing.JTextField notlp_u;
    private javax.swing.JTextField password_u;
    private javax.swing.JTable tabel_u;
    private javax.swing.JButton tambah_u;
    private javax.swing.JButton ubah_u;
    private javax.swing.JTextField username_u;
    // End of variables declaration//GEN-END:variables
}
