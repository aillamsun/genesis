## Genesis. Is a Spring Cloud Project

Genesis 中文意思: 起源, 哈哈哈哈......
------
## 技术架构
genesis 是一个基于Spring cloud(Camden.RELEASE) Spring Boot(1.4.1.RELEASE) Mybatis(3.3.0) 通用Mapper 通用分页Pagehelper完成的一个基础组件架构，后面会持续更新...
## MAVEN模块说明
#### 1. 基础组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-common                | 无 | 公共模块（工具类,资源......）            | 无            |
| genesis-core               | 无 | 核心代码               | 无            |
| genesis-model               | 无 | 公共实体对象      
#### 2. Spring Cloud(genesis-server)组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-server-discovery               | 8761 | 服务注册中心            | 无            |
| genesis-server-discovery2              | 8762 | 服务注册中心2(用作和8761实现高可用注册中心)            | 无            |
| genesis-server-config               | 无 | 服务配置中心(更新中...)            | 无            |
| genesis-server-gateway               | 8050 | 服务网关    | 无            |
| genesis-server-monitor               | 8030 | 服务监控(hystrix-dashboard)    | 无            |
#### 3. Spring(genessis-spring)扩展组件说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-spring-extends                | 无 | Spring 扩展(更新中...)            | 无            |
| genesis-spring-plugins              | 无 | Spring 插件(更新中...)               | 无            |
#### 4. Demo 提供组件使用说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-demo                | 8080 | API接口            | 无            |
| genesis-demo-goods-service              | 8081 | Goods服务提供者              | 无            |
| genesis-demo-goods-service2              | 8083 | Goods服务提供者(用于启动测试 API goods模块Feign Client负载均衡)              | 无            |
| genesis-demo-order-service              | 8082 | Order服务提供者              | 无            |

## 架构图(目前待完善)
![cmd-markdown-logo](https://www.zybuluo.com/static/img/logo.png)


## 服务中心HA说明
| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------- | --------------- |
| genesis-server-discovery               | 8761 | 服务注册中心            | 无            |
| genesis-server-discovery2              | 8762 | 服务注册中心2(用作和8761实现高可用注册中心)            | 无            |

> * 1,（C:\Windows\System32\drivers\etc\hosts文件）
```java
127.0.0.1 discovery1
127.0.0.1 discovery2
```
	

> * 2,每个配置里面都有一个application.properties，本机为了方便在idea工具启动  所以使用了两个项目

> * 3,以后线上可以使用一个工程即可 如下：

application-discovery1.properties
```java
spring.application.name=eureka-server-clustered
server.port=8761
eureka.instance.hostname=discovery1
eureka.client.serviceUrl.defaultZone=http://discovery2:8762/eureka/
```

application-discovery2.properties
```java
spring.application.name=eureka-server-clustered
server.port=8762
eureka.instance.hostname=discovery2
eureka.client.serviceUrl.defaultZone=http://discovery1:8761/eureka/
```
	启动格式：
```java
java -jar discovery1-1.0.0.jar  --spring.profiles.active=discovery1
java -jar discovery2-1.0.0.jar --spring.profiles.active=discovery2
```
	
### 效果图:

### [访问discovery1](http://discovery1:8761)
![discovery1](http://p1.bqimg.com/1949/742e4ba190751504.png)
### [访问discovery2](http://discovery2:8762)
![discovery2](http://p1.bqimg.com/1949/4a6ce06d3452ae63.png)
	

## 熔断监控视图(目前待完善)

## 使用说明
#### 1,主机名配置：
| 主机名配置（C:\Windows\System32\drivers\etc\hosts文件） |
| ---------------------------------------- |
| 127.0.0.1 discovery config-server gateway movie user feign ribbon |


#### 2,项目启动：
> * 数据库脚本 genesis-demo resources/db/下面spring-cloud-test.sql
> * 首先启动：genesis-server-discovery 注册中心,访问:ip:8761
> * 启动API genesis-demo 访问http://localhost:8080/swagger-ui.html
> * 分别启动 两个服务提供者
> * 通过API文档Try 就可以了

#### 3, 服务注册展示：
![server-demo](http://p1.bqimg.com/1949/cbb9020eee0a8c69.png)


## API 文档访问
> * 启动API genesis-demo 访问http://localhost:8080/swagger-ui.html
![api-demo](http://p1.bqimg.com/1949/6721d590be673013.png)

## 分布式事务测试(暂时没开发，后续更新...)

