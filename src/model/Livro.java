package model;

public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;
    private int numeroPaginas;
    private String idioma;

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
}