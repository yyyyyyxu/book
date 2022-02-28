package com.yanxu.book.scheduleTask;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ThreadTaskEnum {
    BORROWHISTORYTASK("1", "10"),
    ;


    private String code;
    private String positition;

    ThreadTaskEnum(String code, String positition) {
        this.code = code;
        this.positition = positition;
    }

}
