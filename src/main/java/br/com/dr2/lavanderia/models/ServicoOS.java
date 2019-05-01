package br.com.dr2.lavanderia.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servico_os")
public class ServicoOS implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Servico servico;
	private OrdemServico ordemServico;
	private String detalhes;
	private int quantidadeServicos;

	public ServicoOS() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "servico_id")
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@ManyToOne
	@JoinColumn(name = "ordem_servico_id")
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@Column(name = "detalhes", columnDefinition = "TEXT")
	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	@Column(name = "quantidade_servicos", length = 10, nullable = false)
	public int getQuantidadeServicos() {
		return quantidadeServicos;
	}

	public void setQuantidadeServicos(int quantidadeServicos) {
		this.quantidadeServicos = quantidadeServicos;
	}

}
