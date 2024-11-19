package Atendimento.view;

import Atendimento.Controller.*;
import Atendimento.Model.Fisica;
import Atendimento.Model.Juridica;
import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FormCliente extends javax.swing.JDialog {
    private final GerenciaJuridica gj;
    private final GerenciaFisica gf;
    private List<Cliente> Clientes;
    private List<Animal> animais;
    private List<Animal> animaisCliente = new ArrayList<>();
    private List<Animal> animaisClienteVazio = new ArrayList<>();
    private FormAnimal formAnimal;
    int modo;
    int id;
    int aux;
    boolean auxCadastrar;
    
     
    public FormCliente(java.awt.Frame parent, boolean modal, List<Cliente> clientes, List<Animal> animais, int modo, int id) {
        super(parent, modal);
        this.Clientes = clientes;
        this.animais = animais;
        this.modo = modo;
        this.id = id;
        this.aux = modo;
        gj = new GerenciaJuridica();
        gf = new GerenciaFisica();
        initComponents();
        EscolhaCPF.setEnabled(true);
        EscolhaCNPJ.setEnabled(true);
        
        BotaoSalvar.setEnabled(false);
        ValidaCPF_PJ();
        
        if(this.modo == 1){
            Cliente cliente = gj.getJuridicaById(this.id);
            Nome.setText(cliente.getNome());
            Logradouro.setText(cliente.getLogradouro());
            Complemento.setText(cliente.getComplemento());
            Bairro.setText(cliente.getBairro());
            Municipio.setText(cliente.getMunicipio());
            Estado.setText(cliente.getEstado());
            Telefone.setText(formatarTelefone(cliente.getTelefone()));
            Numero.setValue(cliente.getNumero()); 
            if(cliente instanceof Fisica){
                CPF_PJ.setText(formatarCPF(((Fisica) cliente).getCpf()));
                EscolhaCPF.setSelected(true);
                EscolhaCNPJ.setSelected(false);
                EscolhaCPF.setEnabled(false);
                EscolhaCNPJ.setEnabled(false);
                CPF_PJ.setEnabled(true);
                CpfCnpj.setText("CPF :");
                BotaoSalvar.setEnabled(true);
                auxCadastrar = true;
                ((AbstractDocument) CPF_PJ.getDocument()).setDocumentFilter(new CPFDocumento());
            }
            if(cliente instanceof Juridica) {
                CPF_PJ.setText(formatarCNPJ(((Juridica) cliente).getCnpj()));
                EscolhaCPF.setSelected(false);
                EscolhaCNPJ.setSelected(true);
                EscolhaCPF.setEnabled(false);
                EscolhaCNPJ.setEnabled(false);
                CPF_PJ.setEnabled(true);
                CpfCnpj.setText("CNPJ :");
                auxCadastrar = false;
                ((AbstractDocument) CPF_PJ.getDocument()).setDocumentFilter(new CNPJDocumento());
            } 
            animaisCliente = cliente.getAnimais();
        }
        
        ((AbstractDocument) Telefone.getDocument()).setDocumentFilter(new TelefoneDocumento());
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Nome = new javax.swing.JTextField();
        EscolhaCPF = new javax.swing.JRadioButton();
        EscolhaCNPJ = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        Logradouro = new javax.swing.JTextField();
        Numero = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        Complemento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Bairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Municipio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Estado = new javax.swing.JTextField();
        CpfCnpj = new javax.swing.JLabel();
        CPF_PJ = new javax.swing.JTextField();
        Telefone = new javax.swing.JTextField();
        CpfCnpj1 = new javax.swing.JLabel();
        BotaoCancelar = new javax.swing.JButton();
        BotaoSalvar = new javax.swing.JButton();
        TituloFormCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setText("Nome :");

        Nome.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                NomeCaretUpdate(evt);
            }
        });

        EscolhaCPF.setText("CPF");
        EscolhaCPF.setEnabled(false);
        EscolhaCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscolhaCPFActionPerformed(evt);
            }
        });

        EscolhaCNPJ.setText("CNPJ");
        EscolhaCNPJ.setToolTipText("");
        EscolhaCNPJ.setEnabled(false);
        EscolhaCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscolhaCNPJActionPerformed(evt);
            }
        });

        jLabel3.setText("Logradouro :");

        Numero.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NumeroStateChanged(evt);
            }
        });

        jLabel4.setText("Número :");

        jLabel5.setText("Complemento :");

        jLabel6.setText("Bairro :");

        jLabel7.setText("Município :");
        jLabel7.setToolTipText("");

        jLabel8.setText("Estado :");

        CpfCnpj.setText("CPF/CNPJ :");
        CpfCnpj.setToolTipText("");

        CPF_PJ.setEnabled(false);
        CPF_PJ.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                CPF_PJCaretUpdate(evt);
            }
        });

        CpfCnpj1.setText("Telefone :");
        CpfCnpj1.setToolTipText("");

        BotaoCancelar.setText("Cancelar");
        BotaoCancelar.setToolTipText("");
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });

        BotaoSalvar.setText("Salvar");
        BotaoSalvar.setToolTipText("");
        BotaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoSalvarActionPerformed(evt);
            }
        });

        TituloFormCliente.setText("Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TituloFormCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EscolhaCPF)
                                .addGap(18, 18, 18)
                                .addComponent(EscolhaCNPJ))
                            .addComponent(jLabel1)
                            .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Municipio, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(BotaoSalvar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BotaoCancelar))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(CPF_PJ, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CpfCnpj, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CpfCnpj1)
                                        .addComponent(Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Complemento)
                                .addGap(24, 24, 24))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(142, 142, 142)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloFormCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EscolhaCPF)
                    .addComponent(EscolhaCNPJ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CpfCnpj)
                    .addComponent(CpfCnpj1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CPF_PJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCancelar)
                    .addComponent(BotaoSalvar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
        int response = JOptionPane.showConfirmDialog(
                this,
                "Deseja sair sem salvar?",
                "Sair sem salvar?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Ação baseada na resposta do usuário
        if (response == JOptionPane.YES_OPTION) {
            this.dispose();
        } else if (response == JOptionPane.NO_OPTION) {
            // Usuário clicou em "Não"
        }
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void EscolhaCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscolhaCNPJActionPerformed
        RadioButtonSelected(false);
        CpfCnpj.setText("CNPJ :");
        CPF_PJ.setEnabled(true);
        CPF_PJ.setText("");
        ((AbstractDocument) CPF_PJ.getDocument()).setDocumentFilter(new CNPJDocumento());
    }//GEN-LAST:event_EscolhaCNPJActionPerformed

    private void EscolhaCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscolhaCPFActionPerformed
        RadioButtonSelected(true);
        CpfCnpj.setText("CPF :");
        CPF_PJ.setEnabled(true);
        CPF_PJ.setText("");
        ((AbstractDocument) CPF_PJ.getDocument()).setDocumentFilter(new CPFDocumento());
    }//GEN-LAST:event_EscolhaCPFActionPerformed

    private void BotaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarActionPerformed
        
        if (modo == 0) {
            if (auxCadastrar) {
                Fisica cliente = retornaClienteF();
                gf.addFisica(cliente);
                this.dispose();
            } else {
                Juridica cliente = retornaClienteJ();
                gj.addJuridica(cliente);
                this.dispose();
            }
        } else if (modo == 1) {
            if (auxCadastrar) {
                Fisica cliente = retornaClienteF();
                gf.updateFisica(id, cliente);
                this.dispose();
            } else {
                Juridica cliente = retornaClienteJ();
                gj.updateJuridica(id, cliente);
                this.dispose();
            }
        }     
       
    }//GEN-LAST:event_BotaoSalvarActionPerformed

    private void CPF_PJCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_CPF_PJCaretUpdate
        verificarCampos();
        validarEntrada();
        if(auxCadastrar){
            if(isCpfCadastrado(CPF_PJ.getText())){
                JOptionPane.showMessageDialog(
                        this,
                        "CPF já Cadastrado",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                SwingUtilities.invokeLater(() -> {
                    CPF_PJ.setText("");
                });     
            }
        }else {
            
            if (aux != 1) {
                if (isCnpjCadastrado(CPF_PJ.getText())) {
                    JOptionPane.showMessageDialog(
                            this,
                            "CNPJ já Cadastrado",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        CPF_PJ.setText("");
                    });
                    
                }
            }
        }
        aux++;
    }//GEN-LAST:event_CPF_PJCaretUpdate

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        animaisClienteVazio = null;
        for(Animal animal : animais){
            if(animal.getDono() == null){
                animaisClienteVazio.add(animal);
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void NomeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_NomeCaretUpdate
        verificarCampos();
        validarEntrada();
    }//GEN-LAST:event_NomeCaretUpdate

    private void NumeroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NumeroStateChanged
        int aux = (int) Numero.getValue(); 
        if(aux < 0){
            Numero.setValue(0);
        }
        
    }//GEN-LAST:event_NumeroStateChanged

                       
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<Animal> animais = new ArrayList<>();
                ArrayList<Cliente> clientes = new ArrayList<>();
                FormCliente dialog = new FormCliente(new javax.swing.JFrame(), true, clientes, animais,0,0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
     public void RadioButtonSelected(boolean selecao){
        if(selecao){
            EscolhaCPF.setSelected(true);
            EscolhaCNPJ.setSelected(false);
            auxCadastrar = true;
        }
        if(!selecao){
            EscolhaCPF.setSelected(false);
            EscolhaCNPJ.setSelected(true);
            auxCadastrar = false;
        }
    }
     
    private boolean isCpfCadastrado(String cpf) {
        for (Cliente cliente : Clientes) {
            if (cliente instanceof Fisica) {
                if (modo == 1) {

                    Cliente cliente2 = gj.getJuridicaById(this.id);
                    if (!(cliente2.getId() == cliente.getId())) {
                        if (((Fisica) cliente).getCpf().equals(cpf)) {
                            return true; // CPF já cadastrado
                        }
                    }
                } else {
                    if (((Fisica) cliente).getCpf().equals(cpf)) {
                        return true; // CPF já cadastrado
                    }
                }
            }
        }
        return false; // CPF não cadastrado
    }
    
    private boolean isCnpjCadastrado(String cnpj) {
        for (Cliente cliente : Clientes) {
            if (cliente instanceof Juridica) {
                if (modo == 1) {
                    Cliente cliente2 = gj.getJuridicaById(this.id);
                    if (!(cliente2.getId() == cliente.getId())) {
                        if (((Juridica) cliente).getCnpj().equals(cnpj)) {
                            return true; // CNPJ já cadastrado
                        }
                    }
                }else {
                        if (((Juridica) cliente).getCnpj().equals(cnpj)) {
                            return true; // CNPJ já cadastrado
                        }
                    }
                }
            }

        return false; // CPF não cadastrado
    }
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Bairro;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoSalvar;
    public javax.swing.JTextField CPF_PJ;
    public javax.swing.JTextField Complemento;
    public javax.swing.JLabel CpfCnpj;
    private javax.swing.JLabel CpfCnpj1;
    public javax.swing.JRadioButton EscolhaCNPJ;
    public javax.swing.JRadioButton EscolhaCPF;
    public javax.swing.JTextField Estado;
    public javax.swing.JTextField Logradouro;
    public javax.swing.JTextField Municipio;
    public javax.swing.JTextField Nome;
    public javax.swing.JSpinner Numero;
    public javax.swing.JTextField Telefone;
    public javax.swing.JLabel TituloFormCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
 
    private Fisica retornaClienteF(){
        
        int id1 = 0;
        String nome = Nome.getText();
        String logradouro = Logradouro.getText();
        int numero = (int) Numero.getValue();
        String complemento = Complemento.getText();
        String bairro = Bairro.getText();
        String municipio = Municipio.getText();
        String estado = Estado.getText();
        String telefone = Telefone.getText();
        List<Animal> animais = animaisCliente;
        String cpf = CPF_PJ.getText();
        Fisica fisica = new Fisica(id1, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais, cpf);
        return fisica;     
    }
    
    private Juridica retornaClienteJ(){
        
        int id1 = 0;
        String nome = Nome.getText();
        String logradouro = Logradouro.getText();
        int numero = (int) Numero.getValue();
        String complemento = Complemento.getText();
        String bairro = Bairro.getText();
        String municipio = Municipio.getText();
        String estado = Estado.getText();
        String telefone = Telefone.getText();
        List<Animal> animais = animaisCliente;
        String cnpj = CPF_PJ.getText();
        Juridica juridica = new Juridica(id1, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais, cnpj);
        return juridica;     
    }
    
    class CPFDocumento extends DocumentFilter {

        private static final int MAX_LENGTH = 14; // "XXX.XXX.XXX-XX" é 14 caracteres

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Insira o novo texto na posição correta
            currentText.insert(offset, string);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Substitua o texto na posição correta
            currentText.replace(offset, offset + length, text);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Remova o texto na posição correta
            currentText.delete(offset, offset + length);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, null);
        }

        private String formatText(String text) {
            // Remove qualquer caractere não numérico
            String digitsOnly = text.replaceAll("\\D", "");

            // Formata com base no número de dígitos
            if (digitsOnly.length() <= 3) {
                return digitsOnly;
            } else if (digitsOnly.length() <= 6) {
                return digitsOnly.substring(0, 3) + "." + digitsOnly.substring(3);
            } else if (digitsOnly.length() <= 9) {
                return digitsOnly.substring(0, 3) + "." + digitsOnly.substring(3, 6) + "." + digitsOnly.substring(6);
            } else {
                return digitsOnly.substring(0, 3) + "." + digitsOnly.substring(3, 6) + "." + digitsOnly.substring(6, 9) + "-" + digitsOnly.substring(9, Math.min(11, digitsOnly.length()));
            }
        }

        private boolean isValid(String text) {
            return text.matches("[\\d.\\-]*") && text.length() <= MAX_LENGTH;
        }
    }
    
    public String formatarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return "";
        }

        // Remove qualquer caractere que não seja dígito
        String apenasDigitos = telefone.replaceAll("\\D", "");

        // Verifica o tamanho e aplica o formato correspondente
        if (apenasDigitos.length() == 10) {
            // Formato (XX) XXXX-XXXX
            return String.format("(%s) %s-%s",
                    apenasDigitos.substring(0, 2),
                    apenasDigitos.substring(2, 6),
                    apenasDigitos.substring(6, 10));
        } else if (apenasDigitos.length() == 11) {
            // Formato (XX) XXXXX-XXXX
            return String.format("(%s) %s-%s",
                    apenasDigitos.substring(0, 2),
                    apenasDigitos.substring(2, 7),
                    apenasDigitos.substring(7, 11));
        } else {
            // Retorna o número sem formatação se o tamanho for inválido
            return telefone;
        }
    }

    // Método para formatar CPF
    public String formatarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return "";
        }

        // Remove qualquer caractere que não seja dígito
        String apenasDigitos = cpf.replaceAll("\\D", "");

        // Verifica se tem exatamente 11 dígitos
        if (apenasDigitos.length() == 11) {
            return String.format("%s.%s.%s-%s",
                    apenasDigitos.substring(0, 3),
                    apenasDigitos.substring(3, 6),
                    apenasDigitos.substring(6, 9),
                    apenasDigitos.substring(9, 11));
        } else {
            // Retorna o número sem formatação se o tamanho for inválido
            return cpf;
        }
    }

    // Método para formatar CNPJ
    public  String formatarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return "";
        }

        // Remove qualquer caractere que não seja dígito
        String apenasDigitos = cnpj.replaceAll("\\D", "");

        // Verifica se tem exatamente 14 dígitos
        if (apenasDigitos.length() == 14) {
            return String.format("%s.%s.%s/%s-%s",
                    apenasDigitos.substring(0, 2),
                    apenasDigitos.substring(2, 5),
                    apenasDigitos.substring(5, 8),
                    apenasDigitos.substring(8, 12),
                    apenasDigitos.substring(12, 14));
        } else {
            // Retorna o número sem formatação se o tamanho for inválido
            return cnpj;
        }  
    }

    class TelefoneDocumento extends DocumentFilter {

        private static final int MAX_LENGTH = 15; // "(XX) XXXXX-XXXX" é 15 caracteres

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Insira o novo texto na posição correta
            currentText.insert(offset, string);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Substitua o texto na posição correta
            currentText.replace(offset, offset + length, text);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Remova o texto na posição correta
            currentText.delete(offset, offset + length);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, null);
        }

        private String formatText(String text) {
            // Remove qualquer caractere não numérico
            String digitsOnly = text.replaceAll("\\D", "");

            // Formata com base no número de dígitos
            if (digitsOnly.length() <= 2) {
                return "(" + digitsOnly;
            } else if (digitsOnly.length() <= 7) {
                return "(" + digitsOnly.substring(0, 2) + ") " + digitsOnly.substring(2);
            } else if (digitsOnly.length() <= 11) {
                return "(" + digitsOnly.substring(0, 2) + ") " + digitsOnly.substring(2, 7) + "-" + digitsOnly.substring(7);
            } else {
                return "(" + digitsOnly.substring(0, 2) + ") " + digitsOnly.substring(2, 7) + "-" + digitsOnly.substring(7, 11);
            }
        }
    }
    
    class CNPJDocumento extends DocumentFilter {

        private static final int MAX_LENGTH = 18; // "XX.XXX.XXX/0001-XX" é 18 caracteres

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Insira o novo texto na posição correta
            currentText.insert(offset, string);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }

            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Substitua o texto na posição correta
            currentText.replace(offset, offset + length, text);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            // Obtenha o texto atual do documento
            StringBuilder currentText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            // Remova o texto na posição correta
            currentText.delete(offset, offset + length);

            // Formate o texto
            String formattedText = formatText(currentText.toString());

            // Substitua todo o texto atual pelo texto formatado
            fb.replace(0, fb.getDocument().getLength(), formattedText, null);
        }

        private String formatText(String text) {
            // Remove qualquer caractere não numérico
            String digitsOnly = text.replaceAll("\\D", "");

            // Formata com base no número de dígitos
            if (digitsOnly.length() <= 2) {
                return digitsOnly;
            } else if (digitsOnly.length() <= 5) {
                return digitsOnly.substring(0, 2) + "." + digitsOnly.substring(2);
            } else if (digitsOnly.length() <= 8) {
                return digitsOnly.substring(0, 2) + "." + digitsOnly.substring(2, 5) + "." + digitsOnly.substring(5);
            } else if (digitsOnly.length() <= 12) {
                return digitsOnly.substring(0, 2) + "." + digitsOnly.substring(2, 5) + "." + digitsOnly.substring(5, 8) + "/" + digitsOnly.substring(8);
            } else {
                return digitsOnly.substring(0, 2) + "." + digitsOnly.substring(2, 5) + "." + digitsOnly.substring(5, 8) + "/" + digitsOnly.substring(8, 12) + "-" + digitsOnly.substring(12, Math.min(14, digitsOnly.length()));
            }
        }

        private boolean isValid(String text) {
            return text.matches("[\\d./-]*") && text.length() <= MAX_LENGTH;
        }
    }
    
    private void ValidaCPF_PJ() {
        CPF_PJ.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarEntrada();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarEntrada();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarEntrada();
            }
        });
    }

    private void validarEntrada() {
        String input = CPF_PJ.getText();
        // Verificação simples para ilustrar
        if (auxCadastrar) {
            if (!Nome.getText().trim().isEmpty() && input.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                BotaoSalvar.setEnabled(true);
            } else {
                BotaoSalvar.setEnabled(false);
            }
        } else {
            if (!Nome.getText().trim().isEmpty() && input.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
                BotaoSalvar.setEnabled(true);
            } else {
                BotaoSalvar.setEnabled(false);
            }
        }
    }
    
    private void verificarCampos() {
        boolean camposPreenchidos = !Nome.getText().trim().isEmpty()
                && !CPF_PJ.getText().trim().isEmpty();

        BotaoSalvar.setEnabled(camposPreenchidos);
    }
    
    
}


    

