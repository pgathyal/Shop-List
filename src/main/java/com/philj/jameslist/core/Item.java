package com.philj.jameslist.core;

import android.content.Context;
import android.media.Image;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2015-08-24.
 */

//Serializing...object in byte form..to save it
public class Item implements Serializable {

    public enum Category{
        Baking,
        Vegetables,
        Deli,
        Cleaning,
        Medical,
        Dairy;
    }
    public String name;
    public Image icon;
    public Category type;
    public static int num_items = 0;
    public static List<Item> itemList = new ArrayList<Item>();
    public static File f = new File(Environment.getDataDirectory(),"itemsSavedata.dat");

    public Item(String n, Category choice){
        this.name = n;
        this.type = choice;
        //icon = ;//Default image
    }


    public void setImage(Image i){
        this.icon = i;
    }

    @Override
    public boolean equals(Object o){
        Item comparedItem = (Item)o;
        boolean result = (this.name.equals(comparedItem.name) )&&( this.type == comparedItem.type);
        return result;
    }

    public int hashCode(){
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.type.hashCode();
        return result;
    }

    public static List<Item> getItems(){
        return itemList;
    }

    public static void saveItems(Context c){
        //Save itemList
        //TODO: Check if file is already created
        FileOutputStream outFile = null;
        ObjectOutputStream out = null;
        String filename = "itemsSavedata.dat";
        try{
            f.createNewFile();      //Will create if file not existing, won't create if file existing
        }catch (Exception e){
            System.out.print("----Cannot create new file----");
            e.printStackTrace();
            System.out.print("------------------------------");
        }

        try{
            outFile = c.openFileOutput(filename,Context.MODE_PRIVATE);
            out = new ObjectOutputStream(outFile);
            out.writeInt(itemList.size());
            for(int i=0; i<itemList.size(); i++)
            out.writeObject(itemList.get(i));
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void loadItems(Context c){
        //Load items to itemlist
        itemList.clear();
        ObjectInputStream in = null;
        FileInputStream fin = null;
        String filename = "itemsSavedata.dat";
        try{
            fin = c.openFileInput(filename);
            in = new ObjectInputStream(fin);
            num_items = in.readInt();
            for (int i=0; i<num_items; i++){
                Item readItem = (Item) in.readObject();
                itemList.add(readItem);
            }
            in.close();
            //Here to delete contents?
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
