package com.yanxu.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxu.book.entity.User;
import com.yanxu.book.entity.UserLoginHistory;
import com.yanxu.book.mapper.UserLoginHistoryMapper;
import com.yanxu.book.service.UserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserLoginHistoryServiceImp extends ServiceImpl<BaseMapper<User>, User> implements UserLoginHistoryService {

    @Autowired
    UserLoginHistoryMapper userLoginHistoryMapper;

    @Override
    public List<UserLoginHistory> list(UserLoginHistory user) {
        List<UserLoginHistory> users = userLoginHistoryMapper.selectList(new QueryWrapper<UserLoginHistory>().lambda().eq(!StringUtils.isEmpty(user.getUserName()),UserLoginHistory::getUserName,user.getUserName())
                .le(!StringUtils.isEmpty(user.getCreatTime()),UserLoginHistory::getCreatTime,user.getCreatTime()));
        return users;
    }


}
