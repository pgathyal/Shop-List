package com.philj.jameslist;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.philj.jameslist.core.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//This class represents management of all items
public class Item_List_Setup extends Activity {

    private SparseBooleanArray checkedIDs;
    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__list__setup);
        c = getApplicationContext();
        Display_list();

    }

    protected void Display_list(){


        ListView listView = (ListView) findViewById(R.id.listView3);

        final ArrayList<String> array_list = new ArrayList<String>();
        final List<Item> Items = Item.getItems();//may need to call main activity again - to refresh stores if user creates more
        for (int i = 0; i < Items.size(); ++i) {
            array_list.add(Items.get(i).name + "(" + Items.get(i).type + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, array_list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            } });
        */

        checkedIDs = listView.getCheckedItemPositions();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item__list__setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //when button is pressed
    public void delete_items(View view){

        List<String> string_delete= new ArrayList<String>();
        for(int i=0; i<Item.itemList.size(); i++){
            final boolean pos = checkedIDs.get(i);
            if (pos)
            string_delete.add(Item.itemList.get(i).name);
        }
        //Refresh activity
        for(int i=0; i<string_delete.size(); i++){
            for(int j=0; j<Item.itemList.size(); j++){
                if(string_delete.get(i).equals(Item.itemList.get(j).name))
                    Item.itemList.remove(j);
            }
        }
        Item.saveItems(c);
        finish();
        startActivity(getIntent());
    }
}
