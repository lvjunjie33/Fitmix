package com.business.app.shop.account;

/**
 * Created by zhangtao on 2016/12/1.
 */
public class RunStatisticsThread extends Thread {

    private AccountService accountService;

    private Integer start;

    private Integer end;

    @Override
    public void run() {
        System.out.println(String.format("%d - %d 统计开始........", start, end));
        this.accountService.runInit(start, end);
    }

    public RunStatisticsThread(AccountService accountService, Integer start, Integer end) {
        this.accountService = accountService;
        this.start = start;
        this.end = end;
    }
}
