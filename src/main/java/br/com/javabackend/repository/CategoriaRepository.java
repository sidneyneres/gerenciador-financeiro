package br.com.javabackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javabackend.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	Categoria findByDescricao(String nomeCategoria);
	Page<Categoria> findByDescricao(String descricao, Pageable paginacao);

}
