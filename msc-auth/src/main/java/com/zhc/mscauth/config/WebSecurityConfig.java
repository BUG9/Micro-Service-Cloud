package com.zhc.mscauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.zhc.mscauth.service.Impl.UserDetailsServiceImpl;

/**
 * Created by jingxian on 2018/7/17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别的权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated() //任何请求均需要登录认证后才可操作
                .and()
                .formLogin()  //使用定制登录操作
                .and()
                .csrf().disable() //关闭防跨域攻击功能 , 否则post请求将无效，
                                    // 任何 POST 提交到后台的表单都要验证是否带有 _csrf 参数，一旦传来的 _csrf 参数不正确，服务器便返回 403 错误
                .httpBasic(); //有两种客户端验证类型，分别为 Http Basic 和 Form ， 默认为 Http Basic
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //使用BCrypt进行密码加密
    }


    //配置验证管理的Bean
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**"); //配置不需要验证的资源
    }
}
