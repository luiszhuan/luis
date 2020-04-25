package com.luis.demo.schedule.test;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {

    @Test
    public void diffPeriodTest() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TestRunnable("task1", 3), 1, 3, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new TestRunnable("task2", 5), 2, 5, TimeUnit.SECONDS);
        Thread.sleep(1 * 60 * 60 * 1000L);
    }

    private class TestRunnable implements Runnable {
        String task;
        int period;

        public TestRunnable(String task, int period) {
            this.task = task;
            this.period = period;
        }

        @Override
        public void run() {
            System.out.println(new Date() + task + " run at " + period);
        }
    }

}
