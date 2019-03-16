package com.philj.jameslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.philj.jameslist.core.Item;
import com.philj.jameslist.core.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2019-03-06.
 */
public class CreateList extends Activity {

    private Context context;
    private SparseBooleanArray checkedIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlist);
        context = getApplicationContext();

        //Display list of stores, for each entry, add click listener (if mode is not initial ask for confirmation),
        // store storename
        List<Store> stores = Store.stores;

        Intent prevIntent = getIntent();
        if(prevIntent.getBooleanExtra("isIncomplete",false)){

        }

        ListView listView = (ListView) findViewById(R.id.listView);

        final ArrayList<String> array_list = new ArrayList<String>();
        for (int i = 0; i < stores.size(); ++i) {
            array_list.add(stores.get(i).name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, array_list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            } });
        */

        checkedIDs = listView.getCheckedItemPositions();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Save to file
    }
}
