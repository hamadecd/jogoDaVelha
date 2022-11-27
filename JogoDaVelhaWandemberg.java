import java.util.Scanner;

public class JogoDaVelhaWandemberg {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //Variáveis
        String[][] tabuleiro = new String[3][3];
        int npartidas = 0;
        int[] coord = new int[2];

        while (npartidas < 1) {
            System.out.println("Digite o número de partidas maior ou igual a 1:");
            npartidas = scan.nextInt();
        }

        int[] resultados = new int[npartidas];

        for (int i = 0; i <= npartidas; i++) {
            zeraMatriz(tabuleiro);
            imprimeJogo(tabuleiro);
            System.out.print("Partida " + (i + 1) + ", ");
            for (int j = 1; j <= 5; j++) {
                System.out.print("rodada " + j + " e ");
                for (int k = 1; k <= 2; k++) {
                    System.out.println("jogador " + k + ". Entre com as coordenadas (linha, coluna):");

                    //Verificando empate no fim da rodada. Se acontecer, será na vez do segundo jogador e na última rodada
                    if (j == 5 & k == 2) {
                        System.out.println("RODADA ACABOU EM EMPATE!");
                        resultados[i] = 0;
                    } else {
                        //Tratamento com exceções
                        try {
                            coord = pegaCoord(tabuleiro);
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                        if (k == 1) {
                            tabuleiro[coord[0]][coord[1]] = "X";
                            imprimeJogo(tabuleiro);
                            if (verificarLinhas(tabuleiro, "X")) {
                                k = 3;
                                j = 10;

                                //Condição para caso entre na rodada de desempate
                                if (i > npartidas - 1) {
                                    System.out.println("\n\nO JOGADOR 1 VENCEU!!!!!!!!!!!\n\n");
                                } else {
                                    resultados[i] = 1;
                                    System.out.println("\nJogador 1 venceu esta rodada.\n");
                                }
                            }
                        } else {
                            tabuleiro[coord[0]][coord[1]] = "O";
                            imprimeJogo(tabuleiro);
                            if (verificarLinhas(tabuleiro, "O")) {
                                k = 3;
                                j = 10;
                                if (i > npartidas - 1) {
                                    System.out.println("\n\nO JOGADOR 2 VENCEU!!!!!!!!!!!!!\n\n");
                                } else {
                                    resultados[i] = 2;
                                    System.out.println("\nJogador 2 venceu esta rodada.\n");
                                }
                            }
                        }
                    }

                }

            }
            //Ao fim da última rodada, verifica se houve empate
            if (i == npartidas - 1) {
                if (contarResultados(resultados) == 1) {
                    System.out.println("O jogador 1 venceu!!");
                    i++;
                } else if (contarResultados(resultados) == 2) {
                    System.out.println("O jogador 2 venceu!!");
                    i++;
                }
            }

        }

    }

    public static void zeraMatriz(String[][] matriz) {
        matriz[0] = new String[]{" ", " ", " "};
        matriz[1] = new String[]{" ", " ", " "};
        matriz[2] = new String[]{" ", " ", " "};
    }

    public static void imprimeJogo(String[][] matriz) {
        System.out.println("   ___C0_____C1_____C2___");
        System.out.println("L0 |  " + matriz[0][0] + "   |  " + matriz[0][1] + "   |  " + matriz[0][2] + "    |");
        System.out.println("   |_____ |______|_______|");
        System.out.println("L1 |  " + matriz[1][0] + "   |  " + matriz[1][1] + "   |  " + matriz[1][2] + "    |");
        System.out.println("   |_____ |______|_______|");
        System.out.println("L2 |  " + matriz[2][0] + "   |  " + matriz[2][1] + "   |  " + matriz[2][2] + "    |");
        System.out.println("   |______|______|_______|");
    }

    //Função recursiva com tratamento de exceções
    public static int[] verificaJogada() {
        int[] coord = new int[2];

        try {
            coord[0] = scan.nextInt();
            coord[1] = scan.nextInt();

            if (coord[0] < 0 || coord[0] > 3 || coord[1] < 0 || coord[1] > 3) {
                throw new ArrayIndexOutOfBoundsException("Não existem linhas ou colunas com essas coordenadas.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Tente novamente:");
            coord = verificaJogada();
        }

        return coord;
    }

    //Função recursiva com tratamento de exceções
    public static int[] pegaCoord(String[][] matriz) {
        int[] coord = new int[2];

        //Validação usando exceções:
        try {
            coord = verificaJogada();
            if (!matriz[coord[0]][coord[1]].equals(" ")) {
                throw new ArrayIndexOutOfBoundsException("Posição já preenchida. ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Tente novamente: ");
            coord = pegaCoord(matriz);
        }

        if (!matriz[coord[0]][coord[1]].equals(" ")) {
            throw new ArrayIndexOutOfBoundsException("Posiço já preenchida: ");
//            System.out.println("Já preenchido. Tente novamente: ");
//            coord = verificaJogada();
        }

        return coord;
    }

    public static boolean verificarLinhas(String[][] matriz, String tipo) {
        boolean teste = false;
        //Verifica Linhas horizontais e verticais
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0].equals(tipo) & matriz[i][1].equals(tipo) & matriz[i][2].equals(tipo)) {
                teste = true;
            } else if (matriz[0][i].equals(tipo) & matriz[1][i].equals(tipo) & matriz[2][i].equals(tipo)) {
                teste = true;
            }
        }

        //Verifica diagonais
        if (matriz[0][0].equals(tipo) & matriz[1][1].equals(tipo) & matriz[2][2].equals(tipo)) {
            teste = true;
        } else if (matriz[0][2].equals(tipo) & matriz[1][1].equals(tipo) & matriz[2][0].equals(tipo)) teste = true;

        return teste;
    }

    public static int contarResultados(int[] resultados) {
        int jogador1 = 0;
        int jogador2 = 0;
        int teste = -1;
        for (int i = 0; i < resultados.length; i++) {
            if (resultados[i] == 1) {
                jogador1++;
            } else if (resultados[i] == 2) {
                jogador2++;
            }
        }
        // Retornará zero, casa haja empate
        if (jogador1 == jogador2) {
            teste = 0;
        } else if (jogador1 > jogador2) {
            teste = 1;
        } else if (jogador2 > jogador1) {
            teste = 2;
        }

        return teste;
    }


}