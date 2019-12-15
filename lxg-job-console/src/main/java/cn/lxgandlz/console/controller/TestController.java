package cn.lxgandlz.console.controller;

import cn.lxgandlz.core.zk.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

/**
 * @author li xin guang
 */
@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ZkClient zkClient;

    @GetMapping("getData")
    public void getData(){

        /*
         *   我是第几个实例, 做负载均衡
         * */
        List<String> instanceList = zkClient.getInstances();
        Collections.sort(instanceList);
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int instanceNo = 0;
        if (hostAddress !=  null) {
            for (int i=0; i<instanceList.size(); i++) {
                if (instanceList.get(i).split("-")[1].equals(hostAddress)) {
                    instanceNo = i;
                }
            }
        } else {
            logger.info("获取本地IP失败");
        }

    }
}
