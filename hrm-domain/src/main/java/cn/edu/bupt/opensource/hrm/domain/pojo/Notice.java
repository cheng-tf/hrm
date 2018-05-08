package cn.edu.bupt.opensource.hrm.domain.pojo;

import java.util.Date;

/**
 * <p>Title: Notice</p>
 * <p>Description: 公告类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:46</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Notice implements java.io.Serializable{

    private static final long serialVersionUID = -3801497193569386478L;

    // ID
    private Integer id;
    // 标题
    private String title;
    // 内容
    private String content;
    // 发布日期
    private Date createDate;
    // 发布人
    private User user;

    public Notice() {
        super();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public void setCreateDate(java.util.Date createDate){
        this.createDate = createDate;
    }

    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Notice [id=" + id + ", title=" + title + ", content=" + content
                + ", createDate=" + createDate + ", user=" + user + "]";
    }

}