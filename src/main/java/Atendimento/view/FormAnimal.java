package Atendimento.view;

import Atendimento.Controller.GerenciaAnimal;
import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import Atendimento.Model.Fisica;
import Atendimento.Model.Juridica;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FormAnimal extends javax.swing.JDialog {
    private final GerenciaAnimal gi;
    private List<Animal> animais;
    private List<Cliente> clientes;
    private File imagemSelecionadaTemp;
    Animal animal;
    int modo;
    int id;
    String peso = "";
    String altura = "";
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    public FormAnimal(java.awt.Frame parent, boolean modal, List<Animal> animais, List<Cliente> clientes, int modo, int id) {
        super(parent, modal);
        this.animais = animais;
        this.clientes = clientes;
        this.modo = modo;
        this.id = id;
        gi = new GerenciaAnimal();
        initComponents();
        BotaoSalvar.setEnabled(false);
        if (this.modo == 1) {

            Animal animal = gi.getAnimalById(this.id);
            Nome.setText(animal.getNome());
            String dataFormatada = animal.getDataNascimento().format(formato);
            DataNascimento.setText(dataFormatada);
            Raca.setText(animal.getRaca());
            peso = String.format("%.2f", animal.getPeso());
            altura = String.format("%.2f", animal.getAltura());
            EscolhaCPF.setSelected(false);
            EscolhaCNPJ.setSelected(false);
            Cliente dono = animal.getDono();
            Dono.removeAllItems();
            Dono.addItem(dono);
            File arquivoDestino = new File("C:\\imagens\\" + animal.getNome() + ".jpg");
            byte[] imagem = animal.getImagem();
            if (imagem != null) {
                try {
                    // Converte os bytes para uma imagem BufferedImage
                    BufferedImage bufferedImage = converterBytesParaBufferedImage(imagem);

                    // Exibe a imagem no painel sem precisar salvar no disco
                    exibirImagemNoPainel(bufferedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao processar a imagem!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

        ((AbstractDocument) DataNascimento.getDocument()).setDocumentFilter(new DataDocumento());
        ((AbstractDocument) Peso.getDocument()).setDocumentFilter(new PrecoDocumento(Peso, peso));
        ((AbstractDocument) Altura.getDocument()).setDocumentFilter(new PrecoDocumento(Altura, altura));

}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DataNascimento = new javax.swing.JTextField();
        Raca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Altura = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Peso = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        BotaoCancelar = new javax.swing.JButton();
        BotaoSalvar = new javax.swing.JButton();
        TituloFormAnimal = new javax.swing.JLabel();
        imagePanel = new javax.swing.JLabel();
        BotaoAlterarImagem = new javax.swing.JButton();
        Dono = new javax.swing.JComboBox();
        EscolhaCPF = new javax.swing.JRadioButton();
        EscolhaCNPJ = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome :");

        Nome.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                NomeCaretUpdate(evt);
            }
        });

        jLabel3.setText("Data de Nascimento :");

        DataNascimento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                DataNascimentoCaretUpdate(evt);
            }
        });

        Raca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                RacaCaretUpdate(evt);
            }
        });

        jLabel5.setText("Raça :");

        jLabel6.setText("Altura :");

        Altura.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                AlturaCaretUpdate(evt);
            }
        });

        jLabel7.setText("Peso :");
        jLabel7.setToolTipText("");

        Peso.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                PesoCaretUpdate(evt);
            }
        });

        jLabel9.setText("Dono:");
        jLabel9.setToolTipText("");

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

        TituloFormAnimal.setText("Animal");

        imagePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagePanel.setText("Imagem");

        BotaoAlterarImagem.setText("Alterar");
        BotaoAlterarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoAlterarImagemActionPerformed(evt);
            }
        });

        Dono.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DonoItemStateChanged(evt);
            }
        });
        Dono.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                DonoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        EscolhaCPF.setSelected(true);
        EscolhaCPF.setText("CPF");
        EscolhaCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscolhaCPFActionPerformed(evt);
            }
        });

        EscolhaCNPJ.setText("CNPJ");
        EscolhaCNPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscolhaCNPJActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BotaoSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(12, 12, 12)
                                        .addComponent(EscolhaCPF)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(EscolhaCNPJ))
                                    .addComponent(Dono, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(Raca, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Altura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(Peso, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BotaoAlterarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(47, 47, 47))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(TituloFormAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(DataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 184, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloFormAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(DataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoAlterarImagem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Raca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Altura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(EscolhaCPF)
                    .addComponent(EscolhaCNPJ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCancelar)
                    .addComponent(BotaoSalvar))
                .addGap(15, 15, 15))
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

    private void BotaoAlterarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoAlterarImagemActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione uma imagem");

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            imagemSelecionadaTemp = fileChooser.getSelectedFile(); // Armazena a imagem selecionada

            // Exibe a imagem no painel
            exibirImagemNoPainel(imagemSelecionadaTemp);
        }
    }//GEN-LAST:event_BotaoAlterarImagemActionPerformed

    private void BotaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarActionPerformed
        if (modo == 0) {
            try {
                animal = retornaAnimal();
                gi.addAnimal(animal);
                Cliente dono = (Cliente) Dono.getSelectedItem();
                List<Animal> a = dono.getAnimais();
                if (a == null) {
                    a = new ArrayList<>();  // Inicialize a lista se estiver nula
                }

                a.add(animal);
                dono.setAnimais(a);               
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FormAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(gi.getAllAnimal());
        } else if (modo == 1) {
            try {
                animal = retornaAnimal();
                gi.updateAnimal(id, animal);
                Cliente dono = (Cliente) Dono.getSelectedItem();
                List<Animal> a = dono.getAnimais();
                if (a == null) {
                    a = new ArrayList<>();  // Inicialize a lista se estiver nula
                }

                a.add(animal);
                dono.setAnimais(a);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(FormAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        System.out.println(gi.getAllAnimal());
        
    }//GEN-LAST:event_BotaoSalvarActionPerformed

    private void EscolhaCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscolhaCPFActionPerformed
        RadioButtonSelected(true);
        listarClientes();
        //verificarCampos();
    }//GEN-LAST:event_EscolhaCPFActionPerformed

    private void EscolhaCNPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscolhaCNPJActionPerformed
        RadioButtonSelected(false);
        listarClientes();
        //verificarCampos();
    }//GEN-LAST:event_EscolhaCNPJActionPerformed

    private void DonoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DonoItemStateChanged
        verificarCampos();
    }//GEN-LAST:event_DonoItemStateChanged

    private void NomeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_NomeCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_NomeCaretUpdate

    private void DataNascimentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_DataNascimentoCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_DataNascimentoCaretUpdate

    private void PesoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_PesoCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_PesoCaretUpdate

    private void RacaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_RacaCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_RacaCaretUpdate

    private void AlturaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_AlturaCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_AlturaCaretUpdate

    private void DonoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_DonoAncestorAdded
        if(modo == 0){
           listarClientes(); 
        }    
    }//GEN-LAST:event_DonoAncestorAdded
    
    private byte[] converterImagemParaBytes(File imagemSelecionada) throws IOException {
        try (FileInputStream fis = new FileInputStream(imagemSelecionada);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }
    
    private void converterBytesParaImagem(byte[] imagemBytes, File arquivoDestino) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(arquivoDestino)) {
            fos.write(imagemBytes);
        }
    }

    private void exibirImagemNoPainel(File imagemSelecionada) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imagemSelecionada);
            Image scaledImage = bufferedImage.getScaledInstance(74, 74, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            imagePanel.setIcon(imageIcon);
            imagePanel.setPreferredSize(new java.awt.Dimension(74, 74));
            imagePanel.setMaximumSize(new java.awt.Dimension(74, 74));
            imagePanel.setMinimumSize(new java.awt.Dimension(74, 74));
            imagePanel.removeAll();
            imagePanel.revalidate();
            imagePanel.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void RadioButtonSelected(boolean selecao){
        if(selecao){
            EscolhaCPF.setSelected(true);
            EscolhaCNPJ.setSelected(false);
        }
        if(!selecao){
            EscolhaCPF.setSelected(false);
            EscolhaCNPJ.setSelected(true);
        }
    }

    public void listarClientes() {

        if (EscolhaCPF.isSelected()) {
            Dono.removeAllItems(); // Limpa os itens existentes no JComboBox
            if (clientes != null) {
                for (Cliente cliente : clientes) {
                    if (cliente instanceof Fisica) {
                        Dono.addItem(cliente);
                    }
                }
            } 
        } else {
            Dono.removeAllItems(); // Limpa os itens existentes no JComboBox
            for (Cliente cliente : clientes) {
                if (cliente instanceof Juridica) {
                    Dono.addItem(cliente);
                }
            }
        }
    }

    
    // Método para configurar o formulário
  
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<Animal> animais = new ArrayList<>();
                ArrayList<Cliente> clientes = new ArrayList<>();
                FormAnimal dialog = new FormAnimal(new javax.swing.JFrame(), true, animais, clientes, 0,0);
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
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Altura;
    private javax.swing.JButton BotaoAlterarImagem;
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoSalvar;
    private javax.swing.JTextField DataNascimento;
    public javax.swing.JComboBox Dono;
    public javax.swing.JRadioButton EscolhaCNPJ;
    public javax.swing.JRadioButton EscolhaCPF;
    private javax.swing.JTextField Nome;
    private javax.swing.JTextField Peso;
    private javax.swing.JTextField Raca;
    public javax.swing.JLabel TituloFormAnimal;
    public javax.swing.JLabel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

    private Animal retornaAnimal() throws IOException {

        int id1 = 0;
        String nome = Nome.getText();
        LocalDate dataNascimento = LocalDate.parse(DataNascimento.getText(), formato);
        String raca = Raca.getText();
        float peso = Float.parseFloat(Peso.getText().replace(",", "."));
        float altura = Float.parseFloat(Altura.getText().replace(",", "."));
        Cliente dono = (Cliente) Dono.getSelectedItem();
        if (modo == 2) {
            dono = null;
        }
        byte[] imagemBytes = null;
        if(imagemSelecionadaTemp != null){
            imagemBytes = converterImagemParaBytes(imagemSelecionadaTemp);
        }
        Animal animal = new Animal(id1, nome, dataNascimento, raca, peso, altura, dono, imagemBytes);
        return animal;
    }

    public class DataDocumento extends DocumentFilter {

        private static final int MAX_LENGTH = 10;
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); // ResolverStyle.STRICT para validação rigorosa

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            newText.insert(offset, string);

            String formattedText = addSlash(newText.toString());
            if (isValid(formattedText)) {
                super.insertString(fb, offset, formattedText.substring(offset, offset + string.length()), attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            newText.replace(offset, offset + length, text);

            String formattedText = addSlash(newText.toString());
            if (isValid(formattedText)) {
                super.replace(fb, 0, fb.getDocument().getLength(), formattedText, attrs);
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isValid(String text) {
            if (text.isEmpty()) {
                return true; // Permitir campo vazio
            }
            if (text.length() > MAX_LENGTH) {
                return false; // Impede que a entrada exceda o tamanho máximo
            }
            // Permitir a digitação progressiva da data
            if (text.length() <= 2) {
                return text.matches("\\d{0,2}"); // Permitir até dois dígitos para o dia
            } else if (text.length() <= 5) {
                return text.matches("\\d{2}/\\d{0,2}"); // Permitir até dois dígitos para o mês após '/'
            } else if (text.length() <= 10) {
                if (!text.matches("\\d{2}/\\d{2}/\\d{0,4}")) {
                    return false; // Formato inválido para parte da data
                }
                if (text.length() == 10) {
                    return isValidDate(text); // Validação completa da data
                }
                return true; // Permitir entradas parciais válidas
            }
            return false; // Qualquer outra entrada é inválida
        }

        private boolean isValidDate(String text) {
            try {
                // Tentar analisar a data usando o formatador estrito
                LocalDate.parse(text, DATE_FORMATTER);
                return true; // Data válida
            } catch (DateTimeParseException e) {
                return false; // Data inválida
            }
        }

        private String addSlash(String text) {
            // Adicionar automaticamente barras para o formato de data
            String cleaned = text.replaceAll("[^\\d]", ""); // Remover caracteres não numéricos
            StringBuilder formatted = new StringBuilder(cleaned);

            if (cleaned.length() > 2) {
                formatted.insert(2, "/");
            }
            if (cleaned.length() > 4) {
                formatted.insert(5, "/");
            }

            return formatted.toString();
        }
    }

    public class PrecoDocumento extends DocumentFilter {

        private final JTextField textField;

        public PrecoDocumento(JTextField textField, String valor) {
            this.textField = textField;
            if (modo == 1) {
                setPrecoInicial(valor);
            } else {
                setPrecoInicial("");
            }
        }

        private void setPrecoInicial(String preco) {
            this.textField.setText(preco);
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder newText = new StringBuilder(textField.getText());
            newText.insert(offset, string);

            if (isValid(newText.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder newText = new StringBuilder(textField.getText());
            newText.replace(offset, offset + length, text);

            if (isValid(newText.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isValid(String text) {
            // Remove o cifrão ou espaço adicional, se houver
            String numberPart = text.trim();

            if (numberPart.isEmpty()) {
                return true; // Permitir campo vazio
            }

            // Verificar se o texto corresponde ao formato de preço permitido
            // Aceita números com vírgula ou ponto como separador decimal e até 2 casas decimais
            return numberPart.matches("\\d*(,\\d{0,2})?") || numberPart.matches("\\d*(\\.\\d{0,2})?");
        }
    }

    private void verificarCampos() {

        boolean camposPreenchidos = Dono.getSelectedItem() != null
                && !Nome.getText().trim().isEmpty()
                && !Raca.getText().trim().isEmpty();

        boolean dataValida = isDataValida(DataNascimento.getText());
        boolean alturaValido = isPrecoValido(Altura.getText());
        boolean pesoValido = isPrecoValido(Peso.getText());

        BotaoSalvar.setEnabled(camposPreenchidos && dataValida && alturaValido && pesoValido);

    }

    private boolean isDataValida(String text) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(text, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isPrecoValido(String text) {
        try {
            String precoSemCifrao = text.trim().replace(",", ".");
            float preco = Float.parseFloat(precoSemCifrao);
            return preco > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private BufferedImage converterBytesParaBufferedImage(byte[] imagem) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imagem);
        return ImageIO.read(bais);
    }
    
    private void exibirImagemNoPainel(BufferedImage bufferedImage) {
        ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH));
        imagePanel.setIcon(imageIcon);
    }
}
