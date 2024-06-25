package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hexin
 * @since 2024-06-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO implements Serializable {

    /**
     * 产品id
     */
    private Long productId;
    /**
     * 产品数量
     */
    private Integer count;


}
