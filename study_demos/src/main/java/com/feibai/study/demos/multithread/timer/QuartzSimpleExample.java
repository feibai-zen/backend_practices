package com.feibai.study.demos.multithread.timer;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * quartz
 */
public class QuartzSimpleExample {

    public static void main(String[] args) throws Exception {
        QuartzSimpleExample example = new QuartzSimpleExample();
        example.run();
    }

    public void run() throws Exception {
        Logger log = LoggerFactory.getLogger(QuartzSimpleExample.class);
        log.info("------- Initializing ----------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        // computer a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + runTime);

        // Start up the scheduler (nothing can actually run until the
        // scheduler has been started)
        sched.start();

        log.info("------- Started Scheduler -----------------");

        // wait long enough so that the scheduler as an opportunity to
        // run the job!
        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");
    }

}

class HelloJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Hello World! - " + new Date());
    }

}