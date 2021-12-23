package br.com.javabackend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javabackend.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{
	Optional<Movimentacao> findById(Integer id);

	Page<Movimentacao> findByCategoriaDescricao(String nomeCategoria, Pageable paginacao);

}