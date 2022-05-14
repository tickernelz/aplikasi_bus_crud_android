package upr.uas.pedro.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import upr.uas.pedro.R;
import upr.uas.pedro.UpdatePemesananActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Pemesanan;

public class PemesananAdapter extends BaseAdapter {

    Context context;
    DBHandler db;
    ArrayList<Pemesanan> pemesananList;

    public PemesananAdapter(Context context, ArrayList<Pemesanan> pemesananList, DBHandler db) {
        this.context = context;
        this.pemesananList = pemesananList;
        this.db = db;
    }

    @Override
    public int getCount() {
        return this.pemesananList.size();
    }

    @Override
    public Object getItem(int position) {
        return pemesananList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_pemesanan, null);

        TextView tvNama = (TextView) view.findViewById(R.id.pemesanan_tv_nama);
        TextView tvKode = (TextView) view.findViewById(R.id.pemesanan_tv_kode);
        TextView tvJadwal = (TextView) view.findViewById(R.id.pemesanan_tv_jadwal);

        Button btnUpdate = (Button) view.findViewById(R.id.post_btn_update);
        Button btnDelete = (Button) view.findViewById(R.id.post_btn_delete);

        Pemesanan pemesanan = pemesananList.get(position);
        tvNama.setText(pemesanan.getNama());
        tvKode.setText(pemesanan.getKode());
        tvJadwal.setText(pemesanan.getJadwal());

        btnUpdate.setOnClickListener(view1 -> {
            Intent i = new Intent(context.getApplicationContext(), UpdatePemesananActivity.class);
            i.putExtra("Pemesanan", pemesanan);
            context.startActivity(i);
        });

        btnDelete.setOnClickListener(view1 -> {
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean delete = db.deletePemesanan(pemesanan.getId());
                            pemesananList.remove(position);
                            notifyDataSetChanged();
                            if (delete) {
                                new AlertDialog.Builder(context)
                                        .setMessage("Delete Success")
                                        .setPositiveButton("OK", null)
                                        .show();
                            } else {
                                new AlertDialog.Builder(context)
                                        .setMessage("Delete Failed")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        });

        return view;
    }
}
