package com.zhansheng.api.Industrialcontrol;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author xufu
 * @description
 * @date 2019/9/29 14:05
 */
@Api(value = "告警邮件管理接口", description = "告警邮件管理接口，提供发送邮件方法")
public interface MailControllerApi {

    @ApiOperation("发送文本邮件")
    public void sendSimpleMail(String to, String subject, String content);

    @ApiOperation("发送HTML邮件")
    public void sendHtmlMail(String to, String subject, String content);

    @ApiOperation("发送带附件的邮件")
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

}
