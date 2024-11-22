
package Base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Produk extends javax.swing.JFrame {
Connection con;
Statement stat;
ResultSet rs;
PreparedStatement pst;

private String selectedKodeProduk;
    
    public Produk() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        load_table();
        
        ubah_p.setEnabled(false);
        hapus_p.setEnabled(false);
        
            
    }
    private void reset() {
        tambah_p.setEnabled(true);
        ubah_p.setEnabled(false);
        hapus_p.setEnabled(false);
        
        
        nama_p.setText("");
        harga_b.setText("");
        harga_s.setText("");
        stok_p.setText("");
        
    }
    private void load_table() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("kode_produk");
        model.addColumn("nama_produk");
        model.addColumn("harga_beli");
        model.addColumn("harga_satuan");
        model.addColumn("stok");

        try {
           
            String sql = "select * from produk";

            rs = stat.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            tabel_produk.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }  
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nama_p = new javax.swing.JTextField();
        harga_b = new javax.swing.JTextField();
        harga_s = new javax.swing.JTextField();
        stok_p = new javax.swing.JTextField();
        tambah_p = new javax.swing.JButton();
        ubah_p = new javax.swing.JButton();
        hapus_p = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_produk = new javax.swing.JTable();
        kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nama_p.setBorder(null);
        getContentPane().add(nama_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 170, 340, 24));

        harga_b.setBorder(null);
        getContentPane().add(harga_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 215, 340, 23));

        harga_s.setBorder(null);
        getContentPane().add(harga_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 255, 340, 23));

        stok_p.setBorder(null);
        getContentPane().add(stok_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 296, 340, 22));

        tambah_p.setContentAreaFilled(false);
        tambah_p.setBorderPainted(false);
        tambah_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_pActionPerformed(evt);
            }
        });
        getContentPane().add(tambah_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 166, 120, 40));

        ubah_p.setContentAreaFilled(false);
        ubah_p.setBorderPainted(false);
        ubah_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_pActionPerformed(evt);
            }
        });
        getContentPane().add(ubah_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 222, 120, 41));

        hapus_p.setContentAreaFilled(false);
        hapus_p.setBorderPainted(false);
        hapus_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_pActionPerformed(evt);
            }
        });
        getContentPane().add(hapus_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 280, 120, 41));

        tabel_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Produk", "Nama Produk ", "Harga Beli", "Harga Satuan", "Stok"
            }
        ));
        tabel_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_produkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_produk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 367, 1038, 350));

        kembali.setContentAreaFilled(false);
        kembali.setBorderPainted(false);
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 283, 207, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/Produk.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambah_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_pActionPerformed
    try {
        String sqlKode = "SELECT kode_produk FROM produk ORDER BY kode_produk DESC LIMIT 1";
        pst = con.prepareStatement(sqlKode);
        ResultSet rs = pst.executeQuery();

        String kodeBaru = "P01"; 
        if (rs.next()) {
            String kodeTerakhir = rs.getString("kode_produk");
            int angka = Integer.parseInt(kodeTerakhir.substring(1)) + 1;
            kodeBaru = String.format("P%02d", angka);
        }

        rs.close();
        pst.close();

 
        String sql = "INSERT INTO produk (kode_produk, nama_produk, harga_beli, harga_satuan, stok) VALUES (?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, kodeBaru);
        pst.setString(2, nama_p.getText());
        pst.setString(3, harga_b.getText());
        pst.setString(4, harga_s.getText());
        pst.setString(5, stok_p.getText());
        
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
    }//GEN-LAST:event_tambah_pActionPerformed

    private void hapus_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_pActionPerformed
      
        int ok=JOptionPane.showConfirmDialog(null, "Ingin menghapus data ini?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok==0){
            try{
                String sql="DELETE FROM produk WHERE kode_produk='"+selectedKodeProduk+"'";
                PreparedStatement st = con.prepareStatement(sql);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sukses!");
                reset();
                load_table();
            } catch (SQLException e){
                  JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                
            }
        }
    }//GEN-LAST:event_hapus_pActionPerformed

    private void ubah_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_pActionPerformed
       
        try {
            String sql = "UPDATE produk SET nama_produk = ?, harga_beli = ?, harga_satuan = ?, stok = ? WHERE kode_produk = ?";
            
            pst = con.prepareStatement(sql);

            pst.setString(1, nama_p.getText());  
            pst.setString(2, harga_b.getText());  
            pst.setString(3, harga_s.getText());  
            pst.setString(4, stok_p.getText());   
            pst.setString(5, selectedKodeProduk); 

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
        
    }//GEN-LAST:event_ubah_pActionPerformed

    private void tabel_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_produkMouseClicked

        tambah_p.setEnabled(false);
        ubah_p.setEnabled(true);
        hapus_p.setEnabled(true);

        int baris = tabel_produk.rowAtPoint(evt.getPoint());

        selectedKodeProduk = tabel_produk.getValueAt(baris, 0).toString();

        String nama_produk = tabel_produk.getValueAt(baris,1).toString(); 
        nama_p.setText(nama_produk);

        String harga_beli = tabel_produk.getValueAt(baris, 2).toString(); 
        harga_b.setText(harga_beli);

        String harga_satuan = tabel_produk.getValueAt(baris, 3).toString(); 
        harga_s.setText(harga_satuan);

        String stok = tabel_produk.getValueAt(baris, 4).toString();
        stok_p.setText(stok);
        
    }//GEN-LAST:event_tabel_produkMouseClicked

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        DashAdmin nw_dashadmin = new DashAdmin();
        nw_dashadmin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed

    
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
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus_p;
    private javax.swing.JTextField harga_b;
    private javax.swing.JTextField harga_s;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField stok_p;
    private javax.swing.JTable tabel_produk;
    private javax.swing.JButton tambah_p;
    private javax.swing.JButton ubah_p;
    // End of variables declaration//GEN-END:variables
}
