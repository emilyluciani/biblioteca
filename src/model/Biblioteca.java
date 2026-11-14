package model;

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


}
