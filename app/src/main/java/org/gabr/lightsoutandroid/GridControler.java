package org.gabr.lightsoutandroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class GridControler {

    public interface OnFinishListener {
        void onFinish();
    }

    private GridLayout grid;
    private OnFinishListener onFinishListener;

    private int enabledColor;
    private int disabledColor;

    public GridControler(GridLayout grid, OnFinishListener listener) {
        this.grid = grid;
        this.onFinishListener = listener;

        enabledColor = Color.rgb(0x3E, 0x65, 0xFF);
        disabledColor = Color.GRAY;
    }

    public void move(View view) {
        Button button = (Button) view;

        int size = grid.getColumnCount();
        int r, c;
        r = c = 0;

        for (int i = 0; i < grid.getChildCount(); i++) {
            if (button == grid.getChildAt(i)) {
                c = i % size;
                r = (i - c) / size;
                break;
            }
        }

        move(r, c);
    }

    public void move(int column, int row) {
        if (column < 0 || column > grid.getColumnCount() - 1 ||
                row < 0 || row > grid.getRowCount() - 1)
            return;

        switchState(column, row);
        switchState(column - 1, row);
        switchState(column + 1, row);
        switchState(column, row - 1);
        switchState(column, row + 1);

        CheckIfFinished();
    }

    public void clear() {
        enableBloard(true);

        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                setState(getButton(c, r), false);
    }

    public void randomBoard() {
                Random rand = new Random(System.currentTimeMillis());

                int c = grid.getColumnCount();
                int r = grid.getRowCount();
                int maximumMoves = c * r;

                int moves = rand.nextInt(maximumMoves);
                for (int i = 0; i < moves; i++)
                    move(rand.nextInt(c), rand.nextInt(r));
    }

    public void CheckIfFinished() {
        boolean state = getState(getButton(0, 0));

        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                if (state != getState(getButton(c, r)))
                    return;

        if (onFinishListener != null)
            onFinishListener.onFinish();

        enableBloard(false);
    }

    private void switchState(int column, int row) {
        if (column < 0 || column > grid.getColumnCount() - 1 ||
                row < 0 || row > grid.getRowCount() - 1)
            return;

        Button button = getButton(column, row);
        setState(button, !getState(button));
    }

    private Button getButton(int column, int row) {
        int index = row + column * grid.getColumnCount();
        return (Button) grid.getChildAt(index);
    }

    private boolean getState(Button button) {
        return ((ColorDrawable)button.getBackground()).getColor() == enabledColor;
    }

    private void setState(Button button, boolean state) {
        button.setBackgroundColor(state ? enabledColor : disabledColor);
    }

    private void enableBloard(boolean enable) {
        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                getButton(c, r).setEnabled(enable);
    }
}
