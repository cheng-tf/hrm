package cn.edu.bupt.opensource.hrm.domain.pojo;

/**
 * <p>Title: Dept</p>
 * <p>Description: 部门类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 11:31</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class Dept implements java.io.Serializable{

    private static final long serialVersionUID = 312653019151087634L;

    // ID
    private Integer id;
    // 部门名称
    private String name;
    // 部门描述
    private String remark;

    public Dept() {
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
        return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
    }

}