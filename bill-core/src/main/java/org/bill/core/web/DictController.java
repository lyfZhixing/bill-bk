package org.bill.core.web;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.bill.common.annotation.Authorization;
import org.bill.common.model.ResultModel;
import org.bill.core.model.entity.Dict;
import org.bill.core.repository.DictMapper;
import org.bill.core.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private IDictService dictService;

    @Authorization
    @GetMapping("/query/children/code/{code}")
    public ResultModel<List<Dict>> getChildByCode(@PathVariable String code) {
        List<Dict> dicts = dictService.list(Wrappers.<Dict>lambdaQuery().eq(Dict::getParent,
                dictService.getOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getCode, code)).getId()
        ));
        return ResultModel.ok(dicts);
    }
}
