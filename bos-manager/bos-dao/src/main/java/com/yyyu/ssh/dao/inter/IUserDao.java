package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.templete.inter.IBaseDao;

import java.util.List;

/**
 * 功能：User Dao 接口
 *
 * @author yu
 * @date 2017/8/30.
 */
public interface IUserDao extends IBaseDao<User>{

    User  getUserByUsernameAndPwd(String username , String password);

    Integer getUsersTotal();

    List<User> getUserListByPage(Integer page , Integer size);

}
