package com.ValuedIn.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MailService {

    public MailService(){}

    public void sendEmail(String from, String to, String subject, String content)
        throws IOException {
        File logFile = createFile(subject, to);
        FileWriter fileWriter = new FileWriter(logFile);
        to = beautifyTo(to);
        from = beautifyFrom(from);
        content = "<div>" + from + "</div>"
            + "<br>"
            + "<div>"+to+"</div>"
            + "<br>"
            + "<div>"+content+"</div>";
        fileWriter.write(content);
        fileWriter.close();
    }

    private String beautifyFrom(String from){
        return "<div>"
            + "-----------"
            + "<p>Sent From: <p>"
            + "<span>" + from +"</span>"
            + "-----------"
            + "</div>";
    }

    private String beautifyTo(String to){
        return "<div>"
            + "-----------"
            + "<p>Recipient: <p>"
            + "<span>" + to +"</span>"
            + "-----------"
            + "</div>";

    }

    private File createFile(String subject, String to) throws IOException {
        String fileName = String.format("logs/Email_%s_To_%s_On_%s.html",subject,to,LocalDateTime.now()).replace(':', '_').replace(' ', '-');

        File file = new File(fileName);
        file.createNewFile();
        return file;
    }

}
