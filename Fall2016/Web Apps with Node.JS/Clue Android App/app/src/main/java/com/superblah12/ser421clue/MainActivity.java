package com.superblah12.ser421clue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public final static String USERNAME = "com.superblah12.ser421clue.USERNAME";
    public static String RECORD = "";
    public static String LAST_GAME = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView recordText = (TextView) findViewById(R.id.recordText);
        recordText.setText(LAST_GAME);
    }

    public void sendName(View view) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String username = editText.getText().toString();
        intent.putExtra(USERNAME,username);
        startActivity(intent);
    }
}
