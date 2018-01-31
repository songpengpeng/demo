package com.laohans.redis.session.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author laohans 2018-01-31 10:28
 */
public class HttpSessionWrapper implements HttpSession {
    private String sid = "";

    private HttpSession session;

    private Map<String, Object> map = null;

    private SessionRepository sessionRepository;

    public HttpSessionWrapper() {
    }

    public HttpSessionWrapper(HttpSession session) {
        this.session = session;
    }

    public HttpSessionWrapper(String sid, HttpSession session) {
        this(session);
        this.sid = sid;
    }

    public HttpSessionWrapper(String sid, HttpSession session, SessionRepository sessionRepository) {
        this(sid, session);
        this.sessionRepository = sessionRepository;
    }

    private Map<String, Object> getSessionMap() {
        if (this.map == null) {
            this.map = sessionRepository.getSession(this.sid);
        }
        return this.map;
    }

    @Override
    public Object getAttribute(String name) {
        if (this.getSessionMap() != null) {
            Object value = this.getSessionMap().get(name);
            return value;
        }
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        this.getSessionMap().put(name, value);
        sessionRepository.saveSession(this.sid, this.getSessionMap());
    }

    @Override
    public void invalidate() {
        this.getSessionMap().clear();
        sessionRepository.removeSession(this.sid);
    }

    @Override
    public void removeAttribute(String name) {
        this.getSessionMap().remove(name);
        sessionRepository.saveSession(this.sid, this.getSessionMap());
    }

    @Override
    public Object getValue(String name) {
        return this.session.getValue(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(this.getSessionMap().keySet());
    }

    @Override
    public String[] getValueNames() {
        return this.session.getValueNames();
    }

    @Override
    public void putValue(String name, Object value) {
        this.session.putValue(name, value);
    }

    @Override
    public void removeValue(String name) {
        this.session.removeValue(name);
    }

    @Override
    public long getCreationTime() {
        return this.session.getCreationTime();
    }

    @Override
    public String getId() {
        return this.sid;
    }

    @Override
    public long getLastAccessedTime() {
        return this.session.getLastAccessedTime();
    }

    @Override
    public ServletContext getServletContext() {
        return this.session.getServletContext();
    }


    public void setMaxInactiveInterval(int interval) {
        this.session.setMaxInactiveInterval(interval);
    }


    public int getMaxInactiveInterval() {
        return this.session.getMaxInactiveInterval();
    }


    public HttpSessionContext getSessionContext() {
        return this.session.getSessionContext();
    }


    public boolean isNew() {
        return this.session.isNew();
    }
}
