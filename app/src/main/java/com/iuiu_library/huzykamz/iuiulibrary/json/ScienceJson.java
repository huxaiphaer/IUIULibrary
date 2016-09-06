package com.iuiu_library.huzykamz.iuiulibrary.json;

import com.iuiu_library.huzykamz.iuiulibrary.model.ScienceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class ScienceJson {


    public static List<ScienceModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<ScienceModel> modelClassList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                ScienceModel modelClass = new ScienceModel();

                modelClass.setBookname(obj.getString("BookName"));
                modelClass.setPhoto(obj.getString("photo"));
                modelClass.setAuthor(obj.getString("Author"));
                modelClass.setDescription(obj.getString("Description"));
                modelClass.setFaculty(obj.getString("Faculty"));
                modelClass.setBookId(obj.getString("BookId"));




                modelClassList.add(modelClass);
            }

            return modelClassList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}

