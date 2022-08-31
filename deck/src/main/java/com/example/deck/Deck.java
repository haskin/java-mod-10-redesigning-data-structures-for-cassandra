package com.example.deck;

import org.springframework.data.cassandra.core.mapping.CassandraType;

// import javax.persistence.*;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import java.util.UUID;

// @Entity
@Table
public class Deck {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private Long id;

    private UUID uuid;

    private Long position;

    // Should use OneToOne in theory. There is a bug preventing for this specific
    // implementation however.
    // @ManyToOne
    // @JoinColumn(name = "uuid", referencedColumnName = "uuid", insertable = false,
    // updatable = false)
    @CassandraType(type = Name.UUID)
    private Card card;

    protected Deck() {
    }

    public Deck(Card card, Long position) {
        setCard(card);
        setPosition(position);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        this.uuid = card.getUUID();
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
