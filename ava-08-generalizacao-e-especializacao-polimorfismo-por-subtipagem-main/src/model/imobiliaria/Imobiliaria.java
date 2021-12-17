package model.imobiliaria;

import util.Bag;

public class Imobiliaria {

  private String nome;
  private Bag locacoes = new Bag(1000);
  private Bag cobrancas = new Bag(1000000);

  public Imobiliaria(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    String str = "Imobiliaria " + nome + "\n";
    str += "Locações: \n";
    for (Object o : this.getLocacoesAtivas()) {
      str += o.toString() + "\n";
    }
    str += "Cobranças: \n";
    for (Object o : this.cobrancas.toArray()) {
      str += o.toString() + "\n";
    }
    return str;
  }


  /**
   * Explicação.
   * @return retorna array de locações ativas.
   */
  public Object[] getLocacoesAtivas() {
    Bag ativas = new Bag(locacoes.count());
    for (Object o : locacoes.toArray()) {
      Locacao l = (Locacao) o;
      if (l.getMeses() > 0) {
        ativas.add(l);
      }
    }
    return ativas.toArray();
  }

  /**
   * Explicação.
   * @param apartamento apartamento.
  */

  public void contratar(Apartamento apartamento) {
    if (locacoes.has(apartamento)) {
      throw new ContratoInvalidoException("locacao deve ser renovada");
    }
    apartamento.setPrazo(12); // mínimo não comercial
    locacoes.add(apartamento);
  }

  /**
   * Explicação.
   * @param comercial comercial.
   */
  public void contratar(Comercial comercial) {
    if (locacoes.has(comercial)) {
      throw new ContratoInvalidoException("locacao deve ser renovada");
    }
    comercial.setPrazo(6);
    locacoes.add(comercial);
  }

  /**
   * Explicação.
   * @param residencial residencial.
   */
  public void contratar(Residencial residencial) {
    if (locacoes.has(residencial)) {
      throw new ContratoInvalidoException("locacao deve ser renovada");
    }

    residencial.setPrazo(12); // mínimo não comercial

    locacoes.add(residencial);
  }

  /**
   * Explicação.
   * @param apartamento apartamento.
   * @param reajuste reajuste.
   * @param extra extra.
   * @param prazo prazo.
   */
  public void renovar(Apartamento apartamento, double reajuste, double extra, int prazo) {
    if (apartamento.getMeses() > 0) {
      throw new ContratoInvalidoException("locacao em andamento");
    }

    if (prazo < 12) {
      throw new PrazoInvalidoException(
      "prazo minimo de 12 meses para residencia e apartamento");
    }

    apartamento.setPrazo(prazo);
    apartamento.renovar(reajuste, extra);
  }

  /**
  *Explicação.
  * @param comercial comercial.
  * @param reajuste reajuste.
  * @param extra extra.
  * @param prazo prazo.
  */
  public void renovar(Comercial comercial, double reajuste, double extra, int prazo) {
    if (comercial.getMeses() > 0) {
      throw new ContratoInvalidoException("locacao em andamento");
    }
    if (prazo < 6) {
      throw new PrazoInvalidoException("prazo minimo de 6 meses para comercial");
    }
    comercial.setPrazo(prazo);
    comercial.renovar(reajuste, extra);
  }

  /**
   * Explicação.
   * @param residencial residencial.
   * @param reajuste reajuste.
   * @param extra extra.
   * @param prazo prazo.
   */
  public void renovar(Residencial residencial, double reajuste, double extra, int prazo) {

    if (residencial.getMeses() > 0) {
      throw new ContratoInvalidoException("locacao em andamento");
    }

    if (prazo < 12) {
      throw new PrazoInvalidoException(
      "prazo minimo de 12 meses para residencia e apartamento");
    }
    residencial.setPrazo(prazo);
    residencial.renovar(reajuste, extra);
  }


  /**
   * Explicação.
   * @param ano ano.
   * @param mes mes.
   * @return Retorna a cobrança.
   */
  public Cobranca novaCobranca(int ano, int mes) {
    Cobranca cobranca = new Cobranca(this, ano, mes);
    cobrancas.add(cobranca);
    return cobranca;
  }

}
