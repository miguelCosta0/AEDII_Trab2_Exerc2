import java.util.ArrayList;
import java.util.List;

public class ParticaoDeConjunto {
  private int[] conjunto;
  private boolean[][] memo;

  public boolean podeParticionar(int[] conjunto) {
    int somaConjunto, metadeSoma, somaMenor;
    this.conjunto = conjunto;
    this.memo = null;
    somaConjunto = getSomaConjunto(conjunto);

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

  // @SuppressWarnings("unchecked")
  public List<Integer>[] particionar() {
    List<Integer>[] subconjuntos;
    boolean celulaAtual, celulaAcima;
    int soma;

    if (
      memo == null || 
      memo[memo.length - 1][memo[0].length - 1] == false
    ) {
      return null;
    }
    
    subconjuntos = new ArrayList[2];
    subconjuntos[0] = new ArrayList<>();
    subconjuntos[1] = new ArrayList<>();
    soma = getSomaConjunto(conjunto) / 2;

    for (int i = conjunto.length; i > 0; i--) {
      celulaAtual = memo[i][soma];
      celulaAcima = memo[i - 1][soma];

      if (soma <= 0 || (celulaAtual == true && celulaAcima == true)) {
        subconjuntos[1].add(conjunto[i - 1]);
      } else {
        subconjuntos[0].add(conjunto[i - 1]);
        soma -= conjunto[i - 1];
      }
    }

    return subconjuntos;
  }

  public int getSomaConjunto(int[] conjunto) {
    int soma = 0;
    for (int elem: conjunto) {
      soma += elem;
    }
    return soma;
  }

}
