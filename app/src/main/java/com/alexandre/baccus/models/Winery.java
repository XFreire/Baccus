package com.alexandre.baccus.models;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.alexandre.baccus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandre on 4/2/17.
 */

public class Winery {
    private static final String winesURL = "http://static.keepcoding.io/baccus/wines.json";

    private static Winery sInstance = null;
    private List<Wine> mWines = null;

    public static Winery getInstance() {
        if (sInstance == null) {
            // Nos bajamos los vinos de la red. Esto es bloqueante
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                sInstance = downloadWines();
            } catch (Exception ex) {
                Log.e("Baccus", "Error downloading wines", ex);
            }

        }
        return sInstance;
    }

    private static Winery downloadWines() throws MalformedURLException, IOException, JSONException {
        Winery winery = new Winery();
        winery.mWines = new LinkedList<Wine>();
        URLConnection conn = new URL(winesURL).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        // Recorremos el array de vinos
        JSONArray wines = new JSONArray(response.toString());
        for (int wineIndex = 0; wineIndex < wines.length(); wineIndex++) {
            String name = null;
            String type = null;
            String company = null;
            String companyWeb = null;
            String notes = null;
            String origin = null;
            int rating = 0;
            List<String> grapes = new LinkedList<String>();

            JSONObject jSONWine = wines.getJSONObject(wineIndex);
            if (jSONWine.has("name")) {
                name = jSONWine.getString("name");
                type = jSONWine.getString("type");
                company = jSONWine.getString("company");
                companyWeb = jSONWine.getString("company_web");
                notes = jSONWine.getString("notes");
                origin = jSONWine.getString("origin");
                rating = jSONWine.getInt("rating");

                // Recorremos los tipos de uva
                JSONArray jSONGrapes = jSONWine.getJSONArray("grapes");
                for (int grapeIndex = 0; grapeIndex < jSONGrapes.length(); grapeIndex++) {
                    grapes.add(jSONGrapes.getJSONObject(grapeIndex).getString("grape"));
                }

                // Creamos el vino con los datos recogidos
                Wine wine = new Wine(name, company, type, origin, companyWeb, notes, R.drawable.bembibre, rating);
                winery.mWines.add(wine);


            }
        }

        return winery;
    }



    public Wine getWine(int index){
        return mWines.get(index);
    }

    public int getWineCount() {
        return mWines.size();
    }

    public List<Wine> getWineList() {
        return mWines;
    }
}
