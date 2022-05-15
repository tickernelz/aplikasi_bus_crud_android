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
import upr.uas.pedro.object.Bus;

public class UpdatePenumpangActivity extends AppCompatActivity {

  EditText etNama, etKode, etTipe, etRute;
  Button btnUpdate;
  DBHandler db;
  Bus bus;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_bus);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setTitle("Update Bus");

    initWidgets();

    bus = getIntent().getParcelableExtra("Bus");
    etNama.setText(bus.getNama());
    etKode.setText(bus.getKode());
    etTipe.setText(bus.getTipe());
    etRute.setText(bus.getRute());

    btnUpdate.setOnClickListener(
        view -> {
          String nama = etNama.getText().toString().trim();
          String kode = etKode.getText().toString().trim();
          String tipe = etTipe.getText().toString().trim();
          String rute = etRute.getText().toString().trim();

          boolean update = db.updateBus(bus.getId(), nama, kode, tipe, rute);

          if (update) {
            Toast.makeText(getBaseContext(), "Bus berhasil diupdate", Toast.LENGTH_SHORT).show();
          } else {
            Toast.makeText(getBaseContext(), "Bus gagal diupdate", Toast.LENGTH_SHORT).show();
          }

          Intent i = new Intent(getBaseContext(), MainActivity.class);
          i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          finish();
          startActivity(i);
        });
  }

  private void initWidgets() {
    db = new DBHandler(this);
    etNama = findViewById(R.id.updateBus_et_nama);
    etKode = findViewById(R.id.updateBus_et_kode);
    etTipe = findViewById(R.id.updateBus_et_tipe);
    etRute = findViewById(R.id.updateBus_et_rute);
    btnUpdate = findViewById(R.id.updateBus_btn_update);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  //    @Override
  //    public boolean onSupportNavigateUp() {
  //        NavController navController = Navigation.findNavController(this,
  // R.id.nav_host_fragment_content_main);
  //        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
  //                || super.onSupportNavigateUp();
  //    }

}
