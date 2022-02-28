package com.yanxu.book.scheduleTask;

import com.yanxu.book.scheduleTask.task.Task;
import com.yanxu.book.scheduleTask.task.imp.BorrowHistoryTaskImp;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class TaskScheduleService {

    static ConcurrentHashMap<String, ScheduledFuture> container = new ConcurrentHashMap<>();


    public static void start() {
        if (ThreadPoolUtil.getRunnableList().size() != 0) {
            for (Task task:ThreadPoolUtil.getRunnableList()) {
                if (!container.containsKey(task.getInstance().getName())) {
                    ThreadPoolTaskScheduler threadPoolTaskScheduler = ThreadPoolUtil.getInstance();
                    ScheduledFuture future = threadPoolTaskScheduler.schedule(task.getInstance(), new CronTrigger("0/3 * * * * ?"));
                    container.put(BorrowHistoryTaskImp.getBorrowHistoryTask().getTaskName(), future);
                }
            }
        }
    }

}


