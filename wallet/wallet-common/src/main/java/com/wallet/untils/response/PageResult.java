package com.wallet.untils.response;

import java.util.List;

/**
 * @author 小肥瑜
 */
public class PageResult<T> {
    private final List<T> items;
    private final PageInfo pageInfo;

    private PageResult(List<T> items, PageInfo pageInfo) {
        this.items = items;
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<T> getItems() {
        return items;
    }

    public static <T> PageResult<T> build(List<T> items, PageInfo pageInfo) {
        return new PageResult<>(items, pageInfo);
    }
}
