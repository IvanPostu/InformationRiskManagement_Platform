package com.irme.server.webapp.utils;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.Map;

@SuppressWarnings({ "unchecked" })
public final class DocumentUtils {
    private DocumentUtils() {
    }

    public static void processNode(XWPFRun run, Object node) {
        if (run == null || node == null)
            return;

        if (node instanceof String) {
            String data = (String) node;
            if (data.contains("\n")) {
                String[] lines = data.split("\n");
                run.setText(lines[0], 0); // set first line into XWPFRun
                for (int i = 1; i < lines.length; i++) {
                    run.addBreak();
                    run.setText(lines[i]);
                }
            } else {
                run.setText(data, 0);
            }
        }

        if (node instanceof Iterable) {
            Iterable<Object> itrbl = (Iterable<Object>) node;
            itrbl.forEach(o -> DocumentUtils.processNode(run, node));
        }
    }

    public static void checkAndReplace(XWPFRun run, Map<String, Object> dictionary) {
        String text = run.getText(0);
        if (text != null && text.startsWith("#")) {
            Object itemFromDictionary = dictionary.get(text);
            processNode(run, itemFromDictionary);
        }
    }
}
