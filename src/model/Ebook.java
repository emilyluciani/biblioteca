package model;

public class Ebook extends Livro{
    private String formato;
    private String isbn;

    public Ebook(String titulo, String autor, boolean disponivel, int numeroPaginas, String idioma) {
        super(titulo, autor, disponivel, numeroPaginas, idioma);
        this.formato="PDF";
    }

    public String getFormato() {
        return formato;
    }
    public void setFormato(String formato) { this.formato = formato; }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 7;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 2;
    }

}