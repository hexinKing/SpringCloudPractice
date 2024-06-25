package com.atguigu.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
@Schema(description = "订单实体")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "产品id")
    @TableField("product_id")
    private Long productId;

    @Schema(description = "数量")
    @TableField("count")
    private Integer count;

    @Schema(description = "金额")
    @TableField("money")
    private Long money;

    /**
     * 0创建中 ，1结束
     */
    @Schema(description = "订单状态")
    @TableField("status")
    private Integer status;


}
