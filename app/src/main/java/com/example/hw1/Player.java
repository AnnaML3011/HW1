package com.example.hw1;


import java.util.List;

public class Player {
    public static enum Players {PLAYER1 , PLAYER2};
    private Players players;
    private CardDeck cardDeck;
    private int score;

    public Player(Players players) {
        this.players = players;
        this.cardDeck = new CardDeck();
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public Players getPlayers() {
        return players;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public void addPoint(){
        this.score++;
    }

    public void addCardsToPlayerDeck(List<Card> card){
        this.getCardDeck().addCard(card);
    }

    @Override
    public String toString() {
        return "Player{" +
                "players=" + players +
                ", cardDeck=" + cardDeck +
                ", score=" + score +
                '}';
    }
}
