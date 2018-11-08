package com.guiji.cloud.zuul.white;

import com.guiji.component.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by ty on 2018/11/8.
 */
public class WhiteIPUtil {
    private static Properties properties;
    private static final String WHITEIP = "whiteIP";

    public static String getIps(){
        return properties.getProperty(WHITEIP);

    }

    @Autowired(required = true)
    public  void setProperties(@Qualifier("whiteIPProperties") Properties properties) {
        this.properties = properties;
    }
}
