package com.jvra.ft;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/26/2015.
 */
public class MeatAdapter extends BaseAdapter{

    private LayoutInflater inflater;

    private int resourceId;

    public MeatAdapter(LayoutInflater inflater, int resource) {
        this.inflater = inflater;
        this.resourceId = resource;
    }

    @Override
    public int getCount() {
        return Meat.MEATS.length;
    }

    @Override
    public Meat getItem(int i) {
        return Meat.MEATS[i];
    }

    @Override
    public long getItemId(int i) {
        return Meat.MEATS[i].resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        final ViewHolder holder;
        if (null == convertView) {
            view = inflater.inflate(resourceId, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        bindView(holder, position);
        return view;
    }

    public void bindView(ViewHolder holder, int position) {
        Meat meat = getItem(position);
        holder.image.setImageResource(meat.resourceId);
//        holder.title.setText(meat.title);
    }


    public static class ViewHolder {
        public ImageView image;
        public TextView title;
    }
}
