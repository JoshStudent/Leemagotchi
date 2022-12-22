package com.example.leemagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeathActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_restart;
    ImageView mainImg;
    TextView txt_status, txt_stats;
    String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        btn_restart = (Button) findViewById(R.id.button);
        btn_restart.setOnClickListener(this);
        mainImg = (ImageView) findViewById(R.id.main_image);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_stats = (TextView) findViewById(R.id.statsView);

        Intent intent = getIntent();
        type = (String)getIntent().getSerializableExtra("choice");

        String name = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
        String status = "Your " + name + " lasted for x days.";
        txt_status.setText(status);

        DBHandler db = new DBHandler(this);
        int happy = db.getColumnHappy();
        int level = db.getColumnLevel();
        int hunger = db.getColumnHunger();
        int discipline = db.getColumnDiscipline();
        int training = db.getColumnTraining();
        int needXp = db.getColumnNeedXp();
        String stats = happy + "/200\n" +
                hunger + "/200\n" +
                discipline + "/200\n" +
                training + "/" + needXp +"\n" +
                level;
        txt_stats.setText(stats);

        if(type.equalsIgnoreCase("LEE"))
            mainImg.setImageResource(R.drawable.leedead);
        else if(type.equalsIgnoreCase("DANA"))
            mainImg.setImageResource(R.drawable.danadead);
        else
            mainImg.setImageResource(R.drawable.zendead);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button)
            restart();
    }

    public void restart(){
        DBHandler db = new DBHandler(this);
        db.restart();
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }
}
