package com.example.deck;

// import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

// @Entity
@Table
public class Value implements Serializable {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private Long id;

    private String name;
    private Long points;

    // @OneToMany(mappedBy = "value")
    @CassandraType(type = Name.SET)
    private List<Card> cardList;

    protected Value() {
    }

    public Value(String name, Long points) {
        setName(name);
        setPoints(points);
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