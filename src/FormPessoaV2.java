/*
------------------------------------------------------------
IFSP - Campus Cubatão
Disciplina: CBTLPR2 - Linguagem de Programação II (Java)
Professor: Wellington Tuler Moraes
Atividade: TP03 - FormPessoa Versão 02 (JComboBox)
Dupla: Gustavo Cerqueira Murai e Igor Cerqueira Murai
------------------------------------------------------------

ENUNCIADO:
Refaça o exercício anterior, porém agora o campo "Sexo" não deve ser digitado
em um TextField e sim escolhido através de um componente JComboBox ("M" ou "F").

Demais regras permanecem iguais:
• Campos obrigatórios: Nome, Sexo, Idade.
• Campo "Número" (kp) é somente leitura.
• Botão "OK": transfere dados para UmaPessoa e incrementa kp.
• Botão "Mostrar": exibe os dados e o contador kp.

------------------------------------------------------------
*/


import javax.swing.*;
import java.awt.*;

public class FormPessoaV2 extends JFrame {
    private JTextField txtNumero;
    private JTextField txtNome;
    private JComboBox<String> cbSexo; // JComboBox ao invés de TextField
    private JTextField txtIdade;

    private Pessoa umaPessoa = new Pessoa();

    public FormPessoaV2() {
        super("FormPessoa V2 (JComboBox Sexo)");
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

        form.add(new JLabel("Sexo:"));
        cbSexo = new JComboBox<>(new String[] {"Selecione", "M", "F"});
        form.add(cbSexo);

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
                String nome = txtNome.getText().trim();
                String idadeStr = txtIdade.getText().trim();
                String sexoSelecionado = (String) cbSexo.getSelectedItem();

                if (nome.isEmpty() || idadeStr.isEmpty() || sexoSelecionado == null || sexoSelecionado.equals("Selecione")) {
                    throw new IllegalArgumentException("Preencha Nome, escolha Sexo e informe Idade.");
                }

                int idade = Integer.parseInt(idadeStr);
                if (idade < 0) throw new IllegalArgumentException("Idade não pode ser negativa.");

                umaPessoa.setNome(nome);
                umaPessoa.setSexo(sexoSelecionado.charAt(0)); // 'M' ou 'F'
                umaPessoa.setIdade(idade);
                umaPessoa.setKp();

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
        SwingUtilities.invokeLater(() -> new FormPessoaV2().setVisible(true));
    }
}
