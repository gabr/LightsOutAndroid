package org.gabr.lightsoutandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements GridControler.OnFinishListener {

    private GridControler gridControler;
    private TextView movesCounter;
    private GridLayout grid;

    private int moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moves = 0;
        movesCounter = (TextView) findViewById(R.id.movesCounter);
        movesCounter.setText("Moves: " + moves);

        grid = (GridLayout) findViewById(R.id.grid);
        gridControler = new GridControler(grid, this);
        gridControler.clear();
        gridControler.randomBoard();
    }

    public void onButtonClick(View view) {
        moves += 1;
        movesCounter.setText("Moves: " + moves);

        gridControler.move(view);
    }

    @Override
    public void onFinish() {
        movesCounter.setText("Finished in: " + moves);
    }

    public void onReloadButtonClick(View view) {
        moves = 0;
        movesCounter.setText("Moves: " + moves);

        gridControler.clear();
        gridControler.randomBoard();
    }
}
