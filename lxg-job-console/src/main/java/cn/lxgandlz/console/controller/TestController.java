package cn.lxgandlz.console.controller;

import cn.lxgandlz.core.zk.JobRegister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author li xin guang
 */
@RestController
public class TestController {

    @Resource
    private JobRegister jobRegister;

    @GetMapping("getData")
    public void getData(){

        jobRegister.saveData();

    }
}
