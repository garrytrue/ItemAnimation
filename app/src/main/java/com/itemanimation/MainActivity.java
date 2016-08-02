package com.itemanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int COUNTER = 20;
    RecyclerView mRecyclerView;
    Adapter mAdapter;
    Button mBtnAddData;
    Button mBtnDeleteData;
    List<String> strings = new ArrayList<>(COUNTER);
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
        @Override
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    };
    private final OnViewClickedCallback onViewClickedCallback = new OnViewClickedCallback() {
        @Override
        public void onViewClicked(View view) {
            int viewPosition = mRecyclerView.getChildAdapterPosition(view);
            if(viewPosition == -1){
                return;
            }
            mAdapter.deleteAtPosition(viewPosition);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }

    private void initUI() {
        mBtnAddData = (Button) findViewById(R.id.btn_set_data);
        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });
        mBtnDeleteData = (Button) findViewById(R.id.btn_delete);
        mBtnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setItemAnimator(new CustomItemAnimation());
        mAdapter = new Adapter();
        mAdapter.setCallback(onViewClickedCallback);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void deleteData() {
        mAdapter.deleteOrAddData(0, 5);
    }

    private void setData() {
        for (int i = 0; i < COUNTER; i++) {
            strings.add("Counter " + i);
        }
        Log.d(TAG, "setData: " + strings);
        mAdapter.addItems(strings);
    }

}
