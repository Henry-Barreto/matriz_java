import java.io.*;
import java.util.*;
public class MatrizMultiplicacao {

    // Função para somar dois números
    public static int soma(int a, int b) {
        return a + b;
    }

    // Função para multiplicar dois números
    public static int multiplica(int a, int b) {
        return a * b;
    }

    // Lê matriz de um arquivo txt
    public static int[][] lerMatriz(String caminhoArquivo) throws IOException {
        List<int[]> linhas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] valores = linha.trim().split("\\s+");
            int[] linhaNumeros = new int[valores.length];

            for (int i = 0; i < valores.length; i++) {
                linhaNumeros[i] = Integer.parseInt(valores[i]);
            }

            linhas.add(linhaNumeros);
        }
        reader.close();

        int[][] matriz = new int[linhas.size()][];
        for (int i = 0; i < linhas.size(); i++) {
            matriz[i] = linhas.get(i);
        }

        return matriz;
    }

    // Verifica se matriz é quadrada
    public static boolean isQuadrada(int[][] matriz) {
        for (int[] linha : matriz) {
            if (linha.length != matriz.length) {
                return false;
            }
        }
        return true;
    }

    // Multiplicação de matrizes usando funções soma/multiplica
    public static int[][] multiplicarMatrizes(int[][] A, int[][] B) {
        int n = A.length;
        int[][] resultado = new int[n][n];

        System.out.println("\nPasso a passo da multiplicação:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int somaParcial = 0;
                System.out.print("C[" + i + "][" + j + "] = ");
                for (int k = 0; k < n; k++) {
                    int mult = multiplica(A[i][k], B[k][j]);
                    somaParcial = soma(somaParcial, mult);
                    System.out.print(A[i][k] + "*" + B[k][j]);
                    if (k < n - 1) System.out.print(" + ");
                }
                resultado[i][j] = somaParcial;
                System.out.println(" = " + resultado[i][j]);
            }
        }

        return resultado;
    }

    // Função para imprimir matriz
    public static void imprimirMatriz(int[][] matriz, String nome) {
        System.out.println("\nMatriz " + nome + ":");
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    // Programa principal
    public static void main(String[] args) {
        try {
            // Caminhos dos arquivos com as matrizes
            String caminhoA = "matrizA.txt";
            String caminhoB = "matrizB.txt";

            // Leitura das matrizes
            int[][] matrizA = lerMatriz(caminhoA);
            int[][] matrizB = lerMatriz(caminhoB);

            // Verificação
            if (!isQuadrada(matrizA) || !isQuadrada(matrizB)) {
                System.out.println("Erro: As matrizes devem ser quadradas!");
                return;
            }

            if (matrizA.length != matrizB.length) {
                System.out.println("Erro: As matrizes devem ter o mesmo tamanho!");
                return;
            }

            // Impressão das matrizes de entrada
            imprimirMatriz(matrizA, "A");
            imprimirMatriz(matrizB, "B");

            // Multiplicação e exibição do resultado
            int[][] resultado = multiplicarMatrizes(matrizA, matrizB);
            imprimirMatriz(resultado, "Resultado (A x B)");

        } catch (Exception e) {
            System.out.println("Erro ao executar o programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
