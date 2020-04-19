package com.hackatum.bookit.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler instance;

    public static final String DATABASE_NAME = "Users.db";
    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String PROVIDER_TABLE = "provider_table";

    private DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public static DBHandler createDBHandler (Context context){
        if (instance == null) {
            instance = new DBHandler(context.getApplicationContext());
        }
        return instance;
    }
    public static synchronized DBHandler getInstance() {
        if (instance == null) {
            try {
                throw new Exception("DB has not been created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCustomers = "CREATE TABLE " + CUSTOMER_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, name TEXT, lastname TEXT, phonenumber TEXT )";
        String sqlProviders = "CREATE TABLE " + PROVIDER_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, name TEXT, lastname TEXT, phonenumber TEXT, businessname TEXT, businesstype TEXT )";

        db.execSQL(sqlCustomers);
        db.execSQL(sqlProviders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlCustomers = "DROP TABLE IF EXISTS " + CUSTOMER_TABLE;
        String sqlProviders = "DROP TABLE IF EXISTS " + PROVIDER_TABLE;

        db.execSQL(sqlCustomers);
        db.execSQL(sqlProviders);

        onCreate(db);
    }

    public String loadHandler(String tableName){
        String result = "";
        String query = "Select * FROM" + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String lastname = cursor.getString(2);
            result += String.valueOf(id) + " " + name + " " + lastname +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    /** Add a user to the database
     * @param user an object of kind LoggedInUser
     * @param tableName
     */
    public void addRegistry(LoggedInUser user, String tableName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUserName());
        contentValues.put("password", user.getPassword());
        contentValues.put("name", user.getName());
        contentValues.put("lastname", user.getLastName());
        contentValues.put("phonenumber", user.getPhoneNumber());
        db.insert(tableName, null, contentValues);
        db.close();
    }

    public LoggedInUser findHandler(String userName, String tableName){
        String query = "Select * FROM " + tableName + " WHERE userName=" + "'" + userName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        LoggedInUser user = null;

        if(tableName.equals(DBHandler.CUSTOMER_TABLE)){
            user = new LoggedInCustomer();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setLastName(cursor.getString(4));
                user.setPhoneNumber(cursor.getString(5));
                cursor.close();
            } else {
                user = null;
            }
        }

        if(tableName.equals(DBHandler.PROVIDER_TABLE)){
            user = new LoggedInProvider();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setLastName(cursor.getString(4));
                user.setPhoneNumber(cursor.getString(5));
                ((LoggedInProvider) user).setBusinessName(cursor.getString(5));
                ((LoggedInProvider) user).setBusinessType(cursor.getString(5));
                cursor.close();
            } else {
                user = null;
            }
        }
        db.close();
        return user;
    }

    public boolean deleteRegistry(int id, String tableName){
        boolean result = false;
        String query = "Select * FROM " + tableName + " WHERE id= '" + String.valueOf(id) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        LoggedInUser user = new LoggedInUser();
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(tableName, "id=?",
                    new String[] {
                            String.valueOf(user.getId())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int id, String userName, String password, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("id", id);
        args.put("username", userName);
        args.put("password", password);
        return db.update(tableName, args, "id=" + id, null) > 0;
    }
}

