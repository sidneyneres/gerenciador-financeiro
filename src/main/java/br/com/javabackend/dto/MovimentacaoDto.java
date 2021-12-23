package br.com.javabackend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.javabackend.model.Movimentacao;

public class MovimentacaoDto {

	private Integer id;
	private String tipo;
	private LocalDate dataMovimento;
	private String categoria;
	private BigDecimal valorMovimento;

	public MovimentacaoDto(Movimentacao movimentacao) {
		this.id = movimentacao.getId();
		this.tipo = movimentacao.getTipo().toString();
		this.dataMovimento = movimentacao.getData();
		this.categoria = movimentacao.getCategoria().getDescricao();
		this.valorMovimento = movimentacao.getValor();
	}

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public LocalDate getDataMovimento() {
		return dataMovimento;
	}

	public String getCategoria() {
		return categoria;
	}

	public BigDecimal getValorMovimento() {
		return valorMovimento;
	}

	public static Page<MovimentacaoDto> converte(Page<Movimentacao> movimentacoes) {

		return movimentacoes.map(MovimentacaoDto::new);

	}
}
