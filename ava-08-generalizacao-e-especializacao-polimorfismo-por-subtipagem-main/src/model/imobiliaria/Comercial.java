package model.imobiliaria;

public class Comercial extends Locacao {

  public Comercial(String endereco, int quartos, int garagem,
                   double valor, double extra) {
    super(endereco, quartos, garagem, valor, extra);
  }


  /**
   * Pega valor total.
   */
  public double getValorTotal() {

    if (this.getMeses() == this.getPrazo()) {
      return this.getValor() + this.getTaxaComercial();
    }

    return this.getValor();
  }

  public String getTipo() {
    return this.getClass().getSimpleName();
  }

  @Override
  public double pagar(int atraso) {

    if (this.getMeses() <= 0) {
      return 0;
    }

    double valorPago = this.getValorTotal();

    valorPago += 0.05 * this.getValorTotal() + (atraso * 0.01 * this.getValorTotal());


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

  @Override
  public void renovar(double percentual) {
    this.renovar(6, percentual);
  }

  @Override
  public void renovar(double percentual, double extra) {
    this.renovar(6, percentual, extra);
  }

  @Override
  public void setPrazo(int prazo) {
    if (prazo < 6) {
      throw new PrazoInvalidoException("prazo minimo 6 meses para comercial");
    }
    super.setPrazo(prazo);
  }

  public String toString() {
    return this.getClass().getSimpleName() + " " + this.getValorTotal();
  }

}
