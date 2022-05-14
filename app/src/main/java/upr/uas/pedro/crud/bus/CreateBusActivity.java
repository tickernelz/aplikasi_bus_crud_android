package upr.uas.pedro.crud.bus;

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

public class CreateBusActivity extends AppCompatActivity {

    DBHandler db;

    EditText etNama, etKode, etTipe, etRute;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create Bus");
        db = new DBHandler(getApplicationContext());

        initWidgets();

        btnAdd.setOnClickListener(view -> {
            String nama = etNama.getText().toString().trim();
            String kode = etKode.getText().toString().trim();
            String tipe = etTipe.getText().toString().trim();
            String rute = etRute.getText().toString().trim();

            if (!nama.equals("") && !kode.equals("") && !tipe.equals("") && !rute.equals("")) {
                boolean insert = db.insertBus(nama, kode, tipe, rute);
                etNama.setText("");
                etKode.setText("");
                etTipe.setText("");
                etRute.setText("");
                if (insert) {
                    Toast.makeText(getBaseContext(), "Bus berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Bus gagal ditambahkan", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(i);
            } else {
                Toast.makeText(this, "Nama Bus, Rute, Tipe dan Kode harus diisi!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initWidgets() {
        etNama = findViewById(R.id.createBus_et_nama);
        etKode = findViewById(R.id.createBus_et_kode);
        etTipe = findViewById(R.id.createBus_et_tipe);
        etRute = findViewById(R.id.createBus_et_rute);
        btnAdd = findViewById(R.id.createBus_btn_create);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}