## Genesis. Is a Spring Cloud Project

Genesis 中文意思: 起源, 哈哈哈哈......
------
## 技术架构
genesis 是一个基于Spring cloud(Camden.SR1) Spring Boot(1.4.1.RELEASE) Mybatis(3.3.0) 通用Mapper 通用分页Pagehelper完成的一个基础组件架构，后面会持续更新...
## MAVEN模块说明
#### 1. 基础组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-common                | 无 | 公共模块（工具类,资源......）            | 无            |
| genesis-core               | 无 | 核心代码               | 无            |
| genesis-model               | 无 | 公共实体对象      
#### 2. Spring Cloud(genesis-microservices)组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-microservices-discovery               | 8761 | 服务注册中心            | 无            |
| genesis-microservices-discovery2              | 8762 | 服务注册中心2(用作和8761实现高可用注册中心)            | 无            |
| genesis-microservices-config               | 8040 | 服务配置中心服务          | 无            |
| genesis-microservices-config-client               | 8041 | 服务配置客户端测试启动访问(ip:port/message打印)            | 无            |
| genesis-microservices-gateway               | 8050 | 服务网关    | 无            |
| genesis-microservices-monitor               | 8030 | 服务监控(hystrix-dashboard)    | 无            |
| genesis-microservices-security               | 无 | security    | 无            | 
| genesis-microservices-sleuth               | 无 | Spring Cloud Sleuth是对Zipkin的一个封装(和Zipkin集成)    | 无            |
| genesis-microservices-zipkin               | 8090 |Zipkin 对Spring Cloud应用进行服务追踪分析(主要和Sleuth)    | 无            |
| genesis-microservices-bus-kafka               | 无 |bus-kafka    | 无            |
| genesis-microservices-bus-amqp                | 无 |bus-amqp    | 无            |
#### 3. Spring(genessis-spring)扩展组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-spring-extends                | 无 | Spring 扩展(更新中...)            | 无            |
| genesis-spring-plugins              | 无 | Spring 插件(更新中...)               | 无            |
#### 4. Examples(genesis-examples) 提供真是服务使用
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-provider                | 8080 | API接口            | 无            |
| genesis-provider-goods              | 8081 | Goods服务提供者              | 无            |
| genesis-provider-goods2              | 8082 | Goods服务提供者(用于启动测试 API goods模块Feign Client负载均衡)              | 无            2z
| genesis-provider-order              | 8083 | Order服务提供者              | 无            |

## 架构图(目前待完善)

后续会更新架构图出去，暂时先这样看着... 焦灼中..........

![Markdown](http://i1.piimg.com/1949/9c5d405775f32e78.png)

## 服务中心HA说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-microservices-discovery               | 8761 | 服务注册中心            | 无            |
| genesis-microservices-discovery2              | 8762 | 服务注册中心2(用作和8761实现高可用注册中心)            | 无            |

> * 1,（C:\Windows\System32\drivers\etc\hosts文件）
```java
127.0.0.1 discovery1
127.0.0.1 discovery2
```
	

> * 2,每个配置里面都有一个application.properties，本机为了方便在idea工具启动  所以使用了两个项目

> * 3,以后线上可以使用一个工程即可 如下：



#### application-discovery1.properties
```java
spring.application.name=eureka-server-clustered
server.port=8761
eureka.instance.hostname=discovery1
eureka.client.serviceUrl.defaultZone=http://discovery2:8762/eureka/
```

#### application-discovery2.properties
```java
spring.application.name=eureka-server-clustered
server.port=8762
eureka.instance.hostname=discovery2
eureka.client.serviceUrl.defaultZone=http://discovery1:8761/eureka/
```
### 命令启动格式1：
```java
java -jar discovery1-1.0.0.jar  --spring.profiles.active=discovery1
java -jar discovery2-1.0.0.jar --spring.profiles.active=discovery2
```
### 命令启动格式2：

#### 添加文件 application.properties
```java
spring.profiles.active=discovery1
或者
spring.profiles.active=discovery2
```
命令修改为：
```java
java -jar discovery1-1.0.0.jar
```
	
### 效果图:

### [访问discovery1](http://discovery1:8761)
![discovery1](http://p1.bqimg.com/1949/742e4ba190751504.png)
### [访问discovery2](http://discovery2:8762)
![discovery2](http://p1.bqimg.com/1949/4a6ce06d3452ae63.png)
	

## 熔断监控视图(目前待完善)

## 使用说明
#### 1,项目启动：
> * 数据库脚本 genesis-provider resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-microservices-discovery 和 genesis-microservices-discovery2
> * 启动API genesis-provider 访问http://localhost:8080/swagger-ui.html
> * 分别启动 两个服务提供者 genesis-provider-*
> * 通过API文档Try 就可以了

#### 3, 服务注册展示：
![Markdown](http://i1.piimg.com/1949/fb0fc9336867151c.png)


## API 文档访问
> * 启动API genesis-demo 访问http://localhost:8080/swagger-ui.html
![api-demo](http://p1.bqimg.com/1949/6721d590be673013.png)

## 分布式事务测试(暂时没开发，后续更新...)

