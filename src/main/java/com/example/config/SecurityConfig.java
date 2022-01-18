package com.example.config;

import com.example.filter.MyFilter1;
import com.example.filter.MyFilter3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .addFilterBefore(new MyFilter1(), UsernamePasswordAuthenticationFilter.class)// UsernamePasswordAuthenticationFilter 직전에 MyFilter 가 실행
//                .addFilterAfter(new MyFilter1(), UsernamePasswordAuthenticationFilter.class)// UsernamePasswordAuthenticationFilter 이후에 MyFilter 가 실행
                .addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                    .formLogin().disable()
                    .httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/user/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                    .antMatchers("/api/v1/manager/**")
                        .hasAnyRole("MANAGER, ADMIN")
                    .antMatchers("/api/v1/admin/**")
                        .hasAnyRole("ADMIN")
                    .anyRequest()
                    .permitAll();

    }
}
