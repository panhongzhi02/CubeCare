package com.taiji.cc.bean;/**
 * Created by panho on 2016-08-15.
 */

/**
 * 作者：panho on 2016-08-15 14:59
 * 邮箱：panhongzhi02@163.com
 */
public class OrderTerm {

    private String anam_id;//病案号
    private String vid;//住院次数
    private String advi_type;//医嘱类型
    private String fun_type;//长期临时类型
    private String state;//执行状态
    private String ini_dt;//开医嘱时间
    private String stop_dt;//停医嘱时间
    private String start;//分页开始行记录数
    private String end;//分页结束行记录数

    public OrderTerm() {
    }

    public OrderTerm(String anam_id, String vid, String advi_type, String fun_type, String state, String ini_dt, String stop_dt, String start, String end) {
        this.anam_id = anam_id;
        this.vid = vid;
        this.advi_type = advi_type;
        this.fun_type = fun_type;
        this.state = state;
        this.ini_dt = ini_dt;
        this.stop_dt = stop_dt;
        this.start = start;
        this.end = end;
    }

    public String getAnam_id() {
        return anam_id;
    }

    public void setAnam_id(String anam_id) {
        this.anam_id = anam_id;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getAdvi_type() {
        return advi_type;
    }

    public void setAdvi_type(String advi_type) {
        this.advi_type = advi_type;
    }

    public String getFun_type() {
        return fun_type;
    }

    public void setFun_type(String fun_type) {
        this.fun_type = fun_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIni_dt() {
        return ini_dt;
    }

    public void setIni_dt(String ini_dt) {
        this.ini_dt = ini_dt;
    }

    public String getStop_dt() {
        return stop_dt;
    }

    public void setStop_dt(String stop_dt) {
        this.stop_dt = stop_dt;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "OrderTerm{" +
                "anam_id='" + anam_id + '\'' +
                ", vid='" + vid + '\'' +
                ", advi_type='" + advi_type + '\'' +
                ", fun_type='" + fun_type + '\'' +
                ", state='" + state + '\'' +
                ", ini_dt='" + ini_dt + '\'' +
                ", stop_dt='" + stop_dt + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
