package upr.uas.pedro.crud.pemesanan;

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

public class CreatePemesananActivity extends AppCompatActivity {

  DBHandler db;

  EditText etNama, etKode, etJadwal;
  Button btnAdd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_pemesanan);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle("Create Pemesanan");
    db = new DBHandler(getApplicationContext());

    initWidgets();

    btnAdd.setOnClickListener(
        view -> {
          String nama = etNama.getText().toString().trim();
          String kode = etKode.getText().toString().trim();
          String jadwal = etJadwal.getText().toString().trim();

          if (!nama.equals("") && !kode.equals("") && !jadwal.equals("")) {
            boolean insert = db.insertPemesanan(nama, kode, jadwal);
            etNama.setText("");
            etKode.setText("");
            etJadwal.setText("");
            if (insert) {
              Toast.makeText(getBaseContext(), "Pemesanan berhasil ditambahkan", Toast.LENGTH_SHORT)
                  .show();
            } else {
              Toast.makeText(getBaseContext(), "Pemesanan gagal ditambahkan", Toast.LENGTH_SHORT)
                  .show();
            }
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
          } else {
            Toast.makeText(this, "Nama Pemesanan, Jadwal dan Kode harus diisi!", Toast.LENGTH_SHORT)
                .show();
          }
        });
  }

  private void initWidgets() {
    etNama = findViewById(R.id.createPemesanan_et_nama);
    etKode = findViewById(R.id.createPemesanan_et_kode);
    etJadwal = findViewById(R.id.createPemesanan_date_jadwal);
    btnAdd = findViewById(R.id.createPemesanan_btn_create);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
}
