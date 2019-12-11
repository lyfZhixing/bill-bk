package org.bill.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.bill.common.annotation.Authorization;
import org.bill.common.annotation.CurrentUser;
import org.bill.common.exception.ServerException;
import org.bill.common.exception.ServiceException;
import org.bill.common.model.ResultModel;
import org.bill.core.model.entity.Token;
import org.bill.core.model.entity.User;
import org.bill.core.model.vo.TokenVO;
import org.bill.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 帐户
 *
 */
@Api(value = "account")
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MapperFacade orikaMapperFacade;

//    @Autowired
//    private VerificationCodeService verificationCodeService;

    /**
     * 登录
     *
     * @param tokenVO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="Login", httpMethod="POST", notes="Login")
    public ResponseEntity<ResultModel> login(@RequestBody TokenVO tokenVO) {
        Token token;
        ResultModel<Token> model;
        try {
            token = accountService.login(tokenVO.getLoginName(), tokenVO.getPassword());
            model = ResultModel.ok(token);
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /**
     * 登出
     *
     * @param user
     * @return ResultModel
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value="Logout", httpMethod="DELETE", notes="Logout")
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        accountService.logout(user);

        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="Register", httpMethod="POST", notes="注册")
    public ResponseEntity<ResultModel> register(@RequestBody User user) {
        ResultModel model;
        try {
            accountService.register(user, null);
            model = ResultModel.ok();
        } catch (ServiceException e) {
            model = ResultModel.fail(e.getCode(), e.getMessage());
            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

//    @RequestMapping(value = "/getSeccode", method = RequestMethod.GET)
//    @ApiOperation(value="获取验证码", httpMethod="GET", notes="获取验证码")
//    public ResponseEntity<ResultModel> getSeccode(@RequestParam("email") String email) {
//        ResultModel model;
//        try {
//            verificationCodeService.sendSeccode(email);
//            model = ResultModel.ok();
//        } catch (ServiceException e) {
//            model = ResultModel.fail(e.getCode(), e.getMessage());
//            throw new ServerException(model, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(model, HttpStatus.OK);
//    }
}
