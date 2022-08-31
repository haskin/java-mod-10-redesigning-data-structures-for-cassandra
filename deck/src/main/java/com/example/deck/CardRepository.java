package com.example.deck;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CassandraRepository<Card, Long> {
}
// public interface CardRepository extends CrudRepository<Card, Long> {}
