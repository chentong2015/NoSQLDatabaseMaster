import redis.clients.jedis.*;

public class RedisClientDemo {

    // 测试单体Redis Server服务的操作
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // jedis.auth("password");  使用配置文件中自定义的authentication password进行身份认证
        // jedis.auth("root", "requirepass");
        jedis.set("key1", "my key");
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    // 测试使用Jedis连接池访问单体Redis服务
    private static void testSingleRedisServerWithJedisPool() {
        JedisPool pool = new JedisPool("8.209.74.47", 6379);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("name"));
        jedis.close();
        pool.close();
    }
}
