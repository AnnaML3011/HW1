package com.example.hw1;

public class Card {
    private CardDeck.CardType cardType;
    private CardDeck.CardValue cardValue;

    public Card(CardDeck.CardValue cardValue, CardDeck.CardType cardType) {
        this.cardValue = cardValue;
        this.cardType = cardType;
    }

    public int getValue() {

        return cardValue.getValue();
    }

    public String getType() {
        return cardType.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return ""+ getType()+"_"+cardValue.getValue();
    }
}