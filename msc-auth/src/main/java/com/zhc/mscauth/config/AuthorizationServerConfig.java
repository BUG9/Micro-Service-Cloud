package com.zhc.mscauth.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import com.zhc.mscauth.service.Impl.UserDetailsServiceImpl;

/**
 * Created by jingxian on 2018/7/18.
 */
@Configuration
@EnableAuthorizationServer  // 开启授权服务的功能
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
        // 配置客户端认证方式，使用内存方式配置应用inMemory()
//        clients.inMemory()
//                .withClient("browaser")
//                .authorizedGrantTypes("refresh_token", "password") //配置了验证类型为 refresh_token 和 password
//                .scopes("ui")   //配置客户端域为 “ ui ”
//                .and()
//                .withClient("service-ui")
//                .secret("123456")
//                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
//                .scopes("server");
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错
     */
    @Primary    // https://blog.csdn.net/doctor_who2004/article/details/75330934 有关Primary注解导致问题可查看该博客
                // @Primary注解的实例优先于其他实例被注入
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7); //默认30天，这里修改
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore()) //配置token的存储方式
                    .userDetailsService(userDetailsService) //配置用于获取用户信息的bean
                    .authenticationManager(authenticationManager); //配置了这个 Bean 才会开启密码类型的验证
        endpoints.tokenServices(defaultTokenServices());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()"); //对获取token的请求不拦截
        security.checkTokenAccess("isAuthenticated()"); //检测策略
        security.allowFormAuthenticationForClients(); //让POST方式的/oauth/token支持client_id以及client_secret作登录认证，即支持From（表单）方式认证
    }
}
