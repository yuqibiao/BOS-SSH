package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.User;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserService {

    User get(String userId);
    void save(User user);
}
