package com.mygamelist.controller;

import com.mygamelist.model.Jogo;
import com.mygamelist.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository repository;

    // Criar um novo jogo
    @PostMapping
    public Jogo cadastrarJogo(@RequestBody Jogo jogo) {
        return repository.save(jogo);
    }

    // Listar todos os jogos
    @GetMapping
    public List<Jogo> listarJogos() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // GET /jogos/{id} → buscar jogo específico (necessário para preencher o formulário ao editar)
    @GetMapping("/{id}")
    public Jogo buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado com id: " + id));
    }

    // PUT /jogos/{id} → atualizar jogo existente
    @PutMapping("/{id}")
    public Jogo atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        return repository.findById(id)
                .map(jogoExistente -> {
                    jogoExistente.setTitulo(jogoAtualizado.getTitulo());
                    jogoExistente.setGenero(jogoAtualizado.getGenero());
                    jogoExistente.setPlataforma(jogoAtualizado.getPlataforma());
                    jogoExistente.setAnoLancamento(jogoAtualizado.getAnoLancamento());
                    jogoExistente.setCapaUrl(jogoAtualizado.getCapaUrl());
                    return repository.save(jogoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado com id: " + id));
    }
}


