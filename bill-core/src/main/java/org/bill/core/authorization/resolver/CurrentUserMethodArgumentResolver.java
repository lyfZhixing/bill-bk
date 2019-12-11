package org.bill.core.authorization.resolver;

import org.bill.common.annotation.CurrentUser;
import org.bill.common.constant.Constants;
import org.bill.core.model.entity.User;
import org.bill.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 *
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(User.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);

    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
            throws Exception {
        Object source = nativeWebRequest.getAttribute(Constants.CURRENT_USER_ID,
                            RequestAttributes.SCOPE_REQUEST);
        if (source == null) {
            return null;
        }

        long currentUserId = (long)source;
        if (currentUserId != 0) {
            return userService.getById(currentUserId);
        }
        // 400 - Bad Request
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}
