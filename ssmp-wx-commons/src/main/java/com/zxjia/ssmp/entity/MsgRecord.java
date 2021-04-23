package com.zxjia.ssmp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("msg_record")
@Data
@Accessors(chain = true)
public class MsgRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long category;
    private String categoryName;
    private String mobile;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date sentTime;
    private Boolean result;
    private String resultDesc;
    private Date createTime;
}
