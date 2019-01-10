package com.example.yohan.editabletextview;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class list extends ListFragment {

    ArrayList<String> data = new ArrayList<>();
    databaseHelper db;
    getItem activity;
    Cursor cursor;


    public interface getItem{

        void ItemSelected(int index);
    }


    public list() {
        // Required empty public constructor

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (getItem) context;
        db = new databaseHelper(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db.open();

        cursor = db.getName();


        int Irow = cursor.getColumnIndex(databaseHelper.KEY_NAME);
            while(cursor.moveToNext()){
                data.add(cursor.getString(Irow));
                setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data));
            }

            db.close();



    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
          activity.ItemSelected(position);


    }
}
