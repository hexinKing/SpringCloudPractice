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
public class AccountDTO implements Serializable {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 金额
     */
    private Long money;


}
