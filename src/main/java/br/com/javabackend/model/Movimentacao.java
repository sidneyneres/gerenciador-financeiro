package br.com.javabackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate data = LocalDate.now();
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	@ManyToOne
	private Categoria categoria;
	private BigDecimal valor;

	public Movimentacao() {
	}

	public Movimentacao(LocalDate dataMovimento, TipoMovimentacao tipoMovimento, Categoria categoria,
			BigDecimal valorMovimento) {
		this.data = dataMovimento;
		this.tipo = tipoMovimento;
		this.categoria = categoria;
		this.valor = valorMovimento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

}
