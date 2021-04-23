package com.zxjia.ssmp.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.zxjia.ssmp.dto.MemberRequest;
import com.zxjia.ssmp.entity.MsgRecord;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Tencent Cloud Sms Sendsms
 * https://cloud.tencent.com/document/product/382/38778
 */
@Component
public class SendSms {

    @Autowired
    MsgService msgService;
    @Value("${sms.secretId}")
    private String accessKeyId;
    @Value("${sms.secretKey}")
    private String accessKeySecret;
    @Value("${sms.appid}")
    private String appId;
    @Value("${sms.sign}")
    private String sign;

    public boolean sendSms(MemberRequest memberRequest) {
        try {
            Credential cred = new Credential(accessKeyId, accessKeySecret);
            // 实例化一个 http 选项，可选，无特殊需求时可以跳过
            HttpProfile httpProfile = new HttpProfile();
            /* SDK 默认使用 POST 方法。
             * 如需使用 GET 方法，可以在此处设置，但 GET 方法无法处理较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK 有默认的超时时间，非必要请不要进行调整 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名* 例如 SMS 的上海金融区域名为 sms.ap-shanghai-fsi.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            /* 非必要步骤:  * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK 默认用 TC3-HMAC-SHA256 进行签名  * 非必要请不要修改该字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化 SMS 的 client 对象* 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppid(appId);
            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
            req.setSign(sign);
            /* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */
            req.setTemplateID(memberRequest.getTemplate());
            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]* 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
            String[] phoneNumbers = {"+86" + memberRequest.getMobile()};
            req.setPhoneNumberSet(phoneNumbers);
            /* 模板参数: 若无模板参数，则设置为空*/
            String[] templateParams = {memberRequest.getMessage()};
            req.setTemplateParamSet(templateParams);
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);
            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

            msgService.saveMessage(ObjectUtil.initObject(new MsgRecord())
                    .init(v -> v.setCategory(1L))
                    .init(v -> v.setCategoryName("登录注册"))
                    .init(v -> v.setContent(memberRequest.getMessage()))
                    .init(v -> v.setCreateTime(new Date()))
                    .init(v -> v.setMobile(memberRequest.getMobile()))
                    .init(v -> v.setResultDesc(SendSmsResponse.toJsonString(res)))
                    .init(v -> v.setSentTime(new Date())).getObject());
        } catch (TencentCloudSDKException e) {
            throw new BusinessException(e.getMessage());
        }
        return true;
    }

}