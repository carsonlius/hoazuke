package com.carsonlius.dubbo.server.popj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tb_ad")
public class Ad extends BasePojo  {

    private static final long serialVersionUID = 6220298050200362351L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    // 广告类型
    private Integer type;

    // 描述
    private String title;

    // 图片地址
    private String url;
}
