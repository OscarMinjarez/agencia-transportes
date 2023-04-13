/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import org.itson.dominio.Persona;
import org.itson.interfaces.IConexionBD;
import org.itson.utils.Validaciones;

/**
 *
 * @author Oscar
 */
public class TramitarLicencia extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final EntityManager ENTITY_MANAGER;
    private Persona persona;
    
    /**
     * Creates new form TramitarLicencia
     * @param MANEJEADOR_CONEXIONES
     */
    public TramitarLicencia(IConexionBD MANEJEADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJEADOR_CONEXIONES;
        this.ENTITY_MANAGER = this.MANEJADOR_CONEXIONES.crearConexion();
        this.persona = null;
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
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void mostrarDatosPersona() {
        if (this.persona != null) {
            this.lblNombrePersona.setText(persona.getNombres() + " " + this.persona.getApellidoPaterno() + " " + this.persona.getApellidoMaterno());
            this.lblEdadPersona.setText(Validaciones.calcularEdad((GregorianCalendar) this.persona.getFechaNacimiento()));
            this.lblRfcPersona.setText(persona.getRfc());
            this.lblEsDiscapacitadoPersona.setText(this.persona.getEsDiscapacitado() ? "Sí" : "No");
            this.lblInstruccion.setText("");
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

        lblTramitarLicencia = new javax.swing.JLabel();
        btnBuscarPersona = new javax.swing.JButton();
        lblInstruccion = new javax.swing.JLabel();
        paneInfoPersona = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        lblNombrePersona = new javax.swing.JLabel();
        lblEsDiscapacitado = new javax.swing.JLabel();
        lblEsDiscapacitadoPersona = new javax.swing.JLabel();
        lblEdadPersona = new javax.swing.JLabel();
        lblRfcPersona = new javax.swing.JLabel();
        lblSeleccionarVigencia = new javax.swing.JLabel();
        lblTotalAPagar = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        rdUnAnho = new javax.swing.JRadioButton();
        rdDosAnhos = new javax.swing.JRadioButton();
        rdTresAnhos = new javax.swing.JRadioButton();
        btnRegistrarLicencia = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tramitar Licencia");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTramitarLicencia.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        lblTramitarLicencia.setText("Tramitar Licencia");

        btnBuscarPersona.setText("Buscar persona");
        btnBuscarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonaActionPerformed(evt);
            }
        });

        lblInstruccion.setText("Selecciona a una persona para ver su información");

        paneInfoPersona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        lblEdad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEdad.setText("Edad:");

        lblRfc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRfc.setText("RFC:");

        lblEsDiscapacitado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEsDiscapacitado.setText("Es discapacitado:");

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
                        .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                                .addComponent(lblEdad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEdadPersona)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRfc))
                            .addComponent(lblEsDiscapacitado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRfcPersona)
                            .addComponent(lblEsDiscapacitadoPersona))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneInfoPersonaLayout.setVerticalGroup(
            paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoPersonaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblNombrePersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEdad)
                    .addComponent(lblRfc)
                    .addComponent(lblEdadPersona)
                    .addComponent(lblRfcPersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneInfoPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEsDiscapacitado)
                    .addComponent(lblEsDiscapacitadoPersona))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSeleccionarVigencia.setText("Seleccionar vigencia");

        lblTotalAPagar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalAPagar.setText("Total a pagar:");

        lblCantidad.setText("NA");

        rdUnAnho.setText("1 año");

        rdDosAnhos.setText("2 años");

        rdTresAnhos.setText("3 años");

        btnRegistrarLicencia.setText("Registrar licencia");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSeleccionarVigencia)
                    .addComponent(lblTramitarLicencia)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotalAPagar)
                        .addGap(10, 10, 10)
                        .addComponent(lblCantidad))
                    .addComponent(lblInstruccion)
                    .addComponent(paneInfoPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdUnAnho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdDosAnhos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdTresAnhos))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarLicencia))
                    .addComponent(btnBuscarPersona))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTramitarLicencia)
                .addGap(10, 10, 10)
                .addComponent(btnBuscarPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInstruccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(lblSeleccionarVigencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdUnAnho)
                    .addComponent(rdDosAnhos)
                    .addComponent(rdTresAnhos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalAPagar)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarLicencia)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void btnBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonaActionPerformed
        this.mostrarPantallaBuscarPersona();
    }//GEN-LAST:event_btnBuscarPersonaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarLicencia;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEdadPersona;
    private javax.swing.JLabel lblEsDiscapacitado;
    private javax.swing.JLabel lblEsDiscapacitadoPersona;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombrePersona;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblRfcPersona;
    private javax.swing.JLabel lblSeleccionarVigencia;
    private javax.swing.JLabel lblTotalAPagar;
    private javax.swing.JLabel lblTramitarLicencia;
    private javax.swing.JPanel paneInfoPersona;
    private javax.swing.JRadioButton rdDosAnhos;
    private javax.swing.JRadioButton rdTresAnhos;
    private javax.swing.JRadioButton rdUnAnho;
    // End of variables declaration//GEN-END:variables
}
