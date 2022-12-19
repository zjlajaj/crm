package com.zjl.mail;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Mail {

    /** 接受者  */
    @NotBlank(message = "接收者不可以为空，格式xxx@qq.com")
    private String receiver;

    /** 主题 */
    @NotBlank(message = "主题不可以为空")
    private String subject;

    /** 发送 消息*/
    @NotBlank(message = "消息不可以为空")
    private String text;
}
