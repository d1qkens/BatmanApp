package com.d1qkens.onixtestingapp.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJson {
    private static JSONArray imagesArr;
    private static JSONArray titlesArr;

    public static ArrayList<String> getCars(JSONObject object) {
        try {
            imagesArr = object.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> carsList = new ArrayList<>();
        for (int i = 0; i < imagesArr.length(); i++) {
            try {
                carsList.add(imagesArr.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return carsList;
    }

    public static ArrayList<String> getTitles(JSONObject object) {
        try {
            titlesArr = object.getJSONArray("titles");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> titlesList = new ArrayList<>();
        for (int i = 0; i < titlesArr.length(); i++) {
            try {
                titlesList.add(titlesArr.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return titlesList;
    }
}
