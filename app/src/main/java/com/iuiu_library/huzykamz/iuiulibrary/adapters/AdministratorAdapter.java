package com.iuiu_library.huzykamz.iuiulibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuiu_library.huzykamz.iuiulibrary.R;
import com.iuiu_library.huzykamz.iuiulibrary.model.AdministratorModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class AdministratorAdapter extends ArrayAdapter<AdministratorModel> {
    private Context context;
    private List<AdministratorModel> modelClassList = null;
    private ArrayList<AdministratorModel> arraylist;

    public AdministratorAdapter(Context context, int resource, List<AdministratorModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.modelClassList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_administrator, parent, false);

        //Display modelClass name in the TextView widget
        AdministratorModel modelClass = modelClassList.get(position);
        TextView bookname = (TextView) view.findViewById(R.id.admin_bookname);
        TextView admin_name = (TextView) view.findViewById(R.id.admin_name);


        bookname.setText(modelClass.getBookname());
        admin_name.setText(modelClass.getName());

        //Display modelClass photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.book_image);
/*        image.setImageBitmap(modelClass.getBitmap());*/

        return view;
    }

}