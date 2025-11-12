/*
------------------------------------------------------------
IFSP - Campus Cubatão
Disciplina: CBTLPR2 - Linguagem de Programação II (Java)
Professor: Wellington Tuler Moraes
Atividade: TP03 - Calculadora com Formulário (Swing)
Dupla: Gustavo Cerqueira Murai e Igor Cerqueira Murai
------------------------------------------------------------

ENUNCIADO:
1) Construir o Form abaixo e possibilitar o cálculo das operações de
divisão, multiplicação, subtração e adição.

OBS:
• O botão "C" (Clear) deve limpar a memória da calculadora e zerar o campo de resultado.
• Implementar tratamento de erros usando try, catch e finally.

------------------------------------------------------------
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm extends JFrame {
    private JTextField txtA;
    private JTextField txtB;
    private JTextField txtResultado;
    private JLabel lblStatus; // para mensagens ao usuário

    public CalculatorForm() {
        super("Calculadora - CBTLPR2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Topo: campos
        JPanel campos = new JPanel(new GridLayout(3, 2, 8, 8));
        campos.add(new JLabel("Valor A:"));
        txtA = new JTextField();
        campos.add(txtA);

        campos.add(new JLabel("Valor B:"));
        txtB = new JTextField();
        campos.add(txtB);

        campos.add(new JLabel("Resultado:"));
        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        campos.add(txtResultado);

        panel.add(campos, BorderLayout.NORTH);

        // Centro: botões de operações
        JPanel botoes = new JPanel(new GridLayout(1, 5, 8, 8));
        JButton btnSomar = new JButton("+");
        JButton btnSubtrair = new JButton("-");
        JButton btnMultiplicar = new JButton("*");
        JButton btnDividir = new JButton("/");
        JButton btnClear = new JButton("C"); // Clear

        botoes.add(btnSomar);
        botoes.add(btnSubtrair);
        botoes.add(btnMultiplicar);
        botoes.add(btnDividir);
        botoes.add(btnClear);

        panel.add(botoes, BorderLayout.CENTER);

        // Rodapé: status
        lblStatus = new JLabel("Pronto.");
        panel.add(lblStatus, BorderLayout.SOUTH);

        setContentPane(panel);

        // Ações
        ActionListener executarOperacao = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a = 0;
                double b = 0;
                double resultado = 0;

                try {
                    lblStatus.setText("Calculando...");
                    // Ler entradas
                    a = Double.parseDouble(txtA.getText().trim());
                    b = Double.parseDouble(txtB.getText().trim());

                    // Qual operação?
                    String op = ((JButton) e.getSource()).getText();
                    switch (op) {
                        case "+" -> resultado = a + b;
                        case "-" -> resultado = a - b;
                        case "*" -> resultado = a * b;
                        case "/" -> {
                            if (b == 0) {
                                throw new ArithmeticException("Divisão por zero não é permitida.");
                            }
                            resultado = a / b;
                        }
                        default -> throw new UnsupportedOperationException("Operação desconhecida.");
                    }

                    txtResultado.setText(String.valueOf(resultado));
                    lblStatus.setText("OK.");

                } catch (NumberFormatException nfe) {
                    lblStatus.setText("Erro: digite números válidos em A e B.");
                    JOptionPane.showMessageDialog(CalculatorForm.this,
                            "Entradas inválidas. Digite números em A e B.",
                            "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (ArithmeticException ae) {
                    lblStatus.setText("Erro: " + ae.getMessage());
                    JOptionPane.showMessageDialog(CalculatorForm.this,
                            ae.getMessage(), "Erro Aritmético", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    lblStatus.setText("Erro inesperado: " + ex.getMessage());
                    JOptionPane.showMessageDialog(CalculatorForm.this,
                            "Erro inesperado: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // finally sempre roda (ex: poderíamos registrar log, limpar algo, etc.)
                    // Aqui vamos só manter a UX consistente.
                }
            }
        };

        btnSomar.addActionListener(executarOperacao);
        btnSubtrair.addActionListener(executarOperacao);
        btnMultiplicar.addActionListener(executarOperacao);
        btnDividir.addActionListener(executarOperacao);

        btnClear.addActionListener(e -> {
            txtA.setText("");
            txtB.setText("");
            txtResultado.setText("");
            lblStatus.setText("Limpou. Pronto.");
            txtA.requestFocus();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorForm().setVisible(true));
    }
}
 