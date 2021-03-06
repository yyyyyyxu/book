package com.yanxu.book.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanxu.book.util.PageParam;

import java.util.List;

public interface BasePageService<Param, Result>  {
    //分页查询
    default PageInfo<Result> page(PageParam<Param> param) {
        return PageHelper.startPage(param.getPageNum(),param.getPageSize()).doSelectPageInfo(() -> list(param.getParam()));
    }

    //集合查询
    List<Result> list(Param param);
}
