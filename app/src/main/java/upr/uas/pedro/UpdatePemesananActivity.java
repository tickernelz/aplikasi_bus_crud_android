package upr.uas.pedro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Pemesanan;

public class UpdatePemesananActivity extends AppCompatActivity {

    EditText etNama, etKode, etJadwal;
    Button btnUpdate;
    DBHandler db;
    Pemesanan pemesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pemesanan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Update Pemesanan");

        initWidgets();

        pemesanan = getIntent().getParcelableExtra("Pemesanan");
        etNama.setText(pemesanan.getNama());
        etKode.setText(pemesanan.getKode());
        etJadwal.setText(pemesanan.getJadwal());


        btnUpdate.setOnClickListener(view -> {
            String nama = etNama.getText().toString().trim();
            String kode = etKode.getText().toString().trim();
            String jadwal = etJadwal.getText().toString().trim();

            boolean update = db.updatePemesanan(pemesanan.getId(), nama, kode, jadwal);

            if (update) {
                Toast.makeText(getBaseContext(), "Pemesanan berhasil diupdate", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Pemesanan gagal diupdate", Toast.LENGTH_SHORT).show();
            }

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(i);
        });
    }

    private void initWidgets() {
        db = new DBHandler(this);
        etNama = findViewById(R.id.updatePemesanan_et_nama);
        etKode = findViewById(R.id.updatePemesanan_et_kode);
        etJadwal = findViewById(R.id.updatePemesanan_date_jadwal);
        btnUpdate = findViewById(R.id.updatePemesanan_btn_update);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

}