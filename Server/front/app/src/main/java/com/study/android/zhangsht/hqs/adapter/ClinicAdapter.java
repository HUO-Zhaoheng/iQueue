package com.study.android.zhangsht.hqs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.model.ClinicItem;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zhangsht on 2017/5/25.
 */

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.ClinicViewHolder> {
    private ArrayList<ClinicItem> list;
    private Context context;

    private OnRecyclerViewItemClickListener onItemClickListener;//点击的接口
    private OnRecyclerViewItemLongClickListener onItemLongClickListener;//长按的接口
    private OnItemTouchListener onItemTouchListener;

    /*
    暴露出去的点击事件的方法
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    /*
    点击要实现的接口
     */
    public interface OnRecyclerViewItemClickListener{
        //接口中实现具体操作的抽象方法
        public abstract void OnItemClick(int itemPosition);
    }

    /*
    暴露出去的长按事件的方法
     */
    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener onItemLongClickListener){
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /*
   暴露出去的点击事件的方法
    */
    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener){
        this.onItemTouchListener = onItemTouchListener;
    }

    /*
    点击要实现的接口
     */
    public interface OnItemTouchListener{
        public void OnItemTouch(MotionEvent event, ClinicViewHolder holder);
    }

    /*
    长按要实现的接口
     */
    public interface OnRecyclerViewItemLongClickListener{
        //接口中实现具体操作的抽象方法
        public abstract void OnItemLongClick(int itemPosition);
    }

    public ClinicAdapter(ArrayList<ClinicItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ClinicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.clinic_item, parent, false);
        //实例化MainViewHolder---- 传View过去
        ClinicViewHolder holder = new ClinicViewHolder(view);
        return holder;
    }

    public class ClinicViewHolder extends RecyclerView.ViewHolder {
        TextView clinicName;
        TextView doctorName;
        TextView inTreatName;
        TextView waitTreatNames;

        public ClinicViewHolder(View itemView) {
            super(itemView);

            clinicName = (TextView) itemView.findViewById(R.id.clinicName);
            doctorName = (TextView) itemView.findViewById(R.id.doctorName);
            inTreatName = (TextView) itemView.findViewById(R.id.inTreatName);
            waitTreatNames = (TextView) itemView.findViewById(R.id.waitTreatNames);
        }

        /**
         * 操作开始时调用的方法
         */
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.GRAY);
        }
        /**
         * 操作完成后调用的方法
         */
        public void onItemNormal() {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public void onBindViewHolder(final ClinicViewHolder holder, final int position) {
        ClinicItem clinicItem = list.get(position);
        //控件中设置值
        holder.clinicName.setText(clinicItem.getClinicName());
        holder.doctorName.setText(clinicItem.getDoctorName());
        holder.inTreatName.setText(clinicItem.getInTreatName());
        holder.waitTreatNames.setText(clinicItem.getWaitTreatNames());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(position);
            }
        });
//
        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.OnItemLongClick(position);
                return true;
            }
        });*/

        holder.waitTreatNames.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onItemTouchListener.OnItemTouch(event, holder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 拖动方法
     */
    public boolean onItemMove(int fromPosition, int toPosition) {
        //互换列表中指定位置的数据
        Collections.swap(list, fromPosition, toPosition);
        //通知改变
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    /**
     * 滑动删除的方法
     */
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
