package cn.lxgandlz.console;

import cn.lxgandlz.core.zk.ZkClient;
import cn.lxgandlz.core.zk.ZkConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ZkConfiguration.class})
public class LxgJobConsoleApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LxgJobConsoleApplication.class, args);
        ZkClient zkClient = context.getBean(ZkClient.class);
        zkClient.register();
    }



}
