package br.com.dr2.lavanderia.models.enums;

public enum TipoUnidadeMedida {

	UN(1, "UNIDADE"), 
	KG(2, "QUILOGRAMA"), 
	PC(3, "PEÇA");

	private int codigo;
	private String descricao;

	private TipoUnidadeMedida(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
