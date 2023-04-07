
package com.wallet.untils.ddd;

import java.util.Date;

/**
 * @author 小肥瑜
 */
public interface EntityMeta<ID> {
    ID getId();

    Long getVersion();

    String getCreateUser();

    String getUpdateUser();

    Date getCreateTime();

    Date getUpdateTime();

    boolean isEnabled();
}
