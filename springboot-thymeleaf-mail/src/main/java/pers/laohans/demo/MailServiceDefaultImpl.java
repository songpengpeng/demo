package pers.laohans.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * @author 老汉憨憨
 */
@Service
public class MailServiceDefaultImpl implements MailService {
    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    private final Environment environment;

    @Autowired
    public MailServiceDefaultImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, Environment environment) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.environment = environment;
    }

    @Override
    public void send(String subject, String to) {
        //给上下文绑定数据
        final Context ctx = new Context(new Locale(""));
        ctx.setVariable("name", "赵匡胤");
        final String htmlContent = this.templateEngine.process("email-template", ctx);
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            message.setSubject(subject);
            message.setFrom(environment.getProperty("spring.mail.username"));
            message.setTo(to);
            message.setText(htmlContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
