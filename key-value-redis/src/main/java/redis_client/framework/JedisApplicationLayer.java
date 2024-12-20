package redis_client.framework;

import java.io.IOException;

public class JedisApplicationLayer {

    private CustomJedisConnection connectionLayer;

    public JedisApplicationLayer(String host, short port) {

        connectionLayer = new CustomJedisConnection(host, port);
    }

    public String set(String key, String value) throws IOException {
        connectionLayer.sendCommand(RedisCommand.SET, key, value);
        return "OK";
    }

    public String get(String key) throws IOException {
        connectionLayer.sendCommand(RedisCommand.GET, key);
        return connectionLayer.getReply();
    }
}
