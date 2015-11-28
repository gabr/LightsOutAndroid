package org.gabr.lightsoutandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private GridControler gridControler;
    private TextView movesCounter;
    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movesCounter = (TextView) findViewById(R.id.movesCounter);
        grid = (GridLayout) findViewById(R.id.grid);

        gridControler = new GridControler(grid);
        gridControler.Clear();
    }

    public void onButtonClick(View view) {
        gridControler.Move(view);
    }
}
