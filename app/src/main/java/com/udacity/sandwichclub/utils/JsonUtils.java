package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        //Create new sandwitch object
        Sandwich sSandwitch = new Sandwich();

        //Set vars
        String strMainName = "";
        String strDesc = "";

        String strPlaceOfOrigin = "";
        String strImage = "";

        List<String> lsIngredients = new ArrayList<String>();
        List<String> lsAlsoKnownAs = new ArrayList<String>();


        //Process JSON
        try {


            JSONObject joMain = new JSONObject(json);
            JSONObject joSanwich = joMain.getJSONObject("name");

            strMainName = joSanwich.getString("mainName");
            strDesc = joMain.getString("description");



            JSONArray jaAlsoKnownAs = joSanwich.getJSONArray("alsoKnownAs");
            for (int i = 0; i < jaAlsoKnownAs.length(); i++) {
                String strAlsoKnownAs = jaAlsoKnownAs.getString(i);
                lsAlsoKnownAs.add(strAlsoKnownAs);
            }

            strPlaceOfOrigin = joMain.getString("placeOfOrigin");
            strImage = joMain.getString("image");


            JSONArray jaIngredients = joMain.getJSONArray("ingredients");
            for (int i = 0; i < jaIngredients.length(); i++) {
                String strIngredients = jaIngredients.getString(i);
                lsIngredients.add(strIngredients);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Store JSON values into new Sandwitch object
        sSandwitch.setMainName(strMainName);
        sSandwitch.setIngredients(lsIngredients);
        sSandwitch.setAlsoKnownAs(lsAlsoKnownAs);
        sSandwitch.setDescription(strDesc);
        sSandwitch.setImage(strImage);
        sSandwitch.setPlaceOfOrigin(strPlaceOfOrigin);



        return sSandwitch;
    }
}
