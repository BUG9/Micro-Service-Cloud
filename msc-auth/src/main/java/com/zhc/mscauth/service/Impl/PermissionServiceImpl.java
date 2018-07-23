package com.zhc.mscauth.service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.zhc.mscauth.service.PermissionService;
import com.zhc.msccommon.Model.Vo.MenuVo;
import com.zhc.msccommon.Model.Vo.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jingxian on 2018/7/17.
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Result<List<MenuVo>> getRolePermission(Integer roleId) {
        log.info("调用{}失败", "getRolePermission");
        return Result.failure(100, "调用getRolePermission失败");
    }
}
