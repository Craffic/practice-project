package com.craffic.mail;

import com.craffic.mail.domain.User;
import com.craffic.mail.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    /**
     * 发送简单的邮件
     */
    @Test
    public void sendSimpleMailTest(){
        String from = "392301161@qq.com";
        String to = "Craffic@126.com";
        String cc = "yl_huang@cn.yokowo.com";
        String subject = "测试Springboot邮箱开发";
        String content = "测试邮件发送内容......";
        mailService.sendSimpleMail(from, to, cc, subject, content);
    }

    /**
     * 发送带附件的邮件
     */
    @Test
    public void sendAttachFileMailTest(){
        String from = "392301161@qq.com";
        String to = "Craffic@126.com";
        String cc = "yl_huang@cn.yokowo.com";
        String subject = "测试发送带附件的邮件";
        String content = "测试邮件发送内容，测试带附件邮件......";
        mailService.sendAttachFileMail(from, to, cc, subject, content, new File("C:\\Users\\Administrator\\Desktop\\unnamed.patch"));
    }

    /**
     * 发送带图片邮件
     */
    @Test
    public void sendPicsMailTest(){
        String from = "392301161@qq.com";
        String to = "Craffic@126.com";
        String cc = "yl_huang@cn.yokowo.com";
        String subject = "测试发送带图片的邮件";
        String content = "<div>这是图片1<div><img src='cid:p01'></div>" +
                "这是图片2<div><img src='cid:p02'></div>" + "</div>";
        String[] srcPath = {"D:\\temp\\p1.jpg", "D:\\temp\\p2.jpg"};
        String[] resIds = {"p01", "p02"};
        mailService.sendPicsMail(from, to, cc, subject, content, srcPath, resIds);
    }

    /**
     * 发送freemarker模板邮件
     */
    @Test
    public void sendHtmlMail(){
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader loader = MailApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "ftl");
            Template template = configuration.getTemplate("mailTemplate.ftl");
            StringWriter mail = new StringWriter();
            User user = new User();
            user.setUsername("Craffic");
            user.setGender("男");
            template.process(user, mail);

            String from = "392301161@qq.com";
            String to = "Craffic@126.com";
            String cc = "yl_huang@cn.yokowo.com";
            String subject = "测试Freemarker构建的邮件模板";
            mailService.sendHtmlMail(from, to, cc, subject, mail.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送freemarker模板邮件
     */
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void sendThymeleafMail(){
        Context context = new Context();
        context.setVariable("username", "Craffic");
        context.setVariable("gender", "男");
        String mail = templateEngine.process("MailTemplate.html", context);
        String from = "392301161@qq.com";
        String to = "Craffic@126.com";
        String cc = "yl_huang@cn.yokowo.com";
        String subject = "测试Freemarker构建的邮件模板";
        mailService.sendHtmlMail(from, to, cc, subject, mail.toString());
    }
}
