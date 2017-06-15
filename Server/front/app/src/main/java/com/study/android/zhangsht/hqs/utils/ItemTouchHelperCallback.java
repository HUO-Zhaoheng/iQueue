package com.study.android.zhangsht.hqs.utils;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.study.android.zhangsht.hqs.adapter.ClinicAdapter;

/**
 * Created by zhangsht on 2017/5/25.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ClinicAdapter clinicAdapter;//RecyclerView的适配器

    /**
     * 多参的构造方法
     *
     * @param clinicAdapter 需要适配器来改变数据的位置，以及通知它来刷新
     */
    public ItemTouchHelperCallback(ClinicAdapter clinicAdapter) {
        this.clinicAdapter = clinicAdapter;

    }

    /**
     * RecyclerView item支持长按进入拖动操作
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * RecyclerView item任意位置触发启用滑动操作
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 指定可以支持的拖动和滑动的方向，上下为拖动（drag），左右为滑动（swipe）
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            //不需要滑动
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    /**
     * 拖动操作会执行的方法
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //判断如果viewHolder类型不一样，返回False
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //通知适配器改变数据 以及刷新
        clinicAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 滑动操作会执行的方法
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //通知适配器删除数据 以及刷新
        clinicAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    /**
     * 当动作开始的时候调用
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //动作不是空闲状态调用
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ClinicAdapter.ClinicViewHolder) {
                //获取执行的item的ViewHolder
                ClinicAdapter.ClinicViewHolder itemViewHolder = (ClinicAdapter.ClinicViewHolder) viewHolder;
                //选中状态回调
               itemViewHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当动作之中的时候调用
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //自定义滑动动画（让它根据滑动的距离与itemView的宽度的比例来改变透明度）
            final float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            //设置透明度
            viewHolder.itemView.setAlpha(alpha);
            //设置平移动画
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * 当动作结束的时候调用
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ClinicAdapter.ClinicViewHolder) {
            //获取执行的item的ViewHolder
            ClinicAdapter.ClinicViewHolder itemViewHolder = (ClinicAdapter.ClinicViewHolder) viewHolder;
            //未选中状态回调
            itemViewHolder.onItemNormal();
        }
    }
}
