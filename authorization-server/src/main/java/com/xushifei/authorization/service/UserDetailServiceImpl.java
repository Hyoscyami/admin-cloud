package com.xushifei.authorization.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自定义用户详情类
 * </p>
 *
 * @author xushifei
 * @since 2021/12/3
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    /**
     * 根据用户名加载用户详情
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .roles("USER")
                .build();
    }
}
