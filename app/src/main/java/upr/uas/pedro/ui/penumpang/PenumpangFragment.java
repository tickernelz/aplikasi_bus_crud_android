package upr.uas.pedro.ui.penumpang;

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
import upr.uas.pedro.adapter.PenumpangAdapter;
import upr.uas.pedro.crud.penumpang.CreatePenumpangActivity;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.object.Penumpang;

public class PenumpangFragment extends Fragment {

  DBHandler db;
  ArrayList<Penumpang> penumpangList;
  ListView lvPenumpang;
  PenumpangAdapter adapter;
  TextView tvNoPenumpang;
  FloatingActionButton fabCreatePenumpang;
  View mView;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_penumpang, container, false);
    db = new DBHandler(requireContext());

    penumpangList = new ArrayList<>();
    tvNoPenumpang = mView.findViewById(R.id.viewPenumpang_tv_noPenumpang);
    lvPenumpang = mView.findViewById(R.id.viewPenumpang_lv_penumpangList);
    fabCreatePenumpang = mView.findViewById(R.id.viewPenumpang_fab_createPenumpang);
    penumpangList = db.getPenumpangData();
    adapter = new PenumpangAdapter(requireContext(), penumpangList, db);
    lvPenumpang.setAdapter(adapter);
    adapter.notifyDataSetChanged();

    if (!penumpangList.isEmpty()) {
      tvNoPenumpang.setVisibility(View.INVISIBLE);
    } else {
      tvNoPenumpang.setVisibility(View.VISIBLE);
    }

    fabCreatePenumpang.setOnClickListener(
        view -> {
          Intent i = new Intent(getContext(), CreatePenumpangActivity.class);
          startActivity(i);
        });
    return mView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    upr.uas.pedro.databinding.FragmentPenumpangBinding binding = null;
  }
}
