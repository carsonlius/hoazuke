package com.carsonlius.dubbo.server.popj;

import lombok.Data;

import java.util.Date;

/**
 * @author carsonlius
 */
@Data
public abstract class BasePojo implements java.io.Serializable {
    private Date created;
    private Date updated;
}
