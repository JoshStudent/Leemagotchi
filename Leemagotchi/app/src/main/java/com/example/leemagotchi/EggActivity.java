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

public class EggActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_zen, btn_dana, btn_lee, btn_help;
    TextView mainText;
    int slot;

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

        slot = Integer.parseInt((String)getIntent().getSerializableExtra("slot"));

        // Check if character is born or not. If so, immediately go to game activity
        DBHandler db = new DBHandler(this);
        if(db.IsBorn(slot)){
            String type = db.getColumnType(slot);
            startGame();
        }
    }

    @Override
    public void onClick(View view) {
        DBHandler db = new DBHandler(this);
        switch (view.getId()){
            case (R.id.btn_lee):
                db.newChar(slot, "LEE");
                break;
            case (R.id.btn_zen):
                db.newChar(slot, "ZEN");
                break;
            case (R.id.btn_dana):
                db.newChar(slot, "DANA");
                break;
            case (R.id.help):
                help();
        }
    }

    public void startGame(){
        if(slot < 1 || slot > 3){
            Toast.makeText(EggActivity.this, "Slot error", Toast.LENGTH_SHORT).show();
        }else{
            Intent intentGame = new Intent(this, GameActivity.class);
            intentGame.putExtra("slot", slot);
            startActivity(intentGame);
            finish();
        }
    }

    public void help(){
        AlertDialog.Builder builder = new AlertDialog.Builder(EggActivity.this);
        builder.setTitle("Uhmmmm");
        builder.setMessage("You noob??");
        builder.setCancelable(true);
        builder.setPositiveButton("ye :(", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(EggActivity.this, "LOL NOOB!!", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}