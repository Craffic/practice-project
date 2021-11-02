#1. 整合Thymeleaf
<ol>
    <li>添加以来</li>
    <li>配置thymeleaf</li>
    <li>配置控制器</li>
    <li>创建BookAction控制层类</li>
    <li>创建books.html页面</li>
    
</ol>


----------------------------------------------------------------
#1. springboot整合Druid+Mybatis
###1.1 添加依赖
###1.2 添加配置文件jdbc.properties、mybatis-config.xml、spring-context-druid.xml、spring-context-mybatis.xml
###1.3 启动主类添加扫描mapper路径下的配置：@MapperScan("com.craffic.practice.dao")
###1.4 application.properties配置Druid连接数据库的信息
###1.5 application.properties配置Mybatis的mapper的location配置
###1.6 编写controller、service、dao、mapper层


# springboot整合redis
### 1. 添加依赖
### 2. 配置redis信息
### 3. 创建实体类，并序列化对象
### 4. 注入RedisTemplate和StringRedisTemplate模板
### 5. 保存数据到redis里