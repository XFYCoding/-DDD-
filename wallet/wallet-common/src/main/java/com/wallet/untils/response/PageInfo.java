package com.wallet.untils.response;

import java.util.Optional;

public class PageInfo {

    private long index;
    private long size;
    /**
     * 总行数
     */
    private long total;
    /**
     * 当前页
     */
    private long curPage;
    /**
     * 总页数
     */
    private long totalPage;

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
        calculatePage();
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
        calculatePage();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        calculatePage();
    }

    public long getCurPage() {
        return curPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    private void calculatePage() {
        if (size > 0) {
            this.curPage = index / size + 1;
            if (total % size > 0) {
                this.totalPage = total / size + 1;
            } else {
                this.totalPage = total / size;
            }
        }
    }

    public static PageInfo build(Integer index, Integer size) {
        return new IntBuilder().setIndex(index).setSize(size).build();
    }

    public static PageInfo build(Integer index, Integer size, Integer total) {
        return new IntBuilder().setIndex(index).setSize(size).setTotal(total).build();
    }

    public static PageInfo build(Long index, Long size) {
        return new LongBuilder().setIndex(index).setSize(size).build();
    }

    public static PageInfo build(Long index, Long size, Long total) {
        return new LongBuilder().setIndex(index).setSize(size).setTotal(total).build();
    }

    public static PageInfo buildForPage(Integer curPage, Integer size) {
        return new IntBuilder().setPage(curPage, size).build();
    }

    public static PageInfo buildForPage(Long curPage, Long size) {
        return new LongBuilder().setPage(curPage, size).build();
    }

    public static Builder<Integer> newBuilder(int index, int size) {
        return new IntBuilder().setIndex(index).setSize(size);
    }

    public static Builder<Integer> newBuilder(Integer index, Integer size) {
        return new IntBuilder().setIndex(index).setSize(size);
    }

    public static Builder<Long> newBuilder(long index, long size) {
        return new LongBuilder().setIndex(index).setSize(size);
    }

    public static Builder<Long> newBuilder(Long index, Long size) {
        return new LongBuilder().setIndex(index).setSize(size);
    }

    public static abstract class Builder<T extends Number> {
        protected T index;
        protected T size;
        protected T total;
        protected int defaultIndex;
        protected int defaultSize;
        protected Integer maxIndex;
        protected Integer maxSize;

        protected Builder() {
            defaultIndex = 0;
            defaultSize = 20;
        }

        public Builder<T> setPage(T curPage, T size) {
            setSize(size);
            setIndex(convertCurPage2Index(curPage));
            return this;
        }

        public Builder<T> setIndex(T index) {
            this.index = index;
            return this;
        }

        public Builder<T> setSize(T size) {
            this.size = size;
            return this;
        }

        public Builder<T> setTotal(T total) {
            this.total = total;
            return this;
        }

        public Builder<T> setDefaultIndex(int defaultIndex) {
            this.defaultIndex = defaultIndex;
            return this;
        }

        public Builder<T> setDefaultSize(int defaultSize) {
            this.defaultSize = defaultSize;
            return this;
        }

        public Builder<T> setDefault(int defaultIndex, int defaultSize) {
            return this.setDefaultIndex(defaultIndex).setDefaultSize(defaultSize);
        }

        public Builder<T> setMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder<T> setMaxIndex(int maxIndex) {
            this.maxIndex = maxIndex;
            return this;
        }

        public Builder<T> setMax(int maxIndex, int maxSize) {
            return this.setMaxIndex(maxIndex).setMaxSize(maxSize);
        }

        public PageInfo build() {
            PageInfo pageInfo = new PageInfo();
            fill(pageInfo);
            return pageInfo;
        }

        private void fill(PageInfo pageInfo) {
            Long index = Optional.ofNullable(getLongValue(this.index)).orElse(Long.valueOf(defaultIndex));
            Long pageSize = Optional.ofNullable(getLongValue(size)).orElse(Long.valueOf(defaultSize));
            index = maxIndex == null ? index : Math.min(index, maxIndex);
            pageSize = maxSize == null ? pageSize : Math.min(pageSize, maxSize);

            pageInfo.setIndex(index);
            pageInfo.setSize(pageSize);
            pageInfo.setTotal(Optional.ofNullable(getLongValue(total)).orElse(0L));
        }

        protected abstract Long getLongValue(T value);

        protected abstract T convertCurPage2Index(T curPage);
    }

    public static class LongBuilder extends Builder<Long> {
        @Override
        protected Long getLongValue(Long value) {
            return value;
        }

        @Override
        protected Long convertCurPage2Index(Long curPage) {
            long longSize = Optional.ofNullable(size).orElse(Long.valueOf(defaultSize));
            long index = (Optional.ofNullable(curPage).orElse(1L) - 1) * longSize;
            return index;
        }
    }

    public static class IntBuilder extends Builder<Integer> {
        @Override
        protected Long getLongValue(Integer value) {
            return value == null ? null : value.longValue();
        }

        @Override
        protected Integer convertCurPage2Index(Integer curPage) {
            int intSize = Optional.ofNullable(size).orElse(defaultSize);
            int index = (Optional.ofNullable(curPage).orElse(1) - 1) * intSize;
            return index;
        }
    }
}
