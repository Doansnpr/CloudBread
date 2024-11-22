
package Base;
import javax.swing.*;  
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Transaksi extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    PreparedStatement pst;

    public Transaksi() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        Tanggal();
        autoNumber();
        setupNamaProdukAutoComplete();
        setupListProdukListener();
        listProduk.setVisible(false);
        
        ubah_p.setEnabled(false);
        hapus_p.setEnabled(false);
        
    }
    

    private void autoNumber() {
        try {
            String sql = "SELECT MAX(id_transaksi) FROM transaksi";
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                String maxNoTransaksi = rs.getString(1);
                if (maxNoTransaksi != null) {
                    int number = Integer.parseInt(maxNoTransaksi.substring(3)) + 1;
                    no_transaksi.setText("TRX" + String.format("%02d", number));
                } else {
                    no_transaksi.setText("TRX01");
                }
                no_transaksi.setEditable(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error generating transaction number: " + e.getMessage());
        }
    }
    
    private int counter; 

    private void idDetail() {
        try {
            String sql = "SELECT MAX(id_detail) FROM detail_transaksi";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() && rs.getString(1) != null) {
                String lastId = rs.getString(1);
                int lastCounter = Integer.parseInt(lastId.replaceAll("\\D+", "")); // Hapus prefix non-angka
                counter = lastCounter + 1; // Set counter ke nilai berikutnya
            } else {
                counter = 1; // Jika tidak ada data, mulai dari 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
            counter = 1; // Default jika ada error
        }
    }
    
    private String generateIDDetail() {
        String idDetail = "DTL" + String.format("%02d", counter);
        counter++;
        return idDetail;
    }


    
    private void setupNamaProdukAutoComplete() {
        nama_p.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                showProdukSuggestions();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                showProdukSuggestions();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                showProdukSuggestions();
            }
        });
    }

    private void showProdukSuggestions() {
        String searchQuery = nama_p.getText();
    if (!searchQuery.isEmpty()) {
        try {
            String sql = "SELECT nama_produk FROM produk WHERE nama_produk LIKE ? LIMIT 10";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + searchQuery + "%");
            rs = pst.executeQuery();
            
            DefaultListModel<String> model = new DefaultListModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("nama_produk"));
            }
            listProduk.setModel(model);
            
            if (model.getSize() > 0) {
                listProduk.setVisible(true);
            } else {
                listProduk.setVisible(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching product suggestions: " + e.getMessage());
        }
        } else {
            listProduk.setVisible(false); 
        }
    }

    private void setupListProdukListener() {
         listProduk.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            String selectedProduk = listProduk.getSelectedValue();
            if (selectedProduk != null) {
                nama_p.setText(selectedProduk);
                HargaSatuan(selectedProduk);
                listProduk.setVisible(false);
            }
        }
    });
    }
    
    private int getStokProdukByNama(String namaProduk) {
        int stok = 0;
        try {
            String sqlStok = "SELECT stok FROM produk WHERE nama_produk = ?";
            pst = con.prepareStatement(sqlStok);
            pst.setString(1, namaProduk);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                stok = rs.getInt("stok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data stok produk: " + e.getMessage());
        }
        return stok;
    }

    private int hargaAsli = 0;

    private String kodeProduk = "";

    private void HargaSatuan(String namaProduk) {
        try {
            String sql = "SELECT kode_produk, harga_satuan FROM produk WHERE nama_produk = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, namaProduk);
            rs = pst.executeQuery();
            if (rs.next()) {
                kodeProduk = rs.getString("kode_produk");
                hargaAsli = rs.getInt("harga_satuan");

                String hargaFormatted = "Rp " + String.format("%,d", hargaAsli);
                harga_s.setText(hargaFormatted);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching product details: " + e.getMessage());
        }
    }
    
    private void TotalHarga() {
    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
    
    int total = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object subtotalObj = model.getValueAt(i, 4); 

        if (subtotalObj != null) {  
            String subtotalStr = subtotalObj.toString().replace("Rp ", "").replace(",", "").replace(".", "").trim();
            
            try {
                int subtotal = Integer.parseInt(subtotalStr); 
                total += subtotal; 
            } catch (NumberFormatException e) {
                System.out.println("Error saat mengonversi subtotal: " + subtotalStr);
                continue;
            }
        }
    }

    total_P1.setText("Rp " + String.format("%,d", total)); 
    total_P2.setText("Rp " + String.format("%,d", total)); 
    }
    

    private void Tanggal() {
        Date tanggal = new Date();

        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");

        tgl.setText(formatTanggal.format(tanggal));
    }

     
    private void cekDanHitungKembalian() {
    try {
        String bayarText = bayar_transaksi.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
        String totalText = total_P2.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");

        if (bayarText.isEmpty() || totalText.isEmpty() || bayarText.equals("0")) {
            kembalian_p.setText("Rp 0");
            return;
        }

        int bayar = Integer.parseInt(bayarText);
        int totalHarga = Integer.parseInt(totalText);

        if (bayar < totalHarga) {
            kembalian_p.setText("Rp 0"); 
            return;
        }

        Kembalian();

    } catch (NumberFormatException e) {
        kembalian_p.setText("Rp 0");
    }
}

    
    private void Kembalian() {
        try {
            String totalText = total_P2.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
            String bayarText = bayar_transaksi.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");

            if (totalText.isEmpty() || bayarText.isEmpty()) {
                kembalian_p.setText("Rp 0");
                return;
            }

            int totalHarga = Integer.parseInt(totalText);
            int bayar = Integer.parseInt(bayarText);

            int kembalian = bayar - totalHarga;

            if (kembalian < 0) {
                JOptionPane.showMessageDialog(null, "Jumlah bayar kurang dari total harga!");
                kembalian_p.setText("Rp 0");
                return;
            }

            kembalian_p.setText("Rp " + String.format("%,d", kembalian));
        } catch (NumberFormatException e) {
            kembalian_p.setText("Rp 0");
            JOptionPane.showMessageDialog(null, "Format input salah. Harap masukkan angka yang valid untuk pembayaran.");
        }
    }


    private void resetTransaksi() {
        no_transaksi.setText("");
        tgl.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); 
        total_P1.setText("Rp 0");
        total_P2.setText("Rp 0");
        bayar_transaksi.setText("Rp 0");
        kembalian_p.setText("Rp 0");

        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        model.setRowCount(0); 
        autoNumber(); 
    }


    
    private void reset() {
        no_transaksi.setText("");
        listProduk.setSelectedIndex(0);
        nama_p.setText("");
        harga_s.setText("");
        qty_p.setText("");
        kodeProduk = "";
        hargaAsli = 0;
        
        autoNumber();
        
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        no_transaksi = new javax.swing.JTextField();
        harga_s = new javax.swing.JTextField();
        nama_p = new javax.swing.JTextField();
        qty_p = new javax.swing.JTextField();
        kembalian_p = new javax.swing.JTextField();
        total_P1 = new javax.swing.JTextField();
        bayar_transaksi = new javax.swing.JTextField();
        tgl = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        total_P2 = new javax.swing.JTextField();
        tambah_p = new javax.swing.JButton();
        clear_p = new javax.swing.JButton();
        ubah_p = new javax.swing.JButton();
        hapus_p = new javax.swing.JButton();
        bayar_p = new javax.swing.JButton();
        kembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listProduk = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        no_transaksi.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        no_transaksi.setBorder(null);
        no_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_transaksiActionPerformed(evt);
            }
        });
        getContentPane().add(no_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 230, 30));

        harga_s.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        harga_s.setToolTipText("");
        harga_s.setBorder(null);
        getContentPane().add(harga_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 248, 176, 31));

        nama_p.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        nama_p.setBorder(null);
        getContentPane().add(nama_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 248, 233, 30));

        qty_p.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        qty_p.setBorder(null);
        getContentPane().add(qty_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 248, 60, 30));

        kembalian_p.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        kembalian_p.setBorder(null);
        getContentPane().add(kembalian_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 200, 52));

        total_P1.setBackground(new java.awt.Color(0, 0, 0));
        total_P1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        total_P1.setForeground(new java.awt.Color(0, 153, 0));
        total_P1.setBorder(null);
        total_P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_P1ActionPerformed(evt);
            }
        });
        getContentPane().add(total_P1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 114, 340, 70));

        bayar_transaksi.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bayar_transaksi.setBorder(null);
        bayar_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayar_transaksiActionPerformed(evt);
            }
        });
        bayar_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayar_transaksiKeyReleased(evt);
            }
        });
        getContentPane().add(bayar_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 620, 236, 33));

        tgl.setBorder(null);
        tgl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        tgl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 180, 31));

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Produk", "Nama Produk", "Harga", "Qty", "Sub Total"
            }
        ));
        tabel_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_transaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 1270, 223));

        total_P2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        total_P2.setBorder(null);
        getContentPane().add(total_P2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 571, 235, 33));

        tambah_p.setContentAreaFilled(false);
        tambah_p.setBorderPainted(false);
        tambah_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_pActionPerformed(evt);
            }
        });
        getContentPane().add(tambah_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 242, 110, 40));

        clear_p.setContentAreaFilled(false);
        clear_p.setBorderPainted(false);
        clear_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_pActionPerformed(evt);
            }
        });
        getContentPane().add(clear_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 242, 90, 40));

        ubah_p.setContentAreaFilled(false);
        ubah_p.setBorderPainted(false);
        ubah_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_pActionPerformed(evt);
            }
        });
        getContentPane().add(ubah_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 242, 90, 40));

        hapus_p.setContentAreaFilled(false);
        hapus_p.setBorderPainted(false);
        hapus_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_pActionPerformed(evt);
            }
        });
        getContentPane().add(hapus_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 242, 100, 40));

        bayar_p.setContentAreaFilled(false);
        bayar_p.setBorderPainted(false);
        bayar_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayar_pActionPerformed(evt);
            }
        });
        getContentPane().add(bayar_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 670, 120, 40));

        kembali.setContentAreaFilled(false);
        kembali.setBorderPainted(false);
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 110, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/TransaksiPage.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        listProduk.setBorder(null);
        jScrollPane2.setViewportView(listProduk);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 250, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void no_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_transaksiActionPerformed

    private void total_P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_P1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_P1ActionPerformed

    private void clear_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_pActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_clear_pActionPerformed

    private void tambah_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_pActionPerformed
    // TODO add your handling code here:
    try {
        String namaProduk = nama_p.getText().trim();
        String qty = qty_p.getText().trim();

        if (namaProduk.isEmpty() || qty.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua data!");
            return;
        }

        int qtyInt = Integer.parseInt(qty);
        if (qtyInt <= 0) {
            JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari nol.");
            return;
        }

        // Cek stok produk berdasarkan nama produk
        int stokProduk = getStokProdukByNama(namaProduk); // Fungsi untuk mendapatkan stok produk dari database berdasarkan nama

        // Pengecekan stok produk
        if (qtyInt > stokProduk) {
            JOptionPane.showMessageDialog(null, "Stok tidak mencukupi! Stok yang tersedia: " + stokProduk);
            return;
        }

        int subtotal = hargaAsli * qtyInt;

        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        boolean produkDitemukan = false;

        // Cek apakah produk sudah ada di dalam tabel
        for (int i = 0; i < model.getRowCount(); i++) {
            Object namaProdukTabelObj = model.getValueAt(i, 1); // Nama produk ada di kolom ke-1
            String namaProdukTabel = (namaProdukTabelObj != null) ? namaProdukTabelObj.toString() : ""; 

            if (namaProdukTabel.equals(namaProduk)) {
                // Jika produk ditemukan, update qty dan subtotal
                int qtyTabel = (int) model.getValueAt(i, 3); 
                int qtyBaru = qtyTabel + qtyInt;
                int subtotalBaru = hargaAsli * qtyBaru; 

                model.setValueAt(qtyBaru, i, 3);
                model.setValueAt("Rp " + String.format("%,d", subtotalBaru), i, 4);
                produkDitemukan = true;
                break;
            }
        }

        // Jika produk tidak ditemukan, tambahkan produk baru ke tabel
        if (!produkDitemukan) {
            model.insertRow(0, new Object[]{
                "KodeProduk",  // Anda bisa menambahkan kode produk di kolom tersembunyi jika dibutuhkan
                namaProduk,
                "Rp " + String.format("%,d", hargaAsli),
                qtyInt,
                "Rp " + String.format("%,d", subtotal)
            });
        }

        reset(); // Fungsi untuk mereset input form
        TotalHarga(); // Fungsi untuk menghitung total harga

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Format input salah. Harap masukkan angka pada jumlah.");
    }

    
    }//GEN-LAST:event_tambah_pActionPerformed

    private void bayar_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayar_transaksiActionPerformed
       
    }//GEN-LAST:event_bayar_transaksiActionPerformed

    private void bayar_transaksiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayar_transaksiKeyReleased
         String input = bayar_transaksi.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");

    try {
        if (!input.isEmpty()) {
            int angka = Integer.parseInt(input); 
            bayar_transaksi.setText("Rp " + String.format("%,d", angka)); 
        } else {
            bayar_transaksi.setText("Rp 0");
        }
    } catch (NumberFormatException e) {
        bayar_transaksi.setText("Rp 0");
    }

    cekDanHitungKembalian();
    }//GEN-LAST:event_bayar_transaksiKeyReleased

    private void bayar_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayar_pActionPerformed
    try {
    String idTransaksi = no_transaksi.getText(); 
    String tglTransaksi = tgl.getText(); 
    String totalHargaText = total_P2.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
    String dibayarText = bayar_transaksi.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
    String kembalianText = kembalian_p.getText().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");

    int totalHarga = Integer.parseInt(totalHargaText);
    int dibayar = Integer.parseInt(dibayarText);
    int kembalian = Integer.parseInt(kembalianText);

    String idUser = Login.activeUserId;

    String sqlTransaksi = "INSERT INTO transaksi (id_transaksi, tgl_transaksi, total_harga, dibayar, kembalian, id_user) VALUES (?, ?, ?, ?, ?, ?)";
    pst = con.prepareStatement(sqlTransaksi);
    pst.setString(1, idTransaksi); 
    pst.setString(2, tglTransaksi);
    pst.setInt(3, totalHarga);
    pst.setInt(4, dibayar);
    pst.setInt(5, kembalian);
    pst.setString(6, idUser);
    pst.executeUpdate();

    idDetail();
    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
    String sqlDetail = "INSERT INTO detail_transaksi (id_detail, id_transaksi, kode_produk, harga, qty, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
    pst = con.prepareStatement(sqlDetail);

    for (int i = 0; i < model.getRowCount(); i++) {
        String idDetail = generateIDDetail(); 
        String kodeProduk = model.getValueAt(i, 0).toString();
        String hargaText = model.getValueAt(i, 2).toString().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
        String qtyText = model.getValueAt(i, 3).toString();
        String subtotalText = model.getValueAt(i, 4).toString().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");

        int harga = Integer.parseInt(hargaText);
        int qty = Integer.parseInt(qtyText);
        int subtotal = Integer.parseInt(subtotalText);

        pst.setString(1, idDetail); 
        pst.setString(2, idTransaksi); 
        pst.setString(3, kodeProduk);
        pst.setInt(4, harga);
        pst.setInt(5, qty);
        pst.setInt(6, subtotal);
        pst.addBatch(); 
    }

    pst.executeBatch(); 

    JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
   
    resetTransaksi();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan transaksi: " + e.getMessage());
    }

    }//GEN-LAST:event_bayar_pActionPerformed

    private void ubah_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_pActionPerformed
    try {
        // Ambil data dari text field
        String qtyText = qty_p.getText();
        int qtyBaru = Integer.parseInt(qtyText);
        int harga = Integer.parseInt(harga_s.getText());
        int totalHargaBaru = harga * qtyBaru;

        // Ambil ID transaksi dari komponen GUI
        String idTransaksi = no_transaksi.getText();

        // Update data di tabel database
        String sqlUpdate = "UPDATE detail_transaksi SET qty = ?, total_harga = ? WHERE id_transaksi = ? AND kode_produk = ?";
        pst = con.prepareStatement(sqlUpdate);
        pst.setInt(1, qtyBaru);
        pst.setInt(2, totalHargaBaru);
        pst.setString(3, idTransaksi);
        pst.setString(4, kodeProduk);
        pst.executeUpdate();

        // Update data di tabel GUI
        int selectedRow = tabel_transaksi.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
            model.setValueAt(qtyBaru, selectedRow, 3); // Update qty
            model.setValueAt(totalHargaBaru, selectedRow, 4); // Update total harga
        }

        // Perbarui total transaksi di form
        int totalBaru = 0;
        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            totalBaru += Integer.parseInt(model.getValueAt(i, 4).toString());
        }
        total_P2.setText("Rp " + String.format("%,d", totalBaru).replace(',', '.'));
        reset();
        JOptionPane.showMessageDialog(null, "Sukses!");
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input qty tidak valid: " + e.getMessage());
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui qty: " + e.getMessage());
    }

    }//GEN-LAST:event_ubah_pActionPerformed

    private void hapus_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_pActionPerformed
        // TODO add your handling code here                                       
    try {
        int selectedRow = tabel_transaksi.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih baris yang ingin dihapus.");
            return;
        }
        
        // Hapus data dari tabel GUI
        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        model.removeRow(selectedRow);

        // Update total transaksi setelah penghapusan
        int totalBaru = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            totalBaru += Integer.parseInt(model.getValueAt(i, 4).toString());
        }
        total_P2.setText("Rp " + String.format("%,d", totalBaru).replace(',', '.'));
        
        reset();
        JOptionPane.showMessageDialog(null, "Sukses!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    }

    }//GEN-LAST:event_hapus_pActionPerformed

    private void tabel_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transaksiMouseClicked
        // TODO add your handling code here:
        
        tambah_p.setEnabled(false);
        ubah_p.setEnabled(true);
        hapus_p.setEnabled(true);
        
        int selectedRow = tabel_transaksi.getSelectedRow();
        if (selectedRow != -1) {
        // Ambil data dari tabel
        String namaProduk = tabel_transaksi.getValueAt(selectedRow, 1).toString();
        String harga = tabel_transaksi.getValueAt(selectedRow, 2).toString().replace("Rp ", "").replace(",", "").replaceAll("[^\\d]", "");
        String qty = tabel_transaksi.getValueAt(selectedRow, 3).toString();

        // Tampilkan data di text field
        nama_p.setText(namaProduk);
        harga_s.setText(harga);
        qty_p.setText(qty);
    }
    }//GEN-LAST:event_tabel_transaksiMouseClicked

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
        DashKasir nw_dashkasir = new DashKasir();
        nw_dashkasir.setVisible(true);
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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayar_p;
    private javax.swing.JTextField bayar_transaksi;
    private javax.swing.JButton clear_p;
    private javax.swing.JButton hapus_p;
    private javax.swing.JTextField harga_s;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kembali;
    private javax.swing.JTextField kembalian_p;
    private javax.swing.JList<String> listProduk;
    private javax.swing.JTextField nama_p;
    private javax.swing.JTextField no_transaksi;
    private javax.swing.JTextField qty_p;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JButton tambah_p;
    private javax.swing.JFormattedTextField tgl;
    private javax.swing.JTextField total_P1;
    private javax.swing.JTextField total_P2;
    private javax.swing.JButton ubah_p;
    // End of variables declaration//GEN-END:variables
}
