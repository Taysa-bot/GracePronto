/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.grace.view;

import br.com.grace.dao.AgendaDao;
import br.com.grace.dao.EventoDao;
import br.com.grace.model.Estudante;
import br.com.grace.model.Evento;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADM
 */
public class AgendaView extends javax.swing.JInternalFrame {

    Evento festa;
    Evento evento;

    public Evento getFesta() {
        return festa;
    }

    public void setFesta(Evento festa) {
        this.festa = festa;
    }
    Estudante estudante;
    HomeView funcoes;

    public HomeView getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(HomeView funcoes) {
        this.funcoes = funcoes;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    /**
     * Creates new form AgendaSemanal
     */
        public void readJTable() {
        DefaultTableModel modelo = (DefaultTableModel) jTableAgendaSemanal.getModel();
        modelo.setNumRows(0);
        AgendaDao adao = new AgendaDao();

        for (Evento f : adao.read()) {
            modelo.addRow(new Object[]{
                f.getId(),
                f.getTipo(),
                f.getNome(),
                f.getData(),
                f.getHora(),
                f.getDisciplina(),
                f.getFrequencia(),
                f.getStatus(),
                f.getAssunto()
            });
        }

    }
    public AgendaView() {
        initComponents();

        DefaultTableModel modelo = (DefaultTableModel) jTableAgendaSemanal.getModel();
        jTableAgendaSemanal.setRowSorter(new TableRowSorter(modelo));
        readJTable();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAgendaSemanal = new javax.swing.JTable();

        setClosable(true);
        setTitle("Agenda Semanal");

        jTableAgendaSemanal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Tipo", "Nome", "Data", "Hora", "Disciplina", "Frequência", "Status", "Assunto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAgendaSemanal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAgendaSemanal;
    // End of variables declaration//GEN-END:variables
}