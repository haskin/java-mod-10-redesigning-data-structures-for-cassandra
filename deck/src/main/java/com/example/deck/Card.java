package com.example.deck;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Card implements Serializable {
    @Id
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID uuid;

    private String name;
    private String suit;
    private Long deckNumber;

    // Should use OneToOne in theory. There is a bug preventing for this specific
    // implementation however.
    // @OneToMany(mappedBy = "card")
    // private List<Deck> deckList;

    // @ManyToOne
    // @JoinColumn(name = "name", referencedColumnName = "name", insertable = false,
    // updatable = false)
    // private Value value;

    private Long points;

    protected Card() {
    }

    public Card(UUID uuid, String name, String suit, Long deckNumber) {
        setUUID(uuid);
        setName(name);
        setSuit(suit);
        setDeckNumber(deckNumber);
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Long getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(Long deckNumber) {
        this.deckNumber = deckNumber;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

}