package cn.edu.bupt.opensource.hrm.domain.pojo;

/**
 * <p>Title: Job</p>
 * <p>Description: 职位类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:34</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Job implements java.io.Serializable{

    private static final long serialVersionUID = 3000206760173173588L;

    // ID
    private Integer id;
    // 职位名称
    private String name;
    // 职位描述
    private String remark;

    public Job() {
        super();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", name=" + name + ", remark=" + remark + "]";
    }

}