package org.bill.core.service.impl;

import org.bill.core.model.entity.Company;
import org.bill.core.repository.CompanyMapper;
import org.bill.core.service.ICompanyService;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
