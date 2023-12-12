package com.example.my_smk_app.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.my_smk_app.Adapter.ListMateriAdapter;
import com.example.my_smk_app.Models.MapelModel;
import com.example.my_smk_app.R;
import com.example.my_smk_app.Responses.MateriApiResponse;
import com.example.my_smk_app.Retrofit.BaseApiServices;
import com.example.my_smk_app.Retrofit.Koneksi;
import com.example.my_smk_app.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MateriFragment extends Fragment {
    private RecyclerView recyclerView;
    private ListMateriAdapter listMateriAdapter;
    private SearchView searchView;
    private BaseApiServices apiServices;
    private SessionManager sessionManager;
    private List<MapelModel> mapelModels = new ArrayList<>();

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_materi, container, false);

        recyclerView = v.findViewById(R.id.rv_materi);
        searchView = v.findViewById(R.id.sv_materi);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        listMateriAdapter = new ListMateriAdapter(requireContext(), mapelModels);
        recyclerView.setAdapter(listMateriAdapter);

        sessionManager = new SessionManager(requireContext());

        fetchDataFromApi();

        return  v;

    }

    private void fetchDataFromApi() {
        apiServices = Koneksi.getAPIService(requireContext());

        String token = "Bearer " + sessionManager.getAccessToken();

        Call<MateriApiResponse> call = apiServices.getMateris();
        call.enqueue(new Callback<MateriApiResponse>() {
            @Override
            public void onResponse(Call<MateriApiResponse> call, Response<MateriApiResponse> response) {
                if (response.isSuccessful()) {
                    MateriApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.isStatus()) {
                        List<MapelModel> apiData = apiResponse.getData();
                        handleSuccessResponse(apiData);
                    } else {
                        handleErrorResponse("API Error: " + (apiResponse != null ? apiResponse.getMessage() : "Unknown error"));
                    }
                } else {
                    handleErrorResponse("Network Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MateriApiResponse> call, Throwable t) {
                Log.e("NetworkError", "Error: " + t.getMessage(), t);
                Toast.makeText(requireContext(), "Kesalahan jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleSuccessResponse(List<MapelModel> apiData) {
        if (apiData != null) {
            updateRecyclerView(apiData);
        } else {
            Toast.makeText(requireContext(), "Response body is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleErrorResponse(String errorMessage) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void updateRecyclerView(List<MapelModel> newData) {
        mapelModels.clear();
        mapelModels.addAll(newData);
        listMateriAdapter.notifyDataSetChanged();
    }

    private void searchList(String text) {
        List<MapelModel> dataSearchList = new ArrayList<>();
        for (MapelModel data : mapelModels) {
            if (data.getNamaMapel().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            listMateriAdapter.setSearchList(dataSearchList);
        }
    }
}