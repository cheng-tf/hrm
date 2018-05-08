package cn.edu.bupt.opensource.hrm.domain.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>Title: Employee</p>
 * <p>Description: 员工类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:39</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Employee implements java.io.Serializable{

    private static final long serialVersionUID = 6142512449873959749L;

    // ID
    private Integer id;
    // 员工关联的部门对象
    private Dept dept;
    // 员工关联的职位对象
    private Job job;
    // 姓名
    private String name;
    // 身份证号码
    private String cardId;
    // 地址
    private String address;
    // 邮政编码
    private String postCode;
    // 电话
    private String tel;
    // 手机
    private String phone;
    // QQ
    private String qqNum;
    // 邮箱
    private String email;
    // 性别
    private Integer sex;
    // 政治面貌
    private String party;
    // 生日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    // 名族
    private String race;
    // 学历
    private String education;
    // 专业
    private String speciality;
    // 爱好
    private String hobby;
    // 备注
    private String remark;
    // 建档日期
    private Date createDate;

    public Employee() {
        super();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setCardId(String cardId){
        this.cardId = cardId;
    }

    public String getCardId(){
        return this.cardId;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setPostCode(String postCode){
        this.postCode = postCode;
    }

    public String getPostCode(){
        return this.postCode;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public String getTel(){
        return this.tel;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setQqNum(String qqNum){
        this.qqNum = qqNum;
    }

    public String getQqNum(){
        return this.qqNum;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setSex(Integer sex){
        this.sex = sex;
    }

    public Integer getSex(){
        return this.sex;
    }

    public void setParty(String party){
        this.party = party;
    }

    public String getParty(){
        return this.party;
    }

    public void setBirthday(java.util.Date birthday){
        this.birthday = birthday;
    }

    public java.util.Date getBirthday(){
        return this.birthday;
    }

    public void setRace(String race){
        this.race = race;
    }

    public String getRace(){
        return this.race;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public String getEducation(){
        return this.education;
    }

    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

    public String getSpeciality(){
        return this.speciality;
    }

    public void setHobby(String hobby){
        this.hobby = hobby;
    }

    public String getHobby(){
        return this.hobby;
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

    @Override
    public String toString() {
        return "Employee [id=" + id + ", dept=" + dept + ", job=" + job
                + ", name=" + name + ", cardId=" + cardId + ", address="
                + address + ", postCode=" + postCode + ", tel=" + tel
                + ", phone=" + phone + ", qqNum=" + qqNum + ", email=" + email
                + ", sex=" + sex + ", party=" + party + ", birthday="
                + birthday + ", race=" + race + ", education=" + education
                + ", speciality=" + speciality +
                ", hobby=" + hobby + ", remark=" + remark + ", createDate="
                + createDate + "]";
    }

}