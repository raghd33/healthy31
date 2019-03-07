package com.example.healthy;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class Listfood extends ListActivity {

    public listfood onCreat(context context){
    healthyDataSource hd= new healthyDataSource(this);
    hd.open();
ArrayList<String> myFood = (ArrayList<String>) hd.getAllFood();
        hd.colse();
setListAdapter(new ArrayAdapter<String> (this, android.R.layout.activity_main,myFood));

  retun ;  }}


