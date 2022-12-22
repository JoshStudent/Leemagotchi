package com.example.leemagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_zen, btn_dana, btn_lee;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_lee = (Button) findViewById(R.id.btn_lee);
        btn_lee.setOnClickListener(this);
        btn_zen = (Button) findViewById(R.id.btn_zen);
        btn_zen.setOnClickListener(this);
        btn_dana = (Button) findViewById(R.id.btn_dana);
        btn_dana.setOnClickListener(this);

        mainText = (TextView) findViewById(R.id.textView);

        // Check if character is born or not. If so, immediately go to game activity
        DBHandler db = new DBHandler(this);
        if(db.IsBorn()){
            String type = db.getColumnType();
            startGame(type);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btn_lee):
                startGame("lee");
                break;
            case (R.id.btn_zen):
                startGame("zen");
                break;
            case (R.id.btn_dana):
                startGame("dana");
        }
    }

    public void startGame(String choice){
        Intent intentGame = new Intent(this, GameActivity.class);
        intentGame.putExtra("choice", choice);
        startActivity(intentGame);
    }

}