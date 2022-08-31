package com.example.deck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueService {
    @Autowired
    private ValueRepository valueRepository;

    public Value getValueByName(String name) {
        return valueRepository.findValueByName(name);
    }
}
