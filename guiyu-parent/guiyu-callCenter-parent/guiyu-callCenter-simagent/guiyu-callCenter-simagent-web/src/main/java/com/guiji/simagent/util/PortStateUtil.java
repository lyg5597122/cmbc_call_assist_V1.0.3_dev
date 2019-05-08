package com.guiji.simagent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guiji.utils.SystemUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 判断某个端口是否正常
 */
public class PortStateUtil {
    private static final Logger logger = LoggerFactory.getLogger(PortStateUtil.class);

    public static boolean PortIsIn(String port){
        Socket socket =null;
        try {
            socket = new Socket(SystemUtil.getHostIp() , Integer.parseInt(port));
            if(!socket.isConnected()) {
                return false;
            }
        } catch (Exception e) {
            logger.info("检查本机某个端口出错",e);
            return false;
        }finally {
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.info("关闭socket出错",e);
                }
            }
        }
        return true;
    }
}
