package com.yanxu.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.yanxu.book.entity.Book;
import com.yanxu.book.entity.ToEmail;
import com.yanxu.book.mapper.BookMapper;
import com.yanxu.book.service.BookInsertService;
import com.yanxu.book.service.BookSearchService;
import com.yanxu.book.util.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/book")
@Controller
public class BookSearchController {

    @Autowired
    private BookInsertService bookInsertService;

    @Autowired
    private BookSearchService bookSearchService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PageParam<Book> pageParam;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    @RequestMapping("getBookList")
    public String getBookList(@RequestParam(required = false, defaultValue = "1") String num, String bookName, String bookCode, Model model) {
        int pageNum = Integer.parseInt(num);
        Book book = new Book();
        book.setBookCode(bookCode);
        book.setBookName(bookName);
        pageParam.setPageNum(pageNum);
        pageParam.setParam(book);
        PageInfo<Book> pageInfo = bookSearchService.page(pageParam);
        model.addAttribute("page", pageInfo);
        return "page";
    }

    @GetMapping("bookDetails")
    @ResponseBody
    public String bookDetails(String bookCode){
        Book book = bookMapper.selectOne(new QueryWrapper<Book>().lambda().eq(Book::getBookCode,bookCode));
        return book.getBookName();
    }

    @PostMapping("sendEmail")
    @ResponseBody
    public String endEmail(@RequestBody ToEmail toEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return "发送普通邮件成功";
        } catch (MailException e) {
            e.printStackTrace();
            return "普通邮件方失败";
        }
    }

}