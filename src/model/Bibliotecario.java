package model;

public class Bibliotecario extends Pessoa {
    private String codFuncionario;

    public Bibliotecario (String nome, String cpf, String email, String codFuncionario) {
        super(nome, cpf, email);
        this.codFuncionario = codFuncionario;
    }

    public String getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(String codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    @Override
    public int getLimiteEmprestimos(){
        return 5;
    }


}
