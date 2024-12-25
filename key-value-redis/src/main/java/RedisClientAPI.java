import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientAPI {

    // 测试单体Redis Server服务的操作
    // 使用配置文件中自定义authentication password进行身份认证
    // jedis.auth("password");
    // jedis.auth("root", "requirepass");
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.get("key1"));
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
