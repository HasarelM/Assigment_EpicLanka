package com.example.assigment_epic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_epic.R;
import com.example.assigment_epic.model.BottomBarModel;

import java.util.ArrayList;

public class BottomBarAdapter extends RecyclerView.Adapter<BottomBarAdapter.ViewHolder> {

    private ArrayList<BottomBarModel> mBottomBarArrayList;
    private Context mContext;

    // get data from main activity
    public BottomBarAdapter(Context context, ArrayList<BottomBarModel> list) {
        this.mBottomBarArrayList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //initiate the adapter item layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_adapter_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // bind the data to item
        BottomBarModel model = mBottomBarArrayList.get(position);
        holder.mTvName.setText(model.getBottomName());
    }

    @Override
    public int getItemCount() {
        return mBottomBarArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.bottom_layout_tv_name);
        }
    }
}
