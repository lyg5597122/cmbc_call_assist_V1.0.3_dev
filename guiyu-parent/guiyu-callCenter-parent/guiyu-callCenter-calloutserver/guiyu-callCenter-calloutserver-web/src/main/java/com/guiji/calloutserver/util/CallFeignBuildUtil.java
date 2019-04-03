package com.guiji.calloutserver.util;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/29 0029 10:59
 * @Description:
 */
public class CallFeignBuildUtil {

   /**
    * 获取指定url的请求接口，用于访问url的接口
    * @param apiType  请求接口.class
    * @param url 请求地址
    * @param <T> 请求接口
    * @return
    */
   public static  <T> T feignBuilderTarget(Class<T> apiType, String url){
      return  Feign.builder()
               .encoder(new JacksonEncoder())
               .decoder(new JacksonDecoder())
               .contract(new SpringMvcContract())
               .options(new Request.Options(240000, 240000))// 超时时间  4分钟  240000
               .retryer(Retryer.NEVER_RETRY) //重试机制，重试次数，重试间隔
               .target( apiType,  url);

   }

}
