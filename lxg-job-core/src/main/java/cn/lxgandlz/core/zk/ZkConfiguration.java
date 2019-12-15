package cn.lxgandlz.core.zk;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author li xin guang
 * @date 2019-12-14
 */
@Configurable
public class ZkConfiguration {
    @Value("${zookeeper.server}")
    private String zookeeperServer;
    @Value(("${zookeeper.sessionTimeoutMs}"))
    private int sessionTimeoutMs;
    @Value("${zookeeper.connectionTimeoutMs}")
    private int connectionTimeoutMs;
    @Value("${zookeeper.maxRetries}")
    private int maxRetries;
    @Value("${zookeeper.baseSleepTimeMs}")
    private int baseSleepTimeMs;
    @Value("${zookeeper.group}")
    private String group;
    @Value(("${zookeeper.nodeName}"))
    private String nodeName;

    @Bean(initMethod = "init", destroyMethod = "stop")
    public ZkClient zkClient() {
        ZkClient zkClient = new ZkClient();
        zkClient.setZookeeperServer(zookeeperServer);
        zkClient.setSessionTimeoutMs(sessionTimeoutMs);
        zkClient.setConnectionTimeoutMs(connectionTimeoutMs);
        zkClient.setMaxRetries(maxRetries);
        zkClient.setBaseSleepTimeMs(baseSleepTimeMs);
        zkClient.setGroup(group);
        zkClient.setNodeName(nodeName);
        return zkClient;
    }

}