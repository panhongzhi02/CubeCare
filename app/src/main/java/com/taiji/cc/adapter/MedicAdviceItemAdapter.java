package com.taiji.cc.adapter;/**
 * Created by panho on 2016-08-15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiji.cc.R;
import com.taiji.cc.bean.V_Order;

import java.util.List;


/**
 * 作者：panho on 2016-08-15 11:46
 * 邮箱：panhongzhi02@163.com
 */
public class MedicAdviceItemAdapter extends RecyclerView.Adapter<MedicAdviceItemAdapter.ItemViewHolder>{

    private Context mContext;

    private List<V_Order> mV_orders;

    private LayoutInflater mInflater;

    public MedicAdviceItemAdapter(Context context, List<V_Order> v_orders){
        this.mContext = context;
        this.mV_orders = v_orders;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.orderlist_item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if(mV_orders.size()==1){
            holder.order_serial.setBackgroundResource(R.drawable.order_serial_04);
        }else if(mV_orders.size()==2){
            if(position==0){
                holder.order_serial.setBackgroundResource(R.drawable.order_serial_01);
            }else {
                holder.order_serial.setBackgroundResource(R.drawable.order_serial_03);
            }
        }else {
            if(position==0){
                holder.order_serial.setBackgroundResource(R.drawable.order_serial_01);
            }else if(position==mV_orders.size()-1){
                holder.order_serial.setBackgroundResource(R.drawable.order_serial_03);
            }else{
                holder.order_serial.setBackgroundResource(R.drawable.order_serial_02);
            }
        }
        holder.order_content.setText(mV_orders.get(position).getDic_name());
        String unit = mV_orders.get(position).getAdvi_unit();
        if(unit==null){
            unit="";
        }
        holder.order_unit.setText(mV_orders.get(position).getOne_quan()+unit);
    }

    @Override
    public int getItemCount() {
        return mV_orders.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView order_serial;
        TextView order_content;
        TextView order_unit;

        public ItemViewHolder(View itemView) {
            super(itemView);
            order_serial = (ImageView) itemView.findViewById(R.id.order_serial);
            order_content = (TextView) itemView.findViewById(R.id.order_content);
            order_unit = (TextView) itemView.findViewById(R.id.order_unit);
        }
    }

}
