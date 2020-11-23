package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activity_Main extends AppCompatActivity {

    private final int NUM_OF_PLAYERS = 2;
    private final int NUM_OF_CARDS = 52;
    private List<Card> cards;
    private List<Player> players;
    private TextView score_LBL_1;
    private TextView score_LBL_2;
    private ImageButton start_game_BTN;
    private ImageView first_IMG_card;
    private ImageView seconed_IMG_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        createPlayers();
        initViews();
        createCards();
        showCards();
        shuffleAndCreateDeck();
        showPlayers();

    }

    @Override
    protected void onStart() {
        Log.d("aaa","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("aaa","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("aaa","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("aaa","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("aaa","onDestroy");
        super.onDestroy();
    }
    private void findViews() {
        score_LBL_1 = findViewById(R.id.score_LBL_1);
        score_LBL_2 = findViewById(R.id.score_LBL_2);
        start_game_BTN = findViewById(R.id.start_game_BTN);
        first_IMG_card = findViewById(R.id.first_IMG_card);
        seconed_IMG_card =findViewById(R.id.seconed_IMG_card);

    }

    private void initViews() {
        score_LBL_1.setText("Score: " + players.get(0).getScore());
        score_LBL_2.setText("Score: " + players.get(1).getScore());

        start_game_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(players.get(0).getCardDeck().deckIsEmpty() == 0 || players.get(1).getCardDeck().deckIsEmpty() == 0){
                    Log.d("aaa","true");
                    checkWinner();
                }else {
                    start_game();
                }
            }
        });
    }

    private void createCards(){
        cards = new ArrayList<>();
        for (CardDeck.CardType type: CardDeck.CardType.values()) {
            for(CardDeck.CardValue value : CardDeck.CardValue.values()){
                cards.add(new Card(value,type));
            }
        }
    }
    private void createPlayers(){
        players = new ArrayList<>();
        for(Player.Players player: Player.Players.values()){
            players.add(new Player(player));
        }
    }

    private void shuffleAndCreateDeck(){
        Collections.shuffle(cards);
        int sizeOfDeckEachPlayer = NUM_OF_CARDS/NUM_OF_PLAYERS;
        List<Card> cardsEachPlayer = new ArrayList<>();
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            cardsEachPlayer.addAll(cards.subList(i*sizeOfDeckEachPlayer,(i+1)*sizeOfDeckEachPlayer));
            players.get(i).addCardsToPlayerDeck(cardsEachPlayer);
            cardsEachPlayer.clear();
        }
    }

    private void showCards(){
        for(Card card : cards){
           // System.out.println(card);
        }
    }
    private void showPlayers(){
        for(Player player : players){
           // System.out.println(player);
        }
    }
    private void start_game() {
        Card card1 = players.get(0).getCardDeck().getCard();
        Card card2 = players.get(1).getCardDeck().getCard();
        String nameCard1 = card1.getType() + "_" + card1.getValue();
        String nameCard2 = card2.getType() + "_" + card2.getValue();
        int id1 = getResources().getIdentifier(nameCard1,"drawable", getPackageName());
        int id2 = getResources().getIdentifier(nameCard2,"drawable", getPackageName());
        first_IMG_card.setImageResource(id1);
        seconed_IMG_card.setImageResource(id2);
        calculateScore(card1, card2);
    }
     private void calculateScore(Card c1, Card c2){
         int cardValue1 = c1.getValue();
         int cardValue2= c2.getValue();
         if(cardValue1 > cardValue2){
             players.get(0).addPoint();
             score_LBL_1.setText("Score: " + players.get(0).getScore());
         }else if(cardValue1 < cardValue2){
             players.get(1).addPoint();
             score_LBL_2.setText("Score: " + players.get(1).getScore());
         }else if(cardValue1 == cardValue2){
             players.get(0).addPoint();
             players.get(1).addPoint();
             score_LBL_1.setText("Score: " + players.get(0).getScore());
             score_LBL_2.setText("Score: " + players.get(1).getScore());
         }
     }

     public void checkWinner(){
        int score = 0; //1-player1 won, 2- player2 won, 3-teco
        int player = 0;// 1-player1 , 2-player2 ,3- teco
        if(players.get(0).getScore() > players.get(1).getScore()){
            score = 1;
            player = 1;
        }else if(players.get(0).getScore() < players.get(1).getScore()){
            score = 2;
            player = 2;
            //go to winner activity with player 2 name
        }else {
            score = 3;
            player = 3;
        }
        Intent myIntent = new Intent(Activity_Main.this, Activity_Winner.class);
        myIntent.putExtra(Activity_Winner.PLAYER,player);
        myIntent.putExtra(Activity_Winner.SCORE,score);
        startActivity(myIntent);
        finish();

     }

}