package br.com.dr2.lavanderia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@ToString()
@NoArgsConstructor
public class Cliente extends EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@Column(name = "nome", nullable = false, length = 100, unique = true)
	private String nome;
	@Getter
	@Setter
	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;
	@Getter
	@Setter
	@Column(name = "telefon", length = 100, unique = true)
	private String telefone;
	@Getter
	@Setter
	@Column(name = "celular", length = 100, unique = true)
	private String celular;
	@Getter
	@Setter
	@Column(name = "cpf", nullable = false, length = 100, unique = true)
	private String cpf;

}
