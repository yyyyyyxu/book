package com.yanxu.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanxu.book.entity.Book;
import com.yanxu.book.mapper.BookMapper;
import com.yanxu.book.service.BookInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    BookInsertService bookInsertService;

    @Autowired
    BookMapper bookMapper;

    @RequestMapping("insertBook")
    @ResponseBody
    public void insert(@RequestBody Book book, @RequestParam("uploadLog") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("mag", new String("文件不得为空"));
        } else {
            try {
                bookInsertService.bookInsert(file, book);
            } catch (IOException e) {
                model.addAttribute("mag", new String("失败"));
            }
        }
    }


    @PostMapping("updateBookPic")
    public String uploadLog(@RequestParam("uploadLog") MultipartFile file, @RequestParam(value = "Code",required = false) String code,Model model) {
        Book book=bookMapper.selectOne(new QueryWrapper<Book>().lambda().eq(Book::getBookCode,code));
        if (book.getImage()!=null){
            book.setImage(null);
        }
        if (file.isEmpty()){
            model.addAttribute("mag",new String("文件不得为空"));
        }else {
            try {
                bookInsertService.bookInsert(file, book);
            } catch (IOException e) {
                model.addAttribute("mag",new String("失败"));
            }
        }
        return "forward:/book/getBookList";
    }




}
