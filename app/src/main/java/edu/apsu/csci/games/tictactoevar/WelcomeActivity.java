package edu.apsu.csci.games.tictactoevar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView textView = findViewById(R.id.welcome_textView);
        textView.setTextSize(30);
        textView.setText("\n\n\n\nWelcome To Tic-Tac-Toe Variations! \n");


        Button a = findViewById(R.id.play_button);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });

        Button b = findViewById(R.id.play2_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame2();
            }
        });

        Button c = findViewById(R.id.about_button);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about();
            }
        });

    }

    private void playGame() {
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
    }
    private void playGame2() {
        Intent intent = new Intent(getApplicationContext(),
                SecondActivity.class);
        startActivity(intent);
    }

    private void about() {
        Intent intent = new Intent(getApplicationContext(),
                AboutApp.class);
        startActivity(intent);
    }


}
