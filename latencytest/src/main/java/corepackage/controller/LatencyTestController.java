package corepackage.controller;


import corepackage.service.SaveFilesToAWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LatencyTestController
{
    @Autowired
    SaveFilesToAWS saveFilesToAWS;

    @GetMapping("/")
    public String home()
    {
        return "<h1>Homepage for file upload</h1>";
    }

    @PostMapping("/uploadexcel/")
    public String uploadExcel() throws IOException
    {
        return saveFilesToAWS.uploadExcelFiles();
    }

    @PostMapping("/uploadzip/")
    public String uploadZip() throws IOException
    {
        return saveFilesToAWS.uploadZipFiles();
    }
}
