package cn.lxgandlz.test;

import cn.lxgandlz.core.zk.ZkClient;
import cn.lxgandlz.core.zk.ZkConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author li xin guang
 * @date 2019/12/13
 */
@SpringBootApplication
@Import({ZkConfiguration.class})
public class LxgJobTestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LxgJobTestApplication.class, args);
        ZkClient zkClient = context.getBean(ZkClient.class);
        zkClient.register();
    }


}
