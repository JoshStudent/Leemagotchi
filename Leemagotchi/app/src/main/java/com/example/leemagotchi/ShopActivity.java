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
    int slot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

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

        Intent intent = getIntent();
        slot = Integer.parseInt((String)getIntent().getSerializableExtra("slot"));

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
        intentGame.putExtra("slot", slot);
        startActivity(intentGame);
        finish();
    }

    public void displayCoins(){
        InventoryDB idb = new InventoryDB(this);
        String amount = Integer.toString(idb.getCoin(slot));
        coins.setText(amount);
    }

    public void buyFood(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyFood(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought a jelly bean!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyBigFood(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyBigFood(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought some McNuggets!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyGaming(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyGaming(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought a gaming device!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyDesert(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyDesert(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought some cake!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyChoker(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyChoker(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought a choker!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }

    public void buyMeme(){
        InventoryDB idb = new InventoryDB(this);
        boolean attempt = idb.buyMeme(slot);
        if(!attempt)
            Toast.makeText(ShopActivity.this, "You have no moneys", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ShopActivity.this, "You bought an epic meme!", Toast.LENGTH_SHORT).show();
        displayCoins();
    }
}
