package com.ducdh.ticket.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@Configuration
@Slf4j
public class FirebaseConfig {

    @Primary
    @Bean
    public void init() {
        createCredentialJsonFile();
        try {
            FileInputStream stream = new FileInputStream("firebase-adminsdk.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .setDatabaseUrl("https://demofirebase-c1df7.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            //System.out.println(">>>>>>>>>> Firebase: " + e.getLocalizedMessage());
            log.info(">>>>>>>>>> Firebase: " + e.getLocalizedMessage());
        }
    }

    private void createCredentialJsonFile() {
        JSONObject credentials = new JSONObject();
        credentials.put("type", "service_account");
        credentials.put("project_id", "logticket-19f38");
        credentials.put("private_key_id", "7afdd99c904f033c72f0d127d42e041f1b9a6e8e");
        credentials.put("private_key", "-----BEGIN PRIVATE " +
                "KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC8760d8iATsoim\nrVNO2ZuW" +
                "+Qhl3oYgLKIETbdLLqA5KnSxIL3tPTzqf7S3K7JrIHPEhfuBRI8hT5Qe" +
                "\n8ViVZT3EIPTiyPQFEshWwDIf7v3vK1yrtYeIIFtOt15eREYtvcQi0XPOHZ+tDFbO\nAtf5" +
                "/rJFkyceTgxJ8GjvQ9VVyjWxPP6exJ/z7y3dpynJL5R7v4LuXownZD9D2EXn\nH/gjf" +
                "/czApptmwIVqQK4mgXeKsn7q4HEMZ/qNfRRZezK7yO8JOqwX0NLiPpA727v" +
                "\n0eeBjTFh9pRpKe2xERjGIZnTs9flNE7hDiivmLOCce8cAvGzcReqAJlhMrGaCbdI" +
                "\nVwMRBQFFAgMBAAECggEAA8mmFcbag99gWnKrzCJtxySZP/OQ8RjiXmw31VYrG6JH\ntYJZ" +
                "+suivdlcERA08wbzEzJHCCcmW6BZ1sn3WoPHIN6y3uk9ueyC7CxNUb/e9hPC" +
                "\nncwImmVg3aHfwcuKgNTzioNaizwJ5rRfEoe5KgRql51hqCIGU76LizQErtDcOGaf" +
                "\nPsDWqHu7vxCcRmDdc0z7ABX6CA54HZUFczsJH1Vg/Zl9+a3yPEwp8e2a7tAorNba\ne+mfvqlw1bgyUi3Vi/bP2" +
                "/KxXusdCXWoi8Red9V8zD/KMXAgBudtbjxewHU3MK3A\nZ" +
                "+hR8NI3BvPOrBtvm9fhRebTPl8luXRmNBFDFyxSOQKBgQDsy7QKxk982LYRgjeW\nDyidT1OXrfbLw" +
                "/mGNMGRhYIoybZdxjByY4ckemkhPt3YC3juhpvci/0u9nSGbeRB\ng8k0pN+417uAqgGl0ZfHHg1J3uHRT10SMpl" +
                "/FIOMrY0e9t0yhB+KAKhoAR2QWS/2\nzKWg/H/ZBl5HbQaHCAsUqD9YiQKBgQDMQlNiRSc7LRG5VxCn6LD8nqrVXsg06hB8" +
                "\nrb5wwVYitNfrBL5uLHAhRZ1Xxf00xW7Jw/M4XecwZH59lZER5kvxhSQTbYCdzqL2" +
                "\nlKl5j1bTCUPZM2ZL8cRkhHYergjI1OeS0ZAFmnWII2WSA0Srmk9IxnuRwflPUNSb" +
                "\n0rOoGf473QKBgQDGiw4MsrZXMcWG7K36zf7511d5qE10EH+ZfYaHo2+9D1QvUEuy\nvazEczbOv013Jjdhl8" +
                "/7ngzNYavWQzHNlNgRoeRLsebvPJxhElm2hIYT2CNzQp4A\n8hgtq1" +
                "+ZhrL6sXZKMunW5jJN8hF9xKoovvJpBw2TRHmsXcfJVH8l9OQYMQKBgQCt\n98ehKRjy074Gx1rOR" +
                "+eGIXWK6YFHYBvOtQRAcMp2IOTNrGqs13hhPjN2sJCqQ4XK\nnvQmjAP+OXlqaNr4DdecyzMgwOEMhxRRQ" +
                "+jcOfNPJtL8yURVZoNIwXEBTGT6+CqL\n56TswNNdshguNk5kgbcJWtOgHz" +
                "/CCCws01Zk1E6umQKBgQCi26E9YNkPOWvuefqP\nkgoxeq+1FJmS39yk1xxR8M0w3HUuMR0miAj" +
                "+aBbH22dC4uIwEGpxRHGx2o3nOmst\n+VeMeGjsKFj4FzsoAolf7Un7D+3V6x+/eZz+REUe6rr9ZdYMFjprdprUoeneVIWx" +
                "\nMGOqVtkK3NT0lEVWsMt1tHJ3eQ==\n-----END PRIVATE KEY-----\n");
        credentials.put("client_email", "firebase-adminsdk-usb6l@logticket-19f38.iam.gserviceaccount.com");
        credentials.put("client_id", "106524595272653315866");
        credentials.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
        credentials.put("token_uri", "https://oauth2.googleapis.com/token");
        credentials.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        credentials.put("client_x509_cert_url", "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-usb6l%40logticket-19f38.iam.gserviceaccount.com");

        try (FileWriter fileWriter = new FileWriter("firebase-adminsdk.json")) {

            fileWriter.write(credentials.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            log.info(">>>>>>>>> Credentials error: " + e.getLocalizedMessage());
        }
    }
}
