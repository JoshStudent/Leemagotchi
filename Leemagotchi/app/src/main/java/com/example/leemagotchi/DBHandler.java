package com.example.leemagotchi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "stats.db";
    private static final int DATABASE_VERSION = 4;

    private static final String TABLE_NAME = "statList";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LIFE = "life";
    private static final String COLUMN_MOOD = "mood";
    private static final String COLUMN_HAPPY = "happy";
    private static final String COLUMN_HUNGER = "hunger";
    private static final String COLUMN_DISCIPLINE = "discipline";
    private static final String COLUMN_TRAINING = "training";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_NEEDXP = "needXp";
    private static final String COLUMN_POTTY = "potty";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_TYPE + " TEXT PRIMARY KEY, " +
                COLUMN_LIFE + " TEXT, " +
                COLUMN_MOOD + " TEXT, " +
                COLUMN_HAPPY + " TEXT, " +
                COLUMN_HUNGER + " TEXT, " +
                COLUMN_DISCIPLINE + " TEXT, " +
                COLUMN_TRAINING + " TEXT, " +
                COLUMN_LEVEL + " TEXT, " +
                COLUMN_NEEDXP + " TEXT, " +
                COLUMN_POTTY + " TEXT )";

        db.execSQL(create_table_cmd);

        String query = "INSERT INTO "+TABLE_NAME+" VALUES('UNBORN','ALIVE','BLANK','50','50','50','0','0','100', '0')";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void restart(){
        setColumnType("UNBORN");
        setAlive();
        setColumnMood("BLANK");
        setColumnHappy(50);
        setColumnHunger(50);
        setColumnDiscipline(50);
        setColumnTraining(0);
        setColumnLevel(0);
        setColumnNeedXp(100);
    }

    public void setColumnType(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_TYPE+"='"+type+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnType() {
        SQLiteDatabase db = this.getReadableDatabase();
        String type ="";
        String query = "SELECT "+COLUMN_TYPE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            type = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return type;
    }

    public void setColumnMood(String type){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MOOD+"='"+type+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnMood() {
        SQLiteDatabase db = this.getReadableDatabase();
        String type ="";
        String query = "SELECT "+COLUMN_MOOD+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            type = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return type;
    }

    public void setColumnHappy(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_HAPPY+"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnHappy() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_HAPPY+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnHunger(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_HUNGER+"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnHunger() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_HUNGER+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void feed(int amount) {
        int food = getColumnHunger();
        food += amount;
        if(food > 200)
            food = 200;
        setColumnHunger(food);
    }

    public void setColumnDiscipline(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DISCIPLINE+"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnDiscipline() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DISCIPLINE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnTraining(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_TRAINING +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnTraining() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_TRAINING+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void train(int amount){
        int xp = getColumnTraining();
        xp += amount;
        if(xp >= getColumnNeedXp()){
            int level = getColumnLevel();
            level ++;
            int need = (level+1)*100;
            setColumnLevel(level);
            setColumnNeedXp(xp);
            xp = 0;
        }
        setColumnTraining(xp);
    }

    public void setColumnLevel(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LEVEL +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnLevel() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_LEVEL+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnNeedXp(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_NEEDXP +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnNeedXp() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_NEEDXP+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void Kill(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LIFE +"='"+"DEAD"+"'";
        db.execSQL(query);
        db.close();
    }

    public boolean IsBorn() {
        SQLiteDatabase db = this.getReadableDatabase();
        String out ="";
        String query = "SELECT "+COLUMN_TYPE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            out = cursor.getString(0);
        }
        cursor.close();
        db.close();
        if(out.equalsIgnoreCase("UNBORN")){
            return false;
        }else{
            return true;
        }
    }

    public void setAlive() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LIFE +"='ALIVE'";
        db.execSQL(query);
        db.close();
    }

    public boolean isAlive() {
        SQLiteDatabase db = this.getReadableDatabase();
        String out ="";
        String query = "SELECT "+COLUMN_LIFE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            out = cursor.getString(0);
        }
        cursor.close();
        db.close();
        if(out.equalsIgnoreCase("ALIVE")){
            return true;
        }else{
            return false;
        }
    }
}
