public class ParticaoDeConjunto {
  // PARA NUMEROS INTEIROS POSITIVOS
  private int[] conjunto;
  private Boolean[][] memo;

  public static void main(String[] args) {
    int[] conjunto = {1, 3, 4, 7, 5};
    // int[] conjunto = {1, 4, 3, 1, 2, 1};
    // int[] conjunto = {8, 1, 3, 1, 2, 1};
    // int[] conjunto = {8, 1, 3, 1, 2};
    ParticaoDeConjunto pc = new ParticaoDeConjunto();
    
    System.out.println(pc.podeParticionar(conjunto));
    System.out.println(pc.podeParticionar_bottomUp(conjunto));

  }

  private boolean podeParticionar_bottomUp(int[] conjunto) {
    this.conjunto = conjunto;
    int somaConjunto, metadeSoma, somaMenor;

    somaConjunto = 0;
    for (int elem: conjunto) somaConjunto += elem;

    memo = null;
    if (somaConjunto % 2 != 0) return false;

    metadeSoma = somaConjunto / 2;
    memo = new Boolean[conjunto.length + 1][metadeSoma + 1];

    for (int i = 0; i < memo[0].length; i++) {
      memo[0][i] = false;
    }
    memo[0][0] = true;

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

  public boolean podeParticionar(int[] conjunto) {
    this.conjunto = conjunto;
    int somaConjunto, metadeSoma;

    somaConjunto = 0;
    for (int elem: conjunto) somaConjunto += elem;

    if (somaConjunto % 2 != 0) return false;

    metadeSoma = somaConjunto / 2;
    memo = new Boolean[conjunto.length][metadeSoma + 1];

    return podeParticionar(conjunto.length - 1, metadeSoma);
  }

  private boolean podeParticionar(int maiorIndice, int soma) {
    boolean mantemSoma, diminuiSoma;

    if (soma == 0) return true;
    if (maiorIndice < 0 || soma < 0) return false;

    if (memo[maiorIndice][soma] == null) {
      mantemSoma = podeParticionar(maiorIndice - 1, soma);
      diminuiSoma = podeParticionar(maiorIndice - 1, soma - conjunto[maiorIndice]);
      memo[maiorIndice][soma] = mantemSoma || diminuiSoma;
    }

    return memo[maiorIndice][soma];
  }

}
