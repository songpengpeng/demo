package com.laohans.redis.session.common;

import com.google.common.collect.Maps;
import com.swallow.cache.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author laohans 2018-01-31 10:42
 */
@Service
public class RedisSessionRepository implements SessionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionRepository.class);

    private Serializer serializer = new JdkSerializer();

    @Autowired
    private RedisService redisService;

    /**
     * session 在redis 中的 key 前缀
     * key为  namespace+sessionid
     */
    private String namespace = "swallow";

    /**
     * 默认10 分钟没有请求 ,session 过期.应用可以自行设置
     * 注意同一个域名下应用一致
     */
    private long maxInternalActive = 5;

    @Override
    public Map<String, Object> getSession(String sid) {
        Map<String, Object> session = Maps.newHashMap();
        try {
            byte[] value = redisService.get((namespace + sid).getBytes());
            if (value != null) {
                Object obj = serializer.deserialize(value);
                session = (Map<String, Object>) obj;
            }
            redisService.expire((namespace + sid).getBytes(), maxInternalActive);
        } catch (Exception e) {
            LOGGER.error("getSession {} error : ", sid, e);
        }

        return session;
    }

    @Override
    public void saveSession(String sid, Map<String, Object> session) {
        try {
            redisService.set((namespace + sid).getBytes(), serializer.serialize(session));
            redisService.expire((namespace + sid).getBytes(), maxInternalActive);
        } catch (Exception e) {
            LOGGER.error("saveSession {} error : ", sid, e);
        }
    }

    @Override
    public void removeSession(String sid) {
        try {
            redisService.delete(namespace + sid);
        } catch (Exception e) {
            LOGGER.error("removeSession {} error:{}", sid, e);
        }
    }
}
