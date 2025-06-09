public class ParticaoDeConjunto {
  private int[] conjunto;
  private Boolean[][] memo;

  public static void main(String[] args) {
    // int[] conjunto = {1, 3, 4, 7, 5};
    // int[] conjunto = {1, 4, 3, 1, 2, 1};
    // int[] conjunto = {8, 1, 3, 1, 2, 1};
    int[] conjunto = {8, 1, 3, 1, 2};
    ParticaoDeConjunto pc = new ParticaoDeConjunto();
    
    System.out.println(pc.partir(conjunto));

  }

  public boolean partir(int[] conjunto) {
    this.conjunto = conjunto;
    int somaConjunto, metadeSoma;

    somaConjunto = 0;
    for (int elem: conjunto) somaConjunto += elem;

    if (somaConjunto % 2 != 0) return false;

    metadeSoma = somaConjunto / 2;
    memo = new Boolean[conjunto.length][metadeSoma + 1];

    return partir(conjunto.length - 1, metadeSoma);
  }

  private boolean partir(int maiorIndice, int soma) {
    boolean mantemSoma, diminuiSoma;

    if (soma == 0) return true;
    if (maiorIndice < 0 || soma < 0) return false;

    if (memo[maiorIndice][soma] == null) {
      mantemSoma = partir(maiorIndice - 1, soma);
      diminuiSoma = partir(maiorIndice - 1, soma - conjunto[maiorIndice]);
      memo[maiorIndice][soma] = mantemSoma || diminuiSoma;
    }

    return memo[maiorIndice][soma];
  }
}
