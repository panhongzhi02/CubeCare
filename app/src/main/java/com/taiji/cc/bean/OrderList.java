package com.taiji.cc.bean;/**
 * Created by panho on 2016-08-15.
 */

import java.util.List;

/**
 * 作者：panho on 2016-08-15 10:08
 * 邮箱：panhongzhi02@163.com
 */
public class OrderList {

    private String dic_code;//主医嘱号
    private String fun_type;//长期临时
    private String ini_dt;//开医嘱时间
    private String usage_name;//用药途径
    private String nums_mean;//频次
    private String use_label;//计划执行时间
    private String state;//医嘱状态
    private List<V_Order> orders;//子医嘱列表

    public OrderList(){
        super();
    }

    public OrderList(String dic_code, String fun_type, String ini_dt, String usage_name, String nums_mean, String use_label, String state, List<V_Order> orders) {
        this.dic_code = dic_code;
        this.fun_type = fun_type;
        this.ini_dt = ini_dt;
        this.usage_name = usage_name;
        this.nums_mean = nums_mean;
        this.use_label = use_label;
        this.state = state;
        this.orders = orders;
    }

    public String getDic_code() {
        return dic_code;
    }

    public void setDic_code(String dic_code) {
        this.dic_code = dic_code;
    }

    public String getFun_type() {
        return fun_type;
    }

    public void setFun_type(String fun_type) {
        this.fun_type = fun_type;
    }

    public String getIni_dt() {
        return ini_dt;
    }

    public void setIni_dt(String ini_dt) {
        this.ini_dt = ini_dt;
    }

    public String getUsage_name() {
        return usage_name;
    }

    public void setUsage_name(String usage_name) {
        this.usage_name = usage_name;
    }

    public String getNums_mean() {
        return nums_mean;
    }

    public void setNums_mean(String nums_mean) {
        this.nums_mean = nums_mean;
    }

    public String getUse_label() {
        return use_label;
    }

    public void setUse_label(String use_label) {
        this.use_label = use_label;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<V_Order> getOrders() {
        return orders;
    }

    public void setOrders(List<V_Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                ", dic_code='" + dic_code + '\'' +
                ", fun_type='" + fun_type + '\'' +
                ", ini_dt='" + ini_dt + '\'' +
                ", usage_name='" + usage_name + '\'' +
                ", nums_mean='" + nums_mean + '\'' +
                ", use_label='" + use_label + '\'' +
                ", state='" + state + '\'' +
                ", orders=" + orders +
                '}';
    }
}
