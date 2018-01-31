package com.laohans.redis.session.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author laohans 2018-01-31 10:21
 */
@Service("commonFilter")
public class CommonFilter extends OncePerRequestFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonFilter.class);

    @Autowired
    private SessionRepository sessionRepository;

    public CommonFilter() {
        super();
        LOGGER.info("init session filter .... ");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie cookie = WebUtils.getCookie(request, "JSESSIONID");
        String sid = cookie != null ? cookie.getValue() : null;
        if (StringUtils.isEmpty(sid)) {
            sid = UUID.randomUUID().toString().replaceAll("-", "");
            addCookie(request.getServerName(), "/", -1, "JSESSIONID", sid);
        }

        filterChain.doFilter(new HttpServletRequestWrapper(sid, sessionRepository, request), response);
    }

    private void addCookie(String domain, String path, int maxAge, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
    }
}
