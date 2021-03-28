package com.example.currencyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater inflater;
    private List<ListItem> listItem = new ArrayList<>();



    public CustomArrayAdapter(@NonNull Context context, int resource, List<ListItem> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItem listItemMain = listItem.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_name);
            viewHolder.code = convertView.findViewById(R.id.tv_code);
            viewHolder.value = convertView.findViewById(R.id.tv_value);
            convertView.setTag(viewHolder);



        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(listItemMain.getCurName());
        viewHolder.code.setText(listItemMain.getCurCode());
        viewHolder.value.setText(listItemMain.getCurValue());








        return convertView;
    }
    private class ViewHolder{
        TextView name;
        TextView code;
        TextView value;


    }
}