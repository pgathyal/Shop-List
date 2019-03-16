package com.philj.jameslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.philj.jameslist.core.Item;
import com.philj.jameslist.Exceptions.duplicateItemNameException;

public class CreateItemActivity extends Activity {

    private EditText text;
    private Item.Category c;
    private Spinner spinner;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_create_item);
        spinner = (Spinner) findViewById(R.id.categories);
        ArrayAdapter<Item.Category> a = new ArrayAdapter<Item.Category>(this, R.layout.simple_spinner_custom, Item.Category.values());
        a.setDropDownViewResource(R.layout.simple_spinner_custom);
        spinner.setAdapter(a);
        text = ((EditText)findViewById(R.id.inputValue));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_item, menu);
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

    public void CreateItem(View view) {
        try {
            c = Item.Category.valueOf(spinner.getSelectedItem().toString()) ;
            Item item = new Item(text.getText().toString(), c);
            if(Item.itemList.contains(item)) {//Check for duplicates
                throw new duplicateItemNameException(item.name + " already present");
            }
            Item.itemList.add(item);
            Item.saveItems(context);
        }catch(duplicateItemNameException de){
            Toast toast  = Toast.makeText(getApplicationContext(),de.errorMessage ,Toast.LENGTH_SHORT);
            toast.show();
            //TODO: create exception - Item has name
            return;
        }
        Toast toast  = Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void view_items(View view){
        Intent intent = new Intent(this,Item_List_Setup.class);
        startActivity(intent);
    }
}
