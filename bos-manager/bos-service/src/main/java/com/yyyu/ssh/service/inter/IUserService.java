package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserService {

    DetachedCriteria getCriteria();
    User get(String userId);
    void save(User user);
    User getUserByUsername(String username);
    User getUserByUsernameAndPwd(String username , String password);
    Integer getUsersTotal();
    List<User> getUserByPage(DetachedCriteria criteria , Integer start , Integer length);
}
