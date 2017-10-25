package com.yyyu.ssh.dao;

import com.yyyu.ssh.dao.inter.IUserDao;
import com.yyyu.ssh.domain.User;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：用户相关Dao操作
 *
 * @author yu
 * @date 2017/8/30.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
    @Override
    public User getUserByUsernameAndPwd(String username , String password) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username" , username) );
        criteria.add(Restrictions.eq("password" , password) );
        List<User> userList = getPageList(criteria,0, 1);
        if (userList!=null && userList.size()>0){
            User user = userList.get(0);
            evict(user);
            user.setPassword("");
            return user;
        }else{
            return null;
        }
    }

    @Override
    public Integer getUsersTotal() {
        return getTotalCount(getCriteria());
    }

    @Override
    public List<User> getUserListByPage(DetachedCriteria criteria , Integer start, Integer length) {
        return getPageList(criteria , start , length);
    }
}
