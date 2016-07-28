package com.globallogic.training.hradcicva.deprecated;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.training.hradcicva.R;

/**
 * Created by jan.murin on 28-Jul-16.
 */
public class MainButtonsRecyclerViewAdapter extends RecyclerView.Adapter<MainButtonsRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = MainButtonsRecyclerViewAdapter.class.getSimpleName();
    private final OnMainButtonClickListener listener;
    private String[] mDataset;

    public interface OnMainButtonClickListener {
        void OnMainButtonClick(int id);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mainButtonTextView;
        private final ImageView mainButtonIcon;
        //  private final TextView numberTextView;

        public ViewHolder(View mainButtonView) {
            super(mainButtonView);
            mainButtonTextView = (TextView) mainButtonView.findViewById(R.id.mainButtonTextView);
            mainButtonIcon = (ImageView) mainButtonView.findViewById(R.id.mainButtonIcon);

            //numberTextView = (TextView) mainButtonView.findViewById(R.id.numberTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainButtonsRecyclerViewAdapter(String[] myDataset, OnMainButtonClickListener list) {
        mDataset = myDataset;
        listener = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainButtonsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_button, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mainButtonTextView.setText(mDataset[position]);
        holder.mainButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked button with id: " + position);
                listener.OnMainButtonClick(position);
            }
        });
//        holder.numberTextView.setText("" + position + ".");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
