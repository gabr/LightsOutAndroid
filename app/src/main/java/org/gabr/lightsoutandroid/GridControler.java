package org.gabr.lightsoutandroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class GridControler {

    private GridLayout grid;
    private int enabledColor;
    private int disabledColor;

    public GridControler(GridLayout grid) {
        this.grid = grid;

        enabledColor = Color.CYAN;
        disabledColor = Color.GREEN;
    }

    public void Move(View view) {
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

        Move(r, c);
    }

    public void Move(int column, int row) {
        if (column < 0 || column > grid.getColumnCount() - 1 ||
                row < 0 || row > grid.getRowCount() - 1)
            return;

        SwitchState(column, row);
        SwitchState(column - 1, row);
        SwitchState(column + 1, row);
        SwitchState(column, row - 1);
        SwitchState(column, row + 1);

        CheckIfFinished();
    }

    public void Clear() {
        EnableBloard(true);

        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                SetState(GetButton(c, r), false);
    }

    public void RandomBoard() {
//                Random rand = new Random(DateTime.Now.Millisecond);
//
//                int c = grid.ColumnDefinitions.Count;
//                int r = grid.RowDefinitions.Count;
//                int maximumMoves = c * r;
//
//                int moves = rand.Next(0, maximumMoves);
//                for (int i = 0; i < moves; i++)
//                    Move(rand.Next(0, c), rand.Next(0, r));
    }

    public void CheckIfFinished() {
        boolean state = GetState(GetButton(0, 0));

        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                if (state != GetState(GetButton(c, r)))
                    return;

//                if (OnFinish != null)
//                    OnFinish();

        EnableBloard(false);
    }

    private void SwitchState(int column, int row) {
        if (column < 0 || column > grid.getColumnCount() - 1 ||
                row < 0 || row > grid.getRowCount() - 1)
            return;

        Button button = GetButton(column, row);
        SetState(button, !GetState(button));
    }

    private Button GetButton(int column, int row) {
        int index = row + column * grid.getColumnCount();
        return (Button) grid.getChildAt(index);
    }

    private boolean GetState(Button button) {
        return ((ColorDrawable)button.getBackground()).getColor() == enabledColor;
    }

    private void SetState(Button button, boolean state) {
        button.setBackgroundColor(state ? enabledColor : disabledColor);
    }

    private void EnableBloard(boolean enable) {
        for (int c = 0; c < grid.getColumnCount(); c++)
            for (int r = 0; r < grid.getRowCount(); r++)
                GetButton(c, r).setEnabled(enable);
    }
}
