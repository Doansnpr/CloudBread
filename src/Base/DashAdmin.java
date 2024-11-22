
package Base;

import javax.swing.JOptionPane;


public class DashAdmin extends javax.swing.JFrame {

 
    public DashAdmin() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        data_user = new javax.swing.JButton();
        data_produk = new javax.swing.JButton();
        laporan_p = new javax.swing.JButton();
        logout_a = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        data_user.setContentAreaFilled(false);
        data_user.setBorderPainted(false);
        data_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_userActionPerformed(evt);
            }
        });
        getContentPane().add(data_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 190, 50));

        data_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_produkActionPerformed(evt);
            }
        });
        data_produk.setContentAreaFilled(false);
        data_produk.setBorderPainted(false);
        getContentPane().add(data_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 190, 50));

        laporan_p.setContentAreaFilled(false);
        laporan_p.setBorderPainted(false);
        laporan_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporan_pActionPerformed(evt);
            }
        });
        getContentPane().add(laporan_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, 50));

        logout_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_aActionPerformed(evt);
            }
        });
        logout_a.setContentAreaFilled(false);
        logout_a.setBorderPainted(false);
        getContentPane().add(logout_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/DashAdm.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void data_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_produkActionPerformed
        // TODO add your handling code here:
        Produk nw_produk = new Produk();
        nw_produk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_data_produkActionPerformed

    private void data_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_userActionPerformed
        // TODO add your handling code here:
        User nw_user = new User();
        nw_user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_data_userActionPerformed

    private void laporan_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporan_pActionPerformed
        // TODO add your handling code here:
        Laporan nw_laporan = new Laporan();
        nw_laporan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_laporan_pActionPerformed

    private void logout_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_aActionPerformed
        // TODO add your handling code here:
        int ok=JOptionPane.showConfirmDialog(null, "Ingin logout?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok==0){
           Login Login = new Login();
            Login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logout_aActionPerformed


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
            java.util.logging.Logger.getLogger(DashAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton data_produk;
    private javax.swing.JButton data_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton laporan_p;
    private javax.swing.JButton logout_a;
    // End of variables declaration//GEN-END:variables
}
