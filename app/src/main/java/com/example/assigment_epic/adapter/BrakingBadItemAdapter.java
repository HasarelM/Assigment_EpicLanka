package com.example.assigment_epic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.assigment_epic.R;
import com.example.assigment_epic.model.BreakingBadModel;
import com.example.assigment_epic.utills.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrakingBadItemAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<BreakingBadModel> mBrakingBadItemArrayList;

    public BrakingBadItemAdapter(Context context, ArrayList<BreakingBadModel> list) {
        this.mContext = context;
        this.mBrakingBadItemArrayList = list;
    }

    @Override
    public int getCount() {
        return mBrakingBadItemArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //adapter layout set here
        View view = LayoutInflater.from(mContext).inflate(R.layout.braking_bad_data_item, container, false);

        //initiate the layout ui components
        ImageView mIvImage;
        TextView mTvName;
        TextView mTvOccupations;
        TextView mTvDummyText;
        mIvImage = view.findViewById(R.id.layout_braking_bad_data_item_iv_image);
        mTvName = view.findViewById(R.id.layout_braking_bad_data_item_tv_name);
        mTvOccupations = view.findViewById(R.id.layout_braking_bad_data_item_tv_occupations);
        mTvDummyText = view.findViewById(R.id.layout_braking_bad_data_item_tv_dummy_text);

        //get data from list and set it into view
        BreakingBadModel model = mBrakingBadItemArrayList.get(position);
        Picasso.get().load(model.getImg()).into(mIvImage);
        mTvName.setText(model.getName());
        if (Utils.isListNotNullAndEmpty(model.getOccupation())) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < model.getOccupation().size(); i++) {
                sb.append(model.getOccupation().get(i) + "\n");
            }

            mTvOccupations.setText(sb);
        }
        mTvDummyText.setText(model.getStatus());


        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
