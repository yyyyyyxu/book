package com.yanxu.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanxu.book.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookInsertService extends IService<Book> {



    String bookInsert(MultipartFile file, Book book) throws IOException;
}
