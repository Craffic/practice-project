package com.craffic.practice.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:/application.properties")
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String cluster;
    @Value("${spring.redis.cluster.poolConfig.max-total}")
    private int maxActive;
    @Value("${spring.redis.cluster.poolConfig.max-wait-millis}")
    private int maxWait;
    @Value("${spring.redis.cluster.poolConfig.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.cluster.poolConfig.min-idle}")
    private int minIdle;

    JedisPoolConfig poolConfig;
    /**
     * 获取配置上的redis节点配置，并生成RedisNode List
     */
    private List<RedisNode> getRedisNodeList() throws Exception {
        if (StringUtils.isNotEmpty(cluster)){
            List<RedisNode> redisNodeList = new ArrayList<>();

            String[] addresses = cluster.split(",");
            for (String address : addresses) {
                String[] addrAndHost = address.split(":");
                String host = addrAndHost[0];
                Integer port = Integer.valueOf(addrAndHost[1]);
                redisNodeList.add(new RedisNode(host, port));
            }
            return redisNodeList;
        } else {
            throw new Exception("redis节点配置为空，请检查配置是否正确！");
        }
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        try {
            List<RedisNode> redisNodes = getRedisNodeList();
            redisClusterConfiguration.setPassword(RedisPassword.of("Craffic"));
            redisClusterConfiguration.setClusterNodes(redisNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redisClusterConfiguration;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        RedisClusterConfiguration redisClusterConfiguration = redisClusterConfiguration();
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig());
        return factory;
    }

    @Bean
    JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    @Bean
    RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        JedisConnectionFactory jedisConnectionFactory = jedisConnectionFactory();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        return stringRedisTemplate;
    }
}
