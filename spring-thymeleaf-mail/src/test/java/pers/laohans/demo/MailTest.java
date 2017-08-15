package pers.laohans.demo;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MailTest.class)
@Configuration
public class MailTest {
    @Bean
    public TemplateEngine templateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(1);
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendHtml() throws MessagingException, javax.mail.MessagingException {
        //给上下文绑定数据
        final Context ctx = new Context(new Locale(""));
        ctx.setVariable("name", "赵匡胤");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ctban.com");
        mailSender.setUsername("ctban@ctban.com");
        mailSender.setPassword("jzwang123");
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"UTF-8");
        message.setSubject("Spring Thymeleaf Email");
        message.setFrom("ctban@ctban.com");
        message.setTo("2841909223@qq.com");
        final String htmlContent = this.templateEngine.process("email-template", ctx);
        // true 标识 HTML
        message.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }
}
