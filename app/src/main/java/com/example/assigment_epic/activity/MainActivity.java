package com.example.assigment_epic.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.assigment_epic.R;
import com.example.assigment_epic.adapter.BottomBarAdapter;
import com.example.assigment_epic.adapter.BrakingBadItemAdapter;
import com.example.assigment_epic.model.BottomBarModel;
import com.example.assigment_epic.model.BreakingBadModel;
import com.example.assigment_epic.retrofit.EndpointCalls;
import com.example.assigment_epic.utills.Utils;
import com.example.assigment_epic.view_model.BrakingBadViewModel;
import com.example.assigment_epic.view_model.BrakingBadViewModelFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    public static final String TAG = MainActivity.class.getSimpleName();

    //Ui components
    private ViewPager mViewPager;
    private BrakingBadViewModel mBrakingBadViewModel;
    private BrakingBadItemAdapter mBrakingBadItemAdapter;
    private RecyclerView mRvBottomList;
    private BottomBarAdapter mBottomBarAdapter;
    private ArrayList<BottomBarModel> mBottomBarModelArrayList = new ArrayList<>();
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate the ui components
        mViewPager = findViewById(R.id.activity_main_vp_brakingbad_view_pager);
        mRvBottomList = findViewById(R.id.activity_main_rv_bottom_list);
        mBottomBarAdapter = new BottomBarAdapter(this, mBottomBarModelArrayList);
        mRvBottomList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mRvBottomList.setAdapter(mBottomBarAdapter);
        setBottomBarData();

        // call the progress dialog from utils common class
        mProgressDialog = Utils.getProgress(this, "Please wait..");
        mProgressDialog.show();

        //initiate the view-model
        mBrakingBadViewModel = new ViewModelProvider(this, new BrakingBadViewModelFactory(this, new EndpointCalls(getApplicationContext()))).get(BrakingBadViewModel.class);
        mBrakingBadViewModel.getBreakingBadDataList().observe(this, mDataList);

    }

    // get data from view-model
    Observer<ArrayList<BreakingBadModel>> mDataList = new Observer<ArrayList<BreakingBadModel>>() {
        @Override
        public void onChanged(ArrayList<BreakingBadModel> list) {
            if (Utils.isListNotNullAndEmpty(list)) {
                mProgressDialog.dismiss();
                mBrakingBadItemAdapter = new BrakingBadItemAdapter(getApplicationContext(), list);
                mViewPager.setAdapter(mBrakingBadItemAdapter);
                mViewPager.setPadding(60, 0, 60, 0);
            } else {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), mBrakingBadViewModel.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        }
    };

    // set hard code values for bottom bar
    private void setBottomBarData() {
        String[] array = {"Recipes", "Shipping", "Subscription", "Payment", "Invoice", "Return", "Orders"};
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array));

        for (int i = 0; i < arrayList.size(); i++) {
            BottomBarModel model = new BottomBarModel();
            model.setBottomName(arrayList.get(i));
            mBottomBarModelArrayList.add(model);
        }
    }
}