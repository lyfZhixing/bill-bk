package org.bill.core.service;

import org.bill.core.model.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import org.bill.core.model.vo.BillVO;
import org.bill.core.model.vo.BillVOs;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyf
 * @since 2019-11-25
 */
public interface IBillService extends IService<Bill> {

    BillVOs selectListVo(Bill bill);
}
