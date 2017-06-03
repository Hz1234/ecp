package com.donglicms.bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserBean implements Serializable {

	 /**
     * 登陆用户ID
     */
    private Long loginId;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 登陆密码
     */
    private String loginPass;

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 构造函数
     * @param loginId
     * @param loginName
     * @param loginPass
     * @param fullName
     * @param nickName
     * @param mobile
     * @param email
     * @param createTime
     */
    public UserBean(Long loginId, String loginName, String loginPass, String fullName, String nickName, String mobile, String email, Date createTime) {
		super();
		this.loginId = loginId;
		this.loginName = loginName;
		this.loginPass = loginPass;
		this.fullName = fullName;
		this.nickName = nickName;
		this.mobile = mobile;
		this.email = email;
		this.createTime = createTime;
	}

	/**
     * 获取登陆用户ID
     *
     * @return login_id - 登陆用户ID
     */
    public Long getLoginId() {
        return loginId;
    }

    /**
     * 设置登陆用户ID
     *
     * @param loginId 登陆用户ID
     */
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    /**
     * 获取登陆账号
     *
     * @return login_name - 登陆账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登陆账号
     *
     * @param loginName 登陆账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取登陆密码
     *
     * @return login_pass - 登陆密码
     */
    public String getLoginPass() {
        return loginPass;
    }

    /**
     * 设置登陆密码
     *
     * @param loginPass 登陆密码
     */
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass == null ? null : loginPass.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return full_name - 用户姓名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置用户姓名
     *
     * @param fullName 用户姓名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取电子邮件
     *
     * @return email - 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "UserBean [loginId=" + loginId + ", loginName=" + loginName + ", loginPass=" + loginPass + ", fullName=" + fullName + ", nickName=" + nickName + ", mobile=" + mobile + ", email=" + email + ", createTime=" + createTime + "]";
	}
    
}
