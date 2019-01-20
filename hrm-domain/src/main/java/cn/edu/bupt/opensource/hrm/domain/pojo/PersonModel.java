package cn.edu.bupt.opensource.hrm.domain.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author chengtf
 * @date 2019/1/20
 */
@Data
public class PersonModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String sex;

    @ExcelProperty(index = 2)
    private Integer age;

    @ExcelProperty(index = 3)
    private String city;

    @ExcelProperty(index = 4)
    private String hobby;

    @ExcelProperty(index = 5)
    private String remark1;

    @ExcelProperty(index = 6)
    private String remark2;

}
