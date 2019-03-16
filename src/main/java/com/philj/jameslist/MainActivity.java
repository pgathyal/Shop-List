package com.philj.jameslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.philj.jameslist.core.Item;
import com.philj.jameslist.core.Store;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = getApplicationContext();
        setContentView(R.layout.activity_main);
        Item.loadItems(c);
        Store.loadStores(c);

        Button newListButton = (Button) findViewById(R.id.buttonNewList);
        newListButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,CreateList.class);
                intent.putExtra("isNew",true);
                startActivity(intent);
            }
        });

        Button incompleteButton = (Button) findViewById(R.id.buttonContinueList);
        incompleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,CreateList.class);
                intent.putExtra("isIncomplete",true);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void ItemClick(View view){
        Intent intent = new Intent(this,CreateItemActivity.class);
        startActivity(intent);
    }

    public void StoreClick(View view){
        Intent intent = new Intent(this,CreateStoreActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Item.saveItems(c);
        Store.saveStores(c);
    }

    }
