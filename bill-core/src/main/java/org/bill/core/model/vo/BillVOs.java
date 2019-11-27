package org.bill.core.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class BillVOs {
    // 收入合计
    private BigDecimal inCome;

    // 产量合计
    private BigDecimal weight;

    private List<BillVO> billVOs;
}
