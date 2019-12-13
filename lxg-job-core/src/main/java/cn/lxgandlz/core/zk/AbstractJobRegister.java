package cn.lxgandlz.core.zk;

import org.apache.curator.framework.CuratorFramework;

import java.net.UnknownHostException;

/**
 * @author li xin guang
 */
public abstract class AbstractJobRegister {

    /**
     * 向zk 中心注册客户端
     * @return zk 客户端
     */
    public CuratorFramework register() throws UnknownHostException {
        return null;
    }

    public void destroy(){}
}
