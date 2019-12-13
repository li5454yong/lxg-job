package cn.lxgandlz.core.pojo;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @author li xin guang
 */
public class ClientDTO {

    private Map<String,Object> server;

    private Map<String, Object> spring;

    private Map<String, Object> zk;

    public Map<String, Object> getZk() {
        return zk;
    }

    public void setZk(Map<String, Object> zk) {
        this.zk = zk;
    }

    public Map<String, Object> getSpring() {
        return spring;
    }

    public void setSpring(Map<String, Object> spring) {
        this.spring = spring;
    }

    public Map<String, Object> getServer() {
        return server;
    }

    public void setServer(Map<String, Object> server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
