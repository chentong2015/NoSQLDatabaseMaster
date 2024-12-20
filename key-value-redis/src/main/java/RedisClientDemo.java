import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisClientDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // Set and Get String value
        jedis.set("key1", "my key");
        String value = jedis.get("key1");
        System.out.println(value);

        // Set and Get List values
        // jedis.rpush("mylist", "item1", "item2");
        List<String> values = jedis.lrange("myList", 0, -1);
        for (String item: values) {
            System.out.println(item);
        }

        // Set and Get Hash Key values
        Map<String, String> map = new HashMap<>();
        map.put("hash-key1", "hash-value1");
        map.put("hash-key2", "hash-value2");
        map.put("hash-key3", "hash-value3");
        jedis.hset("myHash", map);
        Map<String, String> mapAll = jedis.hgetAll("myHash");
        for (Map.Entry<String, String> entry: mapAll.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        jedis.close();
    }
}