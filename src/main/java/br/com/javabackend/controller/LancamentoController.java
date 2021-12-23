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

import br.com.javabackend.dto.MovimentacaoDto;
import br.com.javabackend.form.MovimentacaoForm;
import br.com.javabackend.form.MovimentacaoUpdate;
import br.com.javabackend.model.Movimentacao;
import br.com.javabackend.repository.CategoriaRepository;
import br.com.javabackend.repository.MovimentacaoRepository;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public Page<MovimentacaoDto> listarPorCategoria(@RequestParam(required = false) String nomeCategoria,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		if (nomeCategoria == null) {
			Page<Movimentacao> movimentacoes = movimentacaoRepository.findAll(paginacao);
			return MovimentacaoDto.converte(movimentacoes);
		} else {
			Page<Movimentacao> movimentacoes = movimentacaoRepository.findByCategoriaDescricao(nomeCategoria,
					paginacao);
			return MovimentacaoDto.converte(movimentacoes);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovimentacaoDto> consulta(@PathVariable Integer id) {
		Optional<Movimentacao> optional = movimentacaoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new MovimentacaoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<MovimentacaoDto> cadastrar(@RequestBody @Valid MovimentacaoForm movimentacaoInput,
			UriComponentsBuilder uriBuilder) {
		Movimentacao movimentacao = movimentacaoInput.converte(categoriaRepository);
		
		// to do: verificar o limite da transacao por categoria
		// Se ultrapassou = Exibir mensagem
		// Senao = salvar a transacao no banco
		movimentacaoRepository.save(movimentacao);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(movimentacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new MovimentacaoDto(movimentacao));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MovimentacaoDto> atualizar(@PathVariable Integer id,
			@RequestBody @Valid MovimentacaoUpdate movimentacaoUpdate) {
		Optional<Movimentacao> optional = movimentacaoRepository.findById(id);
		if (optional.isPresent()) {
			Movimentacao movimentacao = movimentacaoUpdate.atualizar(id, movimentacaoRepository);
			return ResponseEntity.ok(new MovimentacaoDto(movimentacao));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id) {

		Optional<Movimentacao> optional = movimentacaoRepository.findById(id);
		if (optional.isPresent()) {
			movimentacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}