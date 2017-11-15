package com.yyyu.ssh.dao.inter;

import com.yyyu.ssh.domain.SysUserToken;
import com.yyyu.ssh.templete.inter.IBaseDao;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public interface IUserTokenDao extends IBaseDao<SysUserToken>{

    SysUserToken getTokenByUserId(Long userId);

}
