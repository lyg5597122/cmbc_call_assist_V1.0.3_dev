## 系统架构 ##
![系统架构图](http://192.168.1.5/mediawiki/images/4/46/%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

## 技术架构 ##
![整体技术架构](http://192.168.1.5/mediawiki/images/e/e4/%E6%95%B4%E4%BD%93%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1.png)

## 技术选型 ##
后端使用Spring Cloud技术体系

Java基于1.8版本

Spring Cloud 选择Edgware

Spring Boot 选择1.5.17

前端框架使用Vue 2.0, UI库使用Element 2.0


## 代码仓库 ##
http://192.168.1.68/si-talk/si-talk

http://192.168.1.68/si-talk/si-talk-web

## Maven 私服 ##
http://192.168.1.187:8081/
本地maven配置私服参考config/setting.xml

## 日志规范 ##
路径: /home/apps/logs/服务/子服务/类型_描述[日期].log

## 配置规范 ##
路径：/home/apps/configs/服务/子服务/application.yml

## 错误码规范 ##
1. 格式：0000000
2. 从左到右错误码含义：00(主服务标识，范围00-99) 00(子服务标识，范围00-99) 000(错误码,范围000-999,000表示成功)
3. 00 公共组件
4. 01 用户中心
5. 02 调度中心
6. 03 呼叫中心
7. 04 机器人能力中心
8. 05 AI能力中心
9. 06 运营中心
10. 07 影像服务 

## 需要提前安装的软件 ##
1. Docker
2. Git
3. JDk1.8
4. FastDFS
5. Mysql5.7版本，并配置主从
6. FreeSwitch
7. Redis5.0