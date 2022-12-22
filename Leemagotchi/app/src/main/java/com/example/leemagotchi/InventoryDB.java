package com.example.leemagotchi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inv.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "invList";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_FOOD = "food";
    private static final String COLUMN_BIG_FOOD = "bigFood";
    private static final String COLUMN_GAMING = "gaming";
    private static final String COLUMN_DESERT = "desert";
    private static final String COLUMN_CHOKER = "whip";
    private static final String COLUMN_MEME = "meme";
    private static final String COLUMN_COIN = "coin";

    public InventoryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_TYPE + " TEXT PRIMARY KEY, " +
                COLUMN_FOOD + " TEXT, " +
                COLUMN_BIG_FOOD + " TEXT, " +
                COLUMN_GAMING + " TEXT, " +
                COLUMN_DESERT + " TEXT, " +
                COLUMN_CHOKER + " TEXT, " +
                COLUMN_MEME + " TEXT, " +
                COLUMN_COIN + " TEXT )";

        db.execSQL(create_table_cmd);

        String query = "INSERT INTO "+TABLE_NAME+" VALUES('INVENTORY','0','0','0','0','0','0','100')";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void resetInventory(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"=50";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"=0";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD+"=0";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING+"=0";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT+"=0";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER+"=0";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME+"=0";
        db.execSQL(query);
        db.close();
    }

    public int getCoin() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_COIN+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void addCoins(int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        int coin = getCoin();
        coin += amount;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"='"+coin+"'";
        db.execSQL(query);
        db.close();
    }

    public boolean spendCoins(int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        int coin = getCoin();
        coin -= amount;
        if(coin < 0){
            return false;
        }
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"='"+coin+"'";
        db.execSQL(query);
        return true;
    }

    public int getFood() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_FOOD+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(10);
        if(!bought)
            return false;

        int item = getFood();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getFood();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getBigFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_BIG_FOOD+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyBigFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(50);
        if(!bought)
            return false;

        int item = getBigFood();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useBigFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getBigFood();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getGaming() {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_GAMING+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyGaming(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(10);
        if(!bought)
            return false;

        int item = getGaming();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useGaming(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getGaming();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getDesert() {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DESERT+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyDesert(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(25);
        if(!bought)
            return false;

        int item = getDesert();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useDesert(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getDesert();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getChoker() {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_CHOKER+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyChoker(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(25);
        if(!bought)
            return false;

        int item = getChoker();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useChoker(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getChoker();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getMeme() {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MEME+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyMeme(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(50);
        if(!bought)
            return false;

        int item = getMeme();
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useMeme(){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getMeme();
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME +"='"+item+"'";
        db.execSQL(query);
        db.close();
        return true;
    }
}
