package com.atguigu.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 总库存
     */
    @TableField("total")
    private Integer total;

    /**
     * 已用库存
     */
    @TableField("used")
    private Integer used;

    /**
     * 剩余库存
     */
    @TableField("residue")
    private Integer residue;


}
