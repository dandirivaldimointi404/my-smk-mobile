package com.example.my_smk_app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_smk_app.Activity.LoginActivity;
import com.example.my_smk_app.Activity.ProfileActivity;
import com.example.my_smk_app.Adapter.ListHomeAdapter;
import com.example.my_smk_app.Models.ListHomeModel;
import com.example.my_smk_app.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageView iv_akun;
    TextView tv_akun;
    private RecyclerView recyclerView;
    private ListHomeAdapter listHomeAdapter;
    private ArrayList<ListHomeModel> listHomeModels;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);

        iv_akun = v.findViewById(R.id.iv_akun);
        tv_akun = v.findViewById(R.id.tv_akun);
        recyclerView = v.findViewById(R.id.recycleview);

        getData();

        String username = getActivity().getIntent().getStringExtra(LoginActivity.TAG_USERNAME);
        Log.d("HomeFragment", "Username: " + username);

        if (username != null) {
            tv_akun.setText("Selamat Datang\n" + username);
            tv_akun.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }

        iv_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
                String loggedInUsername = sharedpreferences.getString(LoginActivity.TAG_USERNAME, null);

                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra(LoginActivity.TAG_USERNAME, loggedInUsername);
                startActivity(intent);
            }
        });

        listHomeAdapter = new ListHomeAdapter(listHomeModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listHomeAdapter);

        return v;
    }

    private void getData() {
        listHomeModels = new ArrayList<>();
        listHomeModels.add(new ListHomeModel("Absensi Mapel", R.drawable.ic_launcher_background));
        listHomeModels.add(new ListHomeModel("Evaluasi Materi", R.drawable.ic_launcher_background));
    }
}