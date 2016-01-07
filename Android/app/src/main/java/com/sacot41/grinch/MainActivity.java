package com.sacot41.grinch;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends GrinchBaseActiviy {

    @Bind(R.id.textview_main_christmas_over)
    TextView headerTextView;

    @Bind(R.id.button_main_activity_activate_christmas)
    StealButton stealChristmasButton;

    @Bind(R.id.textview_main_bluetooth_device_button)
    TextView bluetoothDeviceButton;

    public final String TAG = "Main";
    public Bluetooth bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Grinched.ttf");
        headerTextView.setTypeface(font);

        SpannableString content = new SpannableString(bluetoothDeviceButton.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        bluetoothDeviceButton.setText(content);
        bluetoothDeviceButton.setTypeface(font);
    }

    @Override
    public void onStart() {
        super.onStart();

        stealChristmasButton.setState(StealButton.STATE_OFF);
        bt = new Bluetooth(mHandler);
        connectService();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bt != null)  bt.stop();
    }

    @OnClick(R.id.button_main_activity_activate_christmas)
    public void stealButtonClick(View view) {
        if (bt != null && bt.getState() == Bluetooth.STATE_CONNECTED) {
            if (!stealChristmasButton.isSelected()) bt.sendMessage("ON\r");
            else bt.sendMessage("OFF\r");
        }
    }

    @OnClick(R.id.textview_main_bluetooth_device_button)
    public void deviceListButtonClick(View view) {
        bt.stop();
        connectService();
    }

    public void connectService(){
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("SAPIN");
            } else {
                Log.w(TAG, "Btservice started - bluetooth is not enabled");
            }
        } catch(Exception e){
            Log.e(TAG, "Unable to start bt ", e);
        }
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    if (msg.arg1 == Bluetooth.STATE_CONNECTED) bt.sendMessage("STATUS\r");
                    if (msg.arg1 == Bluetooth.STATE_DISCONNECT) stealChristmasButton.setState(StealButton.STATE_UNKNOWN);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d(TAG, "MESSAGE_WRITE " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_READ:
                    byte[] bytes = (byte[]) msg.obj;
                    Log.d("grinch","message read : "+ msg.obj);
                    if (bytes[0] == 'F') stealChristmasButton.setSelected(true);
                    else if (bytes[0] == 'N')  stealChristmasButton.setSelected(false);
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d(TAG, "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d(TAG, "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };

}
