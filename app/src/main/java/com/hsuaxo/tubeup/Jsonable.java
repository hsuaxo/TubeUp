package com.hsuaxo.tubeup;

import com.google.gson.Gson;

public abstract class Jsonable {

    public String json() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }
}
