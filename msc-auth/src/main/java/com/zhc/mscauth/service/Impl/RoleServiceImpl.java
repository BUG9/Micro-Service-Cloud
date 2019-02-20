package com.zhc.mscauth.service.Impl;

import java.util.List;
import com.zhc.mscauth.service.RoleService;
import com.zhc.msccommon.Model.Vo.Result;
import com.zhc.msccommon.Model.Vo.RoleVo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jingxian on 2018/7/17.
 */
@Slf4j
public class RoleServiceImpl implements RoleService {
    public Result<List<RoleVo>> getRoleByUserId(Integer userId) {
        log.info("调用{}失败", "getRoleByUserId");
        return Result.failure(100,"调用getRoleByUserId失败");
    }
}
