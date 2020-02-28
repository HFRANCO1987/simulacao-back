package br.com.maiscontrole.simulador.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Henrique Santiago
 *
 */
@Entity
@Table(name = "tab_parcelas")
public class Parcela implements Serializable {

	private static final long serialVersionUID = 3353172131721341523L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_simulacao")
	private Simulacao simulacao;

	@Column(name = "numeroparcela")
	private Long numeroParcela;

	@Column(name = "valorparcela")
	private BigDecimal valorParcela;

	@Column(name = "datavencimento")
	private Date dataVencimento;

	public Parcela() {
	}

	public Parcela(Long numeroParcela, BigDecimal valorParcela, Simulacao simulacao, Date dataVencimento) {
		this.numeroParcela = numeroParcela;
		this.valorParcela = valorParcela;
		this.simulacao = simulacao;
		this.dataVencimento = dataVencimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroParcela == null) ? 0 : numeroParcela.hashCode());
		result = prime * result + ((simulacao == null) ? 0 : simulacao.hashCode());
		result = prime * result + ((valorParcela == null) ? 0 : valorParcela.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcela other = (Parcela) obj;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroParcela == null) {
			if (other.numeroParcela != null)
				return false;
		} else if (!numeroParcela.equals(other.numeroParcela))
			return false;
		if (simulacao == null) {
			if (other.simulacao != null)
				return false;
		} else if (!simulacao.equals(other.simulacao))
			return false;
		if (valorParcela == null) {
			if (other.valorParcela != null)
				return false;
		} else if (!valorParcela.equals(other.valorParcela))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}

	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

}
