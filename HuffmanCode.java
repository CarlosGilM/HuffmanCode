//Alunos: Carlos Gil Martins da Silva

import java.util.ArrayList;
public class HuffmanCode {

  public void verificaFrequencia(String src) {
    int[] charFreqs = new int[256];
    for (char c : src.toCharArray()) {
      charFreqs[c]++;
    }
    Node Pai = CriaArvore(charFreqs);
    String Codigo = encode(Pai, src);
    exibir(charFreqs, Pai);

    System.out.println("\nPalavra Compactada: " + Codigo);
    System.out.println("Palavra Apos Descompactacao: " + Pai.decode(Pai, Codigo));
    System.out.println("");
  }

  public Node CriaArvore(int[] charFreqs) {
    ArrayList<Node> Nodes = new ArrayList<>();

    for (int i = 0; i < charFreqs.length; i++) {
      if (charFreqs[i] > 0) {
        Nodes.add(new Node(charFreqs[i], (char) i));
      }
    }
    classificarPorSelecao(Nodes);

    while (Nodes.size() > 1) {
      Node A = Nodes.get(0);
      Nodes.remove(0);
      Node B = Nodes.get(0);
      Nodes.remove(0);

      Nodes.add(new Node((A.getValor() + B.getValor()), A, B));
      classificarPorSelecao(Nodes);

    }
    return Nodes.get(0);
  }

  public static void classificarPorSelecao(ArrayList<Node> Nodes) {
    for (int i = 0; i < Nodes.size() - 1; i++) {
      int indiceMinimo = i;
      for (int j = i + 1; j < Nodes.size(); j++) {
        if (Nodes.get(j).getValor() < Nodes.get(indiceMinimo).getValor()) {
          indiceMinimo = j;
        }
      }
      if (indiceMinimo != i) {
        Node temp = Nodes.get(i);
        Nodes.set(i, Nodes.get(indiceMinimo));
        Nodes.set(indiceMinimo, temp);
      }
    }
  }

  public String encode(Node Pai, String src) {
    String Codigo = "";
    for (char c : src.toCharArray()) {
      String aux = Pai.findCode(Pai, c);
      Codigo += aux;
    }
    return Codigo;
  }

  public void exibir(int[] freq, Node Pai) {
    System.out.println("[ Caractere || Frequência || Codigo ]\n");
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] > 0) {
        String code = Pai.findCode(Pai, (char) i);
        System.out.println("Caractere [ " + (char) i + " ]  ||  " + "Frequencia [ " + freq[i] + " ]  ||  " + "Codigo " + code);
      }

    }
  }

}
