package com.example.deck;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;

public interface ValueRepository extends CrudRepository<Value, Long> {
    public Value findValueByName(String name);
}
