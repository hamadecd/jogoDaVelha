import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoDaVelhaGP06 {

    public static void main(String[] args) {

        // Importando o Util Scanner
        Scanner scan = new Scanner(System.in);

        // Entrada de Dados
        System.out.println("Digite o nome do primeiro jogador, que vai atuar com X : ");
        String jog1 = scan.next();
        System.out.println("Digite o nome do segundo jogador, que vai atuar com O : ");
        String jog2 = scan.next();
        int partidas = 0;
        boolean continua = true;

        do {
            // Tratamento de erro para caso de entrada do número de partidas
            try {
                System.out.println("Digite o número de partidas: ");
                partidas = scan.nextInt();
                if (partidas == 0) {
                    throw new IllegalArgumentException();
                }
                continua = false;
            } catch (InputMismatchException erro) {
                System.err.println("O valor deve ser um número.");
                scan.nextLine();
            } catch (IllegalArgumentException erro) {
                System.err.println("O valor deve ser maior que 0.");
                scan.nextLine();
            }

        } while (continua);


        // Montagem do Tabuleiro
        String[][] matrizJogo = new String[3][3];
        zerarTabela(matrizJogo);

        int pontosJog1 = 0;
        int pontosJog2 = 0;
        int ponto;
        int complet;
        int p; //indica partida atual

        // Controles da Partida
        for (p = 0; p <= partidas - 1; p++) {

            int contador = 0;

            // Tratamento de erro para entrada inválida
            do {
                int linha = 0;
                int coluna = 0;

                try {
                    System.out.print("Digite a linha ( 1 a 3 ):");
                    linha = scan.nextInt();
                    System.out.print("Digite a coluna ( 1 a 3 ):");
                    coluna = scan.nextInt();
                    if (linha > 3 || linha < 1 || coluna > 3 || coluna < 1) {
                        IllegalArgumentException erro = new IllegalArgumentException();
                        throw erro;
                    }
                } catch (InputMismatchException erro) {
                    System.err.println("O valor deve ser um número");
                    scan.nextLine();
                } catch (RuntimeException e) {
                    System.err.println("Digitou valor inválido");
                }

                int jogador = (contador % 2 == 0) ? 1 : 2;
                imprimeTabela(jogador, matrizJogo, linha, coluna);

                // Verifica linhas, colunas e diagonais para pontuar
                ponto = Point(matrizJogo);
                if (ponto == 1) {
                    if (jogador == 1) {
                        pontosJog1++;
                        System.out.println(jog1 + " Venceu esta partida!");
                    } else {
                        pontosJog2++;
                        System.out.println(jog2 + " Venceu esta partida!");
                    }
                }

                // Verifica se tabela está cheia
                complet = tabelaCompleta(matrizJogo);
                if (complet == 1) {
                    System.out.println("Tabela cheia");
                    zerarTabela(matrizJogo);
                }

                contador++;
            } while ((pontosJog1 == 0) && (pontosJog2 == 0));

        }

        if (p == partidas) {
            if (pontosJog1 == pontosJog2) {
                partidas++;
            } else if (pontosJog1 > pontosJog2) {
                System.out.println("O VENCEDOR é " + jog1);
            } else {
                System.out.println("O VENCEDOR é " + jog2);
            }
        }

        scan.close();
    }

    // Limpa a tabela
    public static String[][] zerarTabela(String[][] matrizJogo) {
        for (int i = 0; i < matrizJogo.length; i++) {
            for (int j = 0; j < matrizJogo[i].length; j++) {
                matrizJogo[i][j] = " ";
                System.out.print("[" + matrizJogo[i][j] + "]");
            }
            System.out.println(" ");
        }
        return matrizJogo;
    }

    public static String[][] imprimeTabela(int jogador, String[][] matrizJogo, int linha, int coluna) {
        for (int i = 0; i < matrizJogo.length; i++) {
            for (int j = 0; j < matrizJogo[i].length; j++) {
                if ((linha - 1) == i && (coluna - 1) == j) {
                    if (jogador == 1) {
                        matrizJogo[i][j] = "X";
                    } else {
                        matrizJogo[i][j] = "O";
                    }
                }
                System.out.print("[" + matrizJogo[i][j] + "]");
            }
            System.out.println(" ");
        }
        return matrizJogo;
    }

    public static int Point(String[][] matrizJogo) {
        // Conferir Linhas e Colunas
        int ponto = 0;
        for (int i = 0; i < 3; i++) {
            if (matrizJogo[i][0] != " ") {
                if ((matrizJogo[i][0] == matrizJogo[i][1]) && (matrizJogo[i][1] == matrizJogo[i][2])) {
                    ponto = 1;
                }
            }
            if (matrizJogo[0][i] != " ") {
                if ((matrizJogo[0][i] == matrizJogo[1][i]) && (matrizJogo[1][i] == matrizJogo[2][i])) {
                    ponto = 1;
                }
            }
        }
        // Conferir Diagonais
        if (matrizJogo[1][1] != " ") {
            if ((matrizJogo[0][0] == matrizJogo[1][1]) && (matrizJogo[1][1] == matrizJogo[2][2])) {
                ponto = 1;
            }
            if ((matrizJogo[0][2] == matrizJogo[1][1]) && (matrizJogo[1][1] == matrizJogo[2][0])) {
                ponto = 1;
            }
        }
        return ponto;
    }

    // Verifica se tabela está cheia
    public static int tabelaCompleta(String[][] matrizJogo) {
        int full = 1;
        for (int i = 0; i < matrizJogo.length; i++) {
            for (int j = 0; j < matrizJogo[i].length; j++) {
                if (matrizJogo[i][j] == " ") {
                    full = 0;
                }
            }
        }
        return full;
    }


    public static int UnidadeMatriz(String[][] matrizJogo) {
        int unid = 1;
        for (int i = 0; i < matrizJogo.length; i++) {
            for (int j = 0; j < matrizJogo[i].length; j++) {
                if (matrizJogo[i][j] == " ") {
                    unid = 0;
                }
            }
        }
        return unid;
    }

}