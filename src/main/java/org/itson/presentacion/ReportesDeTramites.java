/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.presentacion;

import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.itson.dominio.Licencia;
import org.itson.dominio.Placa;
import org.itson.dominio.Tramite;
import org.itson.dto.PersonasDTO;
import org.itson.implementaciones.TramitesDAO;
import org.itson.interfaces.IConexionBD;
import org.itson.utils.ManejadorFechas;
import org.itson.utils.Reportes;
import org.itson.utils.Validaciones;

/**
 *
 * @author Oscar
 */
public class ReportesDeTramites extends javax.swing.JFrame {

    private final IConexionBD MANEJADOR_CONEXIONES;
    
    /**
     * Creates new form SeleccionarReporte
     * @param MANEJADOR_CONEXIONES
     */
    public ReportesDeTramites(IConexionBD MANEJADOR_CONEXIONES) {
        this.MANEJADOR_CONEXIONES = MANEJADOR_CONEXIONES;
        initComponents();
    }
    
    private void mostrarMensajeDeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarPantallaPrincipal() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres regresar a la pantalla principal?", "Salir", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            new PantallaPrincipal(MANEJADOR_CONEXIONES).setVisible(true);
            this.dispose();
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }
    
    private PersonasDTO validarDatosPersona() {
        if (!Validaciones.comprobarFormatoNombre(this.txtNombres.getText()) && !Validaciones.campoVacio(this.txtNombres.getText())) {
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
        
        if (Validaciones.campoVacio(this.txtNombres.getText()) &&
                Validaciones.campoVacio(this.txtApellidoPaterno.getText()) &&
                Validaciones.campoVacio(this.txtApellidoMaterno.getText()) &&
                this.calendarDesde.getDate() == null && this.calendarHasta.getDate() == null) {

            return null;
        } else {
            String nombre = !Validaciones.campoVacio(this.txtNombres.getText()) ? this.txtNombres.getText() : null;
            String apellidoPaterno = !Validaciones.campoVacio(this.txtApellidoPaterno.getText()) ? this.txtApellidoPaterno.getText() : null;
            String apellidoMaterno = !Validaciones.campoVacio(this.txtApellidoMaterno.getText()) ? this.txtApellidoMaterno.getText() : null;
            
            return new PersonasDTO(nombre, apellidoPaterno, apellidoMaterno, null, null);
        }
    }
    
    private void mostrarReportesConsultados() {
        Reportes reportes = new Reportes(this.MANEJADOR_CONEXIONES);
        List<Tramite> tramites = reportes.consultarReporte(
                this.validarDatosPersona(),
                ManejadorFechas.convertirLocalDateACalendar(this.calendarDesde.getDate()),
                ManejadorFechas.convertirLocalDateACalendar(this.calendarHasta.getDate()));
        
        if (this.validarDatosPersona() == null) {
            this.mostrarMensajeDeError("Favor de llenar algún campo.", "Error");
            this.limpiarTabla(this.tablaReportes);
        } else {
            if (tramites == null || tramites.size() <= 0 || tramites.isEmpty()) {
                this.mostrarMensajeDeError("No existen trámites con los parámetros dados.", "Error de trámites");
                this.limpiarTabla(this.tablaReportes);
            } else {
                this.mostrarTramites(tramites);
            }
        }
    }
    
    private void mostrarTramites(List<Tramite> tramites) {
        if (tramites == null) {
            this.limpiarTabla(this.tablaReportes);
        } else if (tramites.isEmpty()) {
            this.mostrarMensajeDeError("No hay trámites registrados", "Error");
            this.limpiarTabla(this.tablaReportes);
        } else {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaReportes.getModel();
            modeloTabla.setNumRows(0);

            for (Tramite tramite : tramites) {
                String tipo = "";
                
                if (tramite instanceof Licencia) {
                    tipo = "Licencia";
                } else if (tramite instanceof Placa) {
                    tipo = "Placa";
                }
                
                Object[] fila = {
                    tramite.getPersona().getNombres() + " " + tramite.getPersona().getApellidoPaterno() + " " + tramite.getPersona().getApellidoMaterno(),
                    tipo,
                    Validaciones.formatearFecha((GregorianCalendar) tramite.getFechaEmision()),
                    tramite.getMonto()
                };

                modeloTabla.addRow(fila);
            }
        }
    }
    
    private void limpiarTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReportes = new javax.swing.JTable();
        txtNombres = new javax.swing.JTextField();
        lblNombres = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        lblApellidoMaterno = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        calendarHasta = new com.github.lgooddatepicker.components.DatePicker();
        lblHasta = new javax.swing.JLabel();
        lblDesde = new javax.swing.JLabel();
        calendarDesde = new com.github.lgooddatepicker.components.DatePicker();
        btnFiltrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnMostrarTodosLosTramites = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Reporte de trámites");
        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N

        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre completo", "Tipo de trámite", "Fecha de expedición", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReportes);

        lblNombres.setText("Nombre(s):");

        lblApellidoPaterno.setText("Apellido paterno:");

        lblApellidoMaterno.setText("Apellido materno:");

        lblHasta.setText("Hasta:");

        lblDesde.setText("Desde:");

        btnFiltrar.setText("FiltrarResultados");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnMostrarTodosLosTramites.setText("Mostrar todos los trámites");
        btnMostrarTodosLosTramites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosLosTramitesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblDesde)
                                        .addGap(6, 6, 6)
                                        .addComponent(calendarDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNombres)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblHasta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(calendarHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblApellidoPaterno)
                                        .addGap(11, 11, 11)
                                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnFiltrar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(lblApellidoMaterno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnMostrarTodosLosTramites))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno)
                    .addComponent(lblApellidoMaterno)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDesde)
                    .addComponent(calendarDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendarHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHasta)
                    .addComponent(btnFiltrar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnMostrarTodosLosTramites))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        this.mostrarReportesConsultados();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnMostrarTodosLosTramitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodosLosTramitesActionPerformed
        this.mostrarTramites(new TramitesDAO(this.MANEJADOR_CONEXIONES).buscar());
    }//GEN-LAST:event_btnMostrarTodosLosTramitesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mostrarPantallaPrincipal();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrarTodosLosTramites;
    private javax.swing.JButton btnRegresar;
    private com.github.lgooddatepicker.components.DatePicker calendarDesde;
    private com.github.lgooddatepicker.components.DatePicker calendarHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JTable tablaReportes;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
