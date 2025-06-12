import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    for (Integer[] arr: pc.particionar()) {
      System.out.println(Arrays.toString(arr));
    }

  }

  private boolean podeParticionar_BottomUp(int[] conjunto) {
    int somaConjunto, metadeSoma, somaMenor;
    this.conjunto = conjunto;

    somaConjunto = 0;
    for (int elem: conjunto) somaConjunto += elem;

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

  public Integer[][] particionar() {
    HashSet<Integer> indicesSubconjunto;
    ArrayList<Integer> subconjunto1, subconjunto2;
    int soma, indice;

    if (memo == null || memo[memo.length - 1][memo[0].length - 1] == false) {
      return null;
    }

    indicesSubconjunto = new HashSet<>();
    subconjunto1 = new ArrayList<>();
    subconjunto2 = new ArrayList<>();
    indice = conjunto.length;
    soma = 0;
    for (int elem: conjunto) soma += elem;
    soma /= 2;

    while (soma > 0) {
      if (memo[indice][soma] == true && memo[indice - 1][soma] == true) {
        indice--;
        continue;
      }

      indicesSubconjunto.add(indice - 1);
      soma -= conjunto[indice - 1];
      indice--;
    }

    for (int i = 0; i < conjunto.length; i++) {
      if (indicesSubconjunto.contains(i)) {
        subconjunto1.add(conjunto[i]);
      } else {
        subconjunto2.add(conjunto[i]);
      }
    }

    Integer[][] s = {
      subconjunto1.toArray(new Integer[0]),
      subconjunto2.toArray(new Integer[0])
    };
    return s;
  }

}
