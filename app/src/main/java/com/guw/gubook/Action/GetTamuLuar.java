package com.guw.gubook.Action;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTamuLuar {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tamuLuars")
    @Expose
    private List<TamuLuar> tamuLuars = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TamuLuar> getTamuLuars() {
        return tamuLuars;
    }

    public void setTamuLuars(List<TamuLuar> tamuLuars) {
        this.tamuLuars = tamuLuars;
    }

}

