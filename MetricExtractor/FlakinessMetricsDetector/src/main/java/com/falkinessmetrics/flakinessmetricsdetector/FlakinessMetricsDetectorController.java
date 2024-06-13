package com.falkinessmetrics.flakinessmetricsdetector;

import computation.RunFlakinessMetricsDetection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

@RestController
 public class FlakinessMetricsDetectorController {


    @GetMapping(path = "/getFlakinessMetrics")
    public boolean getFlakinessMetrics(@RequestParam String repositoryName){

        System.out.println("Request Received Metrics Extraction");

        boolean result=true;
        String metricRepository = "../sharedSpace/MetricsDetector/"+repositoryName; // For non-docker use
        //String metricRepository = "./sharedSpace/MetricsDetector/"+repositoryName;
        File file = new File(metricRepository);
        System.out.println("Instantiate the metrics detector");
        RunFlakinessMetricsDetection detector=new RunFlakinessMetricsDetection();

        if (!file.exists()) {
            System.out.println("Extract the Metrics");
            result = detector.getMetrics(repositoryName);
        }else{
            System.out.println("Metrics already extracted");
        }

        return result;
    }


    @GetMapping(path = "/testConnection")
    public String listenMessage(){
        return "Message received and returned";
    }


}
