package hyh.util;

import hyh.entity.AppEmail;
import hyh.global.Variable;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Component
public class Email {
    private Logger log = Logger.getLogger(this.getClass());
    @Resource(name = "mailSender")
    private JavaMailSenderImpl mailsender;
    @Resource(name = "mailSender1")
    private JavaMailSenderImpl mailsender1;
    @Resource(name = "mailSender2")
    private JavaMailSenderImpl mailsender2;
    private static final String TITLE = "东北大学活动提醒";
    volatile private static short cot = 0;
    volatile private static short sendcot = 0;
    volatile private static boolean send[] = new boolean[3];

    @Async
    public void sendEmail(Deque<AppEmail> stack, String genre, UserService userservice, int type) {
        JavaMailSenderImpl sender = getSender();
        short nowcot = 0;

        if (type == 1) {
            if (sendcot == 3) {
                sendcot = 0;
            }

            send[sendcot] = true;
            nowcot = sendcot++;
        }

        AppEmail appemail;

        while (!stack.isEmpty()) {
            appemail = stack.pop();

            if (!sendEmail(appemail, genre, sender)) {
                if (type == 1) {
                    send[nowcot] = false;
                }

                return;
            }

            userservice.updateStatus(1, appemail.getStudentid());
        }

        if (type == 1) {
            send[nowcot] = false;
        }
    }

    public boolean sendEmail(AppEmail appemail, String genre) {
        return sendEmail(appemail.getAddress(), getEmailText(appemail, genre), getSender());
    }

    @Async
    public void sendEmailSafely(AppEmail appemail, String genre) {
        for (int i = 1; i <= 3; i++) {
            if (sendEmail(appemail.getAddress(), getEmailText(appemail, genre), getSender(i))) {
                return;
            }
        }
    }

    private String getEmailText(AppEmail appemail, String type) {
        StringBuilder sb = new StringBuilder("同学您好！我是东北大学志愿者协会的同学，恭喜您" + type + "活动配对成功！" +
                "下面是您的配对对象的一些信息哟:<br>");

        sb.append("1.她或他的名字是:").append(appemail.getName()).append("<br>");
        sb.append("2.她或他的qq是:").append(appemail.getQq()).append("<br>");
        sb.append("3.他或她的电话是:").append(appemail.getPhone()).append("<br>");
        sb.append("同学，赶快拿起手机联系您的小伙伴吧！<br><br>");

        if (type.equals("辅学")) {
            sb.append("为了规范志协活动的展开，协会现今建立了信用分制度。<br>" +
                    "本活动规定如下：<br>" +
                    "1.在二次认证时一经发现有使用作弊手段者，双方都1周时间内不得参与本活动，并且扣除4信用分<br>" +
                    "2.当天晚上11点前辅学者和被辅学者中有任何一人不参与认证，不接受逾期认证。辅学者无法获得志愿时长，被辅学者扣除1信用分。<br>" +
                    "3.若辅学者在两周内辅学了4个人以上，且每次都进行了第二次认证，则增加4信用分。<br>" +
                    "我们实行这样的制度是为了让大家能够更好地投入到活动中去，希望大家能够理解。");
        }

        return sb.toString();
    }

    public boolean isOver() {
        return !send[0] && !send[1] && !send[2];
    }

    public JavaMailSenderImpl getMailsender() {
        return mailsender;
    }

    public JavaMailSenderImpl getMailsender1() {
        return mailsender1;
    }

    public JavaMailSenderImpl getMailsender2() {
        return mailsender2;
    }

    private boolean sendEmail(AppEmail appemail, String genre, JavaMailSenderImpl sender) {
        return sendEmail(appemail.getAddress(), getEmailText(appemail, genre), sender);
    }

    private boolean sendEmail(String address, String text, JavaMailSenderImpl sender) {
        try {
            MimeMessage msg = sender.createMimeMessage();
            //msg.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(username));
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");

            helper.setFrom(sender.getUsername());
            helper.setTo(address);
            helper.setSubject(TITLE);
            helper.setText(text, true);
            sender.send(msg);
        } catch (Exception e) {
            Variable.errornum++;
            log.error("Can not send email.Error Address" + sender.getUsername() + " \n" + e + "\n");
            return false;
        }

        return true;
    }

    private JavaMailSenderImpl getSender() {
        if (++cot == 1) {
            return mailsender;
        } else if (cot == 2) {
            return mailsender1;
        } else {
            cot = 0;
            return mailsender2;
        }
    }

    private JavaMailSenderImpl getSender(int tempcot) {
        if (tempcot == 1) {
            return mailsender;
        } else if (tempcot == 2) {
            return mailsender1;
        } else {
            return mailsender2;
        }
    }
}
