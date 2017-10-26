package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.templete.inter.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 功能：User Dao 接口
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserDao extends IBaseDao<User>{

    User getUserByUsername(String username);

    User  getUserByUsernameAndPwd(String username , String password);

    Integer getUsersTotal();

    List<User> getUserListByPage(DetachedCriteria criteria , Integer page , Integer size);

}
