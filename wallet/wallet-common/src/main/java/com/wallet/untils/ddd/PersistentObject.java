package com.wallet.untils.ddd;

/**
 * 持久对象接口
 *
 * 通常包含一个从持久对象转换成领域对象的实例方法，可命名为：D toDomainObject(...)；
 * 以及一个从领域对象转换成持久对象的<b>静态方法</b>，可命令为：static T from(D d)。
 *
 * @param <D> 所映射的领域对象
 * @author 小肥瑜
 */
public interface PersistentObject<D> {
}
