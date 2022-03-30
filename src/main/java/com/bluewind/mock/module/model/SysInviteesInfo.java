package com.bluewind.mock.module.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author liuxingyu01
 * @date 2022-03-30 18:01
 * @description 系统注册邀请记录表 实体类
 **/
public class SysInviteesInfo implements Serializable {
    private static final long serialVersionUID = 6461621369515194126L;

    String id;

    String invitationCode;

    String inviteUserId;

    String inviteesUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(String inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getInviteesUserId() {
        return inviteesUserId;
    }

    public void setInviteesUserId(String inviteesUserId) {
        this.inviteesUserId = inviteesUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysInviteesInfo{" +
                "id='" + id + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", inviteUserId='" + inviteUserId + '\'' +
                ", inviteesUserId='" + inviteesUserId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
