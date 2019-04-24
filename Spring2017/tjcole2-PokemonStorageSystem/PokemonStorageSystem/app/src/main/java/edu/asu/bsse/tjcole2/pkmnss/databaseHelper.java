package edu.asu.bsse.tjcole2.pkmnss;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Copyright (c) 2017 Tyler Cole.
 *
 * This file is part of PokemonStorageSystem.
 *
 * PokemonStorageSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PokemonStorageSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author   Tyler Cole    mailto:tjcole2@asu.edu.
 * @version April, 2017
 */

public class databaseHelper extends SQLiteOpenHelper{
    private String DATABASE_PATH;
    Context context;
    boolean debugon = false;
    private SQLiteDatabase db;

    public static String DATABASE_NAME = "storagesystem";

    public static final String POKEDEX_TABLE_NAME = "pokedex";
    public static final String POKEDEX_COLUMN_DEX_NUMBER = "dexnumber";
    public static final String POKEDEX_COLUMN_SPECIES_NAME = "speciesname";
    public static final String POKEDEX_COLUMN_SPRITE = "sprite";

    public static final String PC_TABLE_NAME = "pc";
    public static final String PC_BOX_NAMES = "boxname";
    public static final String PC_BOX_NUMBER = "boxnumber";

    public static final String POKEMON_TABLE_NAME = "pokemon";
    public static final String POKEMON_OBJECT_STRING = "objectstring";
    public static final String POKEMON_BOX_NUMBER = "boxnumber";
    public static final String POKEMON_BOX_POSITION = "boxposition";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        DATABASE_PATH = context.getFilesDir().getPath() + "/";
        android.util.Log.d(this.getClass().getSimpleName(), "dbpath: " + DATABASE_PATH);
    }

    public void createDB() throws IOException {
        this.getReadableDatabase();
        try {
            copyDB();
        } catch (IOException e) {
            android.util.Log.w(this.getClass().getSimpleName(),
                    "createDB Error copying database " + e.getMessage());
        }
    }

    /**
     * Does the database exist and has it been initialized? This method determines whether
     * the database needs to be copied to the data/data/pkgName/files directory by
     * checking whether the file exists. If it does it checks to see whether the db is
     * uninitialized or whether it has the course table.
     * @return false if the database file needs to be copied from the assets directory, true
     * otherwise.
     */
    private boolean checkDB(){    //does the database exist and is it initialized?
        SQLiteDatabase checkDB = null;
        boolean ret = false;
        try{
            String path = DATABASE_PATH + DATABASE_NAME + ".db";
            debug("DB --> checkDB: path to db is", path);
            File aFile = new File(path);
            if(aFile.exists()){
                checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
                if (checkDB!=null) {
                    debug("DB --> checkDB","opened db at: "+checkDB.getPath());
                    Cursor tabChk = checkDB.rawQuery("SELECT name FROM sqlite_master where type='table' and name='pokedex';", null);
                    boolean crsTabExists = false;
                    if(tabChk == null){
                        debug("DB --> checkDB","check for pokedex table result set is null");
                    }else{
                        tabChk.moveToNext();
                        debug("DB --> checkDB","check for pokedex table result set is: " +
                                ((tabChk.isAfterLast() ? "empty" : (String) tabChk.getString(0))));
                        crsTabExists = !tabChk.isAfterLast();
                    }
                    if(crsTabExists){
                        Cursor c= checkDB.rawQuery("SELECT * FROM pokedex", null);
                        c.moveToFirst();
                        while(! c.isAfterLast()) {
                            int dexNumber = c.getInt(0);
                            String species = c.getString(1);
                            debug("DB --> checkDB","pokedex table has dexNumber: "+
                                    dexNumber+"	Species: "+species);
                            c.moveToNext();
                        }
                        ret = true;
                    }
                }
            }
        }catch(SQLiteException e){
            android.util.Log.w("DB->checkDB",e.getMessage());
        }
        if(checkDB != null){
            checkDB.close();
        }
        return ret;
    }

    public void copyDB() throws IOException{
        try {
            if(!checkDB()){
                // only copy the database if it doesn't already exist in my database directory
                debug("DB --> copyDB", "checkDB returned false, starting copy");
                InputStream ip =  context.getResources().openRawResource(R.raw.storagesystem);
                // make sure the database path exists. if not, create it.
                File aFile = new File(DATABASE_PATH);
                if(!aFile.exists()){
                    aFile.mkdirs();
                }
                String op=  DATABASE_PATH + DATABASE_NAME +".db";
                OutputStream output = new FileOutputStream(op);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = ip.read(buffer))>0){
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                ip.close();
            }
        } catch (IOException e) {
            android.util.Log.w("DB --> copyDB", "IOException: "+e.getMessage());
        }
    }

    /*
        db = SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + POKEDEX_TABLE_NAME + "(" + POKEDEX_COLUMN_DEX_NUMBER + " INT,"
                + POKEDEX_COLUMN_SPECIES_NAME + " VARCHAR," + POKEDEX_COLUMN_SPRITE + " INT);");
        for(int i = 0; i < pokemon.length; i++){
            db.execSQL("INSERT INTO " + POKEDEX_TABLE_NAME +
                    "VALUES(" + (i+1) + ",'" + pokemon[i] + "'," + sprites[i] +");");
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PC_TABLE_NAME + "(" + PC_BOX_NUMBER + " INT,"
                + PC_BOX_NAMES + " VARCHAR);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + POKEMON_TABLE_NAME + "(" + POKEMON_OBJECT_STRING + " VARCHAR,"
            + POKEMON_BOX_NUMBER + " INT," + POKEMON_BOX_POSITION + "INT);");
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String getBoxName(int x){
        String y = "";
        Cursor c = db.rawQuery("SELECT "+ PC_BOX_NAMES +" FROM " + PC_TABLE_NAME + " WHERE "
                + PC_BOX_NUMBER + "=" + x, null);
        if(c.moveToFirst()) {
            String name = c.getString(0);
            c.close();
            return name;
        }
        else {
            c.close();
            return y;
        }
    }

    public void setBoxName(int x, String name){
        db.execSQL("UPDATE " + PC_TABLE_NAME + " SET " + PC_BOX_NAMES + "='" + name +
                "' WHERE " + POKEMON_BOX_NUMBER + "=" + x);
    }

    public void addPokemonToBox(Pokemon pokemon, int box, int pos){
        db.execSQL("INSERT INTO " + POKEMON_TABLE_NAME + " VALUES(" + box + "," + pos + ",'" + pokemon.toString() + "');");
    }

    public void updatePokemonData(Pokemon pokemon, int box, int pos){
        db.execSQL("UPDATE " + POKEMON_TABLE_NAME + " SET " + POKEMON_OBJECT_STRING + "='" + pokemon.toString() +
                "' WHERE " + POKEMON_BOX_NUMBER + "=" + box + " AND " + POKEMON_BOX_POSITION + "=" + pos);
    }

    public void deletePokemon(int box, int pos){
        db.delete(POKEMON_TABLE_NAME, POKEMON_BOX_NUMBER + "=" + box + " AND " + POKEMON_BOX_POSITION + "=" + pos, null);
    }

    public void movePokemon(int oldBox, int oldPos, int newBox, int newPos){
        db.execSQL("UPDATE " + POKEMON_TABLE_NAME + " SET "
                + POKEMON_BOX_NUMBER + "=" + newBox + "," + POKEMON_BOX_POSITION + "=" + newPos +
                "' WHERE " + POKEMON_BOX_NUMBER + "=" + oldBox + " AND " + POKEMON_BOX_POSITION + "=" + oldPos);
    }

    public Pokemon getPokemon(int box, int pos){
        //Log.d("Checking", "Box:" + box + " Pos:" + pos);
        Cursor c = db.rawQuery("SELECT * FROM " + POKEMON_TABLE_NAME + " WHERE "
            + POKEMON_BOX_NUMBER + "=" + box + " AND " + POKEMON_BOX_POSITION + "=" + pos, null);
        if(c.moveToFirst()) {
            //debug("Found",c.getString(2));
            String poke = c.getString(2);
            c.close();
            return new Pokemon(poke);
        }
        else {
            c.close();
            return new Pokemon();
        }
    }

    public SQLiteDatabase openDB() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME + ".db";
        if(checkDB()) {
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            debug("DB --> openDB", "opened db at path: " + db.getPath());
        }else{
            try {
                this.copyDB();
                db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            }catch(Exception ex) {
                android.util.Log.w(this.getClass().getSimpleName(),"unable to copy and open db: "+ex.getMessage());
            }
        }
        return db;
    }

    @Override
    public synchronized void close() {
        if(db != null)
            db.close();
        super.close();
    }

    private void debug(String hdr, String msg){
        if(debugon){
            android.util.Log.d(hdr,msg);
        }
    }

}
