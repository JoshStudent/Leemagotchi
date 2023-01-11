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
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LIFE = "life";
    private static final String COLUMN_MOOD = "mood";
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
                "(" + COLUMN_TYPE + " TEXT PRIMARY KEY, " +
                COLUMN_LIFE + " TEXT, " +
                COLUMN_MOOD + " TEXT, " +
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

        String query = "INSERT INTO "+TABLE_NAME+" VALUES('UNBORN','ALIVE','BLANK','50','50', '200','50','200', 'false','0','0','100', '0', 'NO', '0', '0','0')";
        db.execSQL(query);
        //db.close();
        //restart();
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
        setColumnMaxHunger(200);
        setColumnDiscipline(50);
        setColumnMaxDiscipline(200);
        setColumnPotty(0);
        setColumnMess("NO");
        setColumnTraining(0);
        setColumnLevel(0);
        setColumnNeedXp(100);
        String sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
        setColumnBirthdate(sdf);
        setColumnLastUpdate(sdf);
        setColumnDeathdate("0");
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

    public void setColumnMaxHunger(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MAX_HUNGER+"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnMaxHunger() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MAX_HUNGER+" FROM "+TABLE_NAME;
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
        int max = getColumnMaxHunger();
        food += amount;
        if(food > max)
            food = max;
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

    public void discipline(int amount){
        int dis = getColumnDiscipline();
        int max = getColumnMaxDiscipline();
        dis += amount;
        if(dis > max)
            dis = max;
        setColumnDiscipline(dis);
    }

    public void setColumnMaxDiscipline(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MAX_DISCIPLINE+"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnMaxDiscipline() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MAX_DISCIPLINE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void setColumnBad(boolean input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query;
        if(input)
            query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BAD +"='TRUE'";
        else
            query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BAD +"='FALSE'";
        db.execSQL(query);
        db.close();
    }

    public boolean getColumnBad(){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_BAD+" FROM "+TABLE_NAME;
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

    public boolean train(int amount){
        int xp = getColumnTraining();
        xp += amount;
        int need = getColumnNeedXp();
        if(xp >= need){
            int level = getColumnLevel();
            level ++;
            need += 10;
            setColumnLevel(level);
            setColumnNeedXp(need);
            xp = 0;
            setColumnTraining(xp);
            return true;
        }
        setColumnTraining(xp);
        return false;
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

    public void setColumnPotty(int input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_POTTY +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public int getColumnPotty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_POTTY+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        int out = Integer.parseInt(pre);
        return out;
    }

    public void incPotty(int amount){
        int potty = getColumnPotty();
        potty += amount;
        setColumnPotty(potty);
    }

    public void setColumnMess(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_MESS +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnMess(){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_MESS+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
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

    public void setColumnLastUpdate(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_LAST_UPDATE +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnLastUpdate(){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_LAST_UPDATE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }

    public void setColumnDeathdate(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_DEATHDATE +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnDeathdate(){
        SQLiteDatabase db = this.getReadableDatabase();
        String pre ="";
        String query = "SELECT "+COLUMN_DEATHDATE+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount()>=1 && cursor.moveToFirst()){
            pre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pre;
    }

    public void setColumnBirthdate(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COLUMN_BIRTHDATE +"='"+input+"'";
        db.execSQL(query);
        db.close();
    }

    public String getColumnBirthdate(){
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
