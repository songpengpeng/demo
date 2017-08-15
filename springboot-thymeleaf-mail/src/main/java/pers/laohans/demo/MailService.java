package pers.laohans.demo;

/**
 * @author 老汉憨憨
 */
public interface MailService {

    /**
     * @param subject 主题
     * @param to      收件人
     */
    void send(String subject, String to);
}
