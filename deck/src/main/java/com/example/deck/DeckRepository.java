package com.example.deck;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeckRepository extends CassandraRepository<Deck, Long> {
    Optional<Deck> findByPosition(Long position);

    Optional<Deck> findFirstByOrderByPositionDesc();

    Deck findFirstByPositionIsNotNullOrderByPosition();
}
