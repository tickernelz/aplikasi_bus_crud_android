package upr.uas.pedro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Bus.db";

    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserParams.TABLE_NAME + "("
                    + UserParams.KEY_ID + " INTEGER PRIMARY KEY, "
                    + UserParams.KEY_NAME + " TEXT, "
                    + UserParams.KEY_USERNAME + " TEXT, "
                    + UserParams.KEY_PASSWORD + " TEXT, "
                    + UserParams.KEY_IS_LOGIN + " INTEGER DEFAULT 0)";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserParams.TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public String getName(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Generating query to read from DataBase
        String select = "SELECT * FROM " + UserParams.TABLE_NAME + " WHERE "
                + UserParams.KEY_ID + " = " + user.getId();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(1));
            Log.d("Bus.db", "Successfully read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return user.getName();
        } else {
            Log.d("Bus.db", "Failed to read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return null;
        }
    }

    public String getUsername(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Generating query to read from DataBase
        String select = "SELECT * FROM " + UserParams.TABLE_NAME + " WHERE "
                + UserParams.KEY_ID + " = " + user.getId();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            user.setUsername(cursor.getString(2));
            Log.d("Bus.db", "Successfully read " + user.getId() + " " + user.getUsername(false));
            cursor.close();
            db.close();
            return user.getUsername(false);
        } else {
            Log.d("Bus.db", "Failed to read " + user.getId() + " " + user.getUsername(false));
            cursor.close();
            db.close();
            return null;
        }
    }

    public void InsertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserParams.KEY_NAME, user.getName());
        values.put(UserParams.KEY_USERNAME, user.getUsername(false));
        values.put(UserParams.KEY_PASSWORD, user.getPassword(false));

        db.insert(UserParams.TABLE_NAME, null, values);
        Log.d("Bus.db", "Successfully inserted " + user.getId() + " " + user.getName());
        db.close();
    }

    public boolean checkUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Generating query to read from DataBase
        String select = "SELECT * FROM " + UserParams.TABLE_NAME + " WHERE "
                + UserParams.KEY_USERNAME + " = " + user.getUsername(true) + " AND "
                + UserParams.KEY_PASSWORD + " = " + user.getPassword(true);
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setUsername(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setIsLogin(cursor.getInt(4));
            Log.d("Bus.db", "Successfully read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return true;
        } else {
            Log.d("Bus.db", "Failed to read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean checkIsLogin(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Generating query to read from DataBase
        String select = "SELECT * FROM " + UserParams.TABLE_NAME + " WHERE "
                + UserParams.KEY_IS_LOGIN + " = " + true;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(1));
            Log.d("Bus.db", "Successfully read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return true;
        } else {
            Log.d("Bus.db", "Failed to read " + user.getId() + " " + user.getName());
            cursor.close();
            db.close();
            return false;
        }
    }

    public int updateIsLogin(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserParams.KEY_IS_LOGIN, user.getIsLogin());

        //Updating
        return db.update(UserParams.TABLE_NAME, values, UserParams.KEY_ID + "=?", new String[]{String.valueOf(user.getId())});
    }

    public int getCount() {
        String query = "SELECT * FROM " + UserParams.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
//
//    public List<contacts> getAllContacts(){
//        List<contacts> contactsList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        //Generating query to read from DataBase
//        String select = "SELECT * FROM " + Params.TABLE_NAME;
//        Cursor cursor  = db.rawQuery(select, null);
//
//        //Loop through now
//        if(cursor.moveToFirst()){
//            do{
//                contacts contact = new contacts();
//                contact.setId(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                contact.setYear(cursor.getString(3));
//                contact.setBranch(cursor.getString(4));
//                contactsList.add(contact);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        return contactsList;
//    }
//
//    public int updateContact(contacts contact){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(Params.KEY_NAME, contact.getName());
//        values.put(Params.KEY_PHONE, contact.getPhoneNumber());
//        values.put(Params.KEY_YEAR, contact.getYear());
//        values.put(Params.KEY_BRANCH, contact.getBranch());
//
//        //Updating
//        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
//    }
//
//    public void deleteContactbyID(int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//    public void deleteContact(contacts contact){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
//        db.close();
//    }
//

}
