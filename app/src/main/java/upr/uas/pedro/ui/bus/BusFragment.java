package upr.uas.pedro.ui.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import upr.uas.pedro.R;
import upr.uas.pedro.adapter.BusAdapter;
import upr.uas.pedro.crud.bus.CreateBusActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Bus;

public class BusFragment extends Fragment {

  DBHandler db;
  ArrayList<Bus> busList;
  ListView lvBus;
  BusAdapter adapter;
  TextView tvNoBus;
  FloatingActionButton fabCreateBus;
  View mView;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_bus, container, false);
    db = new DBHandler(requireContext());

    busList = new ArrayList<>();
    tvNoBus = mView.findViewById(R.id.viewBus_tv_noBus);
    lvBus = mView.findViewById(R.id.viewBus_lv_busList);
    fabCreateBus = mView.findViewById(R.id.viewBus_fab_createBus);
    busList = db.getBusData();
    adapter = new BusAdapter(requireContext(), busList, db);
    lvBus.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    if (!busList.isEmpty()) {
      tvNoBus.setVisibility(View.INVISIBLE);
    } else {
      tvNoBus.setVisibility(View.VISIBLE);
    }

    fabCreateBus.setOnClickListener(
        view -> {
          Intent i = new Intent(getContext(), CreateBusActivity.class);
          startActivity(i);
        });
    return mView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    upr.uas.pedro.databinding.FragmentBusBinding binding = null;
  }
}
