package com.example.singlaslantgithubactions.Services;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {

    public double generateRandomNumber() {
        return Math.random();
    }
}
