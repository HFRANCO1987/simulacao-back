package br.com.maiscontrole.simulador.controle;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.com.maiscontrole.simulador.negocio.SimulacaoService;

/**
 * 
 * @author Henrique Santiago
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class SimulacaoCtrl implements Serializable {

	private static final long serialVersionUID = -8870699623137598742L;

	@Autowired
	private SimulacaoService simulacaoService;

}
