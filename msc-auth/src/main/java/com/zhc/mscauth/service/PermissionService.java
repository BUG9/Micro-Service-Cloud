package com.zhc.mscauth.service;


import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.zhc.mscauth.service.Impl.PermissionServiceImpl;
import com.zhc.msccommon.Model.Vo.MenuVo;
import com.zhc.msccommon.Model.Vo.Result;

/**
 * Created by jingxian on 2018/7/17.
 */
@FeignClient(name = "mss-dataservice", fallback = PermissionServiceImpl.class)
public interface PermissionService {
    @GetMapping("permission/getRolePermission/{roleId}")
    Result<List<MenuVo>> getRolePermission(@PathVariable("roleId") Integer roleId);
}
