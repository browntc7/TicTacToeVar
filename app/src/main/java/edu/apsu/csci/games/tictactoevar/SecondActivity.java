package edu.apsu.csci.games.tictactoevar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private String currentPiece = "";

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        if(player1Turn){
            textViewPlayer1.setTextColor(Color.BLUE);
            textViewPlayer2.setTextColor(Color.BLACK);
        } else {
            textViewPlayer1.setTextColor(Color.BLACK);
            textViewPlayer2.setTextColor(Color.BLUE);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button button_s = findViewById(R.id.btn_select_s);
        button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPiece = "S";
            }
        });

        Button button_o = findViewById(R.id.btn_select_o);
        button_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPiece = "O";
            }
        });

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button buttonAbout = findViewById(R.id.button_about);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
            }
        });
    }
    private void about() {
        Intent intent = new Intent(getApplicationContext(),
                AboutApp.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

 /*       if (player1Turn) {
            ((Button) v).setText("S");
        } else {
            ((Button) v).setText("O");
        }*/


/*
        if(v.getId() == R.id.btn_select_o){
            currentPiece = "S";
        }

        if(v.getId() == R.id.btn_select_s){
            currentPiece = "O";
        }
*/

        ((Button) v).setText(currentPiece);


        if(checkForWin() && player1Turn){
            player1Wins();
        } else if (checkForWin() && !player1Turn){
            player2Wins();
        } else if (roundCount == 9){
            draw();
        } else {
            player1Turn = !player1Turn;
            if(player1Turn){
                textViewPlayer1.setTextColor(Color.BLUE);
                textViewPlayer2.setTextColor(Color.BLACK);
            } else {
                textViewPlayer1.setTextColor(Color.BLACK);
                textViewPlayer2.setTextColor(Color.BLUE);
            }
        }

        roundCount++;


    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        if(field[1][1].equals("S")){
            draw();
        }

        // check for horizontal win
        for(int i = 0; i < 3; i++) {
            if (field[i][0].equals("S") && field[i][1].equals("O") && field[i][2].equals("S")){
                return true;
            }
        }

        // check for vertical win
        for(int i = 0; i < 3; i++) {
            if (field[0][i].equals("S") && field[1][i].equals("O") && field[2][i].equals("S")){
                return true;
            }
        }

        // check for diag win
        if(field[0][0].equals("S") && field[1][1].equals("O") && field[2][2].equals("S")){
            return true;
        }
        if(field[0][2].equals("S") && field[1][1].equals("O") && field[2][0].equals("S")){
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
