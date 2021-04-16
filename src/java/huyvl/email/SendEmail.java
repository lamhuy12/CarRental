/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.email;

import java.util.Random;
import javax.mail.Authenticator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author HUYVU
 */
public class SendEmail {

    public static void send(String reveiver, HttpServletRequest request) {

        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(Constants.MY_EMAIL,
                    Constants.MY_PASSWORD));

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom(Constants.MY_EMAIL);

            // Tiêu đề
            email.setSubject("User Email Verification");

            // Nội dung email
            int code = 100000 + (int) (Math.random() * 1000000);
            HttpSession session = request.getSession();
            session.setAttribute("AUTHCODE", code);
            email.setMsg("Registered successfully.Please verify your account using this code: " + code);

            // Người nhận
            email.addTo(reveiver);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
