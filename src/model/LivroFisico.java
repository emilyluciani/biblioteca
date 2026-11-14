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

    @Override
    public int getPrazoEmprestimoDias() {
        return 14;
    }

    @Override
    public double calcularMulta() {
        return calcularMulta(1);
    }

    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * valorMultaPorDia;
    }
}
}