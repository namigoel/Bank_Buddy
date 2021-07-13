package com.example.amexmate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    @SerializedName("prediction")
    @Expose
    private List<String> prediction = null;
    @SerializedName("test")
    @Expose
    private List<List<String>> test = null;

    public List<String> getPrediction() {
        return prediction;
    }

    public void setPrediction(List<String> prediction) {
        this.prediction = prediction;
    }

    public List<List<String>> getTest() {
        return test;
    }

    public void setTest(List<List<String>> test) {
        this.test = test;
    }

}
