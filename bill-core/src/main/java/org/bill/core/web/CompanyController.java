package org.bill.core.web;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.bill.common.annotation.Authorization;
import org.bill.common.model.ResultModel;
import org.bill.core.model.entity.Company;
import org.bill.core.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyf
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list")
    public ResultModel<List<Company>> getCompanys() {
        return ResultModel.ok(companyService.list(Wrappers.<Company>lambdaQuery().eq(Company::getDeleted, 0)));
    }
}
