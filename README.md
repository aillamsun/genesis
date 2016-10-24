## Genesis. Is a Spring Cloud Project
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
| genesis-server-config               | 无 | 服务配置中心(更新中...)            | 无            |
| genesis-server-gateway               | 无 | 服务网关(更新中...)    
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
| genesis-demo-order-service              | 8082 | Order服务提供者              | 无            |

## 架构图(目前待完善)
![cmd-markdown-logo](https://www.zybuluo.com/static/img/logo.png)


## 服务中心HA说明(目前待完善)

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
## API 文档访问
> * 启动API genesis-demo 访问http://localhost:8080/swagger-ui.html
![api-demo](http://v1.freep.cn/3tb_161024171010fguc512293.png)
## 分布式事务测试(暂时没开发，后续更新...)

