package com.example.leemagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    Button buyFood, buyBigFood, buyGaming, buyDesert, buyChoker, buyMeme, back;
    TextView coins;
    String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        type = (String)getIntent().getSerializableExtra("choice");

        back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(this);
        buyFood = (Button) findViewById(R.id.buyFood);
        buyFood.setOnClickListener(this);
        buyBigFood = (Button) findViewById(R.id.buyBigFood);
        buyBigFood.setOnClickListener(this);
        buyGaming = (Button) findViewById(R.id.buyGaming);
        buyGaming.setOnClickListener(this);
        buyDesert = (Button) findViewById(R.id.buyDesert);
        buyDesert.setOnClickListener(this);
        buyChoker = (Button) findViewById(R.id.buyChoker);
        buyChoker.setOnClickListener(this);
        buyMeme = (Button) findViewById(R.id.buyMeme);
        buyMeme.setOnClickListener(this);

        coins = (TextView) findViewById(R.id.coins);

        displayCoins();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btn_back):
                back();
                break;
            case(R.id.buyFood):
                buyFood();
                break;
            case (R.id.buyBigFood):
                buyBigFood();
                break;
            case (R.id.buyGaming):
                buyGaming();
                break;
            case (R.id.buyDesert):
                buyDesert();
                break;
            case (R.id.buyChoker):
                buyChoker();
                break;
            case (R.id.buyMeme):
                buyMeme();
                break;
        }
    }

    public void back(){
        Intent intentGame = new Intent(this, GameActivity.class);
        intentGame.putExtra("choice", type);
        startActivity(intentGame);
    }

    public void displayCoins(){
        InventoryDB idb = new InventoryDB(this);
        String amount = Integer.toString(idb.getCoin());
        coins.setText(amount);
    }

    public void buyFood(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyFood();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyBigFood(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyBigFood();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyGaming(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyGaming();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyDesert(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyDesert();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyChoker(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyChoker();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyMeme(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyMeme();
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        displayCoins();
    }
}