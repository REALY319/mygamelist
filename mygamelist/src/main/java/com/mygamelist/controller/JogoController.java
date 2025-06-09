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
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        if (jogoRepository.existsById(id)) {
            jogoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


