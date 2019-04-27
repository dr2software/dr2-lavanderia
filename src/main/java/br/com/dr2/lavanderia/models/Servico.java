package br.com.dr2.lavanderia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.dr2.lavanderia.models.enums.TipoUnidadeMedida;

@Entity
@Table(name = "servico")
public class Servico extends EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String descricao;
	private double valor;
	private TipoUnidadeMedida unidadeMedida;

	public Servico() {
	}

	@Column(name = "nome", nullable = false, length = 100, unique = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "descricao", columnDefinition = "TEXT")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "valor", nullable = false, scale = 2, precision = 10)
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_medida", nullable = false, length = 5)
	public TipoUnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(TipoUnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

}
