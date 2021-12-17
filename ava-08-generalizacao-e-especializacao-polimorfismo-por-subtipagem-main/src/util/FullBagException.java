package util;

public class FullBagException extends RuntimeException {

  public FullBagException(int cap) {
    super("A bolsa está na capacidade máxima de " + cap + " objetos");
  }

}
