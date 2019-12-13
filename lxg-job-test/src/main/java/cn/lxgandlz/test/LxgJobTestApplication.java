package cn.lxgandlz.test;

import cn.lxgandlz.core.zk.JobRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author li xin guang
 * @date 2019/12/13
 */
@SpringBootApplication
public class LxgJobTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LxgJobTestApplication.class, args);
    }


    @Bean
    public JobRegister getRegister(){
        return new JobRegister();
    }
}