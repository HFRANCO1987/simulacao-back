package br.com.maiscontrole.simulador.repositorio;


import org.springframework.data.repository.CrudRepository;

import br.com.maiscontrole.simulador.entidade.Simulacao;

public interface SimulacaoRepository extends CrudRepository<Simulacao, Long> {

}
