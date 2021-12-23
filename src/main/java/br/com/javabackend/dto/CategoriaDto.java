package br.com.javabackend.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import br.com.javabackend.model.Categoria;

public class CategoriaDto {

	private Integer id;
	private String descricao;
	private BigDecimal limite;

	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
		this.limite = categoria.getLimite();
	}

	public CategoriaDto() {
	}

	public String getNome() {
		return descricao;
	}

	public void setNome(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return limite;
	}

	public void setValor(BigDecimal valor) {
		this.limite = valor;
	}

	public static Page<CategoriaDto> converte(Page<Categoria> categorias) {

		return categorias.map(CategoriaDto::new);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
