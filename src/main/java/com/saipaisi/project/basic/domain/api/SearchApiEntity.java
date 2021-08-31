package com.saipaisi.project.basic.domain.api;

import com.saipaisi.project.common.Constant;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Author alfred.zong
 * @Date 2021/5/24 18:57
 * @Description
 */
@Data
public class SearchApiEntity implements Serializable {

    /**
     *
     */
    private String appkey;

    /**
     * 密钥
     */

    private String secret_key;

    /**
     * 关键字
     */
    private String keyword;

    private String name;

    public String getAppkey() {

        return Constant.APPKEY;
    }

    public String getSecret_key() {
        return Constant.SECRET_KEY;
    }
}
