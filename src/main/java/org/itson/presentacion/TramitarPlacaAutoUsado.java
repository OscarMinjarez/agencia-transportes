/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.itson.dominio.Automovil;
import org.itson.dominio.Licencia;
import org.itson.dominio.Pago;
import org.itson.dominio.Persona;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dominio.Vehiculo;
import org.itson.dto.PlacasDTO;
import org.itson.implementaciones.PagosDAO;
import org.itson.implementaciones.TramitesDAO;
import org.itson.implementaciones.VehiculosDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.interfaces.IPagosDAO;
import org.itson.interfaces.IVehiculosDAO;
import org.itson.interfaces.ITramitesDAO;
import org.itson.utils.CostosTramites;
import org.itson.utils.ManejadorFechas;
import org.itson.utils.ManejadorPlacas;
import org.itson.utils.Validaciones;

/**
 *
 * @author Oscar
 */
public class TramitarPlacaAutoUsado extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    
    private Persona persona;
    private Vehiculo vehiculo;
    private final IVehiculosDAO vehiculosDAO;
    private final ITramitesDAO tramitesDAO;
    private final IPagosDAO pagosDAO;
    
    /**
     * Creates new form AutoNuevo
     * @param MANEJADOR_CONEXIONES
     */
    public TramitarPlacaAutoUsado(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        this.vehiculosDAO = new VehiculosDAO(this.MANEJADOR_CONEXIONES);
        this.tramitesDAO = new TramitesDAO(this.MANEJADOR_CONEXIONES);
        this.pagosDAO = new PagosDAO(this.MANEJADOR_CONEXIONES);
        initComponents();
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
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
    
    public void comprobarMayorDeEdad() {
        if (Integer.parseInt(Validaciones.calcularEdad((GregorianCalendar) this.persona.getFechaNacimiento())) >= 18) {
            this.verificarLicenciaExistente();
        } else {
            this.mostrarMensajeDeError("Seleccione a alguien mayor de edad", "Persona menor de edad");
            this.btnBuscarAuto.setEnabled(false);
            this.btnRegistrarAutoUsado.setEnabled(false);
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
        int opcion = JOptionPane.showConfirmDialog(this, "Desea registrar la placa para un auto usado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.insertarPlacaAutoUsado();
            this.txtPlaca.setText("");
            this.quitarDatosAutomovil();
        }
    }
    
    public void verificarLicenciaExistente() {
        Licencia licencia = (Licencia) tramitesDAO.obtenerLicenciaPersona(this.persona);
        
        if (licencia != null) {
            if (ManejadorFechas.fechaActualEntre(licencia.getFechaEmision(), licencia.getFechaExpiracion())) {
                this.lblCantidad.setText(CostosTramites.COSTO_PLACA_AUTO_USADO.toString());
                this.btnBuscarAuto.setEnabled(true);
                this.txtPlaca.setEnabled(true);
            }
        } else {
            this.mostrarMensajeDeError("La persona seleccionada no tiene licencia o expiró.", "Sin licencia");
            this.lblCantidad.setText("");
            this.btnBuscarAuto.setEnabled(false);
            this.txtPlaca.setEnabled(false);
            this.btnRegistrarAutoUsado.setEnabled(false);
        }
    }
    
    public Automovil extraerDatosAutomovil() {
        if (!ManejadorPlacas.validarPlaca(this.txtPlaca.getText()) && !Validaciones.campoVacio(this.txtPlaca.getText())) {
            this.mostrarMensajeDeError("El formato de la placa no es válida\nFormato válido: XXX-000", "Formato inválido");
            return null;
        }
        
        List<Placa> placa = this.tramitesDAO.buscar(new PlacasDTO(null, null, this.txtPlaca.getText(), null, null));
        
        if (placa == null || placa.size() <= 0) {
            this.mostrarMensajeDeError("No existe un automovil con la placa dada.", "Error");
            return null;
        } else if (!placa.get(0).getEsActiva()){
            this.mostrarMensajeDeError("La placa buscada ya no está activa.", "Error");
            return null;
        } else {
            System.out.println(placa.get(0).getVehiculo());
            return (Automovil) placa.get(0).getVehiculo();
        }
    }
    
    public void mostrarDatosAutomovil(Automovil automovil) {
        if (this.vehiculo != null) {
            this.lblMarcaVehiculo.setText(automovil.getMarca());
            this.lblModeloVehiculo.setText(automovil.getModelo());
            this.lblLineaVehiculo.setText(automovil.getLinea());
            this.lblColorVehiculo.setText(automovil.getColor());
            this.btnRegistrarAutoUsado.setEnabled(true);
        } else {
            this.quitarDatosAutomovil();
            this.btnRegistrarAutoUsado.setEnabled(false);
        }
    }
    
    public void quitarDatosAutomovil() {
        this.lblMarcaVehiculo.setText("");
        this.lblColorVehiculo.setText("");
        this.lblModeloVehiculo.setText("");
        this.lblLineaVehiculo.setText("");
    }
    
    public void desactivarPlaca() {
        List<Placa> placa = this.tramitesDAO.buscar(new PlacasDTO(null, null, this.txtPlaca.getText(), null, null));
        placa.get(0).setEsActiva(false);
        placa.get(0).setFechaRecepcion(ManejadorFechas.obtenerFechaActual());
        this.tramitesDAO.actualizar(placa.get(0));
    }
    
    public void insertarPlacaAutoUsado() {      
        if (this.extraerDatosAutomovil() == null) {
            this.mostrarMensajeDeError("No se pudo registrar la placa.", "Error");
        } else {
            this.desactivarPlaca();
            Placa placa = new Placa(null, true, this.vehiculo, ManejadorFechas.obtenerFechaActual(), CostosTramites.COSTO_PLACA_AUTO_NUEVO, this.persona);
            Tramite tramite = this.tramitesDAO.insertar(placa);
            this.pagosDAO.insertar(new Pago(ManejadorFechas.obtenerFechaActual(), CostosTramites.COSTO_PLACA_AUTO_USADO, tramite));
            
            this.mostrarDatosCarroRegistrado(placa.getTextoPlaca());
            this.lblTextoPlaca.setText(placa.getTextoPlaca());
        }
    }
    
    public void mostrarDatosCarroRegistrado(String textoPlaca) {
        JOptionPane.showMessageDialog(this, "Se ha registrado correctamente la placa para el automovil con los siguientes datos:"
                + "\nSerie: " + this.vehiculo.getSerie()
                + "\nMarca: " + this.vehiculo.getMarca()
                + "\nModelo: " + this.vehiculo.getModelo()
                + "\nLínea: " + this.vehiculo.getLinea()
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
        lblModelo = new javax.swing.JLabel();
        lblLinea = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblMarcaVehiculo = new javax.swing.JLabel();
        lblModeloVehiculo = new javax.swing.JLabel();
        lblLineaVehiculo = new javax.swing.JLabel();
        lblColorVehiculo = new javax.swing.JLabel();
        lblInformacionAutoUsado = new javax.swing.JLabel();
        lblInstruccion = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnRegistrarAutoUsado = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblBuscarAuto = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        btnBuscarAuto = new javax.swing.JButton();
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
        lblTramitarPlacaAutoNuevo.setText("Tramitar Placa Auto Usado");

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

        lblModelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblModelo.setText("Modelo:");

        lblLinea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLinea.setText("Línea:");

        lblColor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColor.setText("Color:");

        javax.swing.GroupLayout paneInfoAutoLayout = new javax.swing.GroupLayout(paneInfoAuto);
        paneInfoAuto.setLayout(paneInfoAutoLayout);
        paneInfoAutoLayout.setHorizontalGroup(
            paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblColorVehiculo))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblLinea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLineaVehiculo))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarcaVehiculo))
                    .addGroup(paneInfoAutoLayout.createSequentialGroup()
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblModeloVehiculo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneInfoAutoLayout.setVerticalGroup(
            paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneInfoAutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(lblMarcaVehiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModelo)
                    .addComponent(lblModeloVehiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLinea)
                    .addComponent(lblLineaVehiculo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneInfoAutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(lblColorVehiculo))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        lblInformacionAutoUsado.setText("Información auto usado");

        lblInstruccion.setText("Selecciona a una persona con licencia");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotal.setText("Total:");

        btnRegistrarAutoUsado.setText("Registrar automovil");
        btnRegistrarAutoUsado.setEnabled(false);
        btnRegistrarAutoUsado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAutoUsadoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblBuscarAuto.setText("Buscar auto por placa:");

        txtPlaca.setEnabled(false);

        btnBuscarAuto.setText("Buscar auto");
        btnBuscarAuto.setEnabled(false);
        btnBuscarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAutoActionPerformed(evt);
            }
        });

        lblPlaca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPlaca.setText("Placa registrada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTramitarPlacaAutoNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneInfoAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneInfoPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscarAuto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlaca))
                    .addComponent(btnBuscarAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTextoPlaca))
                    .addComponent(lblInformacionAutoUsado)
                    .addComponent(lblInstruccion)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(4, 4, 4)
                        .addComponent(lblCantidad))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrarAutoUsado)))
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
                .addGap(18, 18, 18)
                .addComponent(lblInformacionAutoUsado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarAuto)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneInfoAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)
                    .addComponent(lblTextoPlaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarAutoUsado)
                    .addComponent(btnCancelar))
                .addContainerGap())
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

    private void btnBuscarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAutoActionPerformed
        this.vehiculo = this.extraerDatosAutomovil();
        this.mostrarDatosAutomovil((Automovil) this.vehiculo);
    }//GEN-LAST:event_btnBuscarAutoActionPerformed

    private void btnRegistrarAutoUsadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAutoUsadoActionPerformed
        this.mostrarVentanaDeConfirmacion();
    }//GEN-LAST:event_btnRegistrarAutoUsadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarAuto;
    private javax.swing.JButton btnBuscarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarAutoUsado;
    private javax.swing.JLabel lblBuscarAuto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblColorVehiculo;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEdadPersona;
    private javax.swing.JLabel lblEsDiscapacitado;
    private javax.swing.JLabel lblEsDiscapacitadoPersona;
    private javax.swing.JLabel lblInformacionAutoUsado;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblLineaVehiculo;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMarcaVehiculo;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblModeloVehiculo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombrePersona;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblRfcPersona;
    private javax.swing.JLabel lblTextoPlaca;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTramitarPlacaAutoNuevo;
    private javax.swing.JPanel paneInfoAuto;
    private javax.swing.JPanel paneInfoPersona;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
