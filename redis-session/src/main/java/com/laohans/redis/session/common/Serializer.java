package com.laohans.redis.session.common;

/**
 * @author laohans 2018-01-31 10:43
 */
public interface Serializer {
    public Object deserialize(byte[] data);

    public byte[] serialize(Object object);
}
