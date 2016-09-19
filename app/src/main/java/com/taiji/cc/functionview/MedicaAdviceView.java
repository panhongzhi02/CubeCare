package com.taiji.cc.functionview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.taiji.cc.R;
import com.taiji.cc.adapter.MedicaAdviceAdapter;
import com.taiji.cc.bean.OrderList;
import com.taiji.cc.bean.OrderTerm;
import com.taiji.cc.bean.PageOrder;
import com.taiji.cc.factorys.CommonFactory;
import com.taiji.cc.http.methods.HttpMethod;
import com.taiji.cc.http.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by panho on 2016/9/6.
 */
public class MedicaAdviceView extends BasePageView{

    private Context context;

    private SwipeRefreshLayout medicaadvice_sr;

    private RecyclerView medicaadvice_rv;

    private MedicaAdviceAdapter adapter;

    private List<OrderList> orderLists = new ArrayList<>();

    private OrderTerm orderTerm;

    public MedicaAdviceView(Context context) {
        this(context,null);
    }

    public MedicaAdviceView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.medicaadvice_view_layout,this,true);
        this.context = context;

        medicaadvice_sr = (SwipeRefreshLayout) findViewById(R.id.medicaadvice_sr);
        medicaadvice_sr.setColorSchemeResources(R.color.colorPrimary);
        medicaadvice_sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderTerm.setAnam_id(CommonFactory.getFactory(context).getPid());
                orderTerm.setVid(CommonFactory.getFactory(context).getVid());
                loadOrders(orderTerm);
            }
        });

        medicaadvice_rv = (RecyclerView) findViewById(R.id.medicaadvice_rv);
        adapter = new MedicaAdviceAdapter(orderLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        medicaadvice_rv.setLayoutManager(layoutManager);
        medicaadvice_rv.setAdapter(adapter);

    }

    private void loadOrders(OrderTerm orderTerm){
        OrderService service = HttpMethod.getInstance().getRetrofit().create(OrderService.class);
        Gson gson = new Gson();
        service.getOrderList(gson.toJson(orderTerm))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PageOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PageOrder pageOrder) {
                        adapter.setNewData(pageOrder.getOrderLists());
                        medicaadvice_sr.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onRefreshData(Map<String,Object> params) {
        this.orderTerm = (OrderTerm) params.get("order");
        loadOrders(orderTerm);
    }
}
