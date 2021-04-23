package com.zxjia.ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxjia.ssmp.entity.MsgRecord;

public interface MsgService extends IService<MsgRecord> {

    boolean saveMessage(MsgRecord msgRecord);
}
