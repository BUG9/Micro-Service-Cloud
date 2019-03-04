package com.zhc.msccommon.model.vo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by jingxian on 2018/7/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //实体类与json互转的时候 属性值为null的不参与序列化
public class UserVo implements Serializable {
    private static final long serialVersionUID = 3881610071550902762L;

    private Integer id;

    private String avatar;

    private String username;

    private String password;

    private String salt;

    private String name;

    private Date birthday;

    private Integer sex;

    private String email;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
