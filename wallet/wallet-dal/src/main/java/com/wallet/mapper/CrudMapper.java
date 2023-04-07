package com.wallet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.untils.ddd.BaseEntity;
import com.wallet.untils.ddd.BasePersistentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 基础Mapper
 *
 * @author oopsliu
 */
public interface CrudMapper<ID, E extends BaseEntity<ID>, POJO extends BasePersistentEntity<ID, E>> extends BaseMapper<E> {

    /**
     * 批量插入
     *
     * @param records 记录集合
     * @return 影响行数
     */
    int batchInsert(@Param("records") List<POJO> records);

    /**
     * 可选更新
     *
     * @param record     记录
     * @param preVersion 乐观锁
     * @return 影响行数
     */
    int updateSelectivityById(@Param("record") POJO record,
                              @Param("preVersion") Long preVersion);

    /**
     * 全量更新
     *
     * @param record     record
     * @param preVersion 乐观锁
     * @return 影响行数
     */
    int updateById(@Param("record") POJO record,
                   @Param("preVersion") Long preVersion);

    /**
     * 更新是否有效字段
     *
     * @param ids        ids
     * @param enabled    有效标识
     * @param updateTime 更新时间
     * @param updateUser 更新用户
     * @return 更新条数
     */
    int updateEnabledByIds(@Param("ids") Set<ID> ids,
                           @Param("enabled") Boolean enabled,
                           @Param("updateTime") Date updateTime,
                           @Param("updateUser") String updateUser);
}
