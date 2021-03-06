package com.zxjia.ssmp.handler;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zxjia.ssmp.build.TextBuilder;
import com.zxjia.ssmp.constant.ConfigConstant;
import com.zxjia.ssmp.entity.WxUser;
import com.zxjia.ssmp.mapper.WxUserMapper;
import com.zxjia.ssmp.utils.LocalDateTimeUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    WxUserMapper wxUserMapper;

    public static void setWxUserValue(WxUser wxUser, WxMpUser userWxInfo) {
        wxUser.setAppType(ConfigConstant.WX_APP_TYPE_2);
        ;
        wxUser.setSubscribe(ConfigConstant.SUBSCRIBE_TYPE_YES);
        wxUser.setSubscribeScene(userWxInfo.getSubscribeScene());
        wxUser.setSubscribeTime(LocalDateTimeUtils.timestamToDatetime(userWxInfo.getSubscribeTime() * 1000));
        wxUser.setOpenId(userWxInfo.getOpenId());
        wxUser.setNickName(userWxInfo.getNickname());
        wxUser.setSex(String.valueOf(userWxInfo.getSex()));
        wxUser.setCity(userWxInfo.getCity());
        wxUser.setCountry(userWxInfo.getCountry());
        wxUser.setProvince(userWxInfo.getProvince());
        wxUser.setLanguage(userWxInfo.getLanguage());
        wxUser.setRemark(userWxInfo.getRemark());
        wxUser.setHeadimgUrl(userWxInfo.getHeadImgUrl());
        wxUser.setUnionId(userWxInfo.getUnionId());
        wxUser.setGroupId(JSONUtil.toJsonStr(userWxInfo.getGroupId()));
        wxUser.setTagidList(userWxInfo.getTagIds());
        wxUser.setQrSceneStr(userWxInfo.getQrSceneStr());
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("??????????????? OPENID: " + wxMessage.getFromUser());

        // ??????????????????????????????
        try {
            WxMpUser userWxInfo = weixinService.getUserService()
                    .userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO ??????????????????????????????????????????
                WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery()
                        .eq(WxUser::getOpenId, userWxInfo.getOpenId()));
                if (wxUser == null) {//???????????????
                    wxUser = new WxUser();
                    wxUser.setSubscribeNum(1);
                    setWxUserValue(wxUser, userWxInfo);
                    wxUserMapper.insert(wxUser);
                } else {//???????????????
                    wxUser.setSubscribeNum(wxUser.getSubscribeNum() + 1);
                    this.setWxUserValue(wxUser, userWxInfo);
                    wxUserMapper.updateById(wxUser);
                }
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("?????????????????????????????????????????????");
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("????????????", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
