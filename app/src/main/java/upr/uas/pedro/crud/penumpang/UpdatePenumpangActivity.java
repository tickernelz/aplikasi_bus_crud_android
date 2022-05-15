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
import upr.uas.pedro.object.Penumpang;

public class UpdatePenumpangActivity extends AppCompatActivity {

  EditText etNama, etUmur, etKelamin;
  Button btnUpdate;
  DBHandler db;
  Penumpang penumpang;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_penumpang);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle("Update Penumpang");

    initWidgets();

    penumpang = getIntent().getParcelableExtra("Penumpang");
    etNama.setText(penumpang.getNama());
    etUmur.setText(penumpang.getUmur());
    etKelamin.setText(penumpang.getKelamin());

    btnUpdate.setOnClickListener(
        view -> {
          String nama = etNama.getText().toString().trim();
          String umur = etUmur.getText().toString().trim();
          String kelamin = etKelamin.getText().toString().trim();

          boolean update = db.updatePenumpang(penumpang.getId(), nama, umur, kelamin);

          if (update) {
            Toast.makeText(getBaseContext(), "Penumpang berhasil diupdate", Toast.LENGTH_SHORT)
                .show();
          } else {
            Toast.makeText(getBaseContext(), "Penumpang gagal diupdate", Toast.LENGTH_SHORT).show();
          }

          Intent i = new Intent(getBaseContext(), MainActivity.class);
          i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          finish();
          startActivity(i);
        });
  }

  private void initWidgets() {
    db = new DBHandler(this);
    etNama = findViewById(R.id.updatePenumpang_et_nama);
    etUmur = findViewById(R.id.updatePenumpang_et_umur);
    etKelamin = findViewById(R.id.updatePenumpang_et_kelamin);
    btnUpdate = findViewById(R.id.updatePenumpang_btn_update);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
