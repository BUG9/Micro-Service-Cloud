package com.zhc.msccommon.Model.Vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by jingxian on 2018/7/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {
    private String id;
    private String code;
    private String pCode;
    private String pId;
    private String name;
    private String url;
    private Integer isMenu;
    private Integer level;
    private Integer sort;
    private Integer status;
    private String icon;
    private Date createTime;
    private Date updateTime;
}
