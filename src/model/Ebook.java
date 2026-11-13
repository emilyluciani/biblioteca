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

}