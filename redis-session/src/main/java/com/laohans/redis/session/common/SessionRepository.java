package com.laohans.redis.session.common;

import java.util.Map;

/**
 * @author laohans 2018-01-31 10:29
 */
public interface SessionRepository {
    Map<String, Object> getSession(String sid);

    void saveSession(String sid, Map<String, Object> session);

    void removeSession(String sid);
}
