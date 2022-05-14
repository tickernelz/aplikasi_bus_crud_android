package upr.uas.pedro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "bus_db";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + UserParams.TABLE_NAME + "("
                + UserParams.KEY_ID + " INTEGER PRIMARY KEY, "
                + UserParams.KEY_NAME + " TEXT, "
                + UserParams.KEY_USERNAME + " TEXT, " + UserParams.KEY_PASSWORD + " TEXT)";
        Log.d("bus_db", "Query being run is :\n" + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserParams.KEY_NAME, user.getName());
        values.put(UserParams.KEY_USERNAME, user.getUsername());
        values.put(UserParams.KEY_PASSWORD, user.getPassword());

        db.insert(UserParams.TABLE_NAME, null, values);
        Log.d("bus_db", "Successfully inserted " + user.getId() + " " + user.getName());
        db.close();
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
//    public int getCount() {
//        String query = "SELECT * FROM " + UserParams.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        return cursor.getCount();
//    }
}
