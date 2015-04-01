package com.jvra.nd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jansel R. Abreu on 3/31/2015.
 */
public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder>{

    private String[] dataset;
    private OnItemClickListener listener;

    public PlanetAdapter(String[] dataset, OnItemClickListener listener) {
        this.dataset = dataset;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( viewGroup.getContext() );
        View v = inflater.inflate( R.layout.drawer_list_item,viewGroup,false );
        TextView t = (TextView) v.findViewById( android.R.id.text1 );
        return new ViewHolder(t);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.text.setText( dataset[position] );
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( null != listener )
                    listener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView text;
        public ViewHolder(TextView text) {
            super(text);
            this.text = text;
        }
    }
}
