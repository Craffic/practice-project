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

----------------------------------------------------------------
# springboot整合redis
### 1. 添加依赖
### 2. 配置redis信息
### 3. 创建实体类，并序列化对象
### 4. 注入RedisTemplate和StringRedisTemplate模板
### 5. 保存数据到redis里

----------------------------------------------------------------
# springboot整合RedisCluster
### 1.  添加依赖
### 2. 配置redisCluster信息
### 3. 创建RedisConfig配置信息，返回redisTemplate和StringRedisTemplate实例
### 4. 应用和单体redis一致

----------------------------------------------------------------
# springboot整合MongoDB和MongoTemplate
1. 添加依赖
2. 配置MongoDB配置信息
3. 编写BookDao实体类Dao
4. 测试并在MongoDB中查看插入的数据
5. 直接使用MongoTemplate操作MongoDB

----------------------------------------------------------------
feature_20111111_1_redisCache
# Redis单机缓存
1. 添加依赖spring-boot-starter-cache和Redis依赖
2. 在application.properties中加入cache的配置
3. 在启动主类上添加@EnableCaching注解，开启缓存
4. 测试两次查询之间有删除或者修改数据的情况
