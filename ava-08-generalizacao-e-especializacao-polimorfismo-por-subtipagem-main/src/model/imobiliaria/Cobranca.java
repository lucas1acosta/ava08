package model.imobiliaria;

public class Cobranca {

  private final Imobiliaria imobiliaria;
  private final int ano;
  private final int mes;
  private double valorArrecadado = 0.0;


  /**
   *Explicacao.
   * @param imobiliaria imobiliaria que faz a cobrança.
   * @param ano ano.
   * @param mes mes.
   */
  public Cobranca(Imobiliaria imobiliaria,  int ano, int mes) {
    this.imobiliaria = imobiliaria;
    this.ano = ano;
    this.mes = mes;
  }

  public int getAno() {
    return ano;
  }

  public int getMes() {
    return mes;
  }

  public String getPeriodo() {
    return mes + "/" + ano;
  }

  /**
   * Metodo para pagar.
   */
  public void pagar() {
    if (valorArrecadado > 0) {
      throw new CobrancaIndevidaException("cobrança já realizada");
    }
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      valorArrecadado += locacao.pagar();
    }
  }

  /**
   * Metodo para pagamento com atraso.
   * @param atraso quantidade de dias atrasados.
   */
  public void pagar(int atraso) {
    if (valorArrecadado > 0) {
      return;
    }
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      valorArrecadado += locacao.pagar(atraso);
    }
  }

  public double getValorArrecadado() {
    return valorArrecadado;
  }


  /**
   * Pega valor estimado do lucro.
   * @return retorna o total do valor estimado.
   */
  public double getValorEstimado() {
    double total = 0.0;
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      // PATCH:
      total += locacao.getValorTotal();
    }
    return total;
  }

  @Override
  public String toString() {
    return "Cobranca referente ao período " + this.getPeriodo()
      + " no valor de " + this.getValorArrecadado();
  }
}
