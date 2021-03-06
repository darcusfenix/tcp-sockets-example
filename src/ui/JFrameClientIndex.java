/*
 * The MIT License
 *
 * Copyright 2015 darcusfenix.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ui;

import bean.Archivo;
import cliente.Cliente;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author darcusfenix
 */
public class JFrameClientIndex extends javax.swing.JFrame {

    /**
     * Creates new form JFrameIndex
     */
    private List<Archivo> archivos;
    public JFrameClientIndex() {
        initComponents();
        setTextLOG("INFO: SE INICIÓ LA CONEXIÓN");
        archivos = new ArrayList<Archivo>();
    }
    public void setTextLOG(String text){
        String LOG = textAreaLogClient.getText() + "\n" + "["+(new Date())+"] " + text;
        textAreaLogClient.setText(LOG);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUploadFIles = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaLogClient = new javax.swing.JTextArea();
        labelLogClient = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableClient = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnEnviarArchivos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica 1 - Cliente");

        btnUploadFIles.setText("SELECCIONAR ARCHIVOS");
        btnUploadFIles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFIlesActionPerformed(evt);
            }
        });

        textAreaLogClient.setColumns(20);
        textAreaLogClient.setRows(5);
        jScrollPane1.setViewportView(textAreaLogClient);

        labelLogClient.setText("LOG:");

        tableClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NOMBRE", "TIPO", "TAMAÑO", "ESTADO", "PORCENTAJE"
            }
        ));
        jScrollPane2.setViewportView(tableClient);

        jLabel2.setText("ARCHIVOS A ENVIAR");

        btnEnviarArchivos.setText("ENVIAR ARCHIVOS");
        btnEnviarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarArchivosActionPerformed(evt);
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
                        .addComponent(labelLogClient)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUploadFIles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnviarArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(305, 305, 305)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUploadFIles, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnviarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelLogClient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadFIlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFIlesActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
      
        chooser.showOpenDialog(this);

        File[] files = chooser.getSelectedFiles();
        String spn = files.length > 1 ? "ARCHIVOS" : "ARCHIVO";
        setTextLOG("INFO: SE SELECCIONARON " + files.length + " " + spn);

        for (Integer i = 0; i < files.length; i++) {
            String extension = "";

            Integer j = files[i].getName().lastIndexOf('.');
            if (j > 0) {
                extension = files[i].getName().substring(j + 1);
            }
            
            Archivo archivo = new Archivo(files[i].getName(), extension, files[i].length(), false, 0, files[i].getAbsolutePath());
            setTextLOG("INFO: Archivo [" + (i + 1) + "] { " + "NOMBRE: " + archivo.getNombre() +", " +
                    " EXTENSIÓN: " + archivo.getTipo() +", " +
                    " NOMBRE: " + archivo.getNombre() +", " +
                    " BYTES: " + archivo.getSize()+", " +
                    " ESTADO: " + archivo.isEstado() +", " +
                    " PORCENTAJE: " + archivo.getPorcentaje() +"}");
            
            archivos.add(archivo);
            
            for (Integer w = 0; w < 5; w++){
                String mensaje = null;
                switch(w){
                    case 0: 
                        mensaje = archivo.getNombre();
                        break;
                    case 1:
                        mensaje = archivo.getTipo();
                        break;
                    case 2:
                        mensaje = archivo.getSize() + " \t bytes";
                        break;
                    case 3:
                        mensaje = archivo.isEstado() ? "Enviado" : "En pausa";
                        break;
                    case 4:
                        mensaje = "% \t" + archivo.getPorcentaje();
                        break;
                    default:
                        mensaje = "upss";
                        break;
                }
                tableClient.getModel().setValueAt(mensaje, i, w);
            }
        }
        
    }//GEN-LAST:event_btnUploadFIlesActionPerformed

    private void btnEnviarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarArchivosActionPerformed
        //cliente.Cliente.enviarArchivos(archivos);
    }//GEN-LAST:event_btnEnviarArchivosActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(JFrameClientIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameClientIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameClientIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameClientIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameClientIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarArchivos;
    private javax.swing.JButton btnUploadFIles;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelLogClient;
    private javax.swing.JTable tableClient;
    private javax.swing.JTextArea textAreaLogClient;
    // End of variables declaration//GEN-END:variables
}
