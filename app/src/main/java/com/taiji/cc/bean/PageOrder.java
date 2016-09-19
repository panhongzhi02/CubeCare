package com.taiji.cc.bean;/**
 * Created by panho on 2016-08-17.
 */

import java.util.List;

/**
 * 作者：panho on 2016-08-17 09:27
 * 邮箱：panhongzhi02@163.com
 */
public class PageOrder {

    private String total;//总页数

    private List<OrderList> orderLists;//医嘱列表

    public PageOrder(String total, List<OrderList> orderLists) {
        this.total = total;
        this.orderLists = orderLists;
    }

    public PageOrder() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @Override
    public String toString() {
        return "PageOrder{" +
                "total='" + total + '\'' +
                ", orderLists=" + orderLists +
                '}';
    }
}
