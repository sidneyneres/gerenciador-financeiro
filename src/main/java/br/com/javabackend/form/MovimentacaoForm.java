package br.com.javabackend.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.javabackend.model.Categoria;
import br.com.javabackend.model.Movimentacao;
import br.com.javabackend.model.TipoMovimentacao;
import br.com.javabackend.repository.CategoriaRepository;

public class MovimentacaoForm {

	@NotNull
	private LocalDate dataMovimento = LocalDate.now();
	
	@NotNull
	private TipoMovimentacao tipoMovimento;
	
	@NotNull
	private BigDecimal valorMovimento;
    
	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String nomeCategoria;
	
	
	public LocalDate getDataMovimento() {
		return dataMovimento;
	}


	public void setDataMovimento(LocalDate dataMovimento) {
		this.dataMovimento = dataMovimento;
	}


	public TipoMovimentacao getTipoMovimento() {
		return tipoMovimento;
	}


	public void setTipoMovimento(TipoMovimentacao tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}


	public BigDecimal getValorMovimento() {
		return valorMovimento;
	}


	public void setValorMovimento(BigDecimal valorMovimento) {
		this.valorMovimento = valorMovimento;
	}


	public String getNomeCategoria() {
		return nomeCategoria;
	}


	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}


	public Movimentacao converte(CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.findByDescricao(nomeCategoria);
		return new Movimentacao(dataMovimento, tipoMovimento, categoria, valorMovimento);
	}
}
