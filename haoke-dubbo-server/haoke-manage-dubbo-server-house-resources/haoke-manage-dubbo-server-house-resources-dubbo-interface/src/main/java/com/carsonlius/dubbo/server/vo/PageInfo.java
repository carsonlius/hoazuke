package com.carsonlius.dubbo.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class PageInfo<T> implements java.io.Serializable {
    private static final long serialVersionUID = 5809229587163257165L;
    private Integer total;

    private Integer pageNum;

    private Integer pageSize;

    /**
     * 数据列表
     * */
    private List<T> records = Collections.emptyList();
}
