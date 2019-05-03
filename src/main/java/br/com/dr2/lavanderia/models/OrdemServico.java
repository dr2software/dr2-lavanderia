package br.com.dr2.lavanderia.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.dr2.lavanderia.models.enums.TipoSituacao;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico extends EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Date dataEntrada;
	private Date horaEntrada;
	private Date dataSaida;
	private Date horaSaida;
	private TipoSituacao situacao;
	private List<ServicoOS> servicos;

	public OrdemServico() {
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "cliente_id")
	public Cliente getCliente() {
		return cliente;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy="ordemServico", cascade = {CascadeType.ALL})
	public List<ServicoOS> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<ServicoOS> servicos) {
		this.servicos = servicos;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrada", nullable = false)
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_entrada", nullable = false)
	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_saida")
	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_saida")
	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", nullable = false, length = 20)
	public TipoSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao;
	}

}
