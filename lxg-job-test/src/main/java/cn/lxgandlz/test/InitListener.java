package cn.lxgandlz.test;

import cn.lxgandlz.core.zk.AbstractJobRegister;
import cn.lxgandlz.core.zk.impl.ZookeeperRegisterImpl;
import org.apache.zookeeper.data.Stat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author li xin guang
 */
public class InitListener implements ServletContextListener {

    private AbstractJobRegister register = new ZookeeperRegisterImpl();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        register.register();
        String path = "/job";
        Stat stat = client.checkExists().forPath(path);

        //先判断服务根路径是否存在
        if (stat == null) {
            client.create().forPath(path);
        }
        //将服务的ip和端口作为临时带序号的子节点
        String data = ip + ":" + port;
        client.create().forPath(path + "/child",data.getBytes());

        logger.info("job register success !!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
