### 搭建最基本的spring security框架
1. 加入starter-security和starter-web依赖
2. 启动项目，访问接口会自动跳转到security登录页面
3. 使用springboot项目启动日志中生成的密码，用户为user登录

### 基于配置文件配置用户名密码角色
1. 在配置文件添加如下配置：
2. 重新启动项目，即可使用配置文件里的用户密码登录

## 基于内存的用户密码认证
1. 编写SecurityConfig类，继承WebSecurityConfigurerApapter进行自定义配置
2. 提供密码encoder
3. 重写configure(AuthenticationManagerBuilder)
4. 在方法里定义用内存方法定义用户密码角色
5. 重启系统，用内存里的用户登录

### HttpSecurity
1. 虽然可以实现认证功能，但是受保护的资源也是默认的，且不能根据实际情况进行角色管理
2. 如要实现自定义角色管理，就需要重写另一个configure(HttpSecurity)方法
3. 在方法里调用authorizeRequest()方法来使HttpSecuriy的配置
4. 在方法里可以定义哪些接口允许哪些角色登录
5. 登录表单详细配置
   1. 设置登录页面
   2. 设置登录接口
   3. 设置登录成功回调方法和登录失败回调方法，并返回json信息
   4. 测试：POST http://localhost:8081/login?userName=admin&passwd=1234


### 动态权限配置
1. 增加资源表和资源-角色表
2. 自定义FilterInvocationSecurityMetadataSource
3. 自定义AccessDecisionManager
4. dao层和Mapper层
5. SecurityConfig配置


### oauth
基于security分支
1. 添加依赖
2. 配置Redis信息
3. 配置授权服务器
4. 配置资源服务器
5. 配置securityConfig
6. 添加controller
7. 获取token
8. 拿着token访问资源接口
9. 刷新token


