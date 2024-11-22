
package Base;

import javax.swing.JOptionPane;

public class DashKasir extends javax.swing.JFrame {

   
    public DashKasir() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transaksi_p = new javax.swing.JButton();
        logout_k = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transaksi_p.setContentAreaFilled(false);
        transaksi_p.setBorderPainted(false);
        transaksi_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksi_pActionPerformed(evt);
            }
        });
        getContentPane().add(transaksi_p, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 190, 50));

        logout_k.setOpaque(true);
        logout_k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_kActionPerformed(evt);
            }
        });
        getContentPane().add(logout_k, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 670, 120, 40));
        logout_k.setContentAreaFilled(false);
        logout_k.setBorderPainted(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudAsset/DashKsr.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transaksi_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksi_pActionPerformed

        Transaksi nw_transaksi = new Transaksi();
        nw_transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_transaksi_pActionPerformed

    private void logout_kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_kActionPerformed
        // TODO add your handling code here:
        int ok=JOptionPane.showConfirmDialog(null, "Ingin logout?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok==0){
           Login Login = new Login();
            Login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logout_kActionPerformed

   
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
            java.util.logging.Logger.getLogger(DashKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout_k;
    private javax.swing.JButton transaksi_p;
    // End of variables declaration//GEN-END:variables
}
