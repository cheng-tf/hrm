package cn.edu.bupt.opensource.hrm.dao;

import cn.edu.bupt.opensource.hrm.dao.provider.JobDynaSqlProvider;
import cn.edu.bupt.opensource.hrm.domain.pojo.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants.JOBTABLE;

/**
 * <p>Title: JobDao</p>
 * <p>Description: JobMapper接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 12:38</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface JobDao {

    @Select("select * from "+JOBTABLE+" where ID = #{id}")
    Job selectById(int id);

    @Select("select * from "+JOBTABLE+" ")
    List<Job> selectAllJob();

    // 动态查询
    @SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type=JobDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 根据ID删除部门
    @Delete(" delete from "+JOBTABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入部门
    @SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
    void save(Job job);

    // 动态修改用户
    @SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
    void update(Job job);

}