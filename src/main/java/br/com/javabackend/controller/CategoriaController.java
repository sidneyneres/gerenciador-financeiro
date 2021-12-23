package br.com.javabackend.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.javabackend.dto.CategoriaDto;
import br.com.javabackend.form.CategoriaForm;
import br.com.javabackend.form.CategoriaUpdate;
import br.com.javabackend.model.Categoria;
import br.com.javabackend.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping
	public Page<CategoriaDto> lista(@RequestParam(required = false) String descricao,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		if (descricao == null) {
			Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converte(categorias);
		} else {
			Page<Categoria> categorias = categoriaRepository.findByDescricao(descricao, paginacao);
			return CategoriaDto.converte(categorias);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> consulta(@PathVariable Integer id) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm categoriaInput,
			UriComponentsBuilder uriBuilder) {
		Categoria categoria = new Categoria(categoriaInput.getDescricao(), categoriaInput.getLimite());
		categoriaRepository.save(categoria);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id,
			@RequestBody @Valid CategoriaUpdate categoriaAtlz) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			Categoria categoria = categoriaAtlz.atualizar(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id) {

		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
