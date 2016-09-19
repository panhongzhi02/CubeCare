package com.taiji.cc.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by panho on 2016/9/2.
 */
@Entity
public class IpAddress {

    @Id
    private Long id;

    @NotNull
    private String ip;

    @NotNull
    private int port;

    @NotNull
    private String serverName;

    @Generated(hash = 2055819812)
    public IpAddress(Long id, @NotNull String ip, int port,
            @NotNull String serverName) {
        this.id = id;
        this.ip = ip;
        this.port = port;
        this.serverName = serverName;
    }

    @Generated(hash = 179288771)
    public IpAddress() {
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
