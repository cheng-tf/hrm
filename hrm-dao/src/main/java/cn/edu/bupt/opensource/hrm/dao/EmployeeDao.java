package cn.edu.bupt.opensource.hrm.dao;

import cn.edu.bupt.opensource.hrm.dao.provider.EmployeeDynaSqlProvider;
import cn.edu.bupt.opensource.hrm.domain.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants.EMPLOYEETABLE;

/**
 * <p>Title: EmployeeDao</p>
 * <p>Description: EmployeeMapper接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 12:39</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface EmployeeDao {

    // 根据ID删除员工
    @Delete("delete from " + EMPLOYEETABLE + " where id = #{id}")
    void deleteById(Integer id);

    // 根据ID查询员工
    @Select("select * from " + EMPLOYEETABLE + " where ID = #{id}")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="CARD_ID", property="cardId"),
            @Result(column="POST_CODE", property="postCode"),
            @Result(column="QQ_NUM", property="qqNum"),
            @Result(column="BIRTHDAY", property="birthday", javaType=java.util.Date.class),
            @Result(column="CREATE_DATE", property="createDate", javaType=java.util.Date.class),
            @Result(column="DEPT_ID", property="dept",
                    one = @One(select="cn.edu.bupt.opensource.hrm.dao.DeptDao.selectById",
                            fetchType= FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one = @One(select="cn.edu.bupt.opensource.hrm.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    Employee selectById(Integer id);

    // 插入员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
    void save(Employee employee);

    // 修改员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
    void update(Employee employee);

    // 根据参数查询员工总数
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 根据参数动态查询员工
    @SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CARD_ID",property="cardId"),
            @Result(column="POST_CODE",property="postCode"),
            @Result(column="QQ_NUM",property="qqNum"),
            @Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="DEPT_ID",property="dept",
                    one=@One(select="cn.edu.bupt.opensource.hrm.dao.DeptDao.selectById",
                            fetchType= FetchType.EAGER)),
            @Result(column="JOB_ID",property="job",
                    one=@One(select="cn.edu.bupt.opensource.hrm.dao.JobDao.selectById",
                            fetchType=FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

}