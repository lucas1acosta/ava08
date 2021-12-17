package model.imobiliaria;

public class Residencial extends Locacao {

  public Residencial(String endereco, int quartos, int garagem,
                     double valor) {
    super(endereco, quartos, garagem, valor);
  }

  public double getValorTotal() {
    return this.getValor();
  }

  @Override
  public double pagar(int atraso) {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();

    valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());

    this.setMeses(this.getMeses() - 1);
    this.setUltimoValorPago(valorPago);
    return valorPago;
  }

  @Override
  public double pagar() {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    this.setMeses(this.getMeses() - 1); ;
    this.setUltimoValorPago(valorPago);
    return valorPago;
  }

  /**
   * Define o prazo.
   */
  public void setPrazo(int prazo) {
    if (prazo < 12) {
      throw new PrazoInvalidoException("prazo minimo 12 meses para residencial");
    }
    super.setPrazo(prazo);
  }

  public String getTipo() {
    return this.getClass().getSimpleName();
  }

  public String toString() {
    return this.getClass().getSimpleName() + " " + this.getValorTotal();
  }

}
