package com.philj.jameslist.core;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2015-08-24.
 */
public class Store {

    //Initial - not ready, no list created
    //Final - List created - shopping mode
    public enum mode{
        Initial,
        Incomplete,
        Final
    }
    public String name;
    public static List<Store> stores = new ArrayList<Store>();
    public mode m;
    public static File f = new File(Environment.getDataDirectory(),"storeSavedata.dat");

    public Store(String n){name =  n; m = mode.Initial;}

    @Override
    public boolean equals(Object o){
        Store comparedStore = (Store)o;
        boolean result = this.name.equals(comparedStore.name);
        return result;
    }

    public static void saveStores(Context c){
        FileOutputStream outFile = null;
        ObjectOutputStream out = null;
        String filename = "storeSavedata.dat";
        try{
            f.createNewFile();      //Will create if file not existing, won't create if file existing
        }catch (Exception e) {
            System.out.print("----Cannot create new file----");
            e.printStackTrace();
            System.out.print("------------------------------");
        }
        try{
            outFile = c.openFileOutput(filename,Context.MODE_PRIVATE);
            out = new ObjectOutputStream(outFile);
            out.writeInt(stores.size());
            for(int i=0; i<stores.size(); i++)
                out.writeObject(stores.get(i));
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void loadStores(Context c){
        stores.clear();
        FileInputStream fin = null;
        ObjectInputStream oi = null;
        String fileName = "storeSavedata.dat";
        try {
            fin = c.openFileInput(fileName);
            oi = new ObjectInputStream(fin);
            int numberOfStores = oi.readInt();
            for(int i=0; i<numberOfStores; i++){
                stores.add((Store)oi.readObject());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}

