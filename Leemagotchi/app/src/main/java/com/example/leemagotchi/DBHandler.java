package com.example.leemagotchi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "stats.db";
    private static final int DATABASE_VERSION = 115;

    private static final String TABLE_NAME = "statList";
    private static final String COLUMN_SLOT = "slot";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LIFE = "life";
    private static final String COLUMN_HAPPY = "happy";
    private static final String COLUMN_HUNGER = "hunger";
    private static final String COLUMN_MAX_HUNGER = "maxHunger";
    private static final String COLUMN_DISCIPLINE = "discipline";
    private static final String COLUMN_MAX_DISCIPLINE = "maxDiscipline";
    private static final String COLUMN_BAD = "bad";
    private static final String COLUMN_TRAINING = "training";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_NEEDXP = "needXp";
    private static final String COLUMN_POTTY = "potty";
    private static final String COLUMN_MESS = "mess";
    private static final String COLUMN_LAST_UPDATE = "lastUpdate";
    private static final String COLUMN_DEATHDATE = "deathdate";
    private static final String COLUMN_BIRTHDATE = "birthdate";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_SLOT + " TEXT PRIMARY KEY, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_LIFE + " TEXT, " +
                COLUMN_HAPPY + " TEXT, " +
                COLUMN_HUNGER + " TEXT, " +
                COLUMN_MAX_HUNGER + " TEXT, " +
                COLUMN_DISCIPLINE + " TEXT, " +
                COLUMN_MAX_DISCIPLINE + " TEXT, " +
                COLUMN_BAD + " TEXT, " +
                COLUMN_TRAINING + " TEXT, " +
                COLUMN_LEVEL + " TEXT, " +
                COLUMN_NEEDXP + " TEXT, " +
                COLUMN_POTTY + " TEXT, " +
                COLUMN_MESS + " TEXT, " +
                COLUMN_LAST_UPDATE + " TEXT, " +
                COLUMN_DEATHDATE + " TEXT, " +
                COLUMN_BIRTHDATE +  " TEXT )";

        db.execSQL(create_table_cmd);

        String query = "INSERT INTO "+TABLE_NAME+" VALUES('1', 'UNBORN','ALIVE','50','50', '200','50','200', 'false','0','0','100', '0', 'NO', '0', '0','0')";
        db.execSQL(query);
        query = "INSERT INTO "+TABLE_NAME+" VALUES('2', 'UNBORN','ALIVE','50','50', '200','50','200', 'false','0','0','100', '0', 'NO', '0', '0','0')";
        db.execSQL(query);
        query = "INSERT INTO "+TABLE_NAME+" VALUES('3', 'UNBORN','ALIVE','50','50', '200','50','200', 'false','0','0','100', '0', 'NO', '0', '0','0')";
        db.execSQL(query);
        //db.close();
        //restart();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void newChar(int slot, String type){
        setType(type, slot);
        setAlive(slot);
        setColumnHappy(50, slot);
        setColumnHunger(50, slot);
        setColumnMaxHunger(200, slot);
        setColumnDiscipline(50, slot);
        setColumnMaxDiscipline(200, slot);
        setColumnPotty(0, slot);
        setColumnMess("NO", slot);
        setColumnTraining(0, slot);
        setColumnLevel(0, slot);
        setColumnNeedXp(100, slot);
        String cDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
        setColumnBirthdate(cDate, slot);
        setColumnLastUpdate(cDate, slot);
        setColumnDeathdate("0", slot);
    }

    public void setType(String type, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_TYPE+"='"+type+"' + WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnType(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String type ="";
        String query = "SELECT "+COLUMN_TYPE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            type = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return type;
    }

    public void setColumnHappy(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_HAPPY+"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnHappy(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_HAPPY+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnHunger(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_HUNGER+"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnHunger(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_HUNGER+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnMaxHunger(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MAX_HUNGER+"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnMaxHunger(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MAX_HUNGER+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void feed(int amount, int slot) {
        int food = getColumnHunger(slot);
        int max = getColumnMaxHunger(slot);
        food += amount;
        if(food > max)
            food = max;
        setColumnHunger(food, slot);
    }

    public void setColumnDiscipline(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DISCIPLINE+"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnDiscipline(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DISCIPLINE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void discipline(int amount, int slot){
        int dis = getColumnDiscipline(slot);
        int max = getColumnMaxDiscipline(slot);
        dis += amount;
        if(dis > max)
            dis = max;
        setColumnDiscipline(dis, slot);
    }

    public void setColumnMaxDiscipline(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MAX_DISCIPLINE+"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnMaxDiscipline(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MAX_DISCIPLINE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnBad(boolean input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if(input)
            query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BAD +"='TRUE' + WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        else
            query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BAD +"='FALSE' + WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public boolean getColumnBad(int slot){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_BAD+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        if(pre.equalsIgnoreCase("FALSE")){
            return false;
        }else{
            return true;
        }
    }

    public void setColumnTraining(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_TRAINING +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnTraining(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_TRAINING+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public boolean train(int amount, int slot){
        int xp = getColumnTraining(slot);
        xp += amount;
        int need = getColumnNeedXp(slot);
        if(xp >= need){
            int level = getColumnLevel(slot);
            level ++;
            need += 10;
            setColumnLevel(level, slot);
            setColumnNeedXp(need, slot);
            xp = 0;
            setColumnTraining(xp, slot);
            return true;
        }
        setColumnTraining(xp, slot);
        return false;
    }

    public void setColumnLevel(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LEVEL +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnLevel(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_LEVEL+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnNeedXp(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_NEEDXP +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnNeedXp(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_NEEDXP+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnPotty(int input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_POTTY +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnPotty(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_POTTY+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void incPotty(int amount, int slot){
        int potty = getColumnPotty(slot);
        potty += amount;
        setColumnPotty(potty, slot);
    }

    public void setColumnMess(String input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MESS +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnMess(int slot){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MESS+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }

    public void Kill(int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LIFE +"='"+"DEAD"+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public void setUnborn(int slot){
        setType("UNBORN", slot);
    }

    public boolean IsBorn(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String out ="";
        String query = "SELECT "+COLUMN_TYPE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
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

    public void setAlive(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LIFE +"='ALIVE'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public boolean isAlive(int slot) {
        SQLiteDatabase db = this.getReadableDatabase();
        String out ="";
        String query = "SELECT "+COLUMN_LIFE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
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

    public void setColumnLastUpdate(String input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LAST_UPDATE +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnLastUpdate(int slot){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_LAST_UPDATE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }

    public void setColumnDeathdate(String input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DEATHDATE +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnDeathdate(int slot){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DEATHDATE+" FROM "+TABLE_NAME + " WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }

    public void setColumnBirthdate(String input, int slot){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIRTHDATE +"='"+input+"'+ WHERE " + COLUMN_SLOT + " EQUALS '" + slot + "'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnBirthdate(int slot){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_BIRTHDATE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }
}
