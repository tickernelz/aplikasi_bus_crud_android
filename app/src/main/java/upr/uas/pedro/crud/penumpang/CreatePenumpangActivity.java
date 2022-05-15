package upr.uas.pedro.crud.penumpang;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import upr.uas.pedro.MainActivity;
import upr.uas.pedro.R;
import upr.uas.pedro.db.DBHandler;

public class CreatePenumpangActivity extends AppCompatActivity {

  DBHandler db;

  EditText etNama, etUmur, etKelamin;
  Button btnAdd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_penumpang);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle("Create Penumpang");
    db = new DBHandler(getApplicationContext());

    initWidgets();

    btnAdd.setOnClickListener(
        view -> {
          String nama = etNama.getText().toString().trim();
          String umur = etUmur.getText().toString().trim();
          String kelamin = etKelamin.getText().toString().trim();

          if (!nama.equals("") && !umur.equals("") && !kelamin.equals("")) {
            boolean insert = db.insertPenumpang(nama, umur, kelamin);
            etNama.setText("");
            etUmur.setText("");
            etKelamin.setText("");
            if (insert) {
              Toast.makeText(getBaseContext(), "Penumpang berhasil ditambahkan", Toast.LENGTH_SHORT)
                  .show();
            } else {
              Toast.makeText(getBaseContext(), "Penumpang gagal ditambahkan", Toast.LENGTH_SHORT)
                  .show();
            }
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(i);
          } else {
            Toast.makeText(
                    this, "Nama Penumpang, Kelamin, dan Umur harus diisi!", Toast.LENGTH_SHORT)
                .show();
          }
        });
  }

  private void initWidgets() {
    etNama = findViewById(R.id.createPenumpang_et_nama);
    etUmur = findViewById(R.id.createPenumpang_et_umur);
    etKelamin = findViewById(R.id.createPenumpang_et_kelamin);
    btnAdd = findViewById(R.id.createPenumpang_btn_create);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
}
