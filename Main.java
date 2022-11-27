import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String tabuleiro[][] = new String[3][3];
        String jogador1 = " X |", jogador2 = " O |", jogador = "";

        System.out.println("Bem vindo ao jogo da velha\nEntre com numeros de 1 a 3\n");

        System.out.println("Informe quantas partidas deseja jogar: ");
        short numeroDePartidas = scanner.nextShort();

        if (numeroDePartidas >= 1) {

            int pontosJogador1 = 0, pontosJogador2 = 0;
            limpaTabuleiro(tabuleiro);
            exibeTabuleiro(tabuleiro);
            short jogadaLinha;
            short jogadaColuna;
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

                    System.out.println("Jogador faça sua jogada: ");
                    jogadaLinha = scanner.nextShort();
                    jogadaColuna = scanner.nextShort();
                    validaPosicao = validaJogada(tabuleiro, jogadaLinha, jogadaColuna);

                    while (validaPosicao == true) {
                        System.out.println("Posição já foi marcada. Escolha outra posição: ");
                        jogadaLinha = scanner.nextShort();
                        jogadaColuna = scanner.nextShort();
                        validaPosicao = validaJogada(tabuleiro, jogadaLinha, jogadaColuna);
                    }

                    tabuleiro[jogadaLinha - 1][jogadaColuna - 1] = jogador;

                    exibeTabuleiro(tabuleiro);

                    if (contador >= 5) {
                        ganhador = verificaVencedor(tabuleiro, jogador);
                        if (ganhador && jogador.equals(" X |")) {
                            pontosJogador1++;
                            System.out.println("Jogador1, você venceu!");
                        } else if (ganhador && jogador.equals(" O |")) {
                            pontosJogador2++;
                            System.out.println("Jogador2, você venceu!");
                        }

                        if (ganhador) {
                            System.out.println("Placar do jogo:\n" +
                                    "Jogador1: " + pontosJogador1 + "\n" +
                                    "Jogador2: " + pontosJogador2);
                            limpaTabuleiro(tabuleiro);
                            exibeTabuleiro(tabuleiro);
                            ganhador = false;
                            contador = 1;
                            numeroDePartidas--;
                        }
                    }

                    if (pontosJogador1 == pontosJogador2 && numeroDePartidas == 0) {
                        System.out.println("O número de partidas acabou em empate, para ter um vencedor, jogue mais uma vez!");
//                        limpaTabuleiro(tabuleiro);
//                        exibeTabuleiro(tabuleiro);
                        ganhador = false;
                        contador = 1;
                        numeroDePartidas++;
                    }

                    contador++;
                }

        } else {
            System.out.println("Número de partidas deve ser maior do que zero. Programa encerrado!");
        }
    }

    public static boolean validaJogada(String[][] tabuleiro, short jogadaLinha, short jogadaColuna) {
        if (tabuleiro[jogadaLinha - 1][jogadaColuna -1].contains("X") || tabuleiro[jogadaLinha - 1][jogadaColuna -1].contains("O")) {
            return true;
        }
        return false;
    }

    public static void exibeTabuleiro(String[][] tabuleiro) {
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                System.out.print(tabuleiro[linha][coluna]);
            }
            System.out.println();
        }
    }

    public static void limpaTabuleiro(String[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = "   |";
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
