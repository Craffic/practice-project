package com.craffic.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsvJobController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @GetMapping("/read/csv")
    public void readCsv(){
        try {
            jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
