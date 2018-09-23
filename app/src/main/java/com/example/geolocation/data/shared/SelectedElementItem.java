package com.example.geolocation.data.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class SelectedElementItem {

    private static final String CURRENT_ELEMENT_ITEM = "current_element_item";
    private static final String CURRENT_ELEMENT_ID = "current_element_id";
    private static final long DEFAULT_ELEMENT_ID = -1;


    private SharedPreferences sharedPreferences;

    public SelectedElementItem(Context context) {
        this.sharedPreferences = context.getSharedPreferences(CURRENT_ELEMENT_ITEM, Context.MODE_PRIVATE);
    }

    public void setCurrentElementId(long id){
        sharedPreferences.edit().putLong(CURRENT_ELEMENT_ID, id).apply();
    }

    public long getSelectedItem(){
        return sharedPreferences.getLong(CURRENT_ELEMENT_ID, DEFAULT_ELEMENT_ID);
    }
}
