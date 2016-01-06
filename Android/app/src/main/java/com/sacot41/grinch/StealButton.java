package com.sacot41.grinch;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Samuel on 2015-12-07.
 */
public class StealButton extends Button {

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_ON = 1;
    public static final int STATE_OFF = 2;

    private int state = STATE_UNKNOWN;

    public StealButton(Context context) {
        super(context);
        init();
    }

    public StealButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public StealButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Grinched.ttf");
        setTypeface(font);
    }

    @Override
    public boolean performClick() {
        this.setSelected(!isSelected());
        return super.performClick();
    }

    public void setState(int state) {
        this.state = state;

        if (state == STATE_ON) {
            setEnabled(true);
            super.setSelected(true);
            this.setText(getContext().getString(R.string.main_un_selected_button_text));
        } else if (state == STATE_OFF) {
            setEnabled(true);
            super.setSelected(false);
            this.setText(getContext().getString(R.string.main_selected_button_text));
        } else  {
            setEnabled(false);
            this.setText(getContext().getString(R.string.main_unknow_state_button));
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        if (selected) setState(STATE_ON);
        else setState(STATE_OFF);
        /*
        if (!selected) this.setText(getContext().getString(R.string.main_selected_button_text));
        else this.setText(getContext().getString(R.string.main_un_selected_button_text));*/
    }
}
