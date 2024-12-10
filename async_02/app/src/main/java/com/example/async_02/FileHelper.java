package com.example.async_02;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    private static final String FILE_NAME = "data_1.json";

    public static List<AssetItem> readFromFile(Context context) {
        List<AssetItem> items = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String productName = jsonObject.getString("productName");
                double price = jsonObject.getDouble("price");
                String description = jsonObject.getString("description");

                AssetItem item = new AssetItem(productName, price, description);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void saveToFile(Context context, List<AssetItem> items) {
        JSONArray jsonArray = new JSONArray();
        for (AssetItem item : items) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productName", item.getProductName());
                jsonObject.put("price", item.getPrice());
                jsonObject.put("description", item.getDescription());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d("FileHelper", "Saving data to file: " + jsonArray.toString());

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(jsonArray.toString().getBytes());
            Log.d("FileHelper", "Data saved successfully.");
        } catch (Exception e) {
            Log.e("FileHelper", "Error saving data: ", e);
        }
    }
}
