package br.com.maiscontrole.simulador.negocio;


import static br.com.maiscontrole.simulador.util.Utilitarios.adicionarMes;
import static br.com.maiscontrole.simulador.util.Utilitarios.isPreenchimentoNuloOuVazio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maiscontrole.simulador.entidade.Cliente;
import br.com.maiscontrole.simulador.entidade.Municipio;
import br.com.maiscontrole.simulador.entidade.Parcela;
import br.com.maiscontrole.simulador.entidade.Simulacao;
import br.com.maiscontrole.simulador.excecao.NegocioException;
import br.com.maiscontrole.simulador.repositorio.SimulacaoRepository;

@Service
public class SimulacaoService implements Serializable {

	private static final long serialVersionUID = -2877744107065885986L;

	@Autowired
	private SimulacaoRepository repository;

	public List<Simulacao> findAll() {
		return (List<Simulacao>) repository.findAll();
	}

	public Simulacao findOne(Long id) {
		return repository.findOne(id);
	}

	public void save(Simulacao simulacao) {
		repository.save(simulacao);
	}

	public void gerarParcelas(Simulacao simulacao) {
		validarDadosObrigatoriosCliente(simulacao.getCliente());
		validarDadosObrigatoriosMunicipio(simulacao.getMunicipio());
		validarDadosObrigatoriosValoresSimulacao(simulacao);
		montarParcelasDoImovel(simulacao);
	}

	private void montarParcelasDoImovel(Simulacao simulacao) {
		BigDecimal valorDeCadaParcela = simulacao.getValorImovel().divide(BigDecimal.valueOf(simulacao.getQntParcelas()), 2, BigDecimal.ROUND_HALF_EVEN);

		for (Long numeroParcela = 1L; numeroParcela <= simulacao.getQntParcelas(); numeroParcela++) {
			System.out.println(numeroParcela);
			Date dataVencimentoParcela = adicionarMes(new Date(), numeroParcela);
			Parcela parcela = new Parcela(numeroParcela, valorDeCadaParcela, simulacao, dataVencimentoParcela);
			simulacao.getListaParcelas().add(parcela);
		}
		
		if (simulacao.getQntParcelas().compareTo(Long.valueOf(simulacao.getListaParcelas().size()))!=0) {
			throw new NegocioException("Houve uma falha ao dividir as parcelas, favor tentar novamente!");
		}
		
		Stream<Parcela> streamParcelas = simulacao.getListaParcelas().stream();
		BigDecimal valorTotalParcelas = new BigDecimal(streamParcelas.map(p -> p.getValorParcela()).mapToDouble(BigDecimal::doubleValue).sum());
		
		if (simulacao.getValorImovel().compareTo(valorTotalParcelas.setScale(2, BigDecimal.ROUND_HALF_EVEN))!=0) {
			throw new NegocioException("Houve uma falha de cálculo na divisão das parcelas, favor tentar novamente!");
		}
	}

	private void validarDadosObrigatoriosCliente(Cliente cliente) {
		if (isPreenchimentoNuloOuVazio(cliente) || isPreenchimentoNuloOuVazio(cliente.getCpf())) {
			throw new NegocioException("CPF é um campo obrigatório!");
		}
		if (isPreenchimentoNuloOuVazio(cliente) || isPreenchimentoNuloOuVazio(cliente.getNome())) {
			throw new NegocioException("Nome é um campo obrigatório!");
		}
	}

	private void validarDadosObrigatoriosMunicipio(Municipio municipio) {
		if (isPreenchimentoNuloOuVazio(municipio) || isPreenchimentoNuloOuVazio(municipio.getUf())) {
			throw new NegocioException("Estado é um campo obrigatório!");
		}
		
		if (isPreenchimentoNuloOuVazio(municipio) || isPreenchimentoNuloOuVazio(municipio.getNome())) {
			throw new NegocioException("Municipio é um campo obrigatório!");
		}
	}

	private void validarDadosObrigatoriosValoresSimulacao(Simulacao simulacao) {
		if (isPreenchimentoNuloOuVazio(simulacao.getQntParcelas()) || simulacao.getQntParcelas().longValue() <= 0) {
			throw new NegocioException("Quantidade de parcelas é um campo obrigatório!");
		}

		if (isPreenchimentoNuloOuVazio(simulacao.getValorImovel()) || simulacao.getValorImovel().longValue() <= 0) {
			throw new NegocioException("Valor do imóvel é um campo obrigatório!");
		}
	}

	

}
