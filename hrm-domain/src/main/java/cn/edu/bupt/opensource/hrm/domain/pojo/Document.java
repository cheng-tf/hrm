package cn.edu.bupt.opensource.hrm.domain.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>Title: Document</p>
 * <p>Description: 文件类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:48</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Document implements java.io.Serializable{

    private static final long serialVersionUID = 9041365438269380037L;

    // ID
    private int id;
    // 标题
    private String title;
    // 文件名
    private String fileName;
    // 文件
    private MultipartFile file;
    // 描述
    private String remark;
    // 上传时间
    private Date createDate;
    // 上传人
    private User user;

    public Document() {
        super();
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
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
        return "Document [id=" + id + ", title=" + title + ", fileName="
                + fileName + ", file=" + file + ", remark=" + remark
                + ", createDate=" + createDate + ", user=" + user + "]";
    }

}