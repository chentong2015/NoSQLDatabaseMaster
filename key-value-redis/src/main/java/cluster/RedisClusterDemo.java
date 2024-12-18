package cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class RedisClusterDemo {

    // 测试Redis Cluster集群
    private static void testRedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8001));
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8002));
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8003));
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8004));
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8005));
        jedisClusterNodes.add(new HostAndPort("8.209.74.47", 8006));
        // Jedis连接池的基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);        // 设置最大空闲
        config.setTestOnBorrow(true); // 设置借用测试

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 5000, 10, config);
        jedisCluster.set("firstname", "chen");
        jedisCluster.set("age", "27");
        System.out.println(jedisCluster.get("firstname"));
        System.out.println(jedisCluster.get("age"));
        jedisCluster.close();
    }
}
