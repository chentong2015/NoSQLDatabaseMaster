package client_jedis;

import client_jedis.framework.JedisApplicationLayer;

import java.io.IOException;

public class JedisCommunication {

    // 测试自定义实现的Redis Client客户端
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        short port = 6379;
        JedisApplicationLayer myJedis = new JedisApplicationLayer(host, port);
        myJedis.set("name", "new name");
        System.out.println(myJedis.get("name"));
    }
}
