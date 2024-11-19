package Main;

import java.awt.Point;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }
    
    public Login(Point location) {
        if (location != null) {
            setLocation(location);
        } else {
            setLocationRelativeTo(null); // Padrão se a localização não estiver disponível
        }
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        Categoria = new javax.swing.JLabel();
        Complemento = new javax.swing.JLabel();
        TextLogin = new javax.swing.JLabel();
        Login = new javax.swing.JTextField();
        TextSenha = new javax.swing.JLabel();
        Senha = new javax.swing.JPasswordField();
        BotaoEntrar = new javax.swing.JButton();
        BotaoCriarUsuario = new javax.swing.JButton();
        Aviso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(15, 68, 78));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);

        Categoria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Categoria.setForeground(new java.awt.Color(255, 255, 255));
        Categoria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Categoria.setText("Fazer Login");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Complemento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Complemento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Complemento.setText("Forneça seu Login e Senha para entrar ou criar um usuário");

        TextLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TextLogin.setText("Usuário:");

        Login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TextSenha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TextSenha.setText("Senha:");

        Senha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Senha.setToolTipText("");
        Senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SenhaKeyPressed(evt);
            }
        });

        BotaoEntrar.setBackground(new java.awt.Color(15, 68, 78));
        BotaoEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotaoEntrar.setForeground(new java.awt.Color(255, 255, 255));
        BotaoEntrar.setText("Entrar");
        BotaoEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoEntrarActionPerformed(evt);
            }
        });

        BotaoCriarUsuario.setBackground(new java.awt.Color(242, 242, 242));
        BotaoCriarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BotaoCriarUsuario.setText("Criar Usuário");
        BotaoCriarUsuario.setBorder(null);
        BotaoCriarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCriarUsuarioActionPerformed(evt);
            }
        });

        Aviso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Aviso.setForeground(new java.awt.Color(164, 0, 0));
        Aviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Senha)
                                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(148, 148, 148)))
                    .addComponent(Complemento))
                .addContainerGap(147, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BotaoCriarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Complemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(TextLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TextSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCriarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SenhaKeyPressed

        // Verifica se a tecla pressionada é o Enter
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Dispara a ação associada ao botão de entrar
            BotaoEntrarActionPerformed(null);
        }
    }//GEN-LAST:event_SenhaKeyPressed

    private void BotaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoEntrarActionPerformed
        if(("".equals(Login.getText())) || ("".equals(Senha.getText()))){
            Aviso.setText("Não esqueça de fornecer todas as credenciais para entrar!");
        } else {
            String usuario = Login.getText();
            String senha = Senha.getText();
            String senhaCriptografada = GerenciaLogin.criptografarSenha(senha);
            boolean Logou = GerenciaLogin.verificarLogin(usuario, senha);
            if(!Logou){
                Aviso.setText("Usuário ou senha incorretos");
            } else {
                // Captura a posição da janela atual
                java.awt.Point location = this.getLocation();

                // Fecha a janela atual
                this.dispose();

                // Cria e exibe a nova janela na posição capturada
                FormPrincipal formPrincipal = new FormPrincipal();
                formPrincipal.setVisible(true);
            }
        }
    }//GEN-LAST:event_BotaoEntrarActionPerformed

    private void BotaoCriarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCriarUsuarioActionPerformed
        String usuario = Login.getText();
        String senha = Senha.getText();

        if((usuario.isEmpty() || (senha.isEmpty()))){
            Aviso.setText("Não é possível cadastrar usuário sem credenciais");
        }else{
            boolean usuarioExistente = GerenciaLogin.adicionarNovoUsuario(usuario,senha);

            if(usuarioExistente){
                Aviso.setText("Este usuário já existe, forneça outras credenciais");
            }else{
                Aviso.setText("Usuário cadastrado com sucesso!");
            }
        }
    }//GEN-LAST:event_BotaoCriarUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Aviso;
    private javax.swing.JButton BotaoCriarUsuario;
    private javax.swing.JButton BotaoEntrar;
    private javax.swing.JLabel Categoria;
    private javax.swing.JLabel Complemento;
    private javax.swing.JTextField Login;
    private javax.swing.JPasswordField Senha;
    private javax.swing.JLabel TextLogin;
    private javax.swing.JLabel TextSenha;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
