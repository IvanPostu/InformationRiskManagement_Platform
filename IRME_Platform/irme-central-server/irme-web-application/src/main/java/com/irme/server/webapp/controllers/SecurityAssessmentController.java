package com.irme.server.webapp.controllers;

import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationReportItem;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.webapp.utils.DocumentUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;

@Slf4j
@RestController
@RequestMapping(value = "/api/securityAssessment")
public class SecurityAssessmentController {

    @Autowired
    private SABusinessLogic sABusinessLogic;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadDataProtectionimpactDocument(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "processId", required = true) int processId,
            @RequestParam(value = "organisationName", required = false) String orgName,
            @RequestParam(value = "categoryName", required = false) String category,
            @RequestParam(value = "docType", required = false) Optional<String> docType)
            throws IOException {
        File templateFile = ResourceUtils.getFile("classpath:docTemplates/template1.docx");

        String originalOrgName = orgName != null
                ? new String(Base64.getDecoder().decode(orgName))
                : "";

        String originalCategoryName = orgName != null
                ? new String(Base64.getDecoder().decode(category))
                : "";

        String originalDocType = docType.orElseGet(() -> ".doc");

        try {
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(templateFile));

            EvaluationReport e = sABusinessLogic.getEvaluationReport(processId)
                    .orElseThrow(() -> new RuntimeException());

            StringBuilder content = e.getItems()
                    .stream()
                    .collect(Collector.of(() -> new StringBuilder(),
                            (StringBuilder acc, EvaluationReportItem report) -> {
                                acc.append(String.format("%s\n\n", report.getDescription()));
                            }, (s1, s2) -> s1));

            HashMap<String, Object> dict = new HashMap<>();
            dict.put("#processId", String.format("#%d", processId));
            dict.put("#organisationName", originalOrgName);
            dict.put("#categoryName", originalCategoryName);
            dict.put("#content", content.toString());

            replaceAllCoincides(doc, dict);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                doc.write(bos);
            } finally {
                bos.close();
            }
            byte[] output = bos.toByteArray();

            response.setContentLength(output.length);
            response.setContentType(MediaType.ALL_VALUE);

            String fileName = String.format("Raport_%s_%d.%s", originalOrgName, processId, originalDocType);
            String contentDisposition = String.format("attachment; filename=%s", fileName);

            response.setHeader("Content-Disposition", contentDisposition);
            response.getOutputStream().write(output);
            response.getOutputStream().flush();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private void replaceAllCoincides(XWPFDocument doc, Map<String, Object> dictionary) {

        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    DocumentUtils.checkAndReplace(r, dictionary);
                }
            }
        }

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            DocumentUtils.checkAndReplace(r, dictionary);
                        }
                    }
                }
            }
        }
    }
}
