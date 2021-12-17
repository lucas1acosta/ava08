package model.imobiliaria;

public class Locacao {

  private final String endereco;
  private final int quartos;
  private final int garagem;
  private double valor;
  private double extra;
  private int meses;
  private int prazo;
  private double ultimoValorPago;


  /**
   * Explicação.
   * @param endereco endereço.
   * @param quartos quartos.
   * @param garagem garagem.
   * @param valor valor.
   * @param extra extra.
   */
  public Locacao(String endereco,int quartos, int garagem,
                 double valor, double extra) {
    if (garagem < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (quartos < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (valor < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (extra < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }

    this.endereco = endereco;
    this.quartos = quartos;
    this.garagem = garagem;
    this.valor = valor;
    this.extra = extra;
  }


  /**
   * Explicações.
   * @param endereco endereco.
   * @param quartos quartos.
   * @param garagem garagem.
   * @param valor valor.
   */
  public Locacao(String endereco,int quartos, int garagem,
                double valor) {
    if (garagem < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (quartos < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (valor < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }
    if (extra < 0) {
      throw new LocacaoInvalidaException("Locação Inválida");
    }

    this.endereco = endereco;
    this.quartos = quartos;
    this.garagem = garagem;
    this.valor = valor;
  }

  public void renovar(double percentual) {
    this.renovar(12, percentual);
  }

  public void renovar(double percentual, double extra) {
    this.renovar(12, percentual, extra);
  }

  public void renovar(int prazo, double percentual) {
    setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
  }

  /**
   * Explicações.
   * @param prazo prazo.
   * @param percentual percentual.
   * @param extra extra.
   */
  public void renovar(int prazo, double percentual, double extra) {
    setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
    this.extra = extra;
  }

  public void setPrazo(int prazo) {
    this.prazo = prazo;
    this.meses = prazo;
  }

  public int getPrazo() {
    return this.prazo;
  }

  public int getMeses() {
    return this.meses;
  }

  /**
   * Explicações.
   * @param atraso quantidade de dias atrasados.
   * @return return valor com atraso.
   */
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

  /**
   * Explicações.
   * @return retorna o valor pago.
   */

  public double pagar() {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    this.setMeses(this.getMeses() - 1); ;
    this.setUltimoValorPago(valorPago);
    return valorPago;
  }

  public double getValor() {
    return this.valor;
  }

  public double getValorTotal() {
    return this.getValor();
  }

  public double getValorCondominio() {
    return this.extra;
  }

  public double getUltimoValorPago() {
    return this.ultimoValorPago;
  }

  public double getTaxaComercial() {
    return this.extra;
  }

  public String getEndereco() {
    return endereco;
  }

  public double getExtra() {
    return extra;
  }

  public int getGaragem() {
    return garagem;
  }

  public int getQuartos() {
    return quartos;
  }

  public void setMeses(int i) {
    this.meses = i;
  }

  public double getValorPago() {
    return ultimoValorPago;
  }

  public void setUltimoValorPago(double valorPago) {
    this.ultimoValorPago = valorPago;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public void setExtra(double extra) {
    this.extra = extra;
  }


}
