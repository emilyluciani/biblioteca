package model;

public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;
    private int numeroPaginas;
    private String idioma;

    public Livro(String titulo, String autor, boolean disponivel, int numeroPaginas, String idioma) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public abstract int getPrazoEmprestimoDias();

    public int getPrazo() {
        return getPrazoEmprestimoDias();
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

}