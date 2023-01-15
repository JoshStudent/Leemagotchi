package com.example.leemagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name1, desc1, name2, desc2, name3, desc3;
    ImageView pic1, pic2, pic3;
    ImageButton s1, s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        name1 = (TextView) findViewById(R.id.name1);
        desc1 = (TextView) findViewById(R.id.desc1);
        name2 = (TextView) findViewById(R.id.name2);
        desc2 = (TextView) findViewById(R.id.desc2);
        name3 = (TextView) findViewById(R.id.name3);
        desc3 = (TextView) findViewById(R.id.desc3);

        pic1 = (ImageView) findViewById(R.id.slot1);
        pic2 = (ImageView) findViewById(R.id.slot2);
        pic3 = (ImageView) findViewById(R.id.slot3);

        s1 = (ImageButton) findViewById(R.id.play1);
        s2 = (ImageButton) findViewById(R.id.play2);
        s3 = (ImageButton) findViewById(R.id.play3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.play1):
                start(1);
                break;
            case (R.id.play2):
                start(2);
                break;
            case (R.id.play3):
                start(3);
                break;
        }
    }

    public void start(int slot){
        Intent intentGame = new Intent(this, EggActivity.class);
        intentGame.putExtra("slot", slot);
        startActivity(intentGame);
        finish();
    }

    public void display(){
        String type;
        DBHandler db = new DBHandler(this);

        // Slot 1
        if(db.isAlive(1)){
            type = db.getColumnType(1);
            name1.setText(type);
            String desc = "Birthday: " + db.getColumnBirthdate(1);
            desc1.setText(desc);
            if(type.equalsIgnoreCase("LEE"))
                pic1.setImageResource(R.drawable.leehappy);
            else if(type.equalsIgnoreCase("ZEN"))
                pic1.setImageResource(R.drawable.danahappy);
            else if(type.equalsIgnoreCase("DANA"))
                pic1.setImageResource(R.drawable.danahappy);
        }else{
            name1.setText("None");
            desc1.setText("Birth a friend today!");
        }

        // Slot 2
        if(db.isAlive(2)){
            type = db.getColumnType(2);
            name2.setText(type);
            String desc = "Birthday: " + db.getColumnBirthdate(2);
            desc2.setText(desc);
            if(type.equalsIgnoreCase("LEE"))
                pic2.setImageResource(R.drawable.leehappy);
            else if(type.equalsIgnoreCase("ZEN"))
                pic2.setImageResource(R.drawable.danahappy);
            else if(type.equalsIgnoreCase("DANA"))
                pic2.setImageResource(R.drawable.danahappy);
        }else{
            name2.setText("None");
            desc2.setText("Birth a friend today!");
        }

        // Slot 3
        if(db.isAlive(3)){
            type = db.getColumnType(3);
            name3.setText(type);
            String desc = "Birthday: " + db.getColumnBirthdate(3);
            desc3.setText(desc);
            if(type.equalsIgnoreCase("LEE"))
                pic3.setImageResource(R.drawable.leehappy);
            else if(type.equalsIgnoreCase("ZEN"))
                pic3.setImageResource(R.drawable.danahappy);
            else if(type.equalsIgnoreCase("DANA"))
                pic3.setImageResource(R.drawable.danahappy);
        }else{
            name3.setText("None");
            desc3.setText("Birth a friend today!");
        }
    }
}
