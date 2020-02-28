package br.com.maiscontrole.builders;

import br.com.maiscontrole.simulador.entidade.Municipio;

public class MunicipioBuilder {

	private Municipio municipio;

	public MunicipioBuilder() {
		municipio = new Municipio();
	}

	public Municipio build() {
		return municipio;
	}

	public MunicipioBuilder comId(Long id) {
		municipio.setId(id);
		return this;
	}

	public MunicipioBuilder comNome(String nome) {
		municipio.setNome(nome);
		return this;
	}

	public MunicipioBuilder comUf(String uf) {
		municipio.setUf(uf);
		return this;
	}
	

}
