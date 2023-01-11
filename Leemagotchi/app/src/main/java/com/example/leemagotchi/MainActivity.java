package com.example.leemagotchi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_zen, btn_dana, btn_lee, btn_help;
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
        btn_help = (Button) findViewById(R.id.help);
        btn_help.setOnClickListener(this);

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
                break;
            case (R.id.help):
                help();
        }
    }

    public void startGame(String choice){
        Intent intentGame = new Intent(this, GameActivity.class);
        intentGame.putExtra("choice", choice);
        startActivity(intentGame);
    }

    public void help(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Uhmmmm");
        builder.setMessage("You noob??");
        builder.setCancelable(true);
        builder.setPositiveButton("ye :(", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(MainActivity.this, "LOL NOOB!!", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

}