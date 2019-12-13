package cn.lxgandlz.core.zk.impl;

import cn.lxgandlz.core.pojo.ClientDTO;
import cn.lxgandlz.core.zk.AbstractJobRegister;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author li xin guang
 */
public class ZookeeperRegisterImpl extends AbstractJobRegister {

    private final Logger logger = LoggerFactory.getLogger(ZookeeperRegisterImpl.class);

    @Override
    public CuratorFramework register() throws UnknownHostException {
        /**
         * 在zk创建根节点path,在根节点下创建临时子节点用于存放服务ip和端口
         */
        Yaml yaml = new Yaml();
        ClientDTO clientDTO = yaml.loadAs(ZookeeperRegisterImpl.class.getClassLoader().getResourceAsStream("application.yml"), ClientDTO.class);

        String ip = InetAddress.getLocalHost().getHostAddress();
        //获得端口
        String port = clientDTO.getServer().get("port").toString();

        String zkAddress = clientDTO.getZk().get("zkAddress").toString();

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(zkAddress)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .build();

        client.start();
        return client;

    }

    @Override
    public void destroy() {

    }
}
