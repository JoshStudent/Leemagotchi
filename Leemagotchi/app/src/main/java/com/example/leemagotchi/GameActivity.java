package com.example.leemagotchi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button back, btn_selFood, btn_selTrain, btn_update;
    ImageButton btn_feed, btn_train, btn_scold, btn_toilet, btn_shop, btn_kill;
    ImageView mainImg;
    TextView txt_title, txt_status, txt_stats, txt_coins, time;
    View mainView;
    String type, food, train;
    int slot;
    boolean alive;
    int sfood = 1;
    int strain = 1;

    // Admin
    //Button inc_hung, dec_hung, inc_disc, dec_disc, inc_train, dec_train, inc_coins, dec_coins;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mainView = (View) findViewById(R.id.mainLayout);

        back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(this);
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
        btn_update = (Button) findViewById(R.id.update);
        btn_update.setOnClickListener(this);

        /*inc_hung = (Button) findViewById(R.id.incHunger);
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
        dec_coins.setOnClickListener(this);*/

        mainImg = (ImageView) findViewById(R.id.main_image);

        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_stats = (TextView) findViewById(R.id.statsView);
        txt_coins = (TextView) findViewById(R.id.coins);
        time = (TextView) findViewById(R.id.time);

        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);

        Intent intent = getIntent();
        slot = Integer.parseInt((String)getIntent().getSerializableExtra("slot"));
        type = db.getColumnType(slot);

        // Check if character is born or not. If not, this is new character and must be birthed

        if(db.isAlive(slot) && !db.IsBorn(slot)){ // new birth
            idb.resetInventory(slot);
            db.setType(type,slot);
            String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
            db.setColumnBirthdate(cDate,slot);
            db.setColumnLastUpdate(cDate,slot);
        } else if (!db.isAlive(slot)){
            alive = false;
            goDeathScreen();
        }
        alive = true;
        displayTitle(type);
        update();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btn_back):
                back();
                break;
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
            case(R.id.update):
                update();
                break;
            /*case (R.id.incHunger):
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
                break;*/
        }
    }

    public void back(){
        Intent intentMain = new Intent(this, StartActivity.class);
        startActivity(intentMain);
        finish();
    }

    public void displayTitle(String type){
        if(type.equalsIgnoreCase("LEE"))
            txt_title.setText("LEEMAGOTCHI");
        else if(type.equalsIgnoreCase("DANA"))
            txt_title.setText("DANAGOTCHI");
        else
            txt_title.setText("ZENMAGOTCHI");
    }

    public void displayStats(int level, int happy, int hunger, int maxHunger, int discipline, int maxDiscipline, int training, int needXp){
        String stats = happy + "\n" +
                hunger + "/" + maxHunger + "\n" +
                discipline + "/" + maxDiscipline + "\n" +
                training + "/" + needXp +"\n" +
                level;

        txt_stats.setText(stats);
    }

    public void updateStatus(String mood, String status){
        if(type.equalsIgnoreCase("LEE")){
            if(mood.equalsIgnoreCase("POTTY")){
                mainImg.setImageResource(R.drawable.leestinky);
            }else if(mood.equalsIgnoreCase("BAD")){
                mainImg.setImageResource(R.drawable.leeupset);
            }else if(mood.equalsIgnoreCase("HAPPY")) {
                mainImg.setImageResource(R.drawable.leehappy);
            }else if(mood.equalsIgnoreCase("NORMAL")) {
                mainImg.setImageResource(R.drawable.leenormal);
            }else {
                mainImg.setImageResource(R.drawable.leeupset);
            }
        } else if (type.equalsIgnoreCase("DANA")){
            if(mood.equalsIgnoreCase("POTTY")){
                mainImg.setImageResource(R.drawable.danastinky);
            }else if(mood.equalsIgnoreCase("BAD")){
                mainImg.setImageResource(R.drawable.danaupset);
            }else if(mood.equalsIgnoreCase("HAPPY")) {
                mainImg.setImageResource(R.drawable.danahappy);
            }else if(mood.equalsIgnoreCase("NORMAL")) {
                mainImg.setImageResource(R.drawable.dananormal);
            }else {
                mainImg.setImageResource(R.drawable.danaupset);
            }
        } else {
            if(mood.equalsIgnoreCase("POTTY")){
                mainImg.setImageResource(R.drawable.zenstinky);
            }else if(mood.equalsIgnoreCase("BAD")){
                mainImg.setImageResource(R.drawable.zenupset);
            }else if(mood.equalsIgnoreCase("HAPPY")) {
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

        // Compare the last saved time in the database, then update pet accordingly and save the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String lDate = db.getColumnLastUpdate(slot);
        String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
        LocalDateTime d1 = LocalDateTime.parse(lDate, formatter);
        LocalDateTime d2 = LocalDateTime.parse(cDate, formatter);
        long minDiff = ChronoUnit.MINUTES.between(d2, d1);
        if(minDiff < 0)
            minDiff = -minDiff;
        String t = "Minutes since last update: " + minDiff;
        time.setText(t);

        // Update if its been at least 10 min since last update
        // 1 hunger is lost for every 10 minutes since the last update
        // 2 gold is gained for every 10 minutes since the last update
        // 1 potty is gained for every 10 minutes since the last update
        // 1 discipline is lost for every 20 minutes. If hunger is low, 1 is lost for every 10 instead
        String name = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
        int hunger = db.getColumnHunger(slot);
        if (minDiff>10){
            int amount = ((int)minDiff)/10;
            db.feed(-amount,slot);
            idb.addCoins(amount*2,slot);
            db.incPotty(amount,slot);
            db.setColumnLastUpdate(cDate,slot);
            if(hunger < 0)
                db.discipline(-amount*4,slot);
            else if(hunger - amount < 0){
                int amount2 = Math.abs(hunger-amount);
                db.discipline(-amount2*5,slot);
                db.discipline(-(amount-amount2),slot);
            } else if(hunger < 50)
                db.discipline(-amount,slot);
            else if(hunger - amount < 50){
                int amount2 = 50 - (hunger - amount);
                db.discipline(amount2,slot);
                db.discipline((amount - 50) / 2,slot);
            }else
                db.discipline(amount/2,slot);

            // Return message
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle("Welcome back!");
            builder.setMessage("While you were gone, " + name + " made " + (amount*2) + " coins!");
            builder.setCancelable(true);
            builder.setPositiveButton("Thanks!", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }

        int happy;
        int level = db.getColumnLevel(slot);
        hunger = db.getColumnHunger(slot);
        int maxHunger = db.getColumnMaxHunger(slot);
        int discipline = db.getColumnDiscipline(slot);
        int maxDiscipline = db.getColumnMaxDiscipline(slot);
        int training = db.getColumnTraining(slot);
        int needXp = db.getColumnNeedXp(slot);
        int toilet = db.getColumnPotty(slot);
        String coins = Integer.toString(idb.getCoin(slot));

        // Calculate happiness and instant kill pet if less than 1
        happy = (hunger + discipline) / 2;
        db.setColumnHappy(happy,slot);
        if(happy < 1)
            kill(false);

        // First check potty, if that is high then potty mode
        String mood = "";
        String status = "";
        if(toilet >= 50) {
            mood = "POTTY";
            if (toilet >= 100) {
                // poop everywhere
                db.setColumnMess("YES",slot);
                db.setColumnBad(true,slot);
                status = name + " pooped everywhere!";
            } else {
                // Depending on discipline, either "needs to use potty" or poop everywhere
                if (discipline >= 100) {
                    status = name + " needs to use the potty...";
                    db.setColumnMess("NO",slot);
                } else {
                    db.setColumnMess("YES",slot);
                    db.setColumnBad(true,slot);
                    status = name + " pooped everywhere!";
                }
            }
        }else if(db.getColumnBad(slot)){ // set status to bad if they bad
            mood = "BAD";
            status = name + " is acting up...";
        }else{ // Find mood from happiness
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
        }

        txt_coins.setText(coins);
        displayStats(level, happy, hunger, maxHunger, discipline, maxDiscipline, training, needXp);
        updateStatus(mood, status);
    }

    public void feed(){
        DBHandler db = new DBHandler(this);
        InventoryDB idb = new InventoryDB(this);
        boolean attempt;
        switch(sfood){
            case(1):
                attempt = idb.useFood(slot);
                if(attempt){
                    db.feed(10,slot);
                    db.incPotty(5,slot);
                    Toast.makeText(GameActivity.this, "You fed 1 jelly bean", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 jelly bean", Toast.LENGTH_SHORT).show();
                }
                break;
            case(2):
                attempt = idb.useBigFood(slot);
                if(attempt){
                    db.feed(50,slot);
                    db.incPotty(20,slot);
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
        boolean levelUp = false;
        switch(strain){
            case(1):
                attempt = idb.useGaming(slot);
                if(attempt){
                    levelUp = db.train(20,slot);
                    Toast.makeText(GameActivity.this, "You trained with a gaming device", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 gaming device", Toast.LENGTH_SHORT).show();
                }
                break;
            case(2):
                attempt = idb.useDesert(slot);
                if(attempt){
                    int food = db.getColumnHunger(slot);
                    int maxFood = db.getColumnMaxHunger(slot);
                    food = (food / 10) + (maxFood/10);
                    levelUp = db.train(food,slot);
                    Toast.makeText(GameActivity.this, "You caked up", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 cake", Toast.LENGTH_SHORT).show();
                }
                break;
            case(3):
                attempt = idb.useChoker(slot);
                if(attempt){
                    int dis = db.getColumnDiscipline(slot);
                    int maxDis = db.getColumnMaxDiscipline(slot);
                    dis = (dis / 10) + (maxDis / 10);
                    levelUp = db.train(dis,slot);
                    Toast.makeText(GameActivity.this, "Choking was successful ;)", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 choker", Toast.LENGTH_SHORT).show();
                }
                break;
            case(4):
                attempt = idb.useMeme(slot);
                if(attempt){
                    int happy = db.getColumnHappy(slot);
                    int maxDis = db.getColumnMaxDiscipline(slot);
                    int maxFood = db.getColumnMaxHunger(slot);
                    happy = (happy / 5) + (((maxDis+maxFood)/2)/10);
                    levelUp = db.train(happy,slot);
                    Toast.makeText(GameActivity.this, "You shared an epic meme", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(GameActivity.this, "You have 0 funny", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                Toast.makeText(GameActivity.this, "NO TRAINING SELECTED?", Toast.LENGTH_SHORT).show();
        }
        if(levelUp)
            levelUp();
        update();
    }

    public void levelUp(){
        DBHandler db = new DBHandler(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Level up!");
        builder.setMessage("Choose a stat to increase!");
        builder.setCancelable(false);
        builder.setPositiveButton("Hunger", (DialogInterface.OnClickListener) (dialog, which) -> {
            int max = db.getColumnMaxHunger(slot);
            max+=5;
            db.feed(20,slot);
            db.setColumnMaxHunger(max,slot);
        });
        builder.setNegativeButton("Discipline", (DialogInterface.OnClickListener) (dialog, which) -> {
            int max = db.getColumnMaxDiscipline(slot);
            max+=5;
            db.discipline(20,slot);
            db.setColumnMaxDiscipline(max,slot);
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
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
                    Toast.makeText(GameActivity.this, "You selected Cake", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain3):
                    strain = 3;
                    Toast.makeText(GameActivity.this, "You selected Choker", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.opTrain4):
                    strain = 4;
                    Toast.makeText(GameActivity.this, "You selected Epic Meme", Toast.LENGTH_SHORT).show();
                    return true;
                case(R.id.killConfirm):
                    kill(false);
                    return true;
                case(R.id.noKill):
                    Toast.makeText(GameActivity.this, "You have spared them for now...", Toast.LENGTH_SHORT).show();
                default:
                    return false;
            }
        }
    };

    public void scold(){
        DBHandler db = new DBHandler(this);
        boolean doingBad = db.getColumnBad(slot);
        if(doingBad){
            // They will be scolding properly and increase discipline
            db.discipline(20,slot);
            db.setColumnBad(false,slot);
            Toast.makeText(GameActivity.this, "You properly told them to fuck off", Toast.LENGTH_SHORT).show();
        } else{
            // They are not properly scolded and lose discipline
            db.discipline(-20,slot);
            Toast.makeText(GameActivity.this, "Bad timing! Now they're even more pissed!", Toast.LENGTH_SHORT).show();
        }
        update();
    }

    public void toilet(){
        DBHandler db = new DBHandler(this);
        int toilet = db.getColumnPotty(slot);
        String mess = db.getColumnMess(slot);
        if(toilet>=50){
            db.setColumnPotty(0,slot);
            if(mess.equalsIgnoreCase("YES")){
                db.discipline(10,slot);
                db.setColumnMess("NO",slot);
            }else{
                db.discipline(20,slot);
            }
        }
        update();
    }

    public void shop(){
        Intent intentShop = new Intent(this, ShopActivity.class);
        intentShop.putExtra("choice", type);
        startActivity(intentShop);
        finish();
    }

    public void kill(boolean confirm){
        if(confirm) {
            PopupMenu popup = new PopupMenu(this, btn_kill);
            popup.getMenuInflater().inflate(R.menu.kill_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(menuClickListener);
            popup.show();
        }

        if(!confirm){
            DBHandler db = new DBHandler(this);
            String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
            db.setColumnDeathdate(cDate,slot);

            alive = false;
            db.Kill(slot);
            goDeathScreen();
        }
    }

    public void goDeathScreen(){
        Intent intentDeath = new Intent(this, GameActivity.class);
        intentDeath.putExtra("slot", slot);
        startActivity(intentDeath);
        finish();
    }

    // Admin functions
    /*public void incHung(){
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
        boolean attempt = db.train(10);
        if(attempt)
            levelUp();
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
    }*/


}
