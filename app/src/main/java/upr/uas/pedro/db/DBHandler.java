package upr.uas.pedro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import upr.uas.pedro.db.params.BusParams;
import upr.uas.pedro.db.params.PemesananParams;
import upr.uas.pedro.db.params.UserParams;
import upr.uas.pedro.object.Bus;
import upr.uas.pedro.object.Pemesanan;
import upr.uas.pedro.object.User;

public class DBHandler extends SQLiteOpenHelper {
  public static final int DB_VERSION = 1;
  public static final String DB_NAME = "Bus.db";

  private static final String SQL_CREATE_PEMESANAN =
      "CREATE TABLE "
          + PemesananParams.TABLE_NAME
          + "("
          + PemesananParams.KEY_ID
          + " INTEGER PRIMARY KEY, "
          + PemesananParams.KEY_NAMA
          + " TEXT, "
          + PemesananParams.KEY_KODE
          + " TEXT, "
          + PemesananParams.KEY_JADWAL
          + " TEXT)";

  private static final String SQL_CREATE_BUS =
      "CREATE TABLE "
          + BusParams.TABLE_NAME
          + "("
          + BusParams.KEY_ID
          + " INTEGER PRIMARY KEY, "
          + BusParams.KEY_NAMA
          + " TEXT, "
          + BusParams.KEY_KODE
          + " TEXT, "
          + BusParams.KEY_TIPE
          + " TEXT, "
          + BusParams.KEY_RUTE
          + " TEXT)";

  private static final String SQL_CREATE_USER =
      "CREATE TABLE "
          + UserParams.TABLE_NAME
          + "("
          + UserParams.KEY_ID
          + " INTEGER PRIMARY KEY, "
          + UserParams.KEY_NAME
          + " TEXT, "
          + UserParams.KEY_USERNAME
          + " TEXT, "
          + UserParams.KEY_PASSWORD
          + " TEXT, "
          + UserParams.KEY_IS_LOGIN
          + " INTEGER DEFAULT 0)";

  private static final String SQL_DELETE_PEMESANAN =
      "DROP TABLE IF EXISTS " + PemesananParams.TABLE_NAME;

  private static final String SQL_DELETE_BUS = "DROP TABLE IF EXISTS " + BusParams.TABLE_NAME;

  private static final String SQL_DELETE_USER = "DROP TABLE IF EXISTS " + UserParams.TABLE_NAME;

  public DBHandler(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_USER);
    db.execSQL(SQL_CREATE_PEMESANAN);
    db.execSQL(SQL_CREATE_BUS);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(SQL_DELETE_USER);
    db.execSQL(SQL_DELETE_PEMESANAN);
    db.execSQL(SQL_DELETE_BUS);
    onCreate(db);
  }

  @Override
  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    onUpgrade(db, oldVersion, newVersion);
  }

  public String getName(User user) {
    SQLiteDatabase db = this.getReadableDatabase();

    // Generating query to read from DataBase
    String select =
        "SELECT * FROM "
            + UserParams.TABLE_NAME
            + " WHERE "
            + UserParams.KEY_ID
            + " = "
            + user.getId();
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

    // Generating query to read from DataBase
    String select =
        "SELECT * FROM "
            + UserParams.TABLE_NAME
            + " WHERE "
            + UserParams.KEY_ID
            + " = "
            + user.getId();
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

    // Generating query to read from DataBase
    String select =
        "SELECT * FROM "
            + UserParams.TABLE_NAME
            + " WHERE "
            + UserParams.KEY_USERNAME
            + " = "
            + user.getUsername(true)
            + " AND "
            + UserParams.KEY_PASSWORD
            + " = "
            + user.getPassword(true);
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

    // Generating query to read from DataBase
    String select =
        "SELECT * FROM " + UserParams.TABLE_NAME + " WHERE " + UserParams.KEY_IS_LOGIN + " = " + 1;
    Cursor cursor = db.rawQuery(select, null);
    if (cursor.moveToFirst()) {
      user.setId(cursor.getInt(0));
      user.setName(cursor.getString(1));
      user.setUsername(cursor.getString(2));
      user.setPassword(cursor.getString(3));
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

    // Updating
    return db.update(
        UserParams.TABLE_NAME,
        values,
        UserParams.KEY_ID + "=?",
        new String[] {String.valueOf(user.getId())});
  }

  public int getCount() {
    String query = "SELECT * FROM " + UserParams.TABLE_NAME;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(query, null);
    return cursor.getCount();
  }

  // Pemesanan

  public boolean insertPemesanan(String nama, String kode, String jadwal) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("nama", nama);
    contentValues.put("kode", kode);
    contentValues.put("jadwal", jadwal);

    long result = db.insert(PemesananParams.TABLE_NAME, null, contentValues);

    return result != -1;
  }

  public boolean updatePemesanan(int id, String nama, String kode, String jadwal) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("nama", nama);
    contentValues.put("kode", kode);
    contentValues.put("jadwal", jadwal);

    long result =
        db.update(
            PemesananParams.TABLE_NAME,
            contentValues,
            PemesananParams.KEY_ID + "=?",
            new String[] {String.valueOf(id)});

    db.close();
    return result != -1;
  }

  public boolean deletePemesanan(int id) {
    SQLiteDatabase db = this.getWritableDatabase();
    long result =
        db.delete(
            PemesananParams.TABLE_NAME,
            PemesananParams.KEY_ID + "=?",
            new String[] {String.valueOf(id)});

    db.close();
    return result != -1;
  }

  public ArrayList<Pemesanan> getPemesananData() {
    ArrayList<Pemesanan> pemesananList = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + PemesananParams.TABLE_NAME, null);

    while (cursor.moveToNext()) {
      int id = cursor.getInt(0);
      String nama = cursor.getString(1);
      String kode = cursor.getString(2);
      String jadwal = cursor.getString(3);

      Pemesanan pemesanan = new Pemesanan(id, nama, kode, jadwal);
      pemesananList.add(pemesanan);
    }
    return pemesananList;
  }

  /* Bus */

  public boolean insertBus(String nama, String kode, String tipe, String rute) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(BusParams.KEY_NAMA, nama);
    contentValues.put(BusParams.KEY_KODE, kode);
    contentValues.put(BusParams.KEY_TIPE, tipe);
    contentValues.put(BusParams.KEY_RUTE, rute);

    long result = db.insert(BusParams.TABLE_NAME, null, contentValues);

    return result != -1;
  }

  public boolean updateBus(int id, String nama, String kode, String tipe, String rute) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(BusParams.KEY_NAMA, nama);
    contentValues.put(BusParams.KEY_KODE, kode);
    contentValues.put(BusParams.KEY_TIPE, tipe);
    contentValues.put(BusParams.KEY_RUTE, rute);

    long result =
        db.update(
            BusParams.TABLE_NAME,
            contentValues,
            BusParams.KEY_ID + "=?",
            new String[] {String.valueOf(id)});

    db.close();
    return result != -1;
  }

  public boolean deleteBus(int id) {
    SQLiteDatabase db = this.getWritableDatabase();
    long result =
        db.delete(BusParams.TABLE_NAME, BusParams.KEY_ID + "=?", new String[] {String.valueOf(id)});

    db.close();
    return result != -1;
  }

  public ArrayList<Bus> getBusData() {
    ArrayList<Bus> busList = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + BusParams.TABLE_NAME, null);

    while (cursor.moveToNext()) {
      int id = cursor.getInt(0);
      String nama = cursor.getString(1);
      String kode = cursor.getString(2);
      String tipe = cursor.getString(3);
      String rute = cursor.getString(4);

      Bus bus = new Bus(id, nama, kode, tipe, rute);
      busList.add(bus);
    }
    return busList;
  }
}
