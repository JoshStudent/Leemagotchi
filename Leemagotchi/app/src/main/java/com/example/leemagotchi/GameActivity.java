package com.example.leemagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_selFood, btn_selTrain;
    ImageButton btn_feed, btn_train, btn_scold, btn_toilet, btn_shop, btn_kill;
    ImageView mainImg;
    TextView txt_title, txt_status, txt_stats, txt_coins;
    View mainView;
    String type, food, train;
    boolean doingBad;
    boolean alive;
    int sfood = 1;
    int strain = 1;

    // Admin
    Button inc_hung, dec_hung, inc_disc, dec_disc, inc_train, dec_train, inc_coins, dec_coins;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainView = (View) findViewById(R.id.mainLayout);

        btn_feed = (ImageButton) findViewById(R.id.btn_feed);
        btn_feed.setOnClickListener(this);
        btn_train = (ImageButton) findViewById(R.id.btn_train);
        btn_train.setOnClickListener(this);
        btn_scold = (ImageButton) findViewById(R.id.btn_scold);
        btn_scold.setOnClickListener(this);
        btn_toilet = (ImageButton) findViewById(R.id.btn_toilet);
        btn_toilet.setOnClickListener(this);
        btn_shop = (ImageButton) findViewById(R.id.btn_shop);
        btn_shop.setOnClickListener(this);
        btn_kill = (ImageButton) findViewById(R.id.btn_kill);
        btn_kill.setOnClickListener(this);
        btn_selFood = (Button) findViewById(R.id.foodSelect);
        btn_selFood.setOnClickListener(this);
        btn_selTrain = (Button) findViewById(R.id.trainSelect);
        btn_selTrain.setOnClickListener(this);

        inc_hung = (Button) findViewById(R.id.incHunger);
        inc_hung.setOnClickListener(this);
        dec_hung = (Button) findViewById(R.id.decHunger);
        dec_hung.setOnClickListener(this);
        inc_disc = (Button) findViewById(R.id.incDisc);
        inc_disc.setOnClickListener(this);
        dec_disc = (Button) findViewById(R.id.decDisc);
        dec_disc.setOnClickListener(this);
        inc_train = (Button) findViewById(R.id.incTrain);
        inc_train.setOnClickListener(this);
        dec_train = (Button) findViewById(R.id.decTrain);
        dec_train.setOnClickListener(this);
        inc_coins = (Button) findViewById(R.id.incCoin);
        inc_coins.setOnClickListener(this);
        dec_coins = (Button) findViewById(R.id.decCoin);
        dec_coins.setOnClickListener(this);

        mainImg = (ImageView) findViewById(R.id.main_image);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_stats = (TextView) findViewById(R.id.statsView);
        txt_coins = (TextView) findViewById(R.id.coins);

        Intent intent = getIntent();
        type = (String)getIntent().getSerializableExtra("choice");

        // Check if character is born or not. If not, this is new character and must be birthed
        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);
        if(!db.IsBorn() && db.isAlive()){
            db.setColumnType(type);
            idb.resetInventory();
        } else if (!db.isAlive()){
            alive = false;
            goDeathScreen();
        }
        alive = true;
        doingBad = false;
        displayTitle(type);
        update();
        //gameLoop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btn_feed):
                feed();
                break;
            case (R.id.btn_train):
                train();
                break;
            case (R.id.btn_scold):
                scold();
                break;
            case (R.id.btn_toilet):
                toilet();
                break;
            case (R.id.btn_shop):
                shop();
                break;
            case (R.id.btn_kill):
                kill(true);
                break;
            case(R.id.foodSelect):
                selFood();
                break;
            case(R.id.trainSelect):
                selTrain();
                break;
            case (R.id.incHunger):
                incHung();
                break;
            case (R.id.decHunger):
                decHung();
                break;
            case (R.id.incDisc):
                incDisc();
                break;
            case (R.id.decDisc):
                decDisc();
                break;
            case (R.id.incTrain):
                incTrain();
                break;
            case (R.id.decTrain):
                decTrain();
                break;
            case (R.id.incCoin):
                incCoin();
                break;
            case (R.id.decCoin):
                decCoin();
                break;
        }
    }

    public void displayTitle(String type){
        if(type.equalsIgnoreCase("LEE"))
            txt_title.setText("LEEMAGOTCHI");
        else if(type.equalsIgnoreCase("DANA"))
            txt_title.setText("DANAGOTCHI");
        else
            txt_title.setText("ZENMAGOTCHI");
    }

    public void displayStats(int level, int happy, int hunger, int discipline, int training, int needXp){
        String stats = happy + "/200\n" +
                hunger + "/200\n" +
                discipline + "/200\n" +
                training + "/" + needXp +"\n" +
                level;

        txt_stats.setText(stats);
    }

    public void updateStatus(String mood, String status){
        if(type.equalsIgnoreCase("LEE")){
            if(mood.equalsIgnoreCase("HAPPY")) {
                mainImg.setImageResource(R.drawable.leehappy);
            }else if(mood.equalsIgnoreCase("NORMAL")) {
                mainImg.setImageResource(R.drawable.leenormal);
            }else {
                mainImg.setImageResource(R.drawable.leeupset);
            }
        } else if (type.equalsIgnoreCase("DANA")){
            if(mood.equalsIgnoreCase("HAPPY")) {
                mainImg.setImageResource(R.drawable.danahappy);
            }else if(mood.equalsIgnoreCase("NORMAL")) {
                mainImg.setImageResource(R.drawable.dananormal);
            }else {
                mainImg.setImageResource(R.drawable.danaupset);
            }
        } else {
            if(mood.equalsIgnoreCase("HAPPY")) {
                mainImg.setImageResource(R.drawable.zenhappy);
            }else if(mood.equalsIgnoreCase("NORMAL")) {
                mainImg.setImageResource(R.drawable.zennormal);
            }else {
                mainImg.setImageResource(R.drawable.zenupset);
            }
        }
        txt_status.setText(status);
    }

    public void update(){
        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);

        int happy;
        int level = db.getColumnLevel();
        int hunger = db.getColumnHunger();
        int discipline = db.getColumnDiscipline();
        int training = db.getColumnTraining();
        int needXp = db.getColumnNeedXp();
        String coins = Integer.toString(idb.getCoin());
        String name = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();

        // Calculate happiness and instant kill pet if less than 1
        happy = (hunger + discipline) / 2;
        db.setColumnHappy(happy);
        if(happy < 1)
            kill(false);

        String mood = "";
        String status = "";
        if(happy >= 150){
            mood = "HAPPY";
            status = name + " is very happy!";
        } else if (happy <= 50){
            mood = "UPSET";
            status = name + " is upset...";
        }else{
            mood = "NORMAL";
            status = name + " is ok.";
        }

        txt_coins.setText(coins);
        displayStats(level, happy, hunger, discipline, training, needXp);
        updateStatus(mood, status);
    }

    public void feed(){
        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);
        boolean attempt;
        switch(sfood){
            case(1):
                attempt = idb.useFood();
                if(attempt){
                    db.feed(10);
                    Toast.makeText(GameActivity.this, "You fed 1 jelly bean", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 jelly bean", Toast.LENGTH_SHORT).show();
                }
                break;
            case(2):
                attempt = idb.useBigFood();
                if(attempt){
                    db.feed(50);
                    Toast.makeText(GameActivity.this, "You fed 1 McNuggets", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 McNuggets", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(GameActivity.this, "NO FOOD SELECTED?", Toast.LENGTH_SHORT).show();
        }
        update();
    }

    public void train(){
        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);
        boolean attempt;
        switch(strain){
            case(1):
                attempt = idb.useGaming();
                if(attempt){
                    db.train(20);
                    Toast.makeText(GameActivity.this, "You trained with a gaming device", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 gaming device", Toast.LENGTH_SHORT).show();
                }
                break;
            case(2):
                attempt = idb.useDesert();
                if(attempt){
                    int food = db.getColumnHunger();
                    food = food / 10;
                    db.train(food);
                    Toast.makeText(GameActivity.this, "You caked up", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 cake", Toast.LENGTH_SHORT).show();
                }
                break;
            case(3):
                attempt = idb.useChoker();
                if(attempt){
                    int dis = db.getColumnDiscipline();
                    dis = dis / 10;
                    db.train(dis);
                    Toast.makeText(GameActivity.this, "Choking was successful ;)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 choker", Toast.LENGTH_SHORT).show();
                }
                break;
            case(4):
                attempt = idb.useMeme();
                if(attempt){
                    int happy = db.getColumnHappy();
                    happy = happy / 5;
                    db.train(happy);
                    Toast.makeText(GameActivity.this, "You shared an epic meme", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 funny", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                Toast.makeText(GameActivity.this, "NO TRAINING SELECTED?", Toast.LENGTH_SHORT).show();
        }
        update();
    }

    public void selFood(){
        PopupMenu popup = new PopupMenu(this, btn_selFood);
        popup.getMenuInflater().inflate(R.menu.food_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(menuClickListener);
        popup.show();
    }

    public void selTrain(){
        PopupMenu popup = new PopupMenu(this, btn_selTrain);
        popup.getMenuInflater().inflate(R.menu.train_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(menuClickListener);
        popup.show();
    }

    PopupMenu.OnMenuItemClickListener menuClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch(item.getItemId()){
                case(R.id.opFood):
                    sfood = 1;
                    Toast.makeText(GameActivity.this, "You selected Jelly Bean", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opBigFood):
                    sfood = 2;
                    Toast.makeText(GameActivity.this, "You selected McNuggets", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain1):
                    strain = 1;
                    Toast.makeText(GameActivity.this, "You selected Gaming Device", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain2):
                    strain = 2;
                    Toast.makeText(GameActivity.this, "You selected Desert", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain3):
                    strain = 3;
                    Toast.makeText(GameActivity.this, "You selected Choker", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain4):
                    strain = 4;
                    Toast.makeText(GameActivity.this, "You selected Epic Meme", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    };

    public void scold(){
        if(doingBad){
            // They will be scolding properly and increase discipline
        } else{
            // They are not properly scolded and lose discipline
        }
    }

    public void toilet(){

    }

    public void shop(){
        Intent intentShop = new Intent(this, ShopActivity.class);
        intentShop.putExtra("choice", type);
        startActivity(intentShop);
    }

    public void kill(boolean confirm){
        if(confirm)
            confirm = false;

        if(!confirm){
            DBHandler db = new DBHandler(this);
            alive = false;
            db.Kill();
            goDeathScreen();
        }
    }

    public void goDeathScreen(){
        Intent intentDeath = new Intent(this, DeathActivity.class);
        intentDeath.putExtra("choice", type);
        startActivity(intentDeath);
    }

    public void gameLoop(){
        while(alive){
            update();
        }
    }

    // Admin functions
    public void incHung(){
        DBHandler db = new DBHandler(this);
        int amount = db.getColumnHunger();
        amount += 10;
        db.setColumnHunger(amount);
        update();
    }
    public void decHung(){
        DBHandler db = new DBHandler(this);
        int amount = db.getColumnHunger();
        amount -= 10;
        db.setColumnHunger(amount);
        update();
    }

    public void incDisc(){
        DBHandler db = new DBHandler(this);
        int amount = db.getColumnDiscipline();
        amount += 10;
        db.setColumnDiscipline(amount);
        update();
    }
    public void decDisc(){
        DBHandler db = new DBHandler(this);
        int amount = db.getColumnDiscipline();
        amount -= 10;
        db.setColumnDiscipline(amount);
        update();
    }

    public void incTrain(){
        DBHandler db = new DBHandler(this);
        db.train(10);
        update();
    }
    public void decTrain(){
        DBHandler db = new DBHandler(this);
        int amount = db.getColumnTraining();
        amount -= 10;
        db.setColumnTraining(amount);
        update();
    }

    public void incCoin(){
        InventoryDB idb = new InventoryDB(this);
        idb.addCoins(10);
        update();
    }
    public void decCoin(){
        InventoryDB idb = new InventoryDB(this);
        idb.spendCoins(10);
        update();
    }


}
