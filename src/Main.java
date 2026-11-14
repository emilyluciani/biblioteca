package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();
    private static List<Bibliotecario> bibliotecarios = new ArrayList<>();
    private static Persistencia persistencia = new Persistencia();
    private static final ApiLivros api = new ApiLivros();

    public static void main(String[] args) {
        carregarDados();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha: ");
            System.out.println();
            try {
                switch (opcao) {
                    case 1: cadastrarLivroFisico(); break;
                    case 2: cadastrarEbook(); break;
                    case 3: cadastrarRevista(); break;
                    case 4: cadastrarUsuario(); break;
                    case 5: cadastrarBibliotecario(); break;
                    case 6: listarLivros(); break;
                    case 7: listarUsuarios(); break;
                    case 8: listarBibliotecarios(); break;
                    case 9: listarEmprestimos(); break;
                    case 10: buscarPorTitulo(); break;
                    case 11: emprestar(); break;
                    case 12: devolver(); break;
                    case 13: detalhesEmprestimo(); break;
                    case 14: registrarDevolucaoDireta(); break;
                    case 15: testarMulta(); break;
                    case 16: dadosUsuario(); break;
                    case 17: editarLivro(); break;
                    case 18: editarUsuario(); break;
                    case 19: editarBibliotecario(); break;
                    case 20: cadastrarLivroPelaApi(); break;
                    case 0: System.out.println("Encerrando..."); break;
                    default: System.out.println("Opção inválida.");
                }
                salvarDados();
            } catch (Exception e) {
                System.out.println("ERRO durante o teste: " + e);
            }
        } while (opcao != 0);
    }

    @SuppressWarnings("unchecked")
    private static void carregarDados() {
        try {
            biblioteca = (Biblioteca) persistencia.carregar("biblioteca.dat");
            bibliotecarios = (List<Bibliotecario>) persistencia.carregar("bibliotecarios.dat");
            System.out.println("Dados carregados dos arquivos.");
        } catch (Exception e) {
            System.out.println("Nenhum dado salvo encontrado. Começando do zero.");
        }
    }

    private static void salvarDados() {
        try {
            persistencia.salvar(biblioteca, "biblioteca.dat");
            persistencia.salvar(bibliotecarios, "bibliotecarios.dat");
        } catch (Exception e) {
            System.out.println("Não foi possível salvar os dados: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\n========== MENU BIBLIOTECA ==========");
        System.out.println("--- Cadastros ---");
        System.out.println(" 1 - Cadastrar livro físico");
        System.out.println(" 2 - Cadastrar ebook");
        System.out.println(" 3 - Cadastrar revista");
        System.out.println(" 4 - Cadastrar usuário");
        System.out.println(" 5 - Cadastrar bibliotecário");
        System.out.println("--- Listagens / busca ---");
        System.out.println(" 6 - Listar livros");
        System.out.println(" 7 - Listar usuários");
        System.out.println(" 8 - Listar bibliotecários");
        System.out.println(" 9 - Listar empréstimos");
        System.out.println("10 - Buscar livro por título");
        System.out.println("--- Empréstimos ---");
        System.out.println("11 - Emprestar");
        System.out.println("12 - Devolver");
        System.out.println("13 - Detalhes de um empréstimo");
        System.out.println("14 - Registrar devolução direta");
        System.out.println("15 - Testar multa");
        System.out.println("--- Pessoas / edição ---");
        System.out.println("16 - Dados do usuário (limite, empréstimos ativos)");
        System.out.println("17 - Editar livro");
        System.out.println("18 - Editar usuário");
        System.out.println("19 - Editar bibliotecário");
        System.out.println("--- Internet ---");
        System.out.println("20 - Cadastrar livro pela internet (API), em inglês (pois estamos em uma GlobalClass)");
        System.out.println(" 0 - Sair");
    }


    private static void cadastrarLivroFisico() {
        System.out.println("-- Novo livro físico --");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        int paginas = lerInt("Número de páginas: ");
        String idioma = lerTexto("Idioma: ");
        String isbn = lerTexto("ISBN: ");
        LivroFisico livro = new LivroFisico(titulo, autor, true, paginas, idioma);
        livro.getIsbn(isbn); // no código da classe, o "setter" do ISBN se chama getIsbn(String)
        biblioteca.cadastrarLivro(livro);
        System.out.println("Livro físico cadastrado.");
    }

    private static void cadastrarEbook() {
        System.out.println("-- Novo ebook --");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        int paginas = lerInt("Número de páginas: ");
        String idioma = lerTexto("Idioma: ");
        String isbn = lerTexto("ISBN: ");
        Ebook ebook = new Ebook(titulo, autor, true, paginas, idioma);
        ebook.getIsbn(isbn); // mesmo caso do LivroFisico
        biblioteca.cadastrarLivro(ebook);
        System.out.println("Ebook cadastrado.");
    }

    private static void cadastrarRevista() {
        System.out.println("-- Nova revista --");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        int paginas = lerInt("Número de páginas: ");
        String idioma = lerTexto("Idioma: ");
        String issn = lerTexto("ISSN: ");
        Revista revista = new Revista(titulo, autor, true, paginas, idioma, issn);
        biblioteca.cadastrarLivro(revista);
        System.out.println("Revista cadastrada.");
    }

    private static void cadastrarUsuario() {
        System.out.println("-- Novo usuário --");
        String nome = lerTexto("Nome: ");
        String cpf = lerTexto("CPF: ");
        String email = lerTexto("Email: ");
        String matricula = lerTexto("Matrícula: ");
        Usuario usuario = new Usuario(nome, cpf, email, matricula, new ArrayList<>());
        biblioteca.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado.");
    }

    private static void cadastrarBibliotecario() {
        System.out.println("-- Novo bibliotecário --");
        String nome = lerTexto("Nome: ");
        String cpf = lerTexto("CPF: ");
        String email = lerTexto("Email: ");
        String cod = lerTexto("Código de funcionário: ");
        bibliotecarios.add(new Bibliotecario(nome, cpf, email, cod));
        System.out.println("Bibliotecário cadastrado.");
    }

    private static void cadastrarLivroPelaApi() {
        System.out.println("-- Novo livro pela internet --");
        String titulo = lerTexto("Título para buscar: ");
        try {
            LivroFisico livro = api.buscarPorTitulo(titulo);
            biblioteca.cadastrarLivro(livro);
            System.out.println("Livro cadastrado: " + descreverLivro(livro));
        } catch (Exception e) {
            System.out.println("Não foi possível buscar o livro: " + e.getMessage());
        }
    }


    private static void listarLivros() {
        List<Livro> livros = biblioteca.getLivros();
        System.out.println("-- Livros (" + livros.size() + ") --");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println("[" + i + "] " + descreverLivro(livros.get(i)));
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = biblioteca.getUsuarios();
        System.out.println("-- Usuários (" + usuarios.size() + ") --");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] " + descreverUsuario(usuarios.get(i)));
        }
    }

    private static void listarBibliotecarios() {
        System.out.println("-- Bibliotecários (" + bibliotecarios.size() + ") --");
        for (int i = 0; i < bibliotecarios.size(); i++) {
            System.out.println("[" + i + "] " + descreverBibliotecario(bibliotecarios.get(i)));
        }
    }

    private static void listarEmprestimos() {
        List<Emprestimo> emprestimos = biblioteca.getEmprestimos();
        System.out.println("-- Empréstimos (" + emprestimos.size() + ") --");
        for (int i = 0; i < emprestimos.size(); i++) {
            System.out.println("[" + i + "] " + descreverEmprestimo(emprestimos.get(i)));
        }
    }

    private static void buscarPorTitulo() {
        String trecho = lerTexto("Trecho do título: ");
        List<Livro> resultado = biblioteca.buscarPorTitulo(trecho);
        System.out.println("-- Resultado (" + resultado.size() + ") --");
        for (Livro l : resultado) {
            System.out.println(descreverLivro(l));
        }
    }


    private static void emprestar() {
        Usuario usuario = escolherUsuario();
        if (usuario == null) return;
        Livro livro = escolherLivro();
        if (livro == null) return;
        try {
            System.out.println(biblioteca.emprestar(usuario, livro));
        } catch (RegistroNaoEncontradoException e) {
            System.out.println("Não encontrado: " + e.getMessage());
        } catch (LivroIndisponivelException e) {
            System.out.println("Indisponível: " + e.getMessage());
        } catch (LimiteEmprestimosException e) {
            System.out.println("Limite atingido: " + e.getMessage());
        }
    }

    private static void devolver() {
        Usuario usuario = escolherUsuario();
        if (usuario == null) return;
        Livro livro = escolherLivro();
        if (livro == null) return;
        try {
            System.out.println(biblioteca.devolver(usuario, livro));
        } catch (RegistroNaoEncontradoException e) {
            System.out.println("Não encontrado: " + e.getMessage());
        }
    }

    private static void detalhesEmprestimo() {
        Emprestimo e = escolherEmprestimo();
        if (e == null) return;
        System.out.println("-- Detalhes do empréstimo --");
        System.out.println("Usuário: " + e.getUsuario().getNome());
        System.out.println("Livro: " + e.getLivro().getTitulo());
        System.out.println("Data do empréstimo: " + e.getData());
        System.out.println("Prazo (dias): " + e.getPrazo());
        System.out.println("Data prevista de devolução: " + e.getDataPrevistaDevolucao());
        System.out.println("getStatus(): " + e.getStatus());
        System.out.println("estaAtivo(): " + e.estaAtivo());
        System.out.println("estaAtrasado(): " + e.estaAtrasado());
        System.out.println("calcularMulta(): R$ " + e.calcularMulta());
    }

    private static void registrarDevolucaoDireta() {
        Emprestimo e = escolherEmprestimo();
        if (e == null) return;
        e.registrarDevolucao();
        System.out.println("registrarDevolucao() chamado. estaAtivo() agora: " + e.estaAtivo());
        System.out.println("(Obs.: este método só mexe no Emprestimo, não deixa o livro disponível de novo.)");
    }

    private static void testarMulta() {
        Livro livro = escolherLivro();
        if (livro == null) return;
        if (livro instanceof Multavel) {
            Multavel m = (Multavel) livro;
            System.out.println("calcularMulta(): R$ " + m.calcularMulta());
            int dias = lerInt("Dias de atraso: ");
            System.out.println("calcularMulta(" + dias + "): R$ " + m.calcularMulta(dias));
        } else {
            System.out.println("Este item (" + livro.getClass().getSimpleName() + ") não é Multavel.");
        }
    }


    private static void dadosUsuario() {
        Usuario u = escolherUsuario();
        if (u == null) return;
        System.out.println(descreverUsuario(u));
        System.out.println("getLimiteEmprestimos(): " + u.getLimiteEmprestimos());
        System.out.println("contarEmprestimosAtivos(): " + u.contarEmprestimosAtivos());
        System.out.println("Empréstimos do usuário (" + u.getEmprestimos().size() + "):");
        for (Emprestimo e : u.getEmprestimos()) {
            System.out.println("  - " + descreverEmprestimo(e));
        }
    }


    private static void editarLivro() {
        Livro l = escolherLivro();
        if (l == null) return;
        System.out.println("1-Título  2-Autor  3-Páginas  4-Idioma  5-Disponível  6-Específico do tipo");
        int op = lerInt("Campo: ");
        switch (op) {
            case 1: l.setTitulo(lerTexto("Novo título: ")); break;
            case 2: l.setAutor(lerTexto("Novo autor: ")); break;
            case 3: l.setNumeroPaginas(lerInt("Novas páginas: ")); break;
            case 4: l.setIdioma(lerTexto("Novo idioma: ")); break;
            case 5: l.setDisponivel(lerTexto("Disponível? (s/n): ").equalsIgnoreCase("s")); break;
            case 6:
                if (l instanceof Revista) {
                    ((Revista) l).setIssn(lerTexto("Novo ISSN: "));
                } else if (l instanceof LivroFisico) {
                    ((LivroFisico) l).getIsbn(lerTexto("Novo ISBN: "));
                } else if (l instanceof Ebook) {
                    ((Ebook) l).getIsbn(lerTexto("Novo ISBN: "));
                }
                break;
            default: System.out.println("Opção inválida."); return;
        }
        System.out.println("Atualizado: " + descreverLivro(l));
    }

    private static void editarUsuario() {
        Usuario u = escolherUsuario();
        if (u == null) return;
        System.out.println("1-Nome  2-CPF  3-Email  4-Matrícula  5-Adicionar empréstimo (adicionarEmprestimo)  6-Substituir lista de empréstimos (setEmprestimos)");
        int op = lerInt("Campo: ");
        switch (op) {
            case 1: u.setNome(lerTexto("Novo nome: ")); break;
            case 2: u.setCpf(lerTexto("Novo CPF: ")); break;
            case 3: u.setEmail(lerTexto("Novo email: ")); break;
            case 4: u.setMatricula(lerTexto("Nova matrícula: ")); break;
            case 5:
                Livro livro = escolherLivro();
                if (livro == null) return;
                u.adicionarEmprestimo(new Emprestimo(u, livro));
                break;
            case 6:
                u.setEmprestimos(new ArrayList<>());
                System.out.println("Lista de empréstimos do usuário foi zerada.");
                break;
            default: System.out.println("Opção inválida."); return;
        }
        System.out.println("Atualizado: " + descreverUsuario(u));
    }

    private static void editarBibliotecario() {
        if (bibliotecarios.isEmpty()) {
            System.out.println("Nenhum bibliotecário cadastrado.");
            return;
        }
        listarBibliotecarios();
        int i = lerInt("Índice: ");
        if (i < 0 || i >= bibliotecarios.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Bibliotecario b = bibliotecarios.get(i);
        System.out.println("1-Nome  2-CPF  3-Email  4-Código de funcionário");
        int op = lerInt("Campo: ");
        switch (op) {
            case 1: b.setNome(lerTexto("Novo nome: ")); break;
            case 2: b.setCpf(lerTexto("Novo CPF: ")); break;
            case 3: b.setEmail(lerTexto("Novo email: ")); break;
            case 4: b.setCodFuncionario(lerTexto("Novo código: ")); break;
            default: System.out.println("Opção inválida."); return;
        }
        System.out.println("Atualizado: " + descreverBibliotecario(b));
    }


    private static Livro escolherLivro() {
        List<Livro> livros = biblioteca.getLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return null;
        }
        listarLivros();
        int i = lerInt("Índice do livro: ");
        if (i < 0 || i >= livros.size()) {
            System.out.println("Índice inválido.");
            return null;
        }
        return livros.get(i);
    }

    private static Usuario escolherUsuario() {
        List<Usuario> usuarios = biblioteca.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return null;
        }
        listarUsuarios();
        int i = lerInt("Índice do usuário: ");
        if (i < 0 || i >= usuarios.size()) {
            System.out.println("Índice inválido.");
            return null;
        }
        return usuarios.get(i);
    }

    private static Emprestimo escolherEmprestimo() {
        List<Emprestimo> emprestimos = biblioteca.getEmprestimos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return null;
        }
        listarEmprestimos();
        int i = lerInt("Índice do empréstimo: ");
        if (i < 0 || i >= emprestimos.size()) {
            System.out.println("Índice inválido.");
            return null;
        }
        return emprestimos.get(i);
    }


    private static String descreverLivro(Livro l) {
        String situacao;
        if (l.isDisponivel()) {
            situacao = "Disponível";
        } else {
            situacao = "Emprestado";
        }
        String extra = "";
        if (l instanceof LivroFisico) {
            extra = " | ISBN: " + ((LivroFisico) l).getIsbn();
        } else if (l instanceof Ebook) {
            extra = " | ISBN: " + ((Ebook) l).getIsbn() + " | Formato: " + ((Ebook) l).getFormato();
        } else if (l instanceof Revista) {
            extra = " | ISSN: " + ((Revista) l).getIssn();
        }
        return l.getClass().getSimpleName() + " | " + l.getTitulo() + " | " + l.getAutor()
                + " | " + l.getNumeroPaginas() + " págs | " + l.getIdioma()
                + " | " + situacao
                + " | limite: " + l.getLimiteEmprestimos()
                + " | prazoEmprestimoDias: " + l.getPrazoEmprestimoDias()
                + " | getPrazo(): " + l.getPrazo()
                + extra;
    }

    private static String descreverUsuario(Usuario u) {
        return u.getNome() + " | CPF: " + u.getCpf() + " | " + u.getEmail()
                + " | matrícula: " + u.getMatricula()
                + " | nº empréstimos: " + u.getEmprestimos().size();
    }

    private static String descreverBibliotecario(Bibliotecario b) {
        return b.getNome() + " | CPF: " + b.getCpf() + " | " + b.getEmail()
                + " | cód: " + b.getCodFuncionario()
                + " | limite: " + b.getLimiteEmprestimos();
    }

    private static String descreverEmprestimo(Emprestimo e) {
        return e.getUsuario().getNome() + " -> " + e.getLivro().getTitulo()
                + " | data: " + e.getData()
                + " | prazo: " + e.getPrazo()
                + " | ativo: " + e.getStatus();
    }


    private static String lerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }
}