package model.imobiliaria;

public class Apartamento extends Locacao {

  public Apartamento(String endereco, int quartos, int garagem,
                     double valor, double extra) {
    super(endereco, quartos, garagem, valor, extra);
  }

  public double getValorTotal() {
    return this.getValor() + this.getValorCondominio();
  }

  public String toString() {
    return this.getClass().getSimpleName() + " " + this.getValorTotal();
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

    valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());
    valorPago += 0.10 * this.getValorCondominio();

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
  public void setPrazo(int prazo) {
    if (prazo < 12) {
      throw new PrazoInvalidoException("prazo minimo 12 meses para residencial");
    }
    super.setPrazo(prazo);
  }



}
