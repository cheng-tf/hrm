package cn.edu.bupt.opensource.hrm.dao;

import cn.edu.bupt.opensource.hrm.dao.provider.DocumentDynaSqlProvider;
import cn.edu.bupt.opensource.hrm.domain.pojo.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static cn.edu.bupt.opensource.hrm.common.util.contant.HrmConstants.DOCUMENTTABLE;

/**
 * <p>Title: DocumentDao</p>
 * <p>Description: DocumentMapper接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-07 12:42</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface DocumentDao {

    // 动态查询
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWhitParam")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
            @Result(column="USER_ID",property="user",
                    one=@One(select="cn.edu.bupt.opensource.hrm.dao.UserDao.selectById",
                            fetchType= FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String, Object> params);

    @SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    // 动态插入文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
    void save(Document document);

    @Select("select * from "+DOCUMENTTABLE+" where ID = #{id}")
    Document selectById(int id);

    // 根据id删除文档
    @Delete(" delete from "+DOCUMENTTABLE+" where id = #{id} ")
    void deleteById(Integer id);

    // 动态修改文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
    void update(Document document);

}