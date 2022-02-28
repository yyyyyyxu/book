package com.yanxu.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanxu.book.entity.Book;
import com.yanxu.book.entity.User;
import com.yanxu.book.entity.UserLoginHistory;

public interface UserLoginHistoryService extends BasePageService<UserLoginHistory,UserLoginHistory>, IService<User> {
}
