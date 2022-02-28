package com.yanxu.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanxu.book.entity.Book;
import com.yanxu.book.mapper.BookMapper;
import com.yanxu.book.service.BookInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

@Service
public class BookInsertServiceImp extends ServiceImpl<BaseMapper<Book>,Book> implements BookInsertService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public String bookInsert(MultipartFile file, Book book) throws IOException{
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        if (!file.isEmpty() && !StringUtils.isEmpty(book.bookCode)) {
            byte[] bytes = new byte[512000];
            int len = 0;
            try {
                bufferedInputStream = new BufferedInputStream(file.getInputStream());
                bufferedInputStream.read(bytes);

                book.setImage(bytes);
                bookMapper.insert(book);
            } finally {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "成功";
    }
}
