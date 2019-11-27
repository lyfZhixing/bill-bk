package org.bill.core.model.entity;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import com.baomidou.mybatisplus.annotation.TableField;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
    import org.bill.common.model.VersionEntity;
    import org.springframework.format.annotation.DateTimeFormat;

/**
* <p>
    * 
    * </p>
*
* @author lyf
* @since 2019-11-25
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class Bill extends VersionEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 公司id
            */
    private Long company;

            /**
            * 出货类型字典
            */
        @TableField("saleType")
    private Long saleType;

            /**
            * 草莓品种字典
            */
    private Long variety;

    private BigDecimal weight;

    private BigDecimal income;

            /**
            * 签章人，字典
            */
    private Long signature;

            /**
            * ture(1):收款  false(0):签章
            */
            private Boolean isMoney;
    private Long receiptType;

    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

            /**
            * 创建人id
            */
    private Long createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long updateBy;


}
