package cn.lxgandlz.core.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li xin guang
 * @date 2019-12-14
 */
public class ZkClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CuratorFramework client;
    private String zookeeperServer;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
    private int baseSleepTimeMs;
    private int maxRetries;
    private String group;
    private String nodeName;
    private static final Stat EMPTY_STAT = new Stat();

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setZookeeperServer(String zookeeperServer) {
        this.zookeeperServer = zookeeperServer;
    }

    public String getZookeeperServer() {
        return zookeeperServer;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        client = CuratorFrameworkFactory.builder().connectString(zookeeperServer).retryPolicy(retryPolicy)
                .sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs).build();
        client.start();
    }

    public void stop() {
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void register() {
        try {
            String rootPath = "/" + "services";
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String serviceInstance = this.nodeName + "-" + hostAddress + "-";
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(rootPath + "/" + this.group + "/" + serviceInstance);
        } catch (Exception e) {
            logger.error("注册出错", e);
        }
    }

    public List<String> getChildren(String path) {
        List<String> childrenList = new ArrayList<>();
        try {
            childrenList = client.getChildren().forPath(path);

        } catch (Exception e) {
            logger.error("获取子节点出错", e);
        }
        return childrenList;
    }

    protected ChildData getData(String path) {
        try {
            return new ChildData(path, EMPTY_STAT, client.getData().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getChildrenCount(String path) {
        return getChildren(path).size();
    }

    public List<String> getInstances() {
        return getChildren("/services");
    }

    public int getInstancesCount() {
        return getInstances().size();
    }
}