package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        
        Sandwich sSandwitch = new Sandwich();

        String strMainName = "", strDesc = "", stringredients = "";


        //Having issues finding and getting data from JSON object.
        try{

            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.optJSONArray("name");
            for(int i = 0; i < jArray.length(); i++)
            {
                    JSONObject jObjDetail = jArray.getJSONObject(i);
                    strMainName = jObjDetail.getString("mainName");
                    strDesc = jObjDetail.getString("description");
                    stringredients = jObjDetail.getString("ingredients");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        sSandwitch.setMainName(strMainName);
        sSandwitch.setDescription(strDesc);

        return sSandwitch;
    }
}
