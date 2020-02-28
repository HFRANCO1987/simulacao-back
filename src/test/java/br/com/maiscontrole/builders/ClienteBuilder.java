package br.com.maiscontrole.builders;

import java.util.Date;

import br.com.maiscontrole.simulador.entidade.Cliente;


public class ClienteBuilder {

	private Cliente cliente;

	public ClienteBuilder() {
		cliente = new Cliente();
	}

	public Cliente build() {
		return cliente;
	}

	public ClienteBuilder comId(Long id) {
		cliente.setId(id);
		return this;
	}

	public ClienteBuilder comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}

	public ClienteBuilder comTelefone(String telefone) {
		cliente.setTelefone(telefone);
		return this;
	}

	public ClienteBuilder comCpf(String cpf) {
		cliente.setCpf(cpf);
		return this;
	}

	public ClienteBuilder comNascimento(Date nascimento) {
		cliente.setDataNascimento(nascimento);
		return this;
	}


	public ClienteBuilder comEmail(String email) {
		cliente.setEmail(email);
		return this;
	}


}
