package com.yyyu.ssh.bean;

import com.yyyu.ssh.upload.BaseUploadBean;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/12/5.
 */
public class UploadBean extends BaseUploadBean{

    private float x;
    private float y;
    private float x2;
    private float y2;
    private float boundx;
    private float boundy;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getBoundx() {
        return boundx;
    }

    public void setBoundx(float boundx) {
        this.boundx = boundx;
    }

    public float getBoundy() {
        return boundy;
    }

    public void setBoundy(float boundy) {
        this.boundy = boundy;
    }
}
