package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Emprestimo> getEmprestimos() {
        return new ArrayList<>(emprestimos);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Livro> buscarPorTitulo(String trecho) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(trecho.toLowerCase())) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    private int contarAtivosDoTipo(Usuario usuario, Livro livro) {
        int total = 0;
        for (int i = 0; i < usuario.getEmprestimos().size(); i++) {
            Emprestimo e = usuario.getEmprestimos().get(i);
            if (e.getStatus() && e.getLivro().getClass() == livro.getClass()) {
                total++;
            }
        }
        return total;
    }

    public String emprestar(Usuario usuario, Livro livro) {
        if (!usuarios.contains(usuario)) {
            return "Usuário não cadastrado.";
        } else if (!livros.contains(livro)) {
            return "Livro não cadastrado.";
        } else if (!livro.isDisponivel()) {
            return "Livro indisponível.";
        } else if (contarAtivosDoTipo(usuario, livro) >= livro.getLimiteEmprestimos()) {
            return "Usuário atingiu o limite de empréstimos para este tipo.";
        } else {
            Emprestimo emprestimo = new Emprestimo(usuario, livro);
            emprestimos.add(emprestimo);
            usuario.getEmprestimos().add(emprestimo);
            livro.setDisponivel(false);
            return "Empréstimo realizado com sucesso.";
        }
    }

    public String devolver(Usuario usuario, Livro livro) {
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);

            if (e.getUsuario() == usuario && e.getLivro() == livro && e.getStatus()) {
                e.setStatus(false);
                e.setDataDevolucao(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                livro.setDisponivel(true);
                return "Devolução realizada com sucesso.";
            }
        }
        return "Empréstimo ativo não encontrado.";
    }

}
