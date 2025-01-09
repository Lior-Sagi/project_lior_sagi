package com.lior.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {

    private TextView playerTurnText;
    private GridLayout dominoBoard;
    private Button nextTurnBtn;

    private List<String> dominoDeck;
    private int currentPlayer = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        playerTurnText = findViewById(R.id.playerTurnText);
        dominoBoard = findViewById(R.id.dominoBoard);
        nextTurnBtn = findViewById(R.id.nextTurnBtn);

        dominoDeck = generateDominoDeck();
        setupDominoBoard();

        nextTurnBtn.setOnClickListener(v -> nextTurn());
    }

    // Generate a domino deck (simple example with 0-6 dominoes)
    private List<String> generateDominoDeck() {
        List<String> deck = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                deck.add(i + "|" + j);
            }
        }
        return deck;
    }

    // Dynamically add domino tiles to the board
    private void setupDominoBoard() {
        dominoBoard.removeAllViews();
        for (int i = 0; i < dominoDeck.size(); i++) {
            Button tileButton = new Button(this);
            tileButton.setText(dominoDeck.get(i));
            tileButton.setOnClickListener(v -> onTileClick((Button) v));
            dominoBoard.addView(tileButton);
        }
    }

    // Handle a tile click (a player tries to play it)
    public void onTileClick(Button tileButton) {
        String tile = tileButton.getText().toString();

        // Here we would check if the tile is valid, for simplicity, we assume it's valid.
        tileButton.setEnabled(false); // Disable button after use.
        // Optionally, add tile to played tiles list

        // Show that it’s the other player’s turn.
        nextTurn();
    }

    // Switch turns and update the UI
    private void nextTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        playerTurnText.setText("Player " + currentPlayer + "'s Turn");
    }
}
