package model;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiLivros {

    public LivroFisico buscarPorTitulo(String titulo) throws Exception {
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = "https://openlibrary.org/search.json?q=" + tituloCodificado
                + "&limit=1&fields=title,author_name,number_of_pages_median,language";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                // a Open Library pede para o programa se identificar (troque pelo seu email)
                .header("User-Agent", "BibliotecaPUCPR (seuemail@exemplo.com)")
                .build();
        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        if (resposta.statusCode() != 200) {
            throw new Exception("Erro ao consultar a API. Código: " + resposta.statusCode());
        }

        String texto = resposta.body();

        // os livros ficam dentro do bloco "docs"
        int inicioBloco = texto.indexOf("\"docs\"");
        if (inicioBloco == -1) {
            throw new Exception("Nenhum livro encontrado com esse título.");
        }
        String bloco = texto.substring(inicioBloco);

        String tituloLivro = pegarTexto(bloco, "title");
        if (tituloLivro.equals("")) {
            throw new Exception("Nenhum livro encontrado com esse título.");
        }

        String autor = pegarPrimeiroDaLista(bloco, "author_name");
        if (autor.equals("")) {
            autor = "Autor desconhecido";
        }

        String idioma = pegarPrimeiroDaLista(bloco, "language");
        if (idioma.equals("")) {
            idioma = "Não informado";
        }

        int paginas = pegarNumero(bloco, "number_of_pages_median");

        return new LivroFisico(tituloLivro, autor, true, paginas, idioma);
    }

    // acha o campo e devolve a posição do valor (pula os dois pontos, espaços e quebras de linha)
    private int acharValor(String texto, String campo) {
        String chave = "\"" + campo + "\"";
        int pos = texto.indexOf(chave);
        if (pos == -1) {
            return -1;
        }
        pos = pos + chave.length();
        while (pos < texto.length() && (texto.charAt(pos) == ' ' || texto.charAt(pos) == ':'
                || texto.charAt(pos) == '\n' || texto.charAt(pos) == '\r' || texto.charAt(pos) == '\t')) {
            pos++;
        }
        return pos;
    }

    // pega um texto simples, tipo "title": "Dom Casmurro"
    private String pegarTexto(String texto, String campo) {
        int pos = acharValor(texto, campo);
        if (pos == -1 || pos >= texto.length() || texto.charAt(pos) != '"') {
            return "";
        }
        int inicio = pos + 1;
        int fim = texto.indexOf("\"", inicio);
        return texto.substring(inicio, fim);
    }

    // pega o primeiro item de uma lista, tipo "author_name": [ "Machado de Assis" ]
    private String pegarPrimeiroDaLista(String texto, String campo) {
        int pos = acharValor(texto, campo);
        if (pos == -1 || pos >= texto.length() || texto.charAt(pos) != '[') {
            return "";
        }
        pos++;
        while (pos < texto.length() && (texto.charAt(pos) == ' '
                || texto.charAt(pos) == '\n' || texto.charAt(pos) == '\r' || texto.charAt(pos) == '\t')) {
            pos++;
        }
        if (pos >= texto.length() || texto.charAt(pos) != '"') {
            return "";
        }
        int inicio = pos + 1;
        int fim = texto.indexOf("\"", inicio);
        return texto.substring(inicio, fim);
    }

    // pega um número, tipo "number_of_pages_median": 256
    private int pegarNumero(String texto, String campo) {
        int inicio = acharValor(texto, campo);
        if (inicio == -1) {
            return 0;
        }
        int fim = inicio;
        while (fim < texto.length() && Character.isDigit(texto.charAt(fim))) {
            fim++;
        }
        if (fim == inicio) {
            return 0;
        }
        return Integer.parseInt(texto.substring(inicio, fim));
    }
}