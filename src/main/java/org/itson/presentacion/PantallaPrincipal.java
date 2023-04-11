/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import javax.persistence.EntityManager;
import org.itson.interfaces.IConexionBD;

/**
 *
 * @author Oscar
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;
    
    /**
     * Creates new form PantallaPrincipal
     * @param MANEJEADOR_CONEXIONES
     */
    public PantallaPrincipal(IConexionBD MANEJEADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJEADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnTramitarPlacas = new javax.swing.JButton();
        btnTramitarLicencias = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnTramitarPlacas1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agencia De Transportes");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        lblTitulo.setText("Agencia De Transportes");

        btnTramitarPlacas.setText("Tramitar Placa Auto Usado");
        btnTramitarPlacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTramitarPlacasActionPerformed(evt);
            }
        });

        btnTramitarLicencias.setText("Tramitar licencias");
        btnTramitarLicencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTramitarLicenciasActionPerformed(evt);
            }
        });

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");

        jButton5.setText("Agregar personas");

        btnTramitarPlacas1.setText("Tramitar Placa Auto Nuevo");
        btnTramitarPlacas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTramitarPlacas1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTitulo)
                    .addComponent(btnTramitarLicencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTramitarPlacas1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTramitarPlacas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(btnTramitarLicencias, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTramitarPlacas1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTramitarPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTramitarPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTramitarPlacasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTramitarPlacasActionPerformed

    private void btnTramitarLicenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTramitarLicenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTramitarLicenciasActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnTramitarPlacas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTramitarPlacas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTramitarPlacas1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTramitarLicencias;
    private javax.swing.JButton btnTramitarPlacas;
    private javax.swing.JButton btnTramitarPlacas1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}