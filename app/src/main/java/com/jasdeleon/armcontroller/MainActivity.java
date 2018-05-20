package com.jasdeleon.armcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jasdeleon.bluetootharduinosocket.ArduinoBluetoothHelper;
import com.jasdeleon.bluetootharduinosocket.controller.ConnectionHandler;
import com.jasdeleon.bluetootharduinosocket.threads.ConnectedThread;

public class MainActivity extends AppCompatActivity implements ConnectionHandler, View.OnClickListener {

    private Button connectButton;
    private ArduinoBluetoothHelper btHelper;

    private Button[] buttons;

    public int[] getButtonsIds() {

        return new int[]{
                R.id.openGripperButton,
                R.id.closeGripperButton,
                R.id.turnRightGripperButton,
                R.id.turnLeftGripperButton,
                R.id.upGrpper,
                R.id.downGripper,
                R.id.downBody1,
                R.id.upBody1,
                R.id.upBody2,
                R.id.downBody2,
                R.id.turnLeftBody,
                R.id.turnRightBody

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttons = new Button[getButtonsIds().length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = findViewById(getButtonsIds()[i]);
            buttons[i].setOnClickListener(this);
        }


        connectButton = findViewById(R.id.connectButton);


        btHelper = new ArduinoBluetoothHelper(this, this);

        btHelper.setConnectionTimeout(10000);
        btHelper.setArduinoDelayTime(0);
        connectButton.setOnClickListener(this);
    }

    @Override
    public void doWhenIsConnected() {
        connectButton.setText("Desconectar");
    }

    @Override
    public void doWhenIsConnecting() {
        connectButton.setText("Conectando...");
    }

    @Override
    public void doWhenIsDisconnected() {
        Toast.makeText(this, "Se ha desconectado el arduino", Toast.LENGTH_SHORT).show();
        connectButton.setText("Conectar");
    }

    @Override
    public void doInConnectedThread() {

    }

    @Override
    public void doBeforeExecuteThread() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connectButton:
                if (connectButton.getText().equals("Conectado")) {
                    ConnectedThread.disconnect();
                } else
                    btHelper.showPairedDevicesDialog();
                break;
            case R.id.turnLeftGripperButton:
                btHelper.sendData("l");
                break;
            case R.id.turnRightGripperButton:
                btHelper.sendData("r");
                break;
            case R.id.openGripperButton:
                btHelper.sendData("o");
                break;
            case R.id.closeGripperButton:
                btHelper.sendData("c");
                break;
            case R.id.upGrpper:
                btHelper.sendData("u");
                break;
            case R.id.downGripper:
                btHelper.sendData("d");
                break;
            case R.id.upBody1:
                btHelper.sendData("U");
                break;
            case R.id.downBody1:
                btHelper.sendData("D");
                break;
            case R.id.upBody2:
                btHelper.sendData("s");
                break;
            case R.id.downBody2:
                btHelper.sendData("x");
                break;
            case R.id.turnLeftBody:
                btHelper.sendData("L");
                break;
            case R.id.turnRightBody:
                btHelper.sendData("R");
                break;

        }
    }
}
