package com.globallogic.training.hradcicva.gui;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.globallogic.training.hradcicva.R;

/**
 * Created by jan.murin on 28-Jul-16.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private final String[] buttonNames;
    Context context;
    private static LayoutInflater inflater = null;

    public CustomListViewAdapter(Context context, String[] buttonNames) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.buttonNames = buttonNames;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return buttonNames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return buttonNames[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.main_button, null);
        TextView text = (TextView) vi.findViewById(R.id.mainButtonTextView);
        text.setText(buttonNames[position]);
        ImageView statImageView = (ImageView) vi.findViewById(R.id.mainButtonIcon);
        return vi;
    }


}
