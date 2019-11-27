package org.bill.core.service.impl;

import org.bill.core.model.entity.Bill;
import org.bill.core.model.vo.BillVO;
import org.bill.core.model.vo.BillVOs;
import org.bill.core.repository.BillMapper;
import org.bill.core.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyf
 * @since 2019-11-25
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Autowired
    private BillMapper billMapper;
    @Override
    public BillVOs selectListVo(Bill bill) {
        BillVOs billVOs = new BillVOs();
        List<BillVO> billVOS = billMapper.selectListVo(bill);
        Map<String, BigDecimal> count = billMapper.getCOuntDay(bill);
        billVOs.setBillVOs(billVOS)
                .setInCome(count.get("inCome"))
                .setWeight(count.get("weight"));
        return billVOs;
    }
}
