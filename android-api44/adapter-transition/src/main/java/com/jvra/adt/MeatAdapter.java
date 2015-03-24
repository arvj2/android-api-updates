package com.jvra.adt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/23/2015.
 */
public class MeatAdapter extends BaseAdapter implements AbsListView.OnItemClickListener{

    private final LayoutInflater mLayoutInflater;
    private final int mResourceId;

    public MeatAdapter(LayoutInflater inflater, int resourceId) {
        mLayoutInflater = inflater;
        mResourceId = resourceId;
    }

    @Override
    public int getCount() {
        return Meat.MEATS.length;
    }

    @Override
    public Object getItem(int i) {
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
            view = mLayoutInflater.inflate(mResourceId, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.image = (ImageView) view.findViewById(R.id.meat_image);
            holder.title = (TextView) view.findViewById(R.id.meat_title);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        Meat meat = (Meat)getItem(position);
        holder.image.setImageResource(meat.resourceId);
        holder.title.setText(meat.title);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ViewHolder holder = (ViewHolder) view.getTag();
        Context context = view.getContext();
        if (null != holder && null != holder.title && null != context) {
            Toast.makeText(context, context.getString(R.string.item_clicked,
                    holder.title.getText()), Toast.LENGTH_SHORT).show();
        }
    }

    private static class ViewHolder {
        public ImageView image;
        public TextView title;
    }
}
