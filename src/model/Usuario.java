package model;

import java.util.ArrayList;

public class Usuario extends Pessoa {
    private String matricula;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario (String nome, String cpf, String email, String matricula, ArrayList<Emprestimo> emprestimos) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.emprestimos = emprestimos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public int contarEmprestimosAtivos() {
        int total = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus()) {
                total += total;
            }
        }
        return total;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

}