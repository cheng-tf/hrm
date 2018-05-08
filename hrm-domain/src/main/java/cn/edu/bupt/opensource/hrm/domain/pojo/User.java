package cn.edu.bupt.opensource.hrm.domain.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: User</p>
 * <p>Description: 用户类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:36</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = -6241206839760689251L;

    // ID
    private Integer id;
    // 用户名
    private String username;
    // 登录名
    private String loginname;
    // 密码
    private String password;
    // 状态
    private Integer status;
    // 建档日期
    private Date createDate;

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", loginname="
                + loginname + ", password=" + password + ", status=" + status
                + ", createDate=" + createDate + "]";
    }

}