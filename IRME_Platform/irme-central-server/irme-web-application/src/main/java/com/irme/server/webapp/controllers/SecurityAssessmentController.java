package com.irme.server.webapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/api/securityAssessment")
public class SecurityAssessmentController {
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadDataProtectionimpactDocument(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "processId", required = true) int processId)
            throws IOException {
        File templateFile = ResourceUtils.getFile("classpath:docTemplates/template1.docx");
        byte[] output = new byte[(int) templateFile.length()];

        try (FileInputStream fis = new FileInputStream(templateFile);) {
            fis.read(output);

            response.setContentLength(output.length);
            response.setContentType(MediaType.ALL_VALUE);
            String contentDisposition = String.format("attachment; filename=%s",
                    templateFile.getName());
            response.setHeader("Content-Disposition", contentDisposition);

            response.getOutputStream().write(output);
            response.getOutputStream().flush();
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
