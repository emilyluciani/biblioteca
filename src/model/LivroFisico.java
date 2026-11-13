package model;

public class LivroFisico extends Livro implements Multavel{
    private double valorMultaPorDia;
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void getIsbn(String isbn){
        this.isbn = isbn;
    }
}