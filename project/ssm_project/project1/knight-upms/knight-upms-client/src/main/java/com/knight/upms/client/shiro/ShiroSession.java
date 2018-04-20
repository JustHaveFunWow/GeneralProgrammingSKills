package com.knight.upms.client.shiro;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;

public class ShiroSession extends SimpleSession implements Serializable {
    public static enum OnlineStatus {
        on_line("在线"), off_line("离线"), force_logout("强制退出");
        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


    // 在线状态
    private OnlineStatus status = OnlineStatus.off_line;
    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }
    public ShiroSession(){
        super();
    }

    public ShiroSession(String Host){
        super(Host);
    }


    @Override
    protected void expire(){
        this.stop();
        this.setExpired(true);
    }



}
