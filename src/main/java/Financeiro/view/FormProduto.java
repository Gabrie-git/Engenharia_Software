package Financeiro.view;

import javax.swing.JOptionPane;
import Financeiro.Controller.GerenciaProduto;
import Financeiro.Model.Item;
import Financeiro.Model.Produto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FormProduto extends javax.swing.JDialog {
    private final GerenciaProduto gi;
    int modo;
    int id;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String valor;
    
       
    public FormProduto(java.awt.Frame parent, boolean modal, List<Item> produto, int modo, int id) {
        super(parent, modal);
        initComponents();
        this.modo = modo;
        this.id = id;
        gi = new GerenciaProduto();

        BotaoSalvar.setEnabled(false);
      
        if (this.modo == 1) {
            Item aux = gi.buscarProdutoPorId(id);
            Produto prod = (Produto) aux;
            double ap = prod.getPreco();
            valor = String.format("%.2f", ap);
            tfDescricao.setText(prod.getDescricao());
            tfModelo.setText(prod.getModelo());
            String dataFormatada = prod.getValidade().format(formato);
            tfValidade.setText(dataFormatada);
            tfMarca.setText(prod.getMarca());
            tfModelo.setText(prod.getModelo());
            sQuantidade.setValue(prod.getQuantidade());
        }
        
        ((AbstractDocument) tfValidade.getDocument()).setDocumentFilter(new DataDocumento());
        ((AbstractDocument) tfPreco.getDocument()).setDocumentFilter(new PrecoDocumento(tfPreco, valor));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfMarca = new javax.swing.JTextField();
        tfPreco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfValidade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfModelo = new javax.swing.JTextField();
        BotaoCancelar = new javax.swing.JButton();
        BotaoSalvar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sQuantidade = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Descrição:");

        tfDescricao.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfDescricaoCaretUpdate(evt);
            }
        });

        jLabel3.setText("Marca:");

        tfMarca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfMarcaCaretUpdate(evt);
            }
        });

        tfPreco.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfPrecoCaretUpdate(evt);
            }
        });

        jLabel5.setText("Preço:");

        jLabel7.setText("Validade");
        jLabel7.setToolTipText("");

        tfValidade.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfValidadeCaretUpdate(evt);
            }
        });

        jLabel8.setText("Modelo");

        tfModelo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfModeloCaretUpdate(evt);
            }
        });

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

        jLabel4.setText("Quantidade:");

        sQuantidade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sQuantidadeStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(337, 337, 337))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(BotaoSalvar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(BotaoCancelar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addGap(24, 24, 24))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(192, 192, 192))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfPreco)
                                        .addGap(24, 24, 24))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(sQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(tfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void BotaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoSalvarActionPerformed
        if (modo == 0) {
            Produto produto = retornaProduto();
            gi.addProduto(produto);
            System.out.println(gi.getAllProdutos());
        }
        if (modo == 1) {
            Produto produto = retornaProduto();
            gi.updateProduto(id, produto);
        }
        System.out.println(gi.getAllProdutos());
        this.dispose();
    }//GEN-LAST:event_BotaoSalvarActionPerformed

    private void tfDescricaoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfDescricaoCaretUpdate
         verificarCampos();
    }//GEN-LAST:event_tfDescricaoCaretUpdate

    private void tfMarcaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfMarcaCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_tfMarcaCaretUpdate

    private void tfValidadeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfValidadeCaretUpdate
        verificarCampos();
    }//GEN-LAST:event_tfValidadeCaretUpdate

    private void tfModeloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfModeloCaretUpdate
         verificarCampos();
    }//GEN-LAST:event_tfModeloCaretUpdate

    private void tfPrecoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfPrecoCaretUpdate
         verificarCampos();
    }//GEN-LAST:event_tfPrecoCaretUpdate

    private void sQuantidadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sQuantidadeStateChanged
        int aux = (int) sQuantidade.getValue();
        if(aux < 0){
            sQuantidade.setValue(0);
        }

    }//GEN-LAST:event_sQuantidadeStateChanged


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<Item> produto = new ArrayList<>();
                FormProduto dialog = new FormProduto(new javax.swing.JFrame(), true, produto, 0, 0);
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
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton BotaoSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JSpinner sQuantidade;
    public javax.swing.JTextField tfDescricao;
    public javax.swing.JTextField tfMarca;
    public javax.swing.JTextField tfModelo;
    public javax.swing.JTextField tfPreco;
    public javax.swing.JTextField tfValidade;
    // End of variables declaration//GEN-END:variables

    private Produto retornaProduto() {
        
        String descricao = tfDescricao.getText();
        int id1 = 0;
        String precoSemCifrao = tfPreco.getText().replace("R$ ", "").trim().replace(",", ".");
        double preco = Double.parseDouble(precoSemCifrao);
        LocalDate validade = LocalDate.parse(tfValidade.getText(), formato);
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        int quantidade = (int) sQuantidade.getValue();
        Produto produto = new Produto(id1, descricao, preco, validade, marca, modelo, quantidade);
        return produto;
    }
    
    public class DataDocumento extends DocumentFilter {

        private static final int MAX_LENGTH = 10;
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT); // ResolverStyle.STRICT para validação rigorosa

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            newText.insert(offset, string);

            String formattedText = addSlash(newText.toString());
            if (isValid(formattedText)) {
                super.insertString(fb, offset, formattedText.substring(offset, offset + string.length()), attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder newText = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            newText.replace(offset, offset + length, text);

            String formattedText = addSlash(newText.toString());
            if (isValid(formattedText)) {
                super.replace(fb, 0, fb.getDocument().getLength(), formattedText, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
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
                LocalDate parsedDate = LocalDate.parse(text, DATE_FORMATTER);
                LocalDate currentDate = LocalDate.now();

                if (parsedDate.isBefore(currentDate)) {
                    return false; // Data é anterior à data atual
                }

                return true; // Data válida e não anterior à atual
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
    
    private void verificarCampos() {
        // Verifica se todos os campos estão preenchidos
        boolean camposPreenchidos = !tfValidade.getText().trim().isEmpty()
                && !tfPreco.getText().trim().isEmpty()
                && !tfDescricao.getText().trim().isEmpty()
                && !tfMarca.getText().trim().isEmpty()
                && !tfModelo.getText().trim().isEmpty();

        // Verifica se a data é válida
        boolean dataValida = isDataValida(tfValidade.getText());

        // Verifica se o preço é válido e maior que 0
        boolean precoValido = isPrecoValido(tfPreco.getText());

        // Habilita o botão "Salvar" somente quando todas as condições são atendidas
        BotaoSalvar.setEnabled(camposPreenchidos && dataValida && precoValido);
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
            // Remove o símbolo de moeda e substitui a vírgula por ponto para conversão
            String precoSemCifrao = text.replace("R$ ", "").trim().replace(",", ".");

            // Tenta converter para double
            double preco = Double.parseDouble(precoSemCifrao);

            // Verifica se o preço é maior que zero
            return preco > 0;
        } catch (NumberFormatException e) {
            return false; // Retorna falso se a conversão falhar
        }
    }
    
    public class PrecoDocumento extends DocumentFilter {

        private final JTextField textField;
        private static final String CURRENCY_SYMBOL = "R$ ";
        private static final int MAX_DIGITS = 9; // Limite de dígitos: 7 para reais e 2 para centavos

        public PrecoDocumento(JTextField textField, String valor) {
            this.textField = textField;
            if (modo == 0) {
                setPrecoInicial("0,00");

                // Posiciona o cursor após o símbolo de moeda e antes do valor monetário
                SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
            }else{
                setPrecoInicial(valor);

                // Posiciona o cursor após o símbolo de moeda e antes do valor monetário
                SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
            }    
        }

        private void setPrecoInicial(String preco) {
            this.textField.setText(CURRENCY_SYMBOL + preco);
        }

        private void setCaretPosition(int position) {
            int safePosition = Math.min(position, textField.getText().length());
            textField.setCaretPosition(safePosition);
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            // Permite apenas números
            if (!string.matches("\\d")) {
                return;
            }

            // Impede inserção se o número máximo de dígitos for excedido
            String currentText = textField.getText().replace(CURRENCY_SYMBOL, "").replace(",", "");
            if (currentText.length() >= MAX_DIGITS) {
                return;
            }

            // Adiciona o novo dígito ao final
            StringBuilder newText = new StringBuilder(currentText);
            newText.append(string);

            // Formata o texto para exibir como valor monetário
            String formattedText = formatCurrency(newText.toString());

            super.replace(fb, 0, textField.getText().length(), formattedText, attr);

            // Ajusta o cursor para estar sempre após o símbolo de moeda e antes dos zeros
            SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            // Permite apenas números
            if (text != null && !text.matches("\\d*")) {
                return;
            }

            // Obtém o texto atual sem o símbolo de moeda e vírgula
            String currentText = textField.getText().replace(CURRENCY_SYMBOL, "").replace(",", "");
            StringBuilder newText = new StringBuilder(currentText);

            // Evita tentativa de substituir fora do comprimento da string
            if (offset > newText.length()) {
                return;
            }

            newText.replace(Math.max(0, offset), Math.min(newText.length(), offset + length), text != null ? text : "");

            if (newText.length() > MAX_DIGITS) {
                return; // Impede substituição se exceder o número máximo de dígitos
            }

            // Formata o texto para exibir como valor monetário
            String formattedText = formatCurrency(newText.toString());

            super.replace(fb, 0, textField.getText().length(), formattedText, attrs);

            // Ajusta o cursor para estar sempre após o símbolo de moeda e antes dos zeros
            SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (offset < CURRENCY_SYMBOL.length()) {
                SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
                return; // Impede a remoção do símbolo de moeda
            }

            // Obtém o texto atual sem o símbolo de moeda e vírgula
            String currentText = textField.getText().replace(CURRENCY_SYMBOL, "").replace(",", "");
            StringBuilder newText = new StringBuilder(currentText);

            // Evita tentativa de remoção fora do comprimento da string
            if (newText.length() > 0 && offset - CURRENCY_SYMBOL.length() < newText.length()) {
                newText.deleteCharAt(newText.length() - 1); // Remove o último dígito
            }

            // Formata o texto para exibir como valor monetário
            String formattedText = formatCurrency(newText.toString());

            super.replace(fb, 0, textField.getText().length(), formattedText, null);

            // Ajusta o cursor para estar sempre após o símbolo de moeda e antes dos zeros
            SwingUtilities.invokeLater(() -> setCaretPosition(CURRENCY_SYMBOL.length()));
        }

        private String formatCurrency(String value) {
            // Remove zeros à esquerda, se houver
            value = value.replaceFirst("^0+(?!$)", "");

            // Preenche com zeros à esquerda, se necessário, para garantir pelo menos 3 dígitos
            while (value.length() < 3) {
                value = "0" + value;
            }

            // Insere a vírgula para centavos
            String formattedValue = value.substring(0, value.length() - 2) + "," + value.substring(value.length() - 2);
            return CURRENCY_SYMBOL + formattedValue;
        }

        private boolean isValid(String text) {
            String numberPart = text.replace(CURRENCY_SYMBOL, "").replace(",", ""); // Remove o símbolo de moeda para validação

            if (numberPart.isEmpty() || numberPart.equals(" ")) {
                return true; // Permitir apenas o símbolo de moeda
            }

            return numberPart.matches("\\d*"); // Permite apenas números
        }
    }
}
