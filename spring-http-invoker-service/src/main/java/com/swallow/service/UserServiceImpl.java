package com.swallow.service;

import com.swallow.rpc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 老汉憨憨 2017-06-23 01:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getName() {
        return "老汉憨憨";
    }
}
