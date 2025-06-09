package com.mygamelist.repository;

import com.mygamelist.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
