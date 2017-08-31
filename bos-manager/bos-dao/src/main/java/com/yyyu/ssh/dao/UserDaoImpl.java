package com.yyyu.ssh.dao;

import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 功能：用户相关Dao操作
 *
 * @author yu
 * @date 2017/8/30.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
}
