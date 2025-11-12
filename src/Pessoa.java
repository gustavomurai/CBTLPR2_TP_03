/*
------------------------------------------------------------
IFSP - Campus Cubatão
Disciplina: CBTLPR2 - Linguagem de Programação II (Java)
Professor: Wellington Tuler Moraes
Atividade: TP03 - Classe Pessoa
Dupla: Gustavo Cerqueira Murai e Igor Cerqueira Murai
------------------------------------------------------------

ENUNCIADO:
Crie a estrutura de classe "Pessoa" com os seguintes atributos protegidos:
- kp (static int)
- nome (String)
- sexo (char)
- idade (int)

Métodos:
+ Pessoa()
+ Pessoa(String, char, int)
+ setKp()
+ setNome(String)
+ setSexo(char)
+ setIdade(int)
+ getKp(): int
+ getNome(): String
+ getSexo(): char
+ getIdade(): int

A propriedade "kp" é estática e deve informar quantas pessoas distintas foram "setadas".

------------------------------------------------------------
*/



public class Pessoa {
    // # = protected no diagrama
    protected static int kp = 0;  // contador estático de pessoas "setadas"
    protected String nome;
    protected char sexo;          // 'M' ou 'F'
    protected int idade;

    // Construtor padrão
    public Pessoa() {
    }

    // Construtor com parâmetros
    public Pessoa(String nome, char sexo, int idade) {
        setNome(nome);
        setSexo(sexo);
        setIdade(idade);
        setKp(); // sempre que "setamos" uma pessoa completa, incrementa
    }

    // kp funciona como contador: cada vez que confirmamos (OK) novos dados para UmaPessoa
    public void setKp() {
        kp++;
    }

    public static int getKp() {
        return kp;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setSexo(char sexo) {
        char s = Character.toUpperCase(sexo);
        if (s != 'M' && s != 'F') {
            throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'.");
        }
        this.sexo = s;
    }

    public char getSexo() {
        return sexo;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}
