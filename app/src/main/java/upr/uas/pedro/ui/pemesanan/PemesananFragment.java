package upr.uas.pedro.ui.pemesanan;

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
import upr.uas.pedro.adapter.PemesananAdapter;
import upr.uas.pedro.crud.pemesanan.CreatePemesananActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Pemesanan;

public class PemesananFragment extends Fragment {

  DBHandler db;
  ArrayList<Pemesanan> pemesananList;
  ListView lvPemesanan;
  PemesananAdapter adapter;
  TextView tvNoPemesanan;
  FloatingActionButton fabCreatePemesanan;
  View mView;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_pemesanan, container, false);
    db = new DBHandler(requireContext());

    pemesananList = new ArrayList<>();
    tvNoPemesanan = mView.findViewById(R.id.viewPemesanan_tv_noPemesanan);
    lvPemesanan = mView.findViewById(R.id.viewPemesanan_lv_pemesananList);
    fabCreatePemesanan = mView.findViewById(R.id.viewPemesanan_fab_createPemesanan);
    pemesananList = db.getPemesananData();
    adapter = new PemesananAdapter(requireContext(), pemesananList, db);
    lvPemesanan.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    if (!pemesananList.isEmpty()) {
      tvNoPemesanan.setVisibility(View.INVISIBLE);
    } else {
      tvNoPemesanan.setVisibility(View.VISIBLE);
    }

    fabCreatePemesanan.setOnClickListener(
        view -> {
          Intent i = new Intent(getContext(), CreatePemesananActivity.class);
          startActivity(i);
        });
    return mView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    upr.uas.pedro.databinding.FragmentPemesananBinding binding = null;
  }
}
