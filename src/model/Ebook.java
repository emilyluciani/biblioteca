package model;

public class Ebook extends Livro{
    private String formato;
    private String isbn;

    public String getFormato() {
        return formato;
    }

    public String getIsbn() {
        return isbn;
    }

    public void getIsbn(String isbn){
        this.isbn = isbn;
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 7;
    }

    @Override
    public boolean estaDisponivel() {
        return true;
    }

}