package com.zxjia.ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.entity.MsgRecord;
import com.zxjia.ssmp.mapper.MsgMapper;
import com.zxjia.ssmp.service.MsgService;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, MsgRecord> implements MsgService {

    @Override
    public boolean saveMessage(MsgRecord msgRecord) {
        return save(msgRecord);
    }
}
