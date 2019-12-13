package cn.lxgandlz.test.controller;

import cn.lxgandlz.core.pojo.ClientDTO;
import cn.lxgandlz.test.InitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * @author li xin guang
 */
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);
    @GetMapping("getYaml")
    public Object getYaml(){

        Yaml yaml = new Yaml();
        ClientDTO map = yaml.loadAs(TestController.class.getClassLoader().getResourceAsStream("application.yml"), ClientDTO.class);

        logger.info("post:{}",map.getServer().get("port"));

        return map;
    }
}
