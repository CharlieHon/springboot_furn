package com.charlie.furn.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 1. 如果Furn类名和 furn表名 不能对应，可以通过之前用过的 @TableName
 * 2.
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
    private Integer id;
    private String name;
    private String maker;
    private Double price;
    private Integer sales;
    private Integer stock;
}
