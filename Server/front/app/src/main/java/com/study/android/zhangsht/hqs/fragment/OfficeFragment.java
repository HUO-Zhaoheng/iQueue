package com.study.android.zhangsht.hqs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.activity.MyClinic;
import com.study.android.zhangsht.hqs.adapter.ClinicAdapter;
import com.study.android.zhangsht.hqs.model.ClinicItem;
import com.study.android.zhangsht.hqs.utils.ItemTouchHelperCallback;

import java.util.ArrayList;

public class OfficeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<ClinicItem> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_office, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.clinicList);
        final FragmentActivity activity = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ClinicItem("0" + i, "doc" + i, "pat" + i, "wait"));
        }

        ClinicAdapter adapter = new ClinicAdapter(list, activity);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
//itemTouchHelper与RecyclerView关联
        itemTouchHelper.attachToRecyclerView(recyclerView);
//设置ImgView的触摸监听事件
        adapter.setOnItemTouchListener(new ClinicAdapter.OnItemTouchListener() {

            @Override
            public void OnItemTouch(MotionEvent event, ClinicAdapter.ClinicViewHolder holder) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    //按下则开始拖动
                    itemTouchHelper.startDrag(holder);
                }
            }
        });
        adapter.setOnItemClickListener(new ClinicAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(int itemPosition) {
                Intent intent = new Intent(activity, MyClinic.class);
                startActivity(intent);
            }
        });
        /*adapter.setOnItemLongClickListener(new ClinicAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void OnItemLongClick(int itemPosition) {
                Toast.makeText(activity, "长按" + list.get(itemPosition), Toast.LENGTH_SHORT).show();
            }
        });*/
        recyclerView.setAdapter(adapter);
        return contentView;
    }

    private void init() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
