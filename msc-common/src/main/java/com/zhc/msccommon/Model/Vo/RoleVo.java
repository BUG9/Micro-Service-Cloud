package com.zhc.msccommon.Model.Vo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by jingxian on 2018/7/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleVo implements Serializable {
    private static final long serialVersionUID = 2179037393108205286L;
    private Integer id;

    private String name;

    private String value;

    private String tips;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
