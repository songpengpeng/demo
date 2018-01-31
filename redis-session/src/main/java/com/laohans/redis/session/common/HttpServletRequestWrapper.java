package com.laohans.redis.session.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author laohans 2018-01-31 10:23
 */
public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper {
    private SessionRepository sessionRepository;

    private HttpSession session;

    private String sid = "";

    public HttpServletRequestWrapper(String sid, SessionRepository sessionRepository, HttpServletRequest request) {
        super(request);
        this.sid = sid;
        this.sessionRepository = sessionRepository;
        if (this.session == null) {
            this.session = new HttpSessionWrapper(sid, super.getSession(false), sessionRepository);
        }
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (this.session == null) {
            if (create) {
                this.session = new HttpSessionWrapper(this.sid, super.getSession(true), sessionRepository);
                return this.session;
            } else {
                return null;
            }
        }
        return this.session;
    }

    @Override
    public HttpSession getSession() {
        if (this.session == null) {
            this.session = new HttpSessionWrapper(this.sid, super.getSession(), sessionRepository);
        }
        return this.session;
    }
}
