### springboot整合websocket广播消息功能
1. 添加依赖
2. 配置WebsocketConfig:
    2.1 实现WebSocketMessageBrokerConfigurer接口
    2.2 重写registerStompEndpoints()，设置和前端连接的端点(endPoint)，前缀为/chat
    2.3 重写configureMessageBroker，设置消息代理的前缀/topic，和接收客户端消息的前缀/app
3. 添加controller
    3.1 设置MessageMapping注解
    3.2 设置SendTo注解
4. 编写前端聊天页面
5. 测试
6. todo
    6.1 第二种方法实现websocket广播消息
    6.2 用定时器实现服务器定时向客户端发送消息

------------------------------------------------------------------------------------------------------------------------
### Springbooth整合webSocket点对点聊天
1. 使用Springboot提供好的SimpMessagingTemplate发送消息到broker
2. 既然点对点聊天就有了用户的概念，就应该添加security的依赖
3. 配置SecurityConfig配置类
4. 改造webSocketConfig：在enableSimpleBroker中增加一个broker前缀/queue，方便对群发消息和点对点消息进行管理
5. 配置controller
   1. 群发继续使用SendTo注解来发送到broker
   2. 点对点使用SimpMessagingTemplate来实现
   3. @MessageMapper("/chat")，表示来自/app/chat路径的消息将被chat方法处理
   4. chat方法有两个参数
      1. principle：用户的登录信息
      2. Chat：用户发送过来的消息
   5. 创建聊天页面
   6. 测试