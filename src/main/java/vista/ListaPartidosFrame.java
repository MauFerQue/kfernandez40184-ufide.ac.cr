package vista;

public class ListaPartidosFrame extends javax.swing.JFrame {

    private cliente.ClienteSocket cliente;
    private String rol;
    private PanelPrincipal panel;

    private javax.swing.DefaultListModel<String> modeloLista = new javax.swing.DefaultListModel<>();
    private int idSeleccionado = -1;

    public ListaPartidosFrame(cliente.ClienteSocket cliente, String rol, PanelPrincipal panel) {
        this.cliente = cliente;
        this.rol = rol;
        this.panel = panel;

        initComponents();

        listaPartidos.addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting()) {

        String seleccionado = listaPartidos.getSelectedValue();

        if (seleccionado != null) {

            idSeleccionado = Integer.parseInt(seleccionado.split(" - ")[0]);

            txtEventos.setText(""); // limpiar al cambiar
        }
    }
});

        listaPartidos.setModel(modeloLista);

        configurarPermisos();
        escuchar();
    }

    private void configurarPermisos() {

    // ADMIN → ve todo (no ocultamos nada)
    if (rol.equals("ADMIN")) {
        return;
    }

    // CORRESPONSAL → puede ver botón de evento, pero no chat
    if (rol.equals("CORRESPONSAL")) {
        txtMensaje.setVisible(false);
        btnEnviar.setVisible(false);
        return;
    }

    // FAN → NO puede enviar eventos
    if (rol.equals("FAN")) {
        btnEvento.setVisible(false);
    }
}
    private void escuchar() {

        cliente.escuchar(msg -> {

            javax.swing.SwingUtilities.invokeLater(() -> {

                System.out.println("RECIBIDO: " + msg);

                String[] partes = msg.split("\\|");

                if (partes[0].equals("PARTIDOS")) {

                    modeloLista.clear();

                    String[] partidos = partes[1].split(";");

                    for (String p : partidos) {
                        if (!p.isEmpty()) modeloLista.addElement(p);
                    }
                }

                if (partes[0].equals("EVENTO")) {

                    int id = Integer.parseInt(partes[1]);

                    if (id == idSeleccionado) {
                        txtEventos.append(
                                "[MIN " + partes[4] + "] " + partes[2] + ": " + partes[3] + "\n"
                        );
                    }
                }

                if (partes[0].equals("CHAT")) {

                    int id = Integer.parseInt(partes[1]);

                    if (id == idSeleccionado) {
                        txtEventos.append(
                                partes[2] + ": " + partes[3] + "\n"
                        );
                    }
                }
            });
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPartidos = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEventos = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        btnEvento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listaPartidos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaPartidos);

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtEventos.setColumns(20);
        txtEventos.setRows(5);
        jScrollPane1.setViewportView(txtEventos);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnEvento.setText("Evento");
        btnEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(txtMensaje))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jButton2)
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEvento)
                    .addComponent(btnEnviar))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(btnEvento)
                .addGap(11, 11, 11)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     cliente.getSalida().println("LISTAR_PARTIDOS");
    
   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   panel.setVisible(true);
this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventoActionPerformed

    if (idSeleccionado == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un partido");
        return;
    }

    cliente.getSalida().println(
        "EVENTO|" + idSeleccionado + "|GOL|Gol de prueba|45"
    );

    }//GEN-LAST:event_btnEventoActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

    if (idSeleccionado == -1) return;

    String msg = txtMensaje.getText();

    cliente.getSalida().println(
        "CHAT|" + idSeleccionado + "|usuario|" + msg
    );

    txtMensaje.setText("");

    }//GEN-LAST:event_btnEnviarActionPerformed

public static void main(String args[]) {
    System.out.println("Abrir desde PanelPrincipal");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnEvento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaPartidos;
    private javax.swing.JTextArea txtEventos;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
