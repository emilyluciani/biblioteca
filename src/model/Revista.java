package model;

public class Revista extends Livro{

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
}
