package br.com.javabackend.form;

import java.math.BigDecimal;

import br.com.javabackend.model.Categoria;
import br.com.javabackend.repository.CategoriaRepository;

public class CategoriaUpdate {

	private String descricao;
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

	public Categoria atualizar(Integer id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getById(id);
		categoria.setDescricao(this.descricao);
		categoria.setLimite(this.limite);

		return categoria;
	}

}
