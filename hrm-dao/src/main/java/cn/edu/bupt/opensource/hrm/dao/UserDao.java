package cn.edu.bupt.opensource.hrm.dao;

import cn.edu.bupt.opensource.hrm.dao.provider.UserDynaSqlProvider;
import cn.edu.bupt.opensource.hrm.domain.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants.USERTABLE;

/**
 * <p>Title: UserDao</p>
 * <p>Description: UserMapper接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 12:35</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface UserDao {

    // 根据登录名和密码查询员工
    @Select("select * from " + USERTABLE + " where loginname = #{loginname} and password = #{password}")
    User selectByLoginnameAndPassword(
            @Param("loginname") String loginname,
            @Param("password") String password);

    // 根据ID查询用户
    @Select("select * from " + USERTABLE + " where ID = #{id}")
    User selectById(Integer id);

    // 根据ID删除用户
    @Delete(" delete from " + USERTABLE + " where id = #{id} ")
    void deleteById(Integer id);

    // 动态修改用户
    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    // 动态查询
    @SelectProvider(type=UserDynaSqlProvider.class, method="selectWhitParam")
    List<User> selectByPage(Map<String, Object> params);

    // 根据参数查询用户总数
    @SelectProvider(type=UserDynaSqlProvider.class, method="count")
    Integer count(Map<String, Object> params);

    // 动态插入用户
    @SelectProvider(type=UserDynaSqlProvider.class, method="insertUser")
    void save(User user);

}