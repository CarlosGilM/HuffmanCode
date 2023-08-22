//Alunos: Carlos Gil Martins da Silva

public class Node {
  private int valor; /* Valor armazenado na raiz. */
  private char caractere;
  private Node filho_esq, filho_dir; /* Referências para sub-árvores. */
  // construtor da arvore sem subarvore

  public Node(int v, char s) {
    valor = v;
    caractere = s;
    filho_esq = null;
    filho_dir = null;
  }

  // fornecendo as subarvores no construtor
  public Node(int v, Node NoEsq, Node NoDir) {
    valor = v;
    filho_esq = NoEsq;
    filho_dir = NoDir;
  }

  public char getCaractere() {
    return caractere;
  }

  public int getValor() {
    return valor;
  }

  public Node getFilho_dir() {
    return filho_dir;
  }

  public Node getFilho_esq() {
    return filho_esq;
  }

  public String findCode(Node Pai, char c) {
    String code = "";
    Node curr = Pai;
    while (curr != null) {
      if (curr.isLeaf()) {
        if (curr.getCaractere() == c) {
          return code;
        } else {
          curr = Pai;
          code = "";
          continue;
        }
      } else if (curr.getFilho_esq() != null && curr.getFilho_esq().containsChar(c)) {
        curr = curr.getFilho_esq();
        code += "0";
      } else if (curr.getFilho_dir() != null && curr.getFilho_dir().containsChar(c)) {
        curr = curr.getFilho_dir();
        code += "1";
      } else {
        curr = null;
      }
    }
    return null; // caractere não encontrado na árvore
  }

  public boolean containsChar(char c) { // Verifica se a folha contem esse Caractere Recursivamente ou não
    if (isLeaf() && getCaractere() == c) {
      return true;
    } else {
      boolean leftContains = (getFilho_esq() != null) && getFilho_esq().containsChar(c);
      boolean rightContains = (getFilho_dir() != null) && getFilho_dir().containsChar(c);
      return leftContains || rightContains;
    }
  }

  public boolean isLeaf() { // Verifica se É ou não uma Folha
    return filho_esq == null && filho_dir == null;
  }

  public String decode(Node pai, String codigo) {
    Node no = pai;
    String resultado = "";

    for (int i = 0; i < codigo.length(); i++) {
      if (codigo.charAt(i) == '0') {
        no = no.getFilho_esq();
      } else {
        no = no.getFilho_dir();
      }

      if (no.isLeaf()) {
        resultado += no.getCaractere();
        no = pai;
      }
    }

    return resultado;
  }

  @Override
  public String toString() {
      return ("[ C: " + getCaractere() + " V: " + getValor() + "] ");
  }

}