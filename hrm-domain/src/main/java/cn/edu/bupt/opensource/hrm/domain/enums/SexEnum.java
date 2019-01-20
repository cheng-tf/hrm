package cn.edu.bupt.opensource.hrm.domain.enums;

/**
 * @author chengtf
 * @date 2019/1/20
 */
public enum  SexEnum {

    // 男
    BOY(1, "男"),

    // 女
    GIRL(2, "女"),;

    private Integer code;

    private String description;

    SexEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
