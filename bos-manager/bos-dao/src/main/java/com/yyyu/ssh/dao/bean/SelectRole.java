package com.yyyu.ssh.dao.bean;

import com.yyyu.ssh.domain.SysRole;

/**
 * 功能：
 *
 * @author yyyu
 * @date 2017/11/4
 */
public class SelectRole extends SysRole{

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
