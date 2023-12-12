package com.example.my_smk_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.my_smk_app.R;
import com.example.my_smk_app.databinding.ActivityMainBinding;
import com.example.my_smk_app.Fragment.AbsensiFragment;
import com.example.my_smk_app.Fragment.EvaluasiFragment;
import com.example.my_smk_app.Fragment.HomeFragment;
import com.example.my_smk_app.Fragment.MateriFragment;
import com.example.my_smk_app.Fragment.TugasFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment selectedFragment = null;

            if (itemId == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.materi) {
                selectedFragment = new MateriFragment();
            } else if (itemId == R.id.tugas) {
                selectedFragment = new TugasFragment();
            } else if (itemId == R.id.absensi   ) {
                selectedFragment = new AbsensiFragment();
            } else if (itemId == R.id.evaluasi) {
                selectedFragment = new EvaluasiFragment();
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
                return true;
            }
            return false;
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }



}