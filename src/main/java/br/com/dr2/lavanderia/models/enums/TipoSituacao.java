package br.com.dr2.lavanderia.models.enums;

public enum TipoSituacao {

	EM_ABERTO(1,"Em aberto"),
	EM_ANDAMENTO(2,"Em andamento"),
	FINALIZADA(3,"Finalizada"),
	CANCELADA(4,"Cancelada");
	
	private int codigo;
	private String descricao;
	
	private TipoSituacao(int codigo, String descricao) {
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
