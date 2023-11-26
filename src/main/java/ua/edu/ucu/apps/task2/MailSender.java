package ua.edu.ucu.apps.task2;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

public class MailSender {
    @SneakyThrows
    public static void sendMail(MailInfo mailInfo) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("api_key",
                "api_secret", new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
            .property(Emailv31.MESSAGES, new JSONArray()
                .put(new JSONObject()
                        .put(Emailv31.Message.FROM, new JSONObject()
                                .put("Email", "symko.pn@ucu.edu.ua")
                                .put("Name", "Oleksandra"))
                        .put(Emailv31.Message.TO, new JSONArray()
                                .put(new JSONObject()
                                        .put("Email", mailInfo.getClient().getEmail())
                                        .put("Name", mailInfo.getClient().getName())))
                                .put(Emailv31.Message.SUBJECT, "Hello from Mailjet.")
                                .put(Emailv31.Message.TEXTPART, "Mailjet email")
                                .put(Emailv31.Message.HTMLPART, "<h3>Dear passenger 1, welcome to <a href='https://www.mailjet.com/'>Mailjet</a>!</h3><br />May the delivery force be with you!")
                                .put(Emailv31.Message.CUSTOMID, "lab11")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}