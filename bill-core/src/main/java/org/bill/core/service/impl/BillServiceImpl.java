package org.bill.core.service.impl;

import org.bill.core.model.entity.Bill;
import org.bill.core.repository.BillMapper;
import org.bill.core.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
