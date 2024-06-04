package com.charlie.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 1. 如果Furn类名和 furn表名 不能对应，可以通过之前用过的 @TableName
 */
@Data
//@TableName("furn")
public class Furn {
    /**
     * CREATE TABLE `furn` (
     * `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
     * `name` VARCHAR(64) NOT NULL,		# id
     * `maker` VARCHAR(64) NOT NULL,		# 厂商
     * `price` DECIMAL(11, 2) NOT NULL,	# 价格
     * `sales` INT(11) NOT NULL,		# 销量
     * `stock` INT(11) NOT NULL		# 库存
     * );
     */
    // 使用 @TableId 注解，标识表主键。
    // type指定主键类型：AUTO表示自增长
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 如果是对字符串进行非空校验，应该使用@NotEmpty注解
    @NotEmpty(message = "请输入家具名")
    private String name;

    @NotEmpty(message = "请输入厂商名")
    private String maker;

    // @NotNull支持任何数据类型
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "价格不能小于0")
    private BigDecimal price;

    @NotNull(message = "请输入销量")
    @Range(min = 0, message = "销量不能小于0")
    private Integer sales;

    @NotNull(message = "请输入库存")
    @Range(min = 0, message = "库存不能小于0")
    private Integer stock;
}
