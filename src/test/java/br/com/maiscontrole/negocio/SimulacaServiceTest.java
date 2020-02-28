package br.com.maiscontrole.negocio;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maiscontrole.builders.ClienteBuilder;
import br.com.maiscontrole.builders.MunicipioBuilder;
import br.com.maiscontrole.builders.SimulacaoBuilder;
import br.com.maiscontrole.simulador.entidade.Cliente;
import br.com.maiscontrole.simulador.entidade.Municipio;
import br.com.maiscontrole.simulador.entidade.Simulacao;
import br.com.maiscontrole.simulador.excecao.NegocioException;
import br.com.maiscontrole.simulador.negocio.SimulacaoService;
import br.com.maiscontrole.simulador.repositorio.SimulacaoRepository;

@RunWith(SpringRunner.class)
public class SimulacaServiceTest {

	@InjectMocks
	private SimulacaoService simulacaoService;

	@Mock
	private SimulacaoRepository repository;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	public SimulacaServiceTest() {
		simulacaoService = new SimulacaoService();
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoCpfDoCliente() {
		Cliente cliente = new ClienteBuilder().build();
		Simulacao simulacao = new SimulacaoBuilder().comCliente(cliente).build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("CPF é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoNomeDoCliente() {
		Cliente cliente = new ClienteBuilder().comCpf("000-111-222-55").build();
		Simulacao simulacao = new SimulacaoBuilder().comCliente(cliente).build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Nome é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoUfDoMunicipio() {
		Cliente cliente = new ClienteBuilder().comCpf("000-111-222-55").comNome("teste").build();
		Municipio municipio = new MunicipioBuilder().build();
		Simulacao simulacao = new SimulacaoBuilder()
				.comCliente(cliente)
				.comMunicipio(municipio).build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Estado é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoNomeDoMunicipio() {
		Cliente cliente = new ClienteBuilder()
				.comCpf("000-111-222-55")
				.comNome("teste").build();
		
		Municipio municipio = new MunicipioBuilder().comUf("GO").build();
		Simulacao simulacao = new SimulacaoBuilder()
				.comCliente(cliente)
				.comMunicipio(municipio).build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Municipio é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoQuantidadeParcelas() {
		Cliente cliente = new ClienteBuilder()
				.comCpf("000-111-222-55")
				.comNome("teste").build();
		
		Municipio municipio = new MunicipioBuilder().comUf("GO")
				.comNome("Cachoeira de Goiás").build();
		
		Simulacao simulacao = new SimulacaoBuilder()
				.comCliente(cliente)
				.comMunicipio(municipio).build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Quantidade de parcelas é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	@Test
	public void testValidarPreenchimentoObrigatorioCampoValorImovel() {
		Cliente cliente = new ClienteBuilder()
				.comCpf("000-111-222-55")
				.comNome("teste").build();
		
		Municipio municipio = new MunicipioBuilder()
				.comUf("GO")
				.comNome("Cachoeira de Goiás")
				.build();
		Simulacao simulacao = new SimulacaoBuilder()
				.comCliente(cliente)
				.comMunicipio(municipio)
				.comQntParcelas(6L)
				.build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Valor do imóvel é um campo obrigatório!");

		simulacaoService.gerarParcelas(simulacao);
	}
	
	@Test
	public void testValidarGeracaoNumeroParcelas() {
		Cliente cliente = new ClienteBuilder()
				.comCpf("000-111-222-55")
				.comNome("teste").build();
		
		Municipio municipio = new MunicipioBuilder()
				.comUf("GO")
				.comNome("Cachoeira de Goiás")
				.build();
		Simulacao simulacao = new SimulacaoBuilder()
				.comCliente(cliente)
				.comMunicipio(municipio)
				.comQntParcelas(6L)
				.comValorImovel(new BigDecimal(10000))
				.build();
		
		thrown.expect(NegocioException.class);
		thrown.expectMessage("Houve uma falha ao dividir as parcelas, favor tentar novamente!");

		simulacaoService.gerarParcelas(simulacao);
	}
}
