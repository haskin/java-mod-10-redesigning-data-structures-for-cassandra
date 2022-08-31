package com.example.deck;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Value implements Serializable {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @PrimaryKey
    private UUID id;

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String name;
    private Long points;

    // @OneToMany(mappedBy = "value")
    // private List<Card> cardList;

    protected Value() {
    }

    public Value(String name, Long points) {
        setName(name);
        setPoints(points);
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}