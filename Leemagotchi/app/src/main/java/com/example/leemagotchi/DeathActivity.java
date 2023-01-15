package com.example.leemagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DeathActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_restart;
    ImageView mainImg;
    TextView txt_status, txt_stats;
    String type;
    int slot;

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
        slot = Integer.parseInt((String)getIntent().getSerializableExtra("slot"));

        //Compare birth and death date to see how long the pet lasted for
        DBHandler db = new DBHandler(this);
        String name = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String lDate = db.getColumnLastUpdate(slot);
        String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
        LocalDateTime d1 = LocalDateTime.parse(lDate, formatter);
        LocalDateTime d2 = LocalDateTime.parse(cDate, formatter);
        long dayDiff = ChronoUnit.DAYS.between(d1, d2);
        String status = "Your " + name + " lasted for " + dayDiff + " days.";
        txt_status.setText(status);

        int happy = db.getColumnHappy(slot);
        int level = db.getColumnLevel(slot);
        int hunger = db.getColumnHunger(slot);
        int maxHunger = db.getColumnMaxHunger(slot);
        int discipline = db.getColumnDiscipline(slot);
        int maxDiscipline = db.getColumnMaxDiscipline(slot);
        int training = db.getColumnTraining(slot);
        int needXp = db.getColumnNeedXp(slot);
        String stats = happy + "\n" +
                hunger + "/" + maxHunger + "\n" +
                discipline + "/" + maxDiscipline + "\n" +
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
        db.setUnborn(slot);
        Intent intentMain = new Intent(this, StartActivity.class);
        startActivity(intentMain);
        finish();
    }
}
