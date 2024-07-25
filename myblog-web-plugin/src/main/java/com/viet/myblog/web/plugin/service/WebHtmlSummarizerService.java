package com.viet.myblog.web.plugin.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


@EzySingleton
public class WebHtmlSummarizerService {
    public static String summarizeHtml(String html, int maxLength) {
        // Parse the HTML using Jsoup
        Document doc = Jsoup.parse(html);

        // Extract the text content
        String text = doc.text();

        // Check if the text length exceeds the maximum length
        if (text.length() <= maxLength) {
            return text;
        }

        // Truncate the text to the maximum length
        String summary = text.substring(0, maxLength);

        // Optionally, you can add "..." to indicate that the text is truncated
        summary += " ...";

        return summary;
    }
}
