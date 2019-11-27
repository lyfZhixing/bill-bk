package org.bill.core.repository;

import org.bill.core.model.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.bill.core.model.vo.BillVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyf
 * @since 2019-11-25
 */
public interface BillMapper extends BaseMapper<Bill> {

    List<BillVO> selectListVo(Bill bill);

    Map<String, BigDecimal> getCOuntDay(Bill bill);
}
