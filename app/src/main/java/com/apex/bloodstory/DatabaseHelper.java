package com.apex.bloodstory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my lappy on 21-03-2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION =2;
    private static final String DATABASE_NAME="user.db";
    private static final String TABLE_NAME="User";
    private static final String COLUMN_UNAME="username";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_PASSWORD="password";
    private static final String COLUMN_EMAIL="email";
    //-----------------------------------------------------------------------------------------
    private static final String TABLE_NAMEB="BloodBank";
    private static final String COLUMN_AGE="age";
    private static final String COLUMN_ADDRESS="address";
    private static final String COLUMN_CITY="city";
    private static final String COLUMN_PHONE="phone";
    private static final String COLUMN_BLOODGROUP="bloodgr";
    private static final String COLUMN_DATE="date";

    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table User(username text,name text,password text,email text)";
//--------------------------------------------------------------------------------------------------------------------
    private static final String TABLE_CREATEB="create table BloodBank(name text,age text,address text,city text,phone text,bloodgr text,date text,flag integer default 0);";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATEB);
        this.db=db;
    }
    public void input(Contact c){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_UNAME,c.getuname());
        values.put(COLUMN_NAME,c.getname());
        values.put(COLUMN_PASSWORD,c.getpassword());
        values.put(COLUMN_EMAIL,c.getemail());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public int search(String uname,String pass){
        db=this.getReadableDatabase();
        String querry="select "+COLUMN_UNAME+","+COLUMN_PASSWORD+" from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(querry,null);
        String a,b;
        int x=0;
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                b=cursor.getString(1);
                if(a.equals(uname)&&b.equals(pass))
                {
                    x=1;
                    break;
                }
            }while(cursor.moveToNext());
        }return(x);

    }
    //---------------------------------------------------------------------------------------------
    public int inputblood(Contact c){
        int t=0;
       if(searchifexist(c.getname(),c.getbloodgr(),c.getdate())){
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
           values.put(COLUMN_NAME,c.getname());
           values.put(COLUMN_AGE,c.getage());
           values.put(COLUMN_ADDRESS,c.getaddress());
           values.put(COLUMN_CITY,c.getcity());
           values.put(COLUMN_PHONE,c.getphone());
           values.put(COLUMN_BLOODGROUP,c.getbloodgr());
           values.put(COLUMN_DATE,c.getdate());
        db.insert(TABLE_NAMEB,null,values);
           t=1;
        db.close();
    }return t;
    }
    public boolean searchifexist(String name,String bloodgr,String date){
        db=this.getReadableDatabase();
        String cutmonth=date.substring(5,7);
        String cutyear=date.substring(0,4);
        boolean flag=false;
        int cud=Integer.parseInt(cutmonth);
        int cuy=Integer.parseInt(cutyear);
        String querry="select date from "+TABLE_NAMEB+" where "+COLUMN_NAME+" ='"+name+"'";
        Cursor cursor =db.rawQuery(querry,null);
        if(cursor.getCount()==0){
            flag=true;

        }else{
        if(cursor.moveToFirst()){
            do{
                int year=Integer.parseInt(cursor.getString(0).substring(0,4));
                int x=Integer.parseInt((cursor.getString(0)).substring(5,7));
                if(year==cuy){

                if(cud-x>=3){
                 flag=true;
                    break;
                }
                }
                else if(cuy-year==1){
                    cud=cud+12;
                    if(cud-x>=3){
                        flag=true;
                        break;
                    }
                }else{
                    flag=true;
                }
            }while (cursor.moveToNext());
        }}
return  flag;
    }

    public String[] retrieve(String bloodgrp,String city){
        db=this.getReadableDatabase();

        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String cutmonth=date1.substring(5,7);
        String cutyear=date1.substring(0,4);
        int cud=Integer.parseInt(cutmonth);
        int cuy=Integer.parseInt(cutyear);
        String querry="select date from "+TABLE_NAMEB+" where "+COLUMN_BLOODGROUP+" ='"+bloodgrp+"'";

       Cursor cursor =db.rawQuery(querry,null);
        if(cursor.moveToFirst()){
            do{
                String upquerry="update "+TABLE_NAMEB+" set flag=0 where date='"+cursor.getString(0)+"'";
                int year=Integer.parseInt(cursor.getString(0).substring(0,4));
                int x=Integer.parseInt((cursor.getString(0)).substring(5,7));
                if(year==cuy){

                    if(cud-x>=3){
                        db.execSQL(upquerry);
                        break;
                    }
                }
                else if(cuy-year==1){
                    cud=cud+12;
                    if(cud-x>=3){
                        db.execSQL(upquerry);
                        break;
                    }
                }else{
                    db.execSQL(upquerry);
                }
            }while(cursor.moveToNext());
        }

        String querry2="select name from "+TABLE_NAMEB+" where "+COLUMN_BLOODGROUP+" ='"+bloodgrp+"' and "+COLUMN_CITY+"='"+city+"' and flag=0";
        Cursor cursor1 =db.rawQuery(querry2,null);
        int x=cursor1.getCount();
        String value[]=new String[x];
        int i=0;
        if(cursor1.moveToFirst()){
            do{

               value[i]=cursor1.getString(0);
                i++;

            }while (cursor1.moveToNext());
        }

    return value;
    }

    public String[] searchdonor(String getName){

        db=this.getReadableDatabase();
        String querry="select * from "+TABLE_NAMEB+" where "+COLUMN_NAME+"='"+getName+"'";
        Cursor cursor=db.rawQuery(querry,null);
        cursor.moveToFirst();
        String a[]=new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(5)};
        return a;
    }
    public void changeflag(String name,String date){
        db=this.getReadableDatabase();
        String querry="update "+TABLE_NAMEB+" set "+COLUMN_DATE+"='"+date+"',flag=1 where "+COLUMN_NAME+" ='"+name+"'";
        db.execSQL(querry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
