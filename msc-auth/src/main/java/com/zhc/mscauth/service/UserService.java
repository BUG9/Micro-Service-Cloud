package com.zhc.mscauth.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.zhc.mscauth.service.Impl.UserServiceImpl;
import com.zhc.msccommon.model.vo.Result;
import com.zhc.msccommon.model.vo.UserVo;

/**
 * Created by jingxian on 2018/7/17.
 */
@FeignClient(name = "ms-dataservice", fallback = UserServiceImpl.class)
public interface UserService {
    @GetMapping("user/findByUsername/{username}")
    Result<UserVo> findByUsername(@PathVariable("username") String username);
}
