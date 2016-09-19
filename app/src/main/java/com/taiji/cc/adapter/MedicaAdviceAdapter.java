package com.taiji.cc.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.cc.R;
import com.taiji.cc.bean.OrderList;

import java.util.List;


/**
 * Created by panho on 2016/9/7.
 */
public class MedicaAdviceAdapter extends BaseQuickAdapter<OrderList> {

    public MedicaAdviceAdapter(List<OrderList> data) {
        super(R.layout.medicaadvice_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderList orderList) {
        RecyclerView medicaadvice_item_rv = baseViewHolder.getView(R.id.medicaadvice_item_rv);
        MedicAdviceItemAdapter adapter = new MedicAdviceItemAdapter(mContext,orderList.getOrders());
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        medicaadvice_item_rv.setLayoutManager(manager);
        medicaadvice_item_rv.setAdapter(adapter);
        baseViewHolder.setText(R.id.fun_type,orderList.getFun_type());
        baseViewHolder.setText(R.id.ini_dt,orderList.getIni_dt());

    }
}
