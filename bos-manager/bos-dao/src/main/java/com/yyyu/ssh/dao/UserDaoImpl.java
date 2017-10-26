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
    public User getUserByUsername(String username) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username" , username) );
        List<User> userList = getPageList(criteria,0, 1);
        return fetchOne(userList);
    }

    @Override
    public User getUserByUsernameAndPwd(String username , String password) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("username" , username) );
        criteria.add(Restrictions.eq("password" , password) );
        List<User> userList = getPageList(criteria,0, 1);
        return fetchOne(userList);
    }

    private User fetchOne(List<User> userList){
        if (userList!=null && userList.size()>0){
            User user = userList.get(0);
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

    @Override
    public List<String> getUserRoleName(String username) {
        StringBuilder sb = new StringBuilder();
        sb.append("select auth_role.name ");
        sb.append("from t_user , user_role , auth_role ");
        sb.append("where t_user.id = user_role.user_id and user_role.role_id=auth_role.id and t_user.username =?");
        String sql = sb.toString();
        return  getPageListBySql(sql, new String[]{username}, 0, Integer.MAX_VALUE);
    }
}
