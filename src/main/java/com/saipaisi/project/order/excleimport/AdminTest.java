package com.saipaisi.project.order.excleimport;

import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author alfred.zong
 * @Date 2021/5/24 19:37
 * @Description
 */


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminTest {

    @Value("${qxinbao.appkey}")
    private String appkey;
    /**
     * 密钥
     */
    @Value("${qxinbao.secret_key}")
    private String secret_key;

    @Autowired
    private QXBHttps qxbHttps;

    @Test
    public void test(){
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword("安徽赛派思科技有限公司");
        searchApiEntity.setAppkey(appkey);
        searchApiEntity.setSecret_key(secret_key);
        Map<String,Object> map=qxbHttps.getExecutionListByName(searchApiEntity);
        System.out.println(map);
    }
}
