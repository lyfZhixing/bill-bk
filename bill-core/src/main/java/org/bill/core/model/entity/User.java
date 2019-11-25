package org.bill.core.model.entity;

    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
    import org.bill.common.model.VersionEntity;

/**
* <p>
    * 
    * </p>
*
* @author lyf
* @since 2019-11-25
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class User extends VersionEntity {

    private static final long serialVersionUID = 1L;

    private Long company;

    private String name;

    private String loginName;

    private String password;

    private String sex;

    private String phone;

    private String remark;


}
