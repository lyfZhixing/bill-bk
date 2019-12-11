package org.bill.core.web;


import lombok.extern.slf4j.Slf4j;
import org.bill.common.annotation.Authorization;
import org.bill.common.model.ResultModel;
import org.bill.core.model.entity.Bill;
import org.bill.core.model.vo.BillVOs;
import org.bill.core.service.IBillService;
import org.bill.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyf
 * @since 2019-11-25
 */
@Slf4j
@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private IBillService billService;

    @Autowired
    private IUserService userService;

    @Authorization
    @PostMapping("/add")
    public ResponseEntity<ResultModel> addBill(@RequestBody Bill bill) {
        ResultModel resultModel;
        try {

            if (bill.getCreateBy() == null || bill.getCreateBy() == 0) {
                return new ResponseEntity<>(ResultModel.fail(500, "创建人为空"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            bill.setCompany(userService.getById(bill.getCreateBy()).getCompany());
            bill.setCreateTime(LocalDateTime.now());
            billService.save(bill);
            resultModel = ResultModel.ok();
        } catch (Exception e) {
            log.error("新增失败");
            resultModel = ResultModel.fail(500,e.getMessage());
        }
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @Authorization
    @GetMapping("/list")
    public ResultModel list(Bill bill) {
        if (bill.getCreateBy() == null || bill.getCreateBy() == 0) {
            return ResultModel.fail(500, "创建人为空");
        }
        bill.setCompany(userService.getById(bill.getCreateBy()).getCompany())
                .setCreateBy(null);
        BillVOs billVo = billService.selectListVo(bill);
        return ResultModel.ok(billVo);
    }
}
