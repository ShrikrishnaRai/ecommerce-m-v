package com.shree.ecommerce_m_v.utils.email.mailJetSender;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MailJetSenderClass {

    public void sendMail(String To, String Body, String Subject)
            throws MailjetSocketTimeoutException, JSONException,  MailjetException {

        final String mailJetApiKey = "d0933f98e6a7cf4c30ebfc6c76254da9";
        final String mailJetSecretKey = "1164bd1a9a40b9f0739647fe2f7ca278";

        MailjetClient client = new MailjetClient(mailJetApiKey,
                                    mailJetSecretKey,
                                    new ClientOptions("v3.1"));
        client.post(mail(To, Body, Subject));

    }

    public static MailjetRequest mail(String To, String Body, String Subject) throws JSONException{

        return  new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "info@justnep.com")
                                        .put("Name", "SulabBazaar"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", To)))
                                .put(Emailv31.Message.SUBJECT, Subject)
                                .put(Emailv31.Message.HTMLPART, Body)));

    }


}
