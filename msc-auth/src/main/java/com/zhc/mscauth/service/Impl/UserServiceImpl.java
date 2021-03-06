package com.zhc.mscauth.service.Impl;

import com.zhc.mscauth.service.UserService;
import com.zhc.msccommon.model.vo.Result;
import com.zhc.msccommon.model.vo.UserVo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jingxian on 2018/7/17.
 */
@Slf4j  //  不想每次都写;private  final Logger logger = LoggerFactory.getLogger(XXX.class) 可以用注解@Slf4j
public class UserServiceImpl implements UserService{

    public Result<UserVo> findByUsername(String username) {
        log.info("调用{}失败", "findByUsername");
        return Result.failure(100,"调用findByUsername接口失败");
    }
}
