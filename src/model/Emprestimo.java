package model;

import java.time.LocalDate;
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



}
