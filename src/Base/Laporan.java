package Base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

    public class Laporan extends javax.swing.JFrame {
        Connection con;
        Statement stat;
        ResultSet rs;
        PreparedStatement pst;

    public Laporan() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        isiComboTahun();
      
    }

    private int getMonthFromString(String month) {
        switch (month) {
            case "Januari": return 1;  // private class yang berfungsi untuk menyediakan bulan untuk fitur tanggal
            case "Februari": return 2;
            case "Maret": return 3;
            case "April": return 4;
            case "Mei": return 5;
            case "Juni": return 6;
            case "Juli": return 7;
            case "Agustus": return 8;
            case "September": return 9;
            case "Oktober": return 10;
            case "November": return 11;
            case "Desember": return 12;
            default: return 0;
    }
}
    private void loadDataToTable(String sql, int param){
        DefaultTableModel model = new DefaultTableModel(); // private class yang berfungsi untuk menyediakan colom di swing tabel
        model.addColumn("Tanggal transaksi");
        model.addColumn("Kode produk");
        model.addColumn("Nama produk");
        model.addColumn("Harga satuan");
        model.addColumn("Jumlah terjual");
        model.addColumn("Total penjualan");
        model.addColumn("Harga Beli");
        model.addColumn("Total keuntungan");
        
        try {
           pst = con.prepareStatement(sql);  // fungsi Read dalam CRUD
           pst.setInt(1, param);
           rs = pst.executeQuery();
           
           while(rs.next()) {             // Kode ini digunakan untuk mengambil data pada database yang akan ditampilkan di swing tabel
            model.addRow(new Object[]{ 
            rs.getString("tgl_transaksi"),  
            rs.getString("kode_produk"),
            rs.getString("nama_produk"),
            rs.getDouble("harga_satuan"),
            rs.getInt("qty"),
            rs.getDouble("harga_beli"),
            rs.getDouble("total_penjualan"),
            rs.getDouble("total_keuntungan")
           });
           }
           tabel_p.setModel(model); // nantinya akan diabil oleh variabel tabel dan ditampilkan
           
        } catch (SQLException e) {   // menampilkan error bila terjadi kesalahan supaya lebih mudah jika akan diperbaiki
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());   
        }
    }
    
    private void isiComboTahun() {  // private class yang digunakan untuk membuat jangka waktu tahun dari 2024-2030 
        DefaultComboBoxModel<String> tahunModel = new DefaultComboBoxModel<>();
        tahunModel.addElement("Pilih Tahun");

        int tahunMulai = 2024; 
        int tahunAkhir = 2030; 

        for (int year = tahunMulai; year <= tahunAkhir; year++) {
            tahunModel.addElement(String.valueOf(year));
        }
        tgl2.setModel(tahunModel); // dikoneksikan dengan variabel combobox
}
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_p = new javax.swing.JTable();
        tgl1 = new javax.swing.JComboBox<>();
        tgl2 = new javax.swing.JComboBox<>();
        kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel_p.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal transaksi", "Kode produk", "Nama produk", "Harga satuan", "Jumlah terjual", "Total penjualan", "Total keutungan"
            }
        ));
        tabel_p.setAlignmentX(0.9F);
        tabel_p.setAlignmentY(0.9F);
        jScrollPane1.setViewportView(tabel_p);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 1020, 490));

        tgl1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Bulan", "Januari ", "Februari", "Maret ", "April", "Mei ", "Juni", "July", "Agustus", "September", "Oktober", "November", "Desember" }));
        tgl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl1ActionPerformed(evt);
            }
        });
        getContentPane().add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 169, 160, 30));

        tgl2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tgl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl2ActionPerformed(evt);
            }
        });
        getContentPane().add(tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 169, 160, 30));

        kembali.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        kembali.setContentAreaFilled(false);
        kembali.setBorderPainted(false);
        kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 305, 196, 45));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/Laporan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, -1, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void tgl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl1ActionPerformed
   
        String selectedMonth = (String) tgl1.getSelectedItem();
        // Mengambil angka bulan berdasarkan nama bulan yang dipilih
        int month = getMonthFromString(selectedMonth);
        // Menghindari eksekusi jika "Pilih Bulan" dipilih
        if (selectedMonth.equals("Pilih Bulan")) {
            return;
        }


        // SQL untuk mengambil data transaksi dan detail transaksi pada bulan tertentu
        String sql = "SELECT t.tgl_transaksi, d.kode_produk, p.nama_produk, p.harga_satuan, p.harga_beli, d.qty, "
                    + "(d.qty * p.harga_satuan) AS total_penjualan, "
                    + "(d.qty * (p.harga_satuan - p.harga_beli)) AS total_keuntungan "
                    + "FROM transaksi t "
                    + "JOIN detail_transaksi d ON t.id_transaksi = d.id_transaksi "
                    + "JOIN produk p ON d.kode_produk = p.kode_produk "
                    + "WHERE MONTH (t.tgl_transaksi) = ?"; // Menggunakan parameter untuk bulan
    
        loadDataToTable(sql, month);
    }//GEN-LAST:event_tgl1ActionPerformed

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        DashAdmin nw_produk = new DashAdmin();
        nw_produk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_kembaliActionPerformed
    
    private void tgl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl2ActionPerformed
    
    String selectedYear = (String) tgl2.getSelectedItem();
    int year = Integer.parseInt(selectedYear); 
    if (selectedYear.equals("Pilih Tahun")) {
    return;
    
}

    // SQL untuk mengambil data transaksi dan detail transaksi pada tahun tertentu
    String sql = "SELECT t.tgl_transaksi, d.kode_produk, p.nama_produk, p.harga_satuan, p.harga_beli, d.qty, "
                    + "(d.qty * p.harga_satuan) AS total_penjualan, "
                    + "(d.qty * (p.harga_satuan - p.harga_beli)) AS total_keuntungan "
                    + "FROM transaksi t "
                    + "JOIN detail_transaksi d ON t.id_transaksi = d.id_transaksi "
                    + "JOIN produk p ON d.kode_produk = p.kode_produk "
                    + "WHERE YEAR(t.tgl_transaksi) = ?"; // Menggunakan parameter untuk tahun
    
    loadDataToTable(sql, year);
    }//GEN-LAST:event_tgl2ActionPerformed
    
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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTable tabel_p;
    private javax.swing.JComboBox<String> tgl1;
    private javax.swing.JComboBox<String> tgl2;
    // End of variables declaration//GEN-END:variables
}
