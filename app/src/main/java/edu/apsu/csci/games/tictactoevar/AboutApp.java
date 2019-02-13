package edu.apsu.csci.games.tictactoevar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutApp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        TextView textView = findViewById(R.id.appinfo_textView);
        textView.setTextSize(24);
        textView.setText("\n\n\n\n\n\n\n\nTic-Tac-Toe Variations\nTyler Brown & Charity Edmonds, \nDevelopers\n2019\n\n" +
                "Revenge 3-in-a-row: The player who creates a 3-in-a-row wins unless the opponent can create a 3-in-a-row in " +
                "the next move, exacting revenge on the first player.\n\n\nS.O.S.: The players on each turn choose to play an" +
                " 'S' or an 'O' in an empty square. If a player creates the sequence, SOS vertically, horizontally or diagonally " +
                "they get a point and also take another turn.");
    }
}
