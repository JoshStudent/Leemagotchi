package com.example.leemagotchi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inv.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME = "invList";
    private static final String COLUMN_SLOT = "slot";
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
                "(" + COLUMN_SLOT + " TEXT PRIMARY KEY, " +
                COLUMN_FOOD + " TEXT, " +
                COLUMN_BIG_FOOD + " TEXT, " +
                COLUMN_GAMING + " TEXT, " +
                COLUMN_DESERT + " TEXT, " +
                COLUMN_CHOKER + " TEXT, " +
                COLUMN_MEME + " TEXT, " +
                COLUMN_COIN + " TEXT )";

        db.execSQL(create_table_cmd);

        String query = "INSERT INTO "+TABLE_NAME+" VALUES('1','0','0','0','0','0','0','100')";
        db.execSQL(query);
        query = "INSERT INTO "+TABLE_NAME+" VALUES('2','0','0','0','0','0','0','100')";
        db.execSQL(query);
        query = "INSERT INTO "+TABLE_NAME+" VALUES('3','0','0','0','0','0','0','100')";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void resetInventory(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"=50" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME+"=0" + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getCoin(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_COIN+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void addCoins(int amount, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int coin = getCoin(slot);
        coin += amount;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"='"+coin+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public boolean spendCoins(int amount, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int coin = getCoin(slot);
        coin -= amount;
        if(coin < 0){
            return false;
        }
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_COIN+"='"+coin+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        return true;
    }

    public int getFood(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_FOOD+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyFood(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(10,slot);
        if(!bought)
            return false;

        int item = getFood(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useFood(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getFood(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_FOOD+"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getBigFood(int slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_BIG_FOOD+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyBigFood(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(50,slot);
        if(!bought)
            return false;

        int item = getBigFood(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useBigFood(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getBigFood(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIG_FOOD +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getGaming(int slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_GAMING+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyGaming(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(10,slot);
        if(!bought)
            return false;

        int item = getGaming(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useGaming(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getGaming(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_GAMING +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getDesert(int slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DESERT+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyDesert(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(25,slot);
        if(!bought)
            return false;

        int item = getDesert(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useDesert(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getDesert(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DESERT +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getChoker(int slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_CHOKER+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyChoker(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(25,slot);
        if(!bought)
            return false;

        int item = getChoker(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useChoker(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getChoker(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_CHOKER +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public int getMeme(int slot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MEME+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean buyMeme(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean bought = spendCoins(50,slot);
        if(!bought)
            return false;

        int item = getMeme(slot);
        item++;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }

    public boolean useMeme(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        int item = getMeme(slot);
        item--;
        if(item < 0)
            return false;
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MEME +"='"+item+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
        return true;
    }
}
