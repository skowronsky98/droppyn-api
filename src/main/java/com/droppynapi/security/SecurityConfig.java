package com.droppynapi.security;

import com.droppynapi.filter.CustomAuthenticationFilter;
import com.droppynapi.filter.CustomAuthorizationFilter;
import com.droppynapi.utills.Utills;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/user/token/refresh").permitAll()
                .antMatchers(HttpMethod.POST,"/user/register").permitAll()

                .antMatchers(HttpMethod.GET,"/brand/all").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)
                .antMatchers(HttpMethod.GET,"/sizechart","/sizechart/all").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)
                .antMatchers(HttpMethod.GET,"/shoe","/shoe/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)
                .antMatchers(HttpMethod.GET,"/offer", "/offer/page", "/offer/all", "/offer/shoe","/offer/my/all","/offer/favorite/all").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)
                .antMatchers(HttpMethod.GET,"/user","/user/token/refresh").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)

                .antMatchers(HttpMethod.POST,"/offer/my","/offer/favorite").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)

                .antMatchers(HttpMethod.PUT,"/offer/my").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)
                .antMatchers(HttpMethod.PUT,"/user").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)

                .antMatchers(HttpMethod.DELETE,"/offer/my","/offer/favorite").hasAnyAuthority(Utills.ROLE_SUPERADMIN,Utills.ROLE_USER)

                .antMatchers(HttpMethod.GET,"/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN)
                .antMatchers(HttpMethod.POST,"/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN)
                .antMatchers(HttpMethod.DELETE,"/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN)
                .antMatchers(HttpMethod.PUT,"/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN)
                .antMatchers(HttpMethod.PATCH,"/**").hasAnyAuthority(Utills.ROLE_SUPERADMIN)
//                .anyRequest().authenticated()
                .and().addFilter(new CustomAuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
