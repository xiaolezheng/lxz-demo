# spring-jersey模板工程

## 构建命令 
    mvn clean install
## 支持多环境部署:
    jvm启动增加参数-Dconfig.env=prod
## 不同环境配置文件位置(spring方式加载): 
    lxz-service/src/main/resources/META-INF
## 公共配置文件位置(spring方式加载)
    lxz-service/src/main/resources/META-INF/public.properties
## 支持jetty插件直接启动