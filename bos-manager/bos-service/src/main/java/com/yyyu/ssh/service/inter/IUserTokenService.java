package com.yyyu.ssh.service.inter;

import com.yyyu.ssh.domain.SysUserToken;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/15.
 */
public interface IUserTokenService {

    void save(SysUserToken sysUserToken);

    void saveOrUpdate(SysUserToken sysUserToken);

    SysUserToken getUserTokenByUserId(Long userId);

}
