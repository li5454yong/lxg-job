package cn.lxgandlz.core.zk;

import cn.lxgandlz.core.pojo.ClientDTO;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author li xin guang
 */
public class JobRegister {

    private static final Stat EMPTY_STAT = new Stat();

    private final CuratorFramework client;

    public JobRegister() {
        Yaml yaml = new Yaml();
        ClientDTO clientDTO = yaml.loadAs(JobRegister.class.getClassLoader().getResourceAsStream("application.yml"), ClientDTO.class);

        String zkAddress = clientDTO.getZk().get("address").toString();

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client =
                CuratorFrameworkFactory.builder()
                        .connectString(zkAddress)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .build();

        client.start();
    }


    /**
     * 向zk 中心注册客户端
     * @return zk 客户端
     * 1、3rd获取人员接口添加是否在职筛选
     * 2、3rd获取人员bug修改，调整返回数据解析格式
     * 3、bpm根据规则获取办理人bug修改
     * 4、流程设计、表单设计功能测试，bug回归
     * 5、3rd接口文档重写
     */
    public void saveData(){
        try{
            String path = "/job/child";
            Stat stat = this.client.checkExists().forPath(path);

            if(stat == null){
                this.client.create().forPath(path);
            }
            this.client.setData().forPath(path ,"192.168.0.1:9527".getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public  List<ChildData> getChildren(String path) {
        if (!checkExists(path)) {
            return new ArrayList<>();
        }
        try {
            List<ChildData> childDataList = new ArrayList<>();
            List<String> children = client.getChildren().forPath(path);
            childDataList.addAll(children.stream().map(child -> getData(path + "/" + child)).collect(Collectors.toList()));
            return childDataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ChildData getData(String path) {
        try {
            return new ChildData(path, EMPTY_STAT, client.getData().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    boolean checkExists(String path) {
        try {
            return client.checkExists().forPath(path) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
