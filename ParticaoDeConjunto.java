import java.util.ArrayList;

/* TODO
 * REFATORAR
 * FAZER CLASSE MAIN (TESTES)
 * FAZER README
 */

public class ParticaoDeConjunto {
  // PARA NUMEROS INTEIROS POSITIVOS
  private int[] conjunto;
  private boolean[][] memo;

  public static void main(String[] args) {
    int[] conjunto = {1, 3, 4, 7, 5};
    // int[] conjunto = {1, 4, 3, 1, 2, 1};
    // int[] conjunto = {8, 1, 3, 1, 2, 1};
    // int[] conjunto = {8, 1, 3, 1, 2};
    ParticaoDeConjunto pc = new ParticaoDeConjunto();
    
    System.out.println(pc.podeParticionar_BottomUp(conjunto));
    ArrayList<Integer>[] subconjuntos = pc.particionar();
    System.out.println(subconjuntos[0]);
    System.out.println(subconjuntos[1]);

  }

  public boolean podeParticionar_BottomUp(int[] conjunto) {
    int somaConjunto, metadeSoma, somaMenor;
    this.conjunto = conjunto;
    somaConjunto = getSomaConjunto();
    memo = null;

    if (somaConjunto % 2 != 0) return false;

    metadeSoma = somaConjunto / 2;
    memo = new boolean[conjunto.length + 1][metadeSoma + 1];

    for (int i = 0; i < memo[0].length; i++) {
      memo[0][i] = false;
    }
    memo[0][0] = true;
    
    // memorização bottom-up 
    for (int i = 1; i < memo.length; i++) {
      for (int j = 0; j < memo[0].length; j++) {
        somaMenor = j - conjunto[i-1];
        if (somaMenor >= 0) {
          memo[i][j] = memo[i-1][j] || memo[i-1][somaMenor];
        } else {
          memo[i][j] = memo[i-1][j];
        }
      }
    }

    return memo[memo.length - 1][memo[0].length - 1];
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Integer>[] particionar() {
    int soma, indice;
    boolean podeParticionar, celulaAtual, celulaAcima;
    ArrayList<Integer>[] subconjuntos = new ArrayList[2];
    subconjuntos[0] = new ArrayList<>();
    subconjuntos[1] = new ArrayList<>();
    podeParticionar = memo[memo.length - 1][memo[0].length - 1];

    if (memo == null || podeParticionar == false) {
      return null;
    }

    indice = conjunto.length;
    soma = getSomaConjunto() / 2;

    while (indice > 0) {
      celulaAtual = memo[indice][soma];
      celulaAcima = memo[indice - 1][soma];

      if (soma <= 0 || (celulaAtual == true && celulaAcima == true)) {
        subconjuntos[1].add(conjunto[indice - 1]);
      } else {
        subconjuntos[0].add(conjunto[indice - 1]);
        soma -= conjunto[indice - 1];
      }
      indice--;
    }

    return subconjuntos;
  }

  private int getSomaConjunto() {
    int soma = 0;
    for (int elem: conjunto) {
      soma += elem;
    }
    return soma;
  }

}
