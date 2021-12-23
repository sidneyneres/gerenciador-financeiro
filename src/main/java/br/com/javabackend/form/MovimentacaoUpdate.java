package br.com.javabackend.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.javabackend.model.Movimentacao;
import br.com.javabackend.model.TipoMovimentacao;
import br.com.javabackend.repository.MovimentacaoRepository;

public class MovimentacaoUpdate {

	private LocalDate data;
	private TipoMovimentacao tipo;
	//private Integer categoriaId;
	private BigDecimal valor;


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

//	public Integer getCategoriaId() {
//		return categoriaId;
//	}
//
//	public void setCategoriaId(Integer categoriaId) {
//		this.categoriaId = categoriaId;
//	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Movimentacao atualizar(Integer id, MovimentacaoRepository movimentacaoRepository) {
		Movimentacao movimentacao = movimentacaoRepository.getById(id);
		movimentacao.setData(this.data);
		movimentacao.setTipo(this.tipo);
		movimentacao.setValor(this.valor);
		
		return movimentacao;
	}

}
