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
import upr.uas.pedro.crud.penumpang.UpdatePenumpangActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Penumpang;

public class PenumpangAdapter extends BaseAdapter {

  Context context;
  DBHandler db;
  ArrayList<Penumpang> penumpangList;

  public PenumpangAdapter(Context context, ArrayList<Penumpang> penumpangList, DBHandler db) {
    this.context = context;
    this.penumpangList = penumpangList;
    this.db = db;
  }

  @Override
  public int getCount() {
    return this.penumpangList.size();
  }

  @Override
  public Object getItem(int position) {
    return penumpangList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup viewGroup) {

    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.list_penumpang, null);

    TextView tvNama = (TextView) view.findViewById(R.id.penumpang_tv_nama);
    TextView tvUmur = (TextView) view.findViewById(R.id.penumpang_tv_umur);
    TextView tvKelamin = (TextView) view.findViewById(R.id.penumpang_tv_kelamin);

    Button btnUpdate = (Button) view.findViewById(R.id.post_btn_update);
    Button btnDelete = (Button) view.findViewById(R.id.post_btn_delete);

    Penumpang penumpang = penumpangList.get(position);
    tvNama.setText(penumpang.getNama());
    tvUmur.setText(penumpang.getUmur());
    tvKelamin.setText(penumpang.getKelamin());

    btnUpdate.setOnClickListener(
        view1 -> {
          Intent i = new Intent(context.getApplicationContext(), UpdatePenumpangActivity.class);
          i.putExtra("Penumpang", penumpang);
          context.startActivity(i);
        });

    btnDelete.setOnClickListener(
        view1 -> {
          new AlertDialog.Builder(context)
              .setMessage("Are you sure you want to delete?")
              .setPositiveButton(
                  "Yes",
                  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      boolean delete = db.deletePenumpang(penumpang.getId());
                      penumpangList.remove(position);
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
              .setNegativeButton(
                  "No",
                  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.cancel();
                    }
                  })
              .show();
        });

    return view;
  }
}
