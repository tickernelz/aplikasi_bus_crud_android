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
import upr.uas.pedro.crud.bus.UpdateBusActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Bus;

public class BusAdapter extends BaseAdapter {

  Context context;
  DBHandler db;
  ArrayList<Bus> busList;

  public BusAdapter(Context context, ArrayList<Bus> busList, DBHandler db) {
    this.context = context;
    this.busList = busList;
    this.db = db;
  }

  @Override
  public int getCount() {
    return this.busList.size();
  }

  @Override
  public Object getItem(int position) {
    return busList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup viewGroup) {

    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    view = inflater.inflate(R.layout.list_bus, null);

    TextView tvNama = (TextView) view.findViewById(R.id.bus_tv_nama);
    TextView tvKode = (TextView) view.findViewById(R.id.bus_tv_kode);
    TextView tvTipe = (TextView) view.findViewById(R.id.bus_tv_tipe);
    TextView tvRute = (TextView) view.findViewById(R.id.bus_tv_rute);

    Button btnUpdate = (Button) view.findViewById(R.id.post_btn_update);
    Button btnDelete = (Button) view.findViewById(R.id.post_btn_delete);

    Bus bus = busList.get(position);
    tvNama.setText(bus.getNama());
    tvKode.setText(bus.getKode());
    tvTipe.setText(bus.getTipe());
    tvRute.setText(bus.getRute());

    btnUpdate.setOnClickListener(
        view1 -> {
          Intent i = new Intent(context.getApplicationContext(), UpdateBusActivity.class);
          i.putExtra("Bus", bus);
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
                      boolean delete = db.deleteBus(bus.getId());
                      busList.remove(position);
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
