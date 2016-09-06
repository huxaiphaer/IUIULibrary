package com.iuiu_library.huzykamz.iuiulibrary.json;

import com.iuiu_library.huzykamz.iuiulibrary.model.AdministratorModel;
import com.iuiu_library.huzykamz.iuiulibrary.model.ScienceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class AdministratorJson {


    public static List<AdministratorModel> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<AdministratorModel> modelClassList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                AdministratorModel modelClass = new AdministratorModel();

                modelClass.setBookname(obj.getString("BookName"));

                modelClass.setName(obj.getString("Name"));
                modelClass.setRegno(obj.getString("email"));
                modelClass.setBorrow_date(obj.getString("BorrowDate"));
                modelClass.setBorrowing_days(obj.getString("NumberOfDays"));
                modelClass.setInitialdate(obj.getString("InitialDate"));
                modelClass.setFinaldate(obj.getString("FinalDate"));




                modelClassList.add(modelClass);
            }

            return modelClassList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}

