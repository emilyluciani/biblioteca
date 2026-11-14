package model;

public class Revista extends Livro implements Multavel {
    private static final double VALOR_MULTA_POR_DIA = 0.50;
    private String issn;

    public Revista(String titulo, String autor, boolean disponivel, int numeroPaginas, String idioma, String issn){
        super(titulo, autor, disponivel, numeroPaginas,idioma);
        this.issn =issn;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public int getPrazoEmprestimoDias () {
        return 5;
    }

    @Override
    public double calcularMulta () {
        return calcularMulta(1);
    }

    @Override
    public double calcularMulta (int diasAtraso) {
        return diasAtraso * VALOR_MULTA_POR_DIA;
    }
}
