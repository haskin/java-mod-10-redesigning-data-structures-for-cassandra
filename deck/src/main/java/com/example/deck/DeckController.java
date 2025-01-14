package com.example.deck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class DeckController {

    private Long positionCounter = 1L;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Autowired
    private ValueService valueService;

    @GetMapping("/new")
    public String newDeck(@RequestParam(value = "decks", defaultValue = "1") Long decks) {
        // Drop tables and start new
        deckRepository.deleteAll();
        cardRepository.deleteAll();
        valueRepository.deleteAll();

        // Initialize values table
        valueRepository.save(new Value("Two", 2L));
        valueRepository.save(new Value("Three", 3L));
        valueRepository.save(new Value("Four", 4L));
        valueRepository.save(new Value("Five", 5L));
        valueRepository.save(new Value("Six", 6L));
        valueRepository.save(new Value("Seven", 7L));
        valueRepository.save(new Value("Eight", 8L));
        valueRepository.save(new Value("Nine", 9L));
        valueRepository.save(new Value("Ten", 10L));
        valueRepository.save(new Value("Jack", 10L));
        valueRepository.save(new Value("Queen", 10L));
        valueRepository.save(new Value("King", 10L));
        valueRepository.save(new Value("Ace", 11L));

        // Initialize card table
        List<String> suits = Arrays.asList("Clubs", "Hearts", "Spades", "Diamonds");
        List<String> names = Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Jack", "Queen", "King", "Ace");
        for (Long deck = 1L; deck <= decks; deck++) {
            for (String suit : suits) {
                for (String name : names) {
                    UUID uuid = randomUUID();
                    Card card = new Card(uuid, name, suit, deck);
                    Value value = valueService.getValueByName(name);
                    card.setPoints(value.getPoints());
                    cardRepository.save(card);
                }
            }
        }

        // Initialize deck table
        Long position = 1L;
        Iterable<Card> cards = cardRepository.findAll();
        for (Card card : cards) {
            Deck deckItem = new Deck(card, position);
            deckRepository.save(deckItem);
            position++;
        }

        return String.format("New Deck using %s decks.", decks);
    }

    @GetMapping("/shuffle")
    public String shuffleDeck() {

        // Read order of cards
        Iterable<Deck> deck = deckRepository.findAll();
        List<Long> order = new ArrayList<Long>();
        for (Deck deckItem : deck) {
            order.add(deckItem.getPosition());
        }

        // Shuffle order
        Collections.shuffle(order);

        // Write new order of cards
        ListIterator<Long> orderItr = order.listIterator();
        for (Deck deckItem : deck) {
            deckItem.setPosition(orderItr.next());
            deckRepository.save(deckItem);
        }
        // Position counter reset
        positionCounter = 1L;
        return "Deck shuffled.";
    }

    @GetMapping("/deal")
    public String dealCard() {

        // Deck deckItem =
        // deckRepository.findFirstByOrderByPositionDesc().orElseGet(null);
        Deck deckItem = deckRepository.findByPosition(positionCounter++).orElseGet(null);
        deckRepository.delete(deckItem);

        return String.format("Dealt %s of %s: Worth %s points.", deckItem.getCardName(),
                deckItem.getSuit(),
                deckItem.getPoints());
    }

}
