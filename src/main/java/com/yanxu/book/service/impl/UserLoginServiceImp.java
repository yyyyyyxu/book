package com.yanxu.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxu.book.entity.User;
import com.yanxu.book.entity.UserLoginHistory;
import com.yanxu.book.mapper.UserMapper;
import com.yanxu.book.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginServiceImp extends ServiceImpl<BaseMapper<User>, User> implements UserLoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, userName));
        if (user == null) {
            throw new UsernameNotFoundException("不存在該用戶");
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                new BCryptPasswordEncoder().encode(user.getPassWord()), authorityList);

    }


}
