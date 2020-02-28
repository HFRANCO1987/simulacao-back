package br.com.maiscontrole.builders;

import java.math.BigDecimal;
import java.util.List;

import br.com.maiscontrole.simulador.entidade.Cliente;
import br.com.maiscontrole.simulador.entidade.Municipio;
import br.com.maiscontrole.simulador.entidade.Parcela;
import br.com.maiscontrole.simulador.entidade.Simulacao;

public class SimulacaoBuilder {


	private Simulacao simulacao;

	public SimulacaoBuilder() {
		simulacao = new Simulacao();
	}

	public Simulacao build() {
		return simulacao;
	}

	public SimulacaoBuilder comId(Long id) {
		simulacao.setId(id);
		return this;
	}

	public SimulacaoBuilder comCliente(Cliente cliente) {
		simulacao.setCliente(cliente);
		return this;
	}

	public SimulacaoBuilder comMunicipio(Municipio municipio) {
		simulacao.setMunicipio(municipio);
		return this;
	}

	public SimulacaoBuilder comQntParcelas(Long qnt) {
		simulacao.setQntParcelas(qnt);
		return this;
	}

	public SimulacaoBuilder comValorImovel(BigDecimal valor) {
		simulacao.setValorImovel(valor);
		return this;
	}

	public SimulacaoBuilder parcelas(List<Parcela> parcelas) {
		simulacao.getListaParcelas().clear();
		simulacao.getListaParcelas().addAll(parcelas);
		return this;
	}

}
