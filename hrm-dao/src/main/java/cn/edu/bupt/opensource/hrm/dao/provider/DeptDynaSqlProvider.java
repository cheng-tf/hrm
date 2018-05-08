package cn.edu.bupt.opensource.hrm.dao.provider;

import cn.edu.bupt.opensource.hrm.domain.pojo.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants.DEPTTABLE;

/**
 * <p>Title: DeptDynaSqlProvider</p>
 * <p>Description: 部门动态SQL语句-提供类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 12:47</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class DeptDynaSqlProvider {

    // 分页动态查询
    public String selectWhitParam(Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        return sql;
    }

    // 动态查询总数量
    public String count(Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if(params.get("dept") != null){
                    Dept dept = (Dept) params.get("dept");
                    if(dept.getName() != null && !dept.getName().equals("")){
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertDept(Dept dept){
        return new SQL(){
            {
                INSERT_INTO(DEPTTABLE);
                if(dept.getName() != null && !dept.getName().equals("")){
                    VALUES("name", "#{name}");
                }
                if(dept.getRemark() != null && !dept.getRemark().equals("")){
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateDept(Dept dept){
        return new SQL(){
            {
                UPDATE(DEPTTABLE);
                if(dept.getName() != null){
                    SET(" name = #{name} ");
                }
                if(dept.getRemark() != null){
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

}
