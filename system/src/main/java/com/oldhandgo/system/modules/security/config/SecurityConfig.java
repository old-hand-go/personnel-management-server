package com.oldhandgo.system.modules.security.config;

import com.oldhandgo.system.modules.security.security.JwtAuthenticationEntryPoint;
import com.oldhandgo.system.modules.security.security.JwtAuthorizationTokenFilter;
import com.oldhandgo.system.modules.security.service.impl.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author dormirr
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    /**
     * 自定义基于JWT的安全过滤器
     */
    private final JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.auth.path}")
    private String loginPath;

    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl, JwtAuthorizationTokenFilter authenticationTokenFilter) {
        this.jwtAuthenticationEntryPoint = unauthorizedHandler;
        this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsServiceImpl)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // 禁用 CSRF
                .csrf().disable()

                // 授权异常
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()

                // 不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 过滤请求
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).anonymous()

                //验证
                .antMatchers(HttpMethod.POST, "/auth/" + loginPath).anonymous()
                .antMatchers("/auth/vCode").anonymous()

                // swagger start
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
                // swagger end

                // 接口限流测试
                .antMatchers("/test/**").anonymous()
                // 文件
                .antMatchers("/avatar/**").anonymous()
                .antMatchers("/file/**").anonymous()

                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .antMatchers("/druid/**").anonymous()
                // 所有请求都需要认证
                .anyRequest().authenticated()
                // 防止iframe 造成跨域
                .and().headers().frameOptions().disable();

        httpSecurity
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
