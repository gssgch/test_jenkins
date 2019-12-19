package com.zhansheng.management_system.controller;

import com.zhansheng.api.Industrialcontrol.MailControllerApi;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.MailServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xufu
 * @description 告警邮件发送处理
 * @date 2019/9/29 14:11
 */

@RestController
@RequestMapping("/Mail")
public class MailController implements MailControllerApi {

    @Autowired
    private MailServiceImpl mailService;

    @Override
    @PostMapping("/sendSimpleMail")
    public void sendSimpleMail(String to, String subject, String content) {
        mailService.sendSimpleMail(to, subject, content);
    }

    @Override
    @PostMapping("/sendHtmlMail")
    public void sendHtmlMail(String to, String subject, String content) {
        mailService.sendHtmlMail(to, subject, content);
    }

    @Override
    @PostMapping("/sendAttachmentsMail")
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        mailService.sendAttachmentsMail(to, subject, content, filePath);
    }

}
