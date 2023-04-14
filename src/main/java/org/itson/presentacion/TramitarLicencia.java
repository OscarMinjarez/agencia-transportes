/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.itson.dominio.Licencia;
import org.itson.dominio.Pago;
import org.itson.dominio.Persona;
import org.itson.dominio.Tramite;
import org.itson.implementaciones.PagosDAO;
import org.itson.implementaciones.TramitesDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPagosDAO;
import org.itson.interfaces.ITramitesDAO;
import org.itson.utils.CostosTramites;
import org.itson.utils.ManejadorFechas;
import org.itson.utils.Validaciones;

/**
 *
 * @author Oscar
 */
public class TramitarLicencia extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private Persona persona;
    
    private final ITramitesDAO tramitesDAO;
    private final IPagosDAO pagosDAO;
    
    /**
     * Creates new form TramitarLicencia
     * @param MANEJEADOR_CONEXIONES
     */
    public TramitarLicencia(IConexionBD MANEJEADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJEADOR_CONEXIONES;
        this.persona = null;
        this.tramitesDAO = new TramitesDAO(this.MANEJADOR_CONEXIONES);
        this.pagosDAO = new PagosDAO(this.MANEJADOR_CONEXIONES);
        initComponents();
    }
    
    public void mostrarMensajeDeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
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
    
    public void comprobarVigenciaSeleccionada() {
        if (this.rdUnAnho.isSelected()) {
            this.lblCantidad.setText(this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_UN_ANHO_DISCAPACITADO.toString() : CostosTramites.COSTO_LICENCIA_UN_ANHO_NORMAL.toString());
        } else if (this.rdDosAnhos.isSelected()) {
            this.lblCantidad.setText(this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_DOS_ANHOS_DISCAPACITADO.toString() : CostosTramites.COSTO_LICENCIA_DOS_ANHOS_NORMAL.toString());
        } else if (this.rdTresAnhos.isSelected()) {
            this.lblCantidad.setText(this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_TRES_ANHOS_DISCAPACITO.toString() : CostosTramites.COSTO_LICENCIA_TRES_ANHOS_NORMAL.toString());
        } else {
            this.lblCantidad.setText("");
        }
    }
    
    public void actualizarEstadoRadioButtons(boolean x) {
        this.rdUnAnho.setEnabled(x);
        this.rdDosAnhos.setEnabled(x);
        this.rdTresAnhos.setEnabled(x);
    }
    
    public void quitarSeleccionRadioButtons() {
        this.rdUnAnho.setSelected(false);
        this.rdDosAnhos.setSelected(false);
        this.rdTresAnhos.setSelected(false);
        this.lblCantidad.setText("");
    }
    
    public void comprobarMayorDeEdad() {
        if (Integer.parseInt(Validaciones.calcularEdad((GregorianCalendar) this.persona.getFechaNacimiento())) >= 18) {
            this.actualizarEstadoRadioButtons(true);
            this.btnRegistrarLicencia.setEnabled(true);
        } else {
            this.mostrarMensajeDeError("Seleccione a alguien mayor de edad", "Persona menor de edad");
            this.actualizarEstadoRadioButtons(false);
            this.btnRegistrarLicencia.setEnabled(false);
            this.lblCantidad.setText("");
        }
    }
    
    public void limpiarCamposPersona() {
        this.lblNombrePersona.setText("");
        this.lblEdadPersona.setText("");
        this.lblEsDiscapacitadoPersona.setText("");
        this.lblRfcPersona.setText("");
        this.lblInstruccion.setText("Selecciona a una persona para ver su información");
    }
    
    public void insertarLicenciaEnBaseDeDatos() {
        Float monto = 0f;
        int vigencia = 0;
        
        if (this.rdUnAnho.isSelected()) {
            monto = this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_UN_ANHO_DISCAPACITADO : CostosTramites.COSTO_LICENCIA_UN_ANHO_NORMAL;
            vigencia = 1;
        } else if (this.rdDosAnhos.isSelected()) {
            monto = this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_DOS_ANHOS_DISCAPACITADO : CostosTramites.COSTO_LICENCIA_DOS_ANHOS_NORMAL;
            vigencia = 2;
        } else if (this.rdTresAnhos.isSelected()) {
            monto = this.persona.getEsDiscapacitado() ? CostosTramites.COSTO_LICENCIA_TRES_ANHOS_DISCAPACITO : CostosTramites.COSTO_LICENCIA_TRES_ANHOS_NORMAL;
            vigencia = 3;
        }
        
        this.pagosDAO.insertar(new Pago(ManejadorFechas.obtenerFechaActual(), monto, this.tramitesDAO.insertar(new Licencia(ManejadorFechas.obtenerFechaActual(), ManejadorFechas.sumarAnios(ManejadorFechas.obtenerFechaActual(), vigencia), monto, this.persona))));
    }
    
    public void confirmarInsertarLicencia() {
        int opcion1 = JOptionPane.showConfirmDialog(this, "¿Quieres registrar una nueva licencia para " + this.persona.getNombres() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (opcion1 == JOptionPane.YES_OPTION) {
            this.insertarLicenciaEnBaseDeDatos();
            this.limpiarCamposPersona();
            this.quitarSeleccionRadioButtons();
            this.actualizarEstadoRadioButtons(false);
            this.btnRegistrarLicencia.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Se ha registrado exitosamente una nueva licencia para:\n" + this.persona.getNombres(), "¡Éxito!", JOptionPane.DEFAULT_OPTION);
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
        btnRegresar = new javax.swing.JButton();

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

        rdUnAnho.setText("1 año");
        rdUnAnho.setEnabled(false);
        rdUnAnho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdUnAnhoActionPerformed(evt);
            }
        });

        rdDosAnhos.setText("2 años");
        rdDosAnhos.setEnabled(false);
        rdDosAnhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDosAnhosActionPerformed(evt);
            }
        });

        rdTresAnhos.setText("3 años");
        rdTresAnhos.setEnabled(false);
        rdTresAnhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTresAnhosActionPerformed(evt);
            }
        });

        btnRegistrarLicencia.setText("Registrar licencia");
        btnRegistrarLicencia.setEnabled(false);
        btnRegistrarLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarLicenciaActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
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
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
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
                    .addComponent(btnRegresar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void btnBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonaActionPerformed
        this.mostrarPantallaBuscarPersona();
    }//GEN-LAST:event_btnBuscarPersonaActionPerformed

    private void rdUnAnhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdUnAnhoActionPerformed
        this.rdDosAnhos.setSelected(false);
        this.rdTresAnhos.setSelected(false);
        this.comprobarVigenciaSeleccionada();
    }//GEN-LAST:event_rdUnAnhoActionPerformed

    private void rdDosAnhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDosAnhosActionPerformed
        this.rdUnAnho.setSelected(false);
        this.rdTresAnhos.setSelected(false);
        this.comprobarVigenciaSeleccionada();
    }//GEN-LAST:event_rdDosAnhosActionPerformed

    private void rdTresAnhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTresAnhosActionPerformed
        this.rdUnAnho.setSelected(false);
        this.rdDosAnhos.setSelected(false);
        this.comprobarVigenciaSeleccionada();
    }//GEN-LAST:event_rdTresAnhosActionPerformed

    private void btnRegistrarLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLicenciaActionPerformed
        this.confirmarInsertarLicencia();
    }//GEN-LAST:event_btnRegistrarLicenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnRegistrarLicencia;
    private javax.swing.JButton btnRegresar;
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
