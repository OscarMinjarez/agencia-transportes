/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.itson.dominio.Automovil;
import org.itson.dominio.Licencia;
import org.itson.dominio.Pago;
import org.itson.dominio.Persona;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dominio.Vehiculo;
import org.itson.implementaciones.PagosDAO;
import org.itson.implementaciones.TramitesDAO;
import org.itson.implementaciones.VehiculosDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPagosDAO;
import org.itson.interfaces.ITramitesDAO;
import org.itson.interfaces.IVehiculosDAO;
import org.itson.utils.Colores;
import org.itson.utils.CostosTramites;
import org.itson.utils.ManejadorFechas;
import org.itson.utils.Validaciones;

/**
 *
 * @author Oscar
 */
public class TramitarPlacaAutoNuevo extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    
    private ITramitesDAO tramitesDAO;
    private IPagosDAO pagosDAO;
    private IVehiculosDAO vehiculosDAO;
    private Persona persona;
    
    /**
     * Creates new form AutoNuevo
     * @param MANEJADOR_CONEXIONES
     */
    public TramitarPlacaAutoNuevo(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.tramitesDAO = new TramitesDAO(this.MANEJADOR_CONEXIONES);
        this.pagosDAO = new PagosDAO(this.MANEJADOR_CONEXIONES);
        this.vehiculosDAO = new VehiculosDAO(this.MANEJADOR_CONEXIONES);

        initComponents();
    }
    
    public void mostrarMensajeDeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
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
    
    public void comprobarMayorDeEdad() {
        if (Integer.parseInt(Validaciones.calcularEdad((GregorianCalendar) this.persona.getFechaNacimiento())) >= 18) {
            this.verificarLicenciaExistente();
        } else {
            this.mostrarMensajeDeError("Seleccione a alguien mayor de edad", "Persona menor de edad");
            this.actualizarEstadoDeTxtField(false);
            this.limpiarTxtFields();
            this.btnRegistrarAutoNuevo.setEnabled(false);
            this.lblCantidad.setText("");
        }
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
    
    public void mostrarVentanaDeConfirmacion() {
        int opcion = JOptionPane.showConfirmDialog(this, "Desea registrar la placa y el auto nuevo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.insertarPlacaAutoNuevo();
        }
    }
    
    public void verificarLicenciaExistente() {
        Licencia licencia = (Licencia) tramitesDAO.obtenerLicenciaPersona(this.persona);
        
        if (licencia != null) {
            if (ManejadorFechas.fechaActualEntre(licencia.getFechaEmision(), licencia.getFechaExpiracion())) {
                this.actualizarEstadoDeTxtField(true);
                this.lblCantidad.setText(CostosTramites.COSTO_PLACA_AUTO_NUEVO.toString());
                this.btnRegistrarAutoNuevo.setEnabled(true);
            }
        } else {
            this.mostrarMensajeDeError("La persona seleccionada no tiene licencia o expiró.", "Sin licencia");
            this.actualizarEstadoDeTxtField(false);
            this.lblCantidad.setText("");
            this.btnRegistrarAutoNuevo.setEnabled(false);
        }
    }
    
    public void actualizarEstadoDeTxtField(boolean x) {
        this.txtMarca.setEnabled(x);
        this.txtModelo.setEnabled(x);
        this.txtLinea.setEnabled(x);
        this.txtColor.setEnabled(x);
    }
    
    public void limpiarTxtFields() {
        this.txtMarca.setText("");
        this.txtModelo.setText("");
        this.txtLinea.setText("");
        this.txtColor.setText("");
    }
    
    public boolean validarColor(String color) {
        for (Colores c : Colores.values()) {
            if (c.name().equalsIgnoreCase(color)) {
                return true;
            }
        }
        return false;
    }
    
    public Automovil extraerDatosAutomovil() {
        if (!Validaciones.comprobarFormatoAutomovil(this.txtMarca.getText()) && !Validaciones.campoVacio(this.txtMarca.getText())) {
            this.mostrarMensajeDeError("El formato de la marca no es \nSólo se aceptan letras, números y/o el símbolo de \"-\"", "Formato invalido");
            return null;
        }
        
        if (!Validaciones.comprobarFormatoAutomovil(this.txtModelo.getText()) && !Validaciones.campoVacio(this.txtModelo.getText())) {
            this.mostrarMensajeDeError("El formato del modelo no es válido\nSólo se aceptan letras, números y/o el símbolo de \"-\"", "Formato invalido");
            return null;
        }
        
        if (!Validaciones.comprobarFormatoAutomovil(this.txtLinea.getText()) && !Validaciones.campoVacio(this.txtLinea.getText())) {
            this.mostrarMensajeDeError("El formato de la línea no es válido\nSólo se aceptan letras, números y/o el símbolo de \"-\"", "Formato invalido");
            return null;
        }
        
        if (!this.validarColor(this.txtColor.getText()) && !Validaciones.campoVacio(this.txtColor.getText())) {
            this.mostrarMensajeDeError("El formato del color no es válido\nOpciones disponibles: ROJO, AZUL, VERDE, NEGRO, BLANCO, PLATEADO, DORADO, AMARILLO, ROSA", "Formato invalido");
            return null;
        }
        
        if (Validaciones.campoVacio(this.txtMarca.getText()) ||
                Validaciones.campoVacio(this.txtModelo.getText()) ||
                Validaciones.campoVacio(this.txtLinea.getText()) ||
                Validaciones.campoVacio(this.txtColor.getText())) {
            
            this.mostrarMensajeDeError("Debe llenar al menos un campo", "Campos vacíos");

            return null;
        } else {
            return new Automovil(this.txtMarca.getText(), this.txtLinea.getText(), this.txtLinea.getText(), this.txtColor.getText());
        }
    }
    
    public void insertarPlacaAutoNuevo() {      
        if (this.extraerDatosAutomovil() == null) {
            this.mostrarMensajeDeError("No se pudo registrar el auto.", "Error");
        } else {
            Vehiculo vehiculo = vehiculosDAO.insertar(this.extraerDatosAutomovil());
            Placa placa = new Placa(null, true, vehiculo, ManejadorFechas.obtenerFechaActual(), CostosTramites.COSTO_PLACA_AUTO_NUEVO, this.persona);
            Tramite tramite = this.tramitesDAO.insertar(placa);
            this.pagosDAO.insertar(new Pago(ManejadorFechas.obtenerFechaActual(), CostosTramites.COSTO_PLACA_AUTO_NUEVO, tramite));
            
            this.mostrarDatosCarroRegistrado(placa.getTextoPlaca(), vehiculo.getSerie());
            this.limpiarTxtFields();
            this.lblTextoPlaca.setText(placa.getTextoPlaca());
        }
    }
    
    public void mostrarDatosCarroRegistrado(String textoPlaca, String serie) {
        JOptionPane.showMessageDialog(this, "Se ha registrado correctamente el automovil con los siguientes datos:"
                + "\nSerie: " + serie
                + "\nMarca: " + this.txtMarca.getText()
                + "\nModelo: " + this.txtModelo.getText()
                + "\nLínea: " + this.txtLinea.getText()
                + "\nPlaca: " + textoPlaca, "¡Éxito!", JOptionPane.DEFAULT_OPTION
        );
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
        txtModelo = new javax.swing.JTextField();
        txtLinea = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        lblRegistrarAutoNuevo = new javax.swing.JLabel();
        lblInstruccion = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnRegistrarAutoNuevo = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblPlaca = new javax.swing.JLabel();
        lblTextoPlaca = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();

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

        txtMarca.setEnabled(false);

        txtModelo.setEnabled(false);

        txtLinea.setEnabled(false);

        txtColor.setEnabled(false);

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
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColor))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblLinea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLinea)))
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
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLinea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblRegistrarAutoNuevo.setText("Registrar auto nuevo");

        lblInstruccion.setText("Seleccione una persona con licencia");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("Total:");

        btnRegistrarAutoNuevo.setText("Registrar placa");
        btnRegistrarAutoNuevo.setEnabled(false);
        btnRegistrarAutoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAutoNuevoActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblPlaca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPlaca.setText("Placa registrada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTextoPlaca))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidad))
                    .addComponent(lblTramitarPlacaAutoNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneInfoAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnBuscarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(paneInfoPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrarAutoNuevo)
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lblInstruccion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lblRegistrarAutoNuevo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTramitarPlacaAutoNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInstruccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegistrarAutoNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)
                    .addComponent(lblTextoPlaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarAutoNuevo)
                    .addComponent(btnRegresar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonaActionPerformed
        this.mostrarPantallaBuscarPersona();
    }//GEN-LAST:event_btnBuscarPersonaActionPerformed

    private void btnRegistrarAutoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAutoNuevoActionPerformed
        this.mostrarVentanaDeConfirmacion();
    }//GEN-LAST:event_btnRegistrarAutoNuevoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnRegistrarAutoNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEdadPersona;
    private javax.swing.JLabel lblEsDiscapacitado;
    private javax.swing.JLabel lblEsDiscapacitadoPersona;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombrePersona;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblRegistrarAutoNuevo;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblRfcPersona;
    private javax.swing.JLabel lblTextoPlaca;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTramitarPlacaAutoNuevo;
    private javax.swing.JPanel paneInfoAuto;
    private javax.swing.JPanel paneInfoPersona;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
}
