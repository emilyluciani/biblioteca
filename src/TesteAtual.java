import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TesteAtual {

    private static int ok = 0;
    private static int falhas = 0;

    interface Acao { void executar() throws Exception; }

    private static String excecaoLancada(Acao acao) {
        try {
            acao.executar();
            return null;
        } catch (Exception e) {
            return e.getClass().getSimpleName();
        }
    }

    private static void verificar(String descricao, boolean condicao) {
        if (condicao) {
            ok++;
            System.out.println("  [OK]    " + descricao);
        } else {
            falhas++;
            System.out.println("  [FALHA] " + descricao);
        }
    }

    private static void esperarExcecao(String descricao, String esperada, Acao acao) {
        String obtida = excecaoLancada(acao);
        boolean passou = esperada.equals(obtida);
        verificar(descricao + " -> esperado: " + esperada + ", obtido: " + (obtida == null ? "nenhuma" : obtida), passou);
    }

    public static void main(String[] args) {
        testarFluxoNormal();
        testarExcecoes();
        testarPersistencia();

        System.out.println("\n==== RESUMO ====");
        System.out.println("Passaram: " + ok + " | Falharam: " + falhas);
    }

    private static Usuario novoUsuario(String nome, String matricula) {
        return new Usuario(nome, "000.000.000-00", nome.toLowerCase() + "@email.com", matricula, new ArrayList<>());
    }

    private static LivroFisico novoLivro(String titulo) {
        return new LivroFisico(titulo, "Autor Teste", true, 100, "Português");
    }

    private static void testarFluxoNormal() {
        System.out.println("\n--- Teste 1: fluxo normal (emprestar / devolver) ---");
        try {
            Biblioteca b = new Biblioteca();
            Usuario u = novoUsuario("Ana", "M1");
            LivroFisico l = novoLivro("Dom Casmurro");
            b.cadastrarUsuario(u);
            b.cadastrarLivro(l);

            verificar("Livro cadastrado aparece em getLivros()", b.getLivros().size() == 1);
            verificar("Usuário cadastrado aparece em getUsuarios()", b.getUsuarios().size() == 1);
            verificar("Busca por título encontra o livro", b.buscarPorTitulo("casmurro").size() == 1);

            b.emprestar(u, l);
            verificar("Após emprestar, livro fica indisponível", !l.isDisponivel());
            verificar("Após emprestar, há 1 empréstimo na biblioteca", b.getEmprestimos().size() == 1);

            b.devolver(u, l);
            verificar("Após devolver, livro volta a ficar disponível", l.isDisponivel());
        } catch (Exception e) {
            verificar("Fluxo normal não deveria lançar exceção (lançou " + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + ")", false);
        }
    }

    private static void testarExcecoes() {
        System.out.println("\n--- Teste 2: tratamento de exceções ---");

        try {
            Biblioteca b = new Biblioteca();
            Usuario u1 = novoUsuario("Ana", "M1");
            Usuario u2 = novoUsuario("Beto", "M2");
            LivroFisico l = novoLivro("Livro Unico");
            b.cadastrarUsuario(u1);
            b.cadastrarUsuario(u2);
            b.cadastrarLivro(l);
            b.emprestar(u1, l);
            esperarExcecao("Emprestar livro já emprestado", "LivroIndisponivelException",
                    () -> b.emprestar(u2, l));
        } catch (Exception e) {
            verificar("Preparação do cenário 'livro indisponível' (" + e + ")", false);
        }

        try {
            Biblioteca b = new Biblioteca();
            Usuario u = novoUsuario("Carla", "M3");
            b.cadastrarUsuario(u);
            String excecao = null;
            int emprestados = 0;
            for (int i = 0; i < 50 && excecao == null; i++) {
                LivroFisico l = novoLivro("Livro " + i);
                b.cadastrarLivro(l);
                excecao = excecaoLancada(() -> b.emprestar(u, l));
                if (excecao == null) emprestados++;
            }
            System.out.println("          (emprestou " + emprestados + " livros antes de ser barrado)");
            verificar("Limite de empréstimos barra o usuário com LimiteEmprestimosException (obtido: "
                    + (excecao == null ? "nenhuma" : excecao) + ")", "LimiteEmprestimosException".equals(excecao));
        } catch (Exception e) {
            verificar("Preparação do cenário 'limite' (" + e + ")", false);
        }

        try {
            Biblioteca b = new Biblioteca();
            Usuario u = novoUsuario("Davi", "M4");
            LivroFisico l = novoLivro("Nunca Emprestado");
            b.cadastrarUsuario(u);
            b.cadastrarLivro(l);
            esperarExcecao("Devolver livro que não foi emprestado", "RegistroNaoEncontradoException",
                    () -> b.devolver(u, l));
        } catch (Exception e) {
            verificar("Preparação do cenário 'devolução inválida' (" + e + ")", false);
        }

        try {
            Biblioteca b = new Biblioteca();
            Usuario naoCadastrado = novoUsuario("Fantasma", "M9");
            LivroFisico livroNaoCadastrado = novoLivro("Fantasma");
            esperarExcecao("Emprestar com usuário/livro não cadastrados", "RegistroNaoEncontradoException",
                    () -> b.emprestar(naoCadastrado, livroNaoCadastrado));
        } catch (Exception e) {
            verificar("Preparação do cenário 'não cadastrado' (" + e + ")", false);
        }
    }

    private static void testarPersistencia() {
        System.out.println("\n--- Teste 3: persistência de dados ---");
        File arqBiblioteca = new File("teste_biblioteca.dat");
        File arqBibliotecarios = new File("teste_bibliotecarios.dat");
        Persistencia p = new Persistencia();

        try {
            Biblioteca b = new Biblioteca();
            Usuario u = novoUsuario("Eva", "M5");
            LivroFisico l1 = novoLivro("Persistente 1");
            LivroFisico l2 = novoLivro("Persistente 2");
            b.cadastrarUsuario(u);
            b.cadastrarLivro(l1);
            b.cadastrarLivro(l2);
            b.emprestar(u, l1);

            List<Bibliotecario> bibs = new ArrayList<>();
            bibs.add(new Bibliotecario("Fábio", "111.111.111-11", "fabio@email.com", "F1"));

            p.salvar(b, arqBiblioteca.getName());
            p.salvar(bibs, arqBibliotecarios.getName());
            verificar("Arquivos foram criados em disco", arqBiblioteca.exists() && arqBibliotecarios.exists());

            Biblioteca lida = (Biblioteca) p.carregar(arqBiblioteca.getName());
            @SuppressWarnings("unchecked")
            List<Bibliotecario> bibsLidos = (List<Bibliotecario>) p.carregar(arqBibliotecarios.getName());

            verificar("Livros recarregados: 2", lida.getLivros().size() == 2);
            verificar("Usuários recarregados: 1", lida.getUsuarios().size() == 1);
            verificar("Empréstimos recarregados: 1", lida.getEmprestimos().size() == 1);
            verificar("Título do livro preservado",
                    "Persistente 1".equals(lida.getLivros().get(0).getTitulo()));
            verificar("Estado de disponibilidade preservado (livro 1 emprestado, livro 2 livre)",
                    !lida.getLivros().get(0).isDisponivel() && lida.getLivros().get(1).isDisponivel());
            verificar("Nome do usuário preservado", "Eva".equals(lida.getUsuarios().get(0).getNome()));
            verificar("Bibliotecários recarregados: 1 (cód F1)",
                    bibsLidos.size() == 1 && "F1".equals(bibsLidos.get(0).getCodFuncionario()));
        } catch (Exception e) {
            verificar("Salvar/carregar não deveria lançar exceção (lançou " + e.getClass().getSimpleName()
                    + ": " + e.getMessage() + ")", false);
        } finally {
            arqBiblioteca.delete();
            arqBibliotecarios.delete();
        }

        String excecao = excecaoLancada(() -> new Persistencia().carregar("arquivo_que_nao_existe.dat"));
        verificar("Carregar arquivo inexistente lança exceção (obtido: " + (excecao == null ? "nenhuma" : excecao) + ")",
                excecao != null);
    }
}