package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String data;
    private int prazo;
    private Usuario usuario;
    private Livro livro;
    private boolean status;
    private String dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.data = LocalDate.now().format(FORMATO);
        this.prazo = livro.getPrazo();
        this.usuario = usuario;
        this.livro = livro;
        this.status = true;
    }
    public boolean getStatus() {
        return status;
    }

    public String getData(){
        return data;
    }
    public int getPrazo(){
        return prazo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Livro getLivro (){
            return livro;
    }

    private LocalDate calcularDataPrevista() {
        return LocalDate.parse(data, FORMATO).plusDays(prazo);
    }

    public String getDataPrevistaDevolucao() {
        return calcularDataPrevista().format(FORMATO);
    }

    public boolean estaAtivo() {
        return status;
    }

    public boolean estaAtrasado() {
        return status && LocalDate.now().isAfter(calcularDataPrevista());
    }

    public void registrarDevolucao() {
        this.status = false;
        this.dataDevolucao = LocalDate.now().format(FORMATO);
    }

    public double calcularMulta() {
        if (livro instanceof Multavel && estaAtrasado()) {
            int dias = Period.between(calcularDataPrevista(), LocalDate.now()).getDays();
            return ((Multavel) livro).calcularMulta(dias);
        }
        return 0;
    }


}
