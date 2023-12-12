package com.example.my_smk_app.Responses;

import com.example.my_smk_app.Models.MapelModel;

import java.util.Collections;
import java.util.List;

public class MateriApiResponse {

    private boolean status;
    private String message;
    private List<MapelModel> data;
    private String access_token;

    public MateriApiResponse(boolean status, String message, List<MapelModel> data, String access_token) {
        this.status = status;
        this.message = message;
        this.data = (data != null) ? data : Collections.emptyList();
        this.access_token = access_token;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<MapelModel> getData() {
        return (List<MapelModel>) data;
    }

    public String getAccessToken() {
        return access_token;
    }
}
