/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import org.itson.interfaces.IConexionBD;

/**
 *
 * @author Oscar
 */
public class TramitarPlacaAutoNuevo extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;
    
    /**
     * Creates new form AutoNuevo
     * @param MANEJEADOR_CONEXIONES
     */
    public TramitarPlacaAutoNuevo(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
        initComponents();
    }
    
    public void mostrarPantallaBuscarPersona() {
        new BuscarPersona(this.MANEJADOR_CONEXIONES, this).setVisible(true);
        this.setEnabled(false);
    }
    
    public void mostrarPantallaPrincipal() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres regresar a la pantalla principal?", "Salir", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            new PantallaPrincipal(MANEJADOR_CONEXIONES).setVisible(true);
            this.dispose();
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

        lblTramitarPlacaAutoNuevo = new javax.swing.JLabel();
        btnBuscarPersona = new javax.swing.JButton();
        paneInfoPersona = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        lblEsDiscapacitado = new javax.swing.JLabel();
        lblNombrePersona = new javax.swing.JLabel();
        lblEdadPersona = new javax.swing.JLabel();
        lblRfcPersona = new javax.swing.JLabel();
        lblEsDiscapacitadoPersona = new javax.swing.JLabel();
        paneInfoAuto = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        lblLinea = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtLinea = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        lblRegistrarAutoNuevo = new javax.swing.JLabel();
        lblInformacionPersona = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTotalCantidad = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Auto Nuevo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTramitarPlacaAutoNuevo.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        lblTramitarPlacaAutoNuevo.setText("Tramitar Placa Auto Nuevo");

        btnBuscarPersona.setText("Buscar persona");
        btnBuscarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonaActionPerformed(evt);
            }
        });

        paneInfoPersona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        lblEdad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEdad.setText("Edad:");

        lblRfc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRfc.setText("RFC:");

        lblEsDiscapacitado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEsDiscapacitado.setText("Es discapacitado:");

        lblNombrePersona.setText("NA");

        lblEdadPersona.setText("NA");

        lblRfcPersona.setText("NA");

        lblEsDiscapacitadoPersona.setText("NA");

        javax.swing.GroupLayout paneInfoPersonaLayout = new javax.swing.GroupLayout(paneInfoPersona);
        paneInfoPersona.setLayout(paneInfoPersonaLayout);
        paneInfoPersonaLayout.setHorizontalGroup(
            paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombrePersona))
                    .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                        .addComponent(lblEdad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEdadPersona))
                    .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                        .addComponent(lblRfc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRfcPersona))
                    .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                        .addComponent(lblEsDiscapacitado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEsDiscapacitadoPersona)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneInfoPersonaLayout.setVerticalGroup(
            paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblNombrePersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(lblEdadPersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRfc)
                    .addComponent(lblRfcPersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEsDiscapacitado)
                    .addComponent(lblEsDiscapacitadoPersona))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneInfoAuto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMarca.setText("Marca:");

        lblLinea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLinea.setText("Linea:");

        lblColor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColor.setText("Color:");

        lblModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblModelo.setText("Modelo:");

        javax.swing.GroupLayout paneInfoAutoLayout = new javax.swing.GroupLayout(paneInfoAuto);
        paneInfoAuto.setLayout(paneInfoAutoLayout);
        paneInfoAutoLayout.setHorizontalGroup(
            paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblLinea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLinea))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColor)))
                .addContainerGap())
        );
        paneInfoAutoLayout.setVerticalGroup(
            paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLinea)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRegistrarAutoNuevo.setText("Registrar auto nuevo");

        lblInformacionPersona.setText("Información de la persona");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("Total:");

        lblTotalCantidad.setText("NA");

        btnRegistrar.setText("Registrar");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInformacionPersona, javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTramitarPlacaAutoNuevo, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneInfoAuto, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarPersona, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneInfoPersona, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRegistrarAutoNuevo, javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalCantidad))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTramitarPlacaAutoNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInformacionPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegistrarAutoNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTotalCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCancelar))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonaActionPerformed
        this.mostrarPantallaBuscarPersona();
    }//GEN-LAST:event_btnBuscarPersonaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEdadPersona;
    private javax.swing.JLabel lblEsDiscapacitado;
    private javax.swing.JLabel lblEsDiscapacitadoPersona;
    private javax.swing.JLabel lblInformacionPersona;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombrePersona;
    private javax.swing.JLabel lblRegistrarAutoNuevo;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblRfcPersona;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalCantidad;
    private javax.swing.JLabel lblTramitarPlacaAutoNuevo;
    private javax.swing.JPanel paneInfoAuto;
    private javax.swing.JPanel paneInfoPersona;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
}
