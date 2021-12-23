package br.com.javabackend.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.sun.istack.NotNull;

public class CategoriaForm {

	@NonNull
	@NotEmpty
	@Length(min = 1)
	private String descricao;
	
	@NotNull
	private BigDecimal limite;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}


	
	
}
