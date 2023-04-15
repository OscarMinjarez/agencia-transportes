/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.itson.interfaces.IConexionBD;
import org.itson.utils.Validaciones;
import org.itson.utils.ManejadorRFC;
import org.itson.dto.PersonasDTO;
import org.itson.implementaciones.PersonasDAO;
import org.itson.interfaces.IPersonasDAO;
import java.util.List;
import org.itson.dominio.Persona;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.util.GregorianCalendar;

/**
 *
 * @author Oscar
 */
public class BuscarPersona extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    private final TramitarLicencia pantallaTramitarLicencia;
    private final TramitarPlacaAutoNuevo pantallaTramitarPlacaAutoNuevo;
    private final TramitarPlacaAutoUsado pantallaTramitarPlacaAutoUsado;
    
    private final IPersonasDAO personasDAO;
    
    /**
     * Creates new form BuscarPersona
     * @param MANEJADOR_CONEXIONES
     * @param pantallaTramitarLicencia
     */
    public BuscarPersona(IConexionBD MANEJADOR_CONEXIONES, TramitarLicencia pantallaTramitarLicencia) {
        this.pantallaTramitarPlacaAutoNuevo = null;
        this.pantallaTramitarPlacaAutoUsado = null;
        this.pantallaTramitarLicencia = pantallaTramitarLicencia;
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        
        this.personasDAO = new PersonasDAO(this.MANEJADOR_CONEXIONES);
        
        initComponents();
    }
    
    public BuscarPersona(IConexionBD MANEJADOR_CONEXIONES, TramitarPlacaAutoNuevo pantallaTramitarPlacaAutoNuevo) {
        this.pantallaTramitarLicencia = null;
        this.pantallaTramitarPlacaAutoUsado = null;
        this.pantallaTramitarPlacaAutoNuevo = pantallaTramitarPlacaAutoNuevo;
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        
        this.personasDAO = new PersonasDAO(this.MANEJADOR_CONEXIONES);
        
        initComponents();
    }
    
    public BuscarPersona(IConexionBD MANEJADOR_CONEXIONES, TramitarPlacaAutoUsado pantallaTramitarPlacaAutoUsado) {
        this.pantallaTramitarLicencia = null;
        this.pantallaTramitarPlacaAutoNuevo = null;
        this.pantallaTramitarPlacaAutoUsado = pantallaTramitarPlacaAutoUsado;
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        
        this.personasDAO = new PersonasDAO(this.MANEJADOR_CONEXIONES);
        
        initComponents();
    }
    
    public void mostrarMensajeDeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarPantallaAnterior() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres volver a la pantalla anterior?", "Volver", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            if (this.pantallaTramitarPlacaAutoNuevo != null) {
                this.pantallaTramitarPlacaAutoNuevo.setEnabled(true);
            }
            
            if (this.pantallaTramitarLicencia != null) {
                this.pantallaTramitarLicencia.setEnabled(true);
            }
            
            this.dispose();
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }
    
    public PersonasDTO extraerDatosPersona() {        
        if (!Validaciones.comprobarFormatoNombre(this.txtNombre.getText()) && !Validaciones.campoVacio(this.txtNombre.getText())) {
            this.mostrarMensajeDeError("El formato del nombre no es válido", "Formato inválido");
            return null;
        }
        
        if (!Validaciones.comprobarFormatoApellido(this.txtApellidoPaterno.getText()) && !Validaciones.campoVacio(this.txtApellidoPaterno.getText())){
            this.mostrarMensajeDeError("El formato del apellido paterno no es válido", "Formato inválido");
            return null;
        }
        
        if (!Validaciones.comprobarFormatoApellido(this.txtApellidoMaterno.getText()) && !Validaciones.campoVacio(this.txtApellidoMaterno.getText())) {
            this.mostrarMensajeDeError("El formato del apellido materno no es válido", "Formato inválido");
            return null;
        }
        
        if (!Validaciones.comprobarFormatoAnho(this.txtAnhoNacimiento.getText()) && !Validaciones.campoVacio(this.txtAnhoNacimiento.getText())) {
            this.mostrarMensajeDeError("El formato del año de nacimiento no es válido\nSólo se permiten 4 dígitos numéricos.", "Formato inválido");
            return null;
        }
        
        if (!ManejadorRFC.validarRFC(this.txtRfc.getText()) && !Validaciones.campoVacio(this.txtRfc.getText())) {
            this.mostrarMensajeDeError("El formato del RFC no es válido\nSólo se permiten 13 caracteres con el siguiente formato:\n\tXXXX000000XX0", "Formato inválido");
            return null;
        }
        
        if (Validaciones.campoVacio(this.txtNombre.getText()) &&
                Validaciones.campoVacio(this.txtApellidoPaterno.getText()) &&
                Validaciones.campoVacio(this.txtApellidoMaterno.getText()) &&
                Validaciones.campoVacio(this.txtAnhoNacimiento.getText()) &&
                Validaciones.campoVacio(this.txtRfc.getText())) {
            
            this.mostrarMensajeDeError("Debe llenar al menos un campo", "Campos vacíos");

            return null;
        } else {
            String nombre = !Validaciones.campoVacio(this.txtNombre.getText()) ? this.txtNombre.getText() : null;
            String apellidoPaterno = !Validaciones.campoVacio(this.txtApellidoPaterno.getText()) ? this.txtApellidoPaterno.getText() : null;
            String apellidoMaterno = !Validaciones.campoVacio(this.txtApellidoMaterno.getText()) ? this.txtApellidoMaterno.getText() : null;
            Integer anhoNacimiento = !Validaciones.campoVacio(this.txtAnhoNacimiento.getText()) ? Integer.valueOf(this.txtAnhoNacimiento.getText()) : null;
            String rfc = !Validaciones.campoVacio(this.txtRfc.getText()) ? this.txtRfc.getText() : null;
            
            return new PersonasDTO(nombre, apellidoPaterno, apellidoMaterno, anhoNacimiento, rfc);
        }
    }
    
    public List<Persona> listarPersonasPorParametros(PersonasDTO personaPorParametros) {
        if (personaPorParametros == null) {
            return null;
        }
        
        return this.personasDAO.buscar(personaPorParametros);
    }
    
    public void mostrarPersonasEnLaTabla(List<Persona> listaPersonas) {
        if (listaPersonas == null) {
            this.limpiarTabla(this.tablePersonas);
        } else if (listaPersonas.isEmpty()) {
            this.mostrarMensajeDeError("No hay ningún registro que concuerde con los parámetros.", "Sin registros");
            this.limpiarTabla(this.tablePersonas);
        } else {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablePersonas.getModel();
            modeloTabla.setNumRows(0);

            for (Persona persona : listaPersonas) {
                Object[] fila = {
                    persona.getNombres(),
                    persona.getApellidoPaterno(),
                    persona.getApellidoMaterno(),
                    Validaciones.formatearFecha((GregorianCalendar) persona.getFechaNacimiento()),
                    persona.getEsDiscapacitado() ? "Sí" : "No",
                    persona.getRfc()
                };

                modeloTabla.addRow(fila);
            }
        }
    }
    
    public void limpiarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    public void obtenerValorDeLaTabla() {
        int filaSeleccionada = this.tablePersonas.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) this.tablePersonas.getModel();
        
        String rfcPersona = (String) model.getValueAt(filaSeleccionada, 5);
        
        List<Persona> lista = this.personasDAO.buscar(new PersonasDTO(null, null, null, null, rfcPersona));
        
        if (this.pantallaTramitarLicencia != null) {
            this.pantallaTramitarLicencia.setPersona(lista.get(0));
        }
        
        if (this.pantallaTramitarPlacaAutoNuevo != null) {
            this.pantallaTramitarPlacaAutoNuevo.setPersona(lista.get(0));
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

        lblBuscarPersona = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        txtRfc = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        scrollPanePersonas = new javax.swing.JScrollPane();
        tablePersonas = new javax.swing.JTable();
        lblAnhoNacimiento = new javax.swing.JLabel();
        txtAnhoNacimiento = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Persona");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblBuscarPersona.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        lblBuscarPersona.setText("Buscar Persona");

        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setText("Apellido paterno:");

        lblApellidoMaterno.setText("Apellido Materno:");

        lblRfc.setText("RFC:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablePersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido paterno", "Apellido materno", "Fecha de nacimiento", "Discapacitado", "RFC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePersonasMouseClicked(evt);
            }
        });
        scrollPanePersonas.setViewportView(tablePersonas);

        lblAnhoNacimiento.setText("Año de nacimiento:");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setEnabled(false);
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionar))
                            .addComponent(lblBuscarPersona)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRfc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRfc))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblApellidoMaterno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoMaterno))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblApellidoPaterno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoPaterno))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAnhoNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnhoNacimiento)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPanePersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscarPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellidoPaterno)
                            .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellidoMaterno)
                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnhoNacimiento)
                            .addComponent(txtAnhoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRfc)
                            .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar))
                    .addComponent(scrollPanePersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaAnterior();
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.mostrarPantallaAnterior();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.mostrarPersonasEnLaTabla(this.listarPersonasPorParametros(this.extraerDatosPersona()));
        this.btnSeleccionar.setEnabled(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seleccionar a este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.obtenerValorDeLaTabla();
            
            if (this.pantallaTramitarPlacaAutoNuevo != null) {
                this.pantallaTramitarPlacaAutoNuevo.setEnabled(true);
                this.pantallaTramitarPlacaAutoNuevo.comprobarMayorDeEdad();
                this.pantallaTramitarPlacaAutoNuevo.mostrarDatosPersona();
            }
            
            if (this.pantallaTramitarLicencia != null) {
                this.pantallaTramitarLicencia.setEnabled(true);
                this.pantallaTramitarLicencia.comprobarMayorDeEdad();
                this.pantallaTramitarLicencia.quitarSeleccionRadioButtons();
                this.pantallaTramitarLicencia.mostrarDatosPersona();
            }
            
            this.dispose();
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void tablePersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePersonasMouseClicked
        this.btnSeleccionar.setEnabled(true);
    }//GEN-LAST:event_tablePersonasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel lblAnhoNacimiento;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblBuscarPersona;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JScrollPane scrollPanePersonas;
    private javax.swing.JTable tablePersonas;
    private javax.swing.JTextField txtAnhoNacimiento;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRfc;
    // End of variables declaration//GEN-END:variables
}
