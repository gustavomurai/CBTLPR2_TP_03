/*
------------------------------------------------------------
IFSP - Campus Cubatão
Disciplina: CBTLPR2 - Linguagem de Programação II (Java)
Professor: Wellington Tuler Moraes
Atividade: TP03 - FormPessoa Versão 01 (TextField)
Dupla: Gustavo Cerqueira Murai e Igor Cerqueira Murai
------------------------------------------------------------

ENUNCIADO:
Crie o FormPessoa conforme o layout proposto.

• O campo "Número" (kp) não deve estar acessível à digitação.
• Campos "Nome", "Sexo" e "Idade" são obrigatórios.
• O campo "Sexo" deve aceitar apenas 'M' ou 'F'.
• Botão "OK": transfere os dados para o Objeto UmaPessoa (instância de Pessoa).
• Botão "Mostrar": exibe os dados de UmaPessoa e o contador kp.

Nesta versão, o campo "Sexo" é digitado manualmente (TextField).

------------------------------------------------------------
*/


import javax.swing.*;
import java.awt.*;

public class FormPessoaV1 extends JFrame {
    private JTextField txtNumero;
    private JTextField txtNome;
    private JTextField txtSexo;   // TextField aceita 'M' ou 'F'
    private JTextField txtIdade;

    private Pessoa umaPessoa = new Pessoa(); // objeto alvo

    public FormPessoaV1() {
        super("FormPessoa V1 (TextField Sexo)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.add(new JLabel("Número (kp):"));
        txtNumero = new JTextField(String.valueOf(Pessoa.getKp()));
        txtNumero.setEditable(false);
        form.add(txtNumero);

        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Sexo (M/F):"));
        txtSexo = new JTextField();
        form.add(txtSexo);

        form.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        form.add(txtIdade);

        panel.add(form, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk = new JButton("OK");
        JButton btnMostrar = new JButton("Mostrar");

        botoes.add(btnOk);
        botoes.add(btnMostrar);

        panel.add(botoes, BorderLayout.SOUTH);

        setContentPane(panel);

        btnOk.addActionListener(e -> {
            try {
                // Validar campos obrigatórios
                String nome = txtNome.getText().trim();
                String sx = txtSexo.getText().trim().toUpperCase();
                String idadeStr = txtIdade.getText().trim();

                if (nome.isEmpty() || sx.isEmpty() || idadeStr.isEmpty()) {
                    throw new IllegalArgumentException("Preencha Nome, Sexo e Idade.");
                }
                if (!(sx.equals("M") || sx.equals("F"))) {
                    throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'.");
                }
                int idade = Integer.parseInt(idadeStr);
                if (idade < 0) {
                    throw new IllegalArgumentException("Idade não pode ser negativa.");
                }

                // Transferir dados para o objeto
                umaPessoa.setNome(nome);
                umaPessoa.setSexo(sx.charAt(0));
                umaPessoa.setIdade(idade);
                umaPessoa.setKp(); // contar esta "pessoa setada"

                txtNumero.setText(String.valueOf(Pessoa.getKp()));
                JOptionPane.showMessageDialog(this, "Dados salvos em UmaPessoa!", "OK", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Idade deve ser número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnMostrar.addActionListener(e -> {
            String msg = String.format(
                "Pessoa:\n- Nome: %s\n- Sexo: %s\n- Idade: %d\n\nContador kp: %d",
                umaPessoa.getNome() != null ? umaPessoa.getNome() : "(não setado)",
                umaPessoa.getSexo() == 0 ? "(não setado)" : String.valueOf(umaPessoa.getSexo()),
                umaPessoa.getIdade(),
                Pessoa.getKp()
            );
            JOptionPane.showMessageDialog(this, msg, "Mostrar", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPessoaV1().setVisible(true));
    }
}
