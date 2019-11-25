package org.bill.core.service.impl;

import org.bill.core.model.entity.User;
import org.bill.core.repository.UserMapper;
import org.bill.core.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
