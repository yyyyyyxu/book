package com.yanxu.book.scheduleTask.task.imp;

import com.yanxu.book.settingEnum.ParameterCodeEnum;
import com.yanxu.book.mapper.UserMapper;
import com.yanxu.book.scheduleTask.task.Task;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@Component
public class LoginHistoryTaskImp implements Task {

    @Autowired
    private UserMapper userMapper;

    private static LoginHistoryTaskImp loginHistoryTaskImp;

    private String taskName="登陆历史定时";

    public static LoginHistoryTaskImp getBorrowHistoryTask() {
        if (loginHistoryTaskImp == null) {
            synchronized (BorrowHistoryTaskImp.class) {
                if (loginHistoryTaskImp == null) {
                    loginHistoryTaskImp = new LoginHistoryTaskImp();
                    loginHistoryTaskImp.setTaskName("LoginHistoryTask");
                }
            }
        }
        return loginHistoryTaskImp;
    }

    @Override
    public void run() {

        ParameterCodeEnum.LOGININ_HISTORY_TASK.getParameterCode();
    }

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public Task getInstance() {
        return LoginHistoryTaskImp.getBorrowHistoryTask();
    }
}
