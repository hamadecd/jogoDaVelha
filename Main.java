import java.util.Scanner;

public class Main {

    public static void exibeTabuleiro(String tabuleiro[][]) {
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            String impressao = "";
            for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
                if (tabuleiro[linha][coluna] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(tabuleiro[linha][coluna]);
                }
            }
            System.out.println("|");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tabuleiro[][] = new String[3][3];
        String jogador1 = "X", jogador2 = "O";

        exibeTabuleiro(tabuleiro);

//        System.out.println("Informe quantas partidas deseja jogar: ");
//        short numeroDePartidas = scanner.nextShort();
//
//        if (numeroDePartidas >= 1) {
//            for (int linha = 0; linha < tabuleiro.length; linha++) {
//                for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
//                    System.out.print(tabuleiro[linha][coluna]);
//                }
//                System.out.println();
//            }
//            short contador = 0;
//            while (contador < 9) {
//                System.out.println("Jogador faça sua jogada: ");
//                short jogadaLinha = scanner.nextShort();
//                short jogadaColuna = scanner.nextShort();
//
//                for (int linha = 0; linha < tabuleiro.length; linha++) {
//                    for (int coluna = 0; coluna < tabuleiro[linha].length; coluna++) {
////                        System.out.println("Insira o número da linha " + linha + " e coluna " + coluna);
//                        if (tabuleiro[linha][coluna].contains("X") || tabuleiro[linha][coluna].contains("O")) {
//
//                        } else {
//                            tabuleiro[jogadaLinha-1][jogadaColuna-1] = jogador1;
//                        }
//                        System.out.print(tabuleiro[linha][coluna]);
//                    }
//                    System.out.println();
//                }
//
//                contador++;
//            }
//
//        } else {
//            System.out.println("Número de partidas deve ser maior do que zero. Programa encerrado!");
//        }

    }
}
