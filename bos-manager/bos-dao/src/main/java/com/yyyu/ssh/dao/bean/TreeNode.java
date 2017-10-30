package com.yyyu.ssh.dao.bean;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/10/30.
 */
public class TreeNode {

    private  String id;
    private String pId;
    private String name;
    private boolean checked;
    private boolean open;

    public TreeNode() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
