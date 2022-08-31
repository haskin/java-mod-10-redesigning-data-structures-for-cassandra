package com.example.deck;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.convert.ColumnType;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Deck {
    // @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    // private UUID uuid;

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Long position;

    // Should use OneToOne in theory. There is a bug preventing for this specific
    // implementation however.
    // @ManyToOne
    // @JoinColumn(name="uuid", referencedColumnName="uuid", insertable=false,
    // updatable=false)
    // private Card card;

    private String cardName;
    private Long points;
    private String suit;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    protected Deck() {
    }

    public Deck(Card card, Long position) {
        // this.uuid = UUID.randomUUID();
        setCardName(card.getName());
        setPoints(card.getPoints());
        setSuit(card.getSuit());
        setPosition(position);
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    // public UUID getUuid() {
    // return uuid;
    // }

    // public void setUuid(UUID uuid) {
    // this.uuid = uuid;
    // }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

}
