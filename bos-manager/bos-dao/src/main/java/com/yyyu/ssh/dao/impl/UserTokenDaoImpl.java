package com.yyyu.ssh.dao.impl;

import com.yyyu.ssh.dao.inter.IUserTokenDao;
import com.yyyu.ssh.domain.SysUserToken;
import com.yyyu.ssh.templete.BaseDaoImpl;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
@Repository
public class UserTokenDaoImpl extends BaseDaoImpl<SysUserToken>implements IUserTokenDao {

    @Override
    public SysUserToken getTokenByUserId(Long userId) {
        DetachedCriteria criteria = getCriteria();
        criteria.add(Restrictions.eq("userId" , userId));
        List<SysUserToken> pageList = getPageList(criteria, 0, 1);
        if (pageList!=null && pageList.size()>0){
            return pageList.get(0);
        }
        return null;
    }
}
