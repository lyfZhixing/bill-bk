package org.bill.core.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bill.common.model.VersionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BillVO extends VersionEntity {

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

    private String saleTypeName;

    /**
     * 草莓品种字典
     */
    private Long variety;
    private String varietyName;

    private BigDecimal weight;

    private BigDecimal income;

    /**
     * 签章人，字典
     */
    private Long signature;
    private String signatureName;

    /**
     * ture(1):收款  false(0):签章
     */
    private Boolean isMoney;
    private Long receiptType;
    private String receiptTypeName;

    private String remark;

    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;

}
