package exemplodaogenerico;

import util.Relatorio;
import entidades.Cidade;
import javax.swing.JOptionPane;

public class CadastroCidades extends javax.swing.JFrame {

    private DAO<Cidade> dao;

    /** Creates new form CadastroCidades */
    public CadastroCidades() {
        dao = new DAO<Cidade>(Cidade.class);

        initComponents();
        setVisible(true);
        setLocationRelativeTo(null); //Centraliza a tela
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtnIncluir = new javax.swing.JButton();
        jbtnAlterar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jbtnImprimirRelatorio = new javax.swing.JButton();
        jbtnConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Cidades");

        jTable1.setModel(new TMCidade());
        jScrollPane1.setViewportView(jTable1);

        jbtnIncluir.setText("Incluir");
        jbtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIncluirActionPerformed(evt);
            }
        });

        jbtnAlterar.setText("Alterar");
        jbtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAlterarActionPerformed(evt);
            }
        });

        jbtnExcluir.setText("Excluir");
        jbtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExcluirActionPerformed(evt);
            }
        });

        jbtnImprimirRelatorio.setText("Visualizar Relatório");
        jbtnImprimirRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnImprimirRelatorioActionPerformed(evt);
            }
        });

        jbtnConsultar.setText("Consultar");
        jbtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnImprimirRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnIncluir)
                    .addComponent(jbtnAlterar)
                    .addComponent(jbtnExcluir)
                    .addComponent(jbtnImprimirRelatorio)
                    .addComponent(jbtnConsultar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jbtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConsultarActionPerformed
// TODO add your handling code here:
    String f = JOptionPane.showInputDialog("Filtro");
    jTable1.setModel(new TMCidade(f));
    jTable1.updateUI();
}//GEN-LAST:event_jbtnConsultarActionPerformed

private void jbtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIncluirActionPerformed
// TODO add your handling code here:
    DetalhesCidade dc = new DetalhesCidade(this, true);
    dc.setCid(new Cidade());
    dc.setVisible(true);
    if (dc.isConfirmado()) {
        dc.interfaceEntidade();
        dao.insert(dc.getCid());
        jTable1.setModel(new TMCidade());
        jTable1.updateUI();
    }
}//GEN-LAST:event_jbtnIncluirActionPerformed

private void jbtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAlterarActionPerformed
// TODO add your handling code here:
    DetalhesCidade dc = new DetalhesCidade(this, true);
    int selecionado = jTable1.getSelectedRow();
    if (selecionado != -1) {
        TMCidade tmc = (TMCidade) jTable1.getModel();
        dc.setCid(tmc.getElemento(selecionado));
        dc.entidadeInterface();
        dc.setVisible(true);
        if (dc.isConfirmado()) {
            dc.interfaceEntidade();
            dao.update(dc.getCid());
            jTable1.setModel(new TMCidade());
            jTable1.updateUI();
        }
    }

}//GEN-LAST:event_jbtnAlterarActionPerformed

private void jbtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExcluirActionPerformed
// TODO add your handling code here:
    int selecionado = jTable1.getSelectedRow();
    if (selecionado != -1) {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir o registro selecionado","Confirmar",JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.OK_OPTION) {
            TMCidade tmc = (TMCidade) jTable1.getModel();
            dao.delete(tmc.getElemento(selecionado));
            jTable1.setModel(new TMCidade());
            jTable1.updateUI();
        }
    }
}//GEN-LAST:event_jbtnExcluirActionPerformed

    private void jbtnImprimirRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnImprimirRelatorioActionPerformed
           Relatorio reCidades = new Relatorio("../ExemploCadastro1/src/relatorios/Cidade.jasper");
           reCidades.setVisible(true); 
    }//GEN-LAST:event_jbtnImprimirRelatorioActionPerformed

            public void run() {
                new CadastroCidades().setVisible(true);
            }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnAlterar;
    private javax.swing.JButton jbtnConsultar;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnImprimirRelatorio;
    private javax.swing.JButton jbtnIncluir;
    // End of variables declaration//GEN-END:variables
}
