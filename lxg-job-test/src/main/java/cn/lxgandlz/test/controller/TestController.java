package cn.lxgandlz.test.controller;

import cn.lxgandlz.core.zk.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author li xin guang
 */
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private ZkClient zkClient;

    @GetMapping("getYaml")
    public void getYaml(){

        zkClient.register();

    }
}
