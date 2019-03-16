package com.philj.jameslist;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.philj.jameslist.core.Store;

public class CreateStoreActivity extends Activity {

    private Context context;
    private EditText storeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_create_store);
        storeName = ((EditText)findViewById(R.id.storeName));

    }

    public void CreateStore(View view){
        String strName = storeName.getText().toString();
        Store s = new Store(strName);
            if(Store.stores.contains(s)) {//Check for duplicates
                Toast toast = Toast.makeText(getApplicationContext(),s.name + " store already present" ,Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        Store.stores.add(s);
        Store.saveStores(context);
        Toast toast  = Toast.makeText(context,"Store Added",Toast.LENGTH_SHORT);
        toast.show();

    }


}
