package com.zhc.mscauth.service;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.zhc.mscauth.service.Impl.RoleServiceImpl;
import com.zhc.msccommon.Model.Vo.Result;
import com.zhc.msccommon.Model.Vo.RoleVo;


/**
 * Created by jingxian on 2018/7/17.
 */
@FeignClient(name = "mss-dataservice", fallback = RoleServiceImpl.class)
public interface RoleService {
    @GetMapping("role/getRoleByUserId/{userId}")
    Result<List<RoleVo>> getRoleByUserId(@PathVariable("userId") Integer userId);
}
