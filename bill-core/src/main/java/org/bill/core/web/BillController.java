package org.bill.core.web;


import lombok.extern.slf4j.Slf4j;
import org.bill.common.exception.ServiceException;
import org.bill.common.model.ResultModel;
import org.bill.core.model.entity.Bill;
import org.bill.core.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public ResponseEntity<ResultModel> addBill(@RequestBody Bill bill) {
        ResultModel resultModel;
        try {
            //  暂时写死
            bill.setCompany(1L);
            bill.setCreateTime(LocalDateTime.now());
            bill.setCreateBy(1L);
            billService.save(bill);
            resultModel = ResultModel.ok();
        } catch (Exception e) {
            log.error("新增失败");
            resultModel = ResultModel.fail(500,e.getMessage());
        }

        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }
}
