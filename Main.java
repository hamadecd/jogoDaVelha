import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static int jogadaLinha, jogadaColuna;

    public static void main(String[] args) {
        String tabuleiro[][] = new String[3][3];
        String jogador1 = " X ", jogador2 = " O ", jogador = "";

        System.out.println("Bem vindo ao jogo da velha\nEntre com numeros de 1 a 3 para escolher as posições\n");

        int numeroDePartidas;
        do {
            System.out.println("Informe quantas partidas deseja jogar: ");
            numeroDePartidas = validaCaractere();
        } while (numeroDePartidas == -1);

        if (numeroDePartidas >= 1) {

            int pontosJogador1 = 0, pontosJogador2 = 0;
            limpaTabuleiro(tabuleiro);
            exibeTabuleiro(tabuleiro);

            short contador = 1;
            boolean verifica = true;
            boolean ganhador = false;
            boolean validaPosicao = true;

            while (contador <= 9 && numeroDePartidas > 0) {

                if (verifica) {
                    jogador = jogador1;
                    verifica = false;
                } else {
                    jogador = jogador2;
                    verifica = true;
                }

                do {
                    validaPosicao = validaCaractere(tabuleiro, jogador);
                } while (validaPosicao == false);

                exibeTabuleiro(tabuleiro);

                if (contador >= 5) {
                    ganhador = verificaVencedor(tabuleiro, jogador);
                    if (ganhador && jogador.equals(" X ")) {
                        pontosJogador1++;
                        System.out.println("Jogador1, você venceu!");
                    } else if (ganhador && jogador.equals(" O ")) {
                        pontosJogador2++;
                        System.out.println("Jogador2, você venceu!");
                    }

                    if (ganhador) {
                        System.out.println("Placar do jogo:\n" +
                                "Jogador1: " + pontosJogador1 + "\n" +
                                "Jogador2: " + pontosJogador2);
                        limpaTabuleiro(tabuleiro);
                        ganhador = false;
                        contador = 0;
                        numeroDePartidas--;
                    }
                }

                if (contador == 9 && numeroDePartidas == 0) {
                    contador = 0;
                    numeroDePartidas++;
                    limpaTabuleiro(tabuleiro);
                }

                if (pontosJogador1 == pontosJogador2 && numeroDePartidas == 0) {
                    contador = 0;
                    numeroDePartidas++;
                    limpaTabuleiro(tabuleiro);
                }

                if (numeroDePartidas != 1 && contador == 9) {
                    System.out.println("A rodada ficou empatada!");
                    contador = 0;
                    numeroDePartidas--;
                    limpaTabuleiro(tabuleiro);
                }

                contador++;
            }

        } else {
            System.out.println("Número de partidas deve ser maior do que zero. Programa encerrado!");
        }
    }

    public static void preparaTabuleiro(String tabuleiro[][]) {
        limpaTabuleiro(tabuleiro);
        exibeTabuleiro(tabuleiro);
    }

    public static int validaCaractere() {
        int numero;
        try {
            return numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERRO: valor inválido, informe um número inteiro positivo");
            scanner.nextLine(); // para sair da linha de erro do scanner
            return -1;
        }
    }

    public static boolean validaCaractere(String[][] tabuleiro, String jogador) {
        boolean validaPosicao = false;
        try {
            System.out.println("Jogador escolha o número da linha: ");
            jogadaLinha = scanner.nextInt();
            System.out.println("Jogador escolha o número da coluna: ");
            jogadaColuna = scanner.nextInt();
            validaPosicao = validaJogada(tabuleiro, jogadaLinha, jogadaColuna);
            if (validaPosicao) {
                tabuleiro[jogadaLinha - 1][jogadaColuna - 1] = jogador;
            }
        } catch (InputMismatchException e) {
            System.out.println("ERRO: valor inválido, informe um número inteiro positivo");
            scanner.nextLine();
        }
        return validaPosicao;
    }

    public static boolean validaJogada(String[][] tabuleiro, int jogadaLinha, int jogadaColuna) {
        try {

            if (jogadaLinha < 1 || jogadaLinha > 3 || jogadaColuna < 1 || jogadaColuna > 3) {
                exibeTabuleiro(tabuleiro);
                throw new JogoException("ERRO: valor inválido, escolha os valores 1, 2 ou 3\n");
            }

            if (tabuleiro[jogadaLinha - 1][jogadaColuna - 1].contains("X") || tabuleiro[jogadaLinha - 1][jogadaColuna - 1].contains("O")) {
                exibeTabuleiro(tabuleiro);
                throw new JogoException("ERRO: posição já foi utilizada.\n");
            }

        } catch (JogoException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void exibeTabuleiro(String[][] tabuleiro) {
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                System.out.print(tabuleiro[linha][coluna]);
                if (coluna < (tabuleiro.length - 1)) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public static void limpaTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = "   ";
            }
        }
    }

    public static boolean verificaVencedor(String tabuleiro[][], String jogador) {

        for (int linha = 0; linha < tabuleiro.length; linha++) {
            if (tabuleiro[linha][0].contains(jogador) && tabuleiro[linha][1].contains(jogador) && tabuleiro[linha][2].contains(jogador)) {
                return true;
            }
        }

        for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
            if (tabuleiro[0][coluna].contains(jogador) && tabuleiro[1][coluna].contains(jogador) && tabuleiro[2][coluna].contains(jogador)) {
                return true;
            }
        }

        if (tabuleiro[0][0].contains(jogador) && tabuleiro[1][1].contains(jogador) && tabuleiro[2][2].contains(jogador)) {
            return true;
        }

        if (tabuleiro[0][2].contains(jogador) && tabuleiro[1][1].contains(jogador) && tabuleiro[2][0].contains(jogador)) {
            return true;
        }

        return false;
    }

}