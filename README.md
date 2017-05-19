## Genesis. Is a Spring Cloud Project

------
## 技术架构
genesis 是一个基于Spring cloud(Camden.SR1) Spring Boot(1.4.1.RELEASE) Mybatis(3.3.0) 通用Mapper 通用分页Pagehelper完成的一个基础组件架构，
使用Spring Cloud Eureka、Feign、Zuul、Spring config、Zipkin、Sleuth等组件后面会持续更新...
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
| genesis-microservices-discovery               | 8761 | 服务注册中心(用作和8762 8763实现高可用注册中心)            | 无            |
| genesis-microservices-discovery2              | 8762 | 服务注册中心2(用作和8761 8763实现高可用注册中心)            | 无            |
| genesis-microservices-discovery3              | 8763 | 服务注册中心3(用作和8761 8762实现高可用注册中心)            | 无            |
| genesis-microservices-config               | 8040 | 服务配置中心服务          | 无            |
| genesis-microservices-config-client               | 8041 | 服务配置客户端测试启动访问(ip:port/message打印)            | 无            |
| genesis-microservices-gateway               | 8050 | 服务网关    | 无            |
| genesis-microservices-hystrix-dashboard               | 8051 | 服务监控(Hystrix Dashboard)    | 无            |
| genesis-microservices-hystrix-turbine               | 8052 |  服务监控(Hystrix  Turbine)  | 无            |
| genesis-microservices-monitor               | 8060 | 服务监控(spring boot admin)    | 无            |
| genesis-microservices-security               | 无 | security    | 无            | 
| genesis-microservices-sleuth               | 8092 | 提供测试Zipkin 服务 提供本地、远程调用API    | 无            |
| genesis-microservices-zipkin               | 8091 |Zipkin Server 对Spring Cloud应用进行服务追踪分析(主要和Sleuth)    | 无            |
| genesis-microservices-bus-kafka               | 无 |bus-kafka    | 无            |
| genesis-microservices-bus-amqp                | 无 |bus-amqp    | 无            |



#### 3. Spring(genessis-spring)扩展组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-spring-extends                | 无 | Spring 扩展(更新中...)            | 无            |
| genesis-spring-plugins              | 无 | Spring 插件(更新中...)               | 无            |
| genesis-spring-plugins-mybatis             | 无 | Spring boot mybatis stater自定义(在genesis-provider-goods使用测试)             | 无            |
#### 4. Examples(genesis-examples) 提供真是服务使用
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-common-config                | 无 | 通用配置            | 无            |
| genesis-provider-by-feign                | 8080 | API接口(使用Feign 负载均衡)            | 无            |
| genesis-provider-by-ribbon                | 8084 | API接口(使用 Ribbon 负载均衡)            | 无            |
| genesis-provider-by-zuul                | 8051 | API接口网关(使用Zuul)            | 无            |
| genesis-provider-goods              | 8081 | Goods服务提供者(此服务使用了genesis-spring-plugins-mybatis stater)              | 无            |
| genesis-provider-goods2              | 8082 | Goods服务提供者(用于启动测试 API goods模块Feign Client负载均衡)              | 无            
| genesis-provider-order              | 8083 | Order服务提供者              | 无            |
| genesis-sleuth-zipkin-demo              | 8093 | sleuth-zipkin-demo 接口              | 无            |

## 架构图(目前待完善)

后续会更新架构图出去，暂时先这样看着... 焦灼中..........

![Markdown](http://p1.bqimg.com/1949/744b75531ed0a198.png)

## 服务中心HA说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-microservices-discovery               | 8761 | 服务注册中心(用作和8762 8763实现高可用注册中心)            | 无            |
| genesis-microservices-discovery2              | 8762 | 服务注册中心2(用作和8761 8763实现高可用注册中心)            | 无            |
| genesis-microservices-discovery3              | 8763 | 服务注册中心3(用作和8761 8762实现高可用注册中心)            | 无            |

> * 1,（C:\Windows\System32\drivers\etc\hosts文件）
```java
127.0.0.1 discovery1
127.0.0.1 discovery2
127.0.0.1 discovery3
```
	

> * 2,每个配置里面都有一个application.properties，本机为了方便在idea工具启动  所以使用了两个项目

> * 3,以后线上可以使用一个工程即可 如下：



#### application-discovery1.properties
```java
spring.application.name=eureka-server-clustered
server.port=8761
eureka.instance.hostname=discovery1
eureka.client.serviceUrl.defaultZone=http://discovery2:8762/eureka/,http://discovery3:8763/eureka/
```

#### application-discovery2.properties
```java
spring.application.name=eureka-server-clustered
server.port=8762
eureka.instance.hostname=discovery2
eureka.client.serviceUrl.defaultZone=http://discovery1:8761/eureka/,http://discovery3:8763/eureka/
```

#### application-discovery3.properties
```java
spring.application.name=eureka-server-clustered
server.port=8763
eureka.instance.hostname=discovery3
eureka.client.serviceUrl.defaultZone=http://discovery1:8761/eureka/,http://discovery2:8762/eureka/
```

### 命令启动格式1：
```java
java -jar discovery1-1.0.0.jar  --spring.profiles.active=discovery1
java -jar discovery2-1.0.0.jar --spring.profiles.active=discovery2
java -jar discovery3-1.0.0.jar --spring.profiles.active=discovery3
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
![discovery1](http://p1.bpimg.com/1949/1f7f7b2967a8efd3.png)
### [访问discovery2](http://discovery2:8762)
![discovery2](http://p1.bpimg.com/1949/ecacb2a6b1ddbc12.png)
### [访问discovery3](http://discovery3:8763)
![discovery3](http://p1.bpimg.com/1949/8d08a1bd40c7ea48.png)
	

## 监控视图测试

### 使用说明：


#### 1,Spring boot Admin 监控
> * 数据库脚本 genesis-common-config resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-microservices-monitor 端口 8060
> * 启动 genesis-provider-goods 端口 8081

访问 http://localhost:8060 admin UI

访问 http://localhost:8081/goods


#### 1.2,效果图
-----
#### 2,Hystrix-dashboard 监控
> * 数据库脚本 genesis-common-config resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-microservices-discovery 和 genesis-microservices-discovery2、genesis-microservices-discovery3
> * 启动genesis-microservices-hystrix-dashboard 端口 8051
> * 启动genesis-provider-by-feign 端口8080

访问 http://localhost:8051  在地址栏输入:http://localhost:8080/hystrix.stream


#### 2.2,效果图

-----
#### 3,Hystrix Turbine 监控
> * 数据库脚本 genesis-common-config resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-microservices-discovery 和 genesis-microservices-discovery2、genesis-microservices-discovery3
> * 启动genesis-microservices-hystrix-turbine 端口 8052
> * 分别启动 genesis-provider-goods 端口8081 、genesis-provider-order 端口 8083

访问http://localhost:8052

#### 3.2,效果图

## 服务跟踪监控Zipkin、Sleuth 测试

### 使用说明：

#### 1,项目启动
> * 启动 Zipkin Server 服务 genesis-microservices-zipkin 端口 8091
> * 启动 Zipkin Server 服务demo  genesis-microservices-sleuth 端口 8092
> * 启动测试 Zipkin、Sleuth 服务提供者  genesis-sleuth-zipkin-demo 端口 8093
> * 直接调用 8092 Controller接口即可

#### 2,跟踪列表效果图

![跟踪列表](http://i1.piimg.com/1949/09934fc78dda3bf6.png)

#### 2,跟踪详细信息效果图

![跟踪详细信息](http://i1.piimg.com/1949/e11be74f0f7615e6.png)

## Zuul 和 Feign 测试

### 使用说明：

#### 1,项目启动：
> * 数据库脚本 genesis-common-config resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-microservices-discovery 和 genesis-microservices-discovery2、genesis-microservices-discovery3
> * 测试Fegin可以启动genesis-provider-by-feign。前提启动genesis-provider-good、genesis-provider-order
> * 测试Zuul可以启动genesis-provider-by-zuul 。前提启动genesis-provider-good、genesis-provider-order
> * genesis-provider-by-feign提供swgger UI 通过API文档Try 就可以了

#### 2, 服务注册展示：
![Markdown](http://i1.piimg.com/1949/fb0fc9336867151c.png)


## API 文档访问测试

### 使用说明：

#### 1,项目启动：
> * 启动API genesis-provider-by-feign访问http://localhost:8080/swagger-ui.html
![api-demo](http://p1.bqimg.com/1949/6721d590be673013.png)


## 分布式事务测试(暂时没开发，后续更新...)

