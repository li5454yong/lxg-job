package cn.lxgandlz.console;

import cn.lxgandlz.core.zk.JobRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LxgJobConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LxgJobConsoleApplication.class, args);
    }

    @Bean
    public JobRegister getRegister(){
        return new JobRegister();
    }

}
