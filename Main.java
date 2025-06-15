import java.util.Arrays;
import java.util.List;

public class Main {
  private static final String SEPARADOR = "----------------------------------------------------------------";
  private static ParticaoDeConjunto pc = new ParticaoDeConjunto();

  public static void main(String[] args) {
    int[] arr1 = {1, 5, 11, 5, 6, 6, 2, 10, 4, 16};
    int[] arr2 = {1, 5, 11, 5, 6, 6, 2, 10, 4, 15};
    int[] arr3 = new int[1000];
    int[] arr4 = new int[1001];
    for (int i = 0; i < arr3.length; i++) arr3[i] = i + 1; 
    for (int i = 0; i < arr4.length; i++) arr4[i] = i + 1;

    testa(arr1);
    System.out.printf("%s\n\n", SEPARADOR);
    testa(arr2);
    System.out.printf("%s\n\n", SEPARADOR);
    testa(arr3);
    System.out.printf("%s\n\n", SEPARADOR);
    testa(arr4);
  }
  
  public static void testa(int[] arr) {
    List<Integer>[] subconjuntos;
    boolean podeParticionar;
    long t0, tempoExecMemorizacao, tempoExecParticionamento;

    System.out.printf("Conjunto:\n%s\n\n", conjuntoToString(arr));

    t0 = System.nanoTime();
    podeParticionar = pc.podeParticionar(arr);
    tempoExecMemorizacao = System.nanoTime() - t0; // em ns
    System.out.printf(
      "Tempo de execução do algoritmo de memorização: %.5f ms\n", 
      tempoExecMemorizacao / 1e6
    );

    if (podeParticionar == false) {
      System.out.println("O conjunto não pode ser particionado.\n");
      return;
    }

    t0 = System.nanoTime();
    subconjuntos = pc.particionar();
    tempoExecParticionamento = System.nanoTime() - t0; // em ns
    System.out.printf(
      "Tempo de execução do particionamento: %.5f ms\n", 
      tempoExecParticionamento / 1e6
    );

    System.out.printf(
      "Soma dos elementos de cada subconjunto: %d\n\n",
      pc.getSomaConjunto(arr) / 2
    );
    System.out.printf(
      "Subconjuntos:\n%s\n%s\n\n",
      conjuntoToString(subconjuntos[0]), 
      conjuntoToString(subconjuntos[1])
    );
  }

  private static String conjuntoToString(int[] arr) {
    StringBuilder sb = new StringBuilder(100);

    if (arr.length <= 20) {
      return Arrays.toString(arr);
    }

    sb.append("[");
    for (int i = 0; i < 10; i++) {
      sb.append(String.format("%d, ", arr[i]));
    }
    sb.append("...");
    for (int i = arr.length - 10; i < arr.length; i++) {
      sb.append(String.format(", %d", arr[i]));
    }
    sb.append("]");

    return sb.toString();
  }

  private static String conjuntoToString(List<Integer> list) {
    StringBuilder sb = new StringBuilder(100);

    if (list.size() <= 20) {
      return list.toString();
    }

    sb.append("[");
    for (int i = 0; i < 10; i++) {
      sb.append(String.format("%d, ", list.get(i)));
    }
    sb.append("...");
    for (int i = list.size() - 10; i < list.size(); i++) {
      sb.append(String.format(", %d", list.get(i)));
    }
    sb.append("]");

    return sb.toString();
  }

}
