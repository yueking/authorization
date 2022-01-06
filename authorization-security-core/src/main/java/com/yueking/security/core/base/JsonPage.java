package com.yueking.security.core.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.yueking.security.core.entity.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 包装默认的 Page 数据 为了其内容支持 JsonView 分组
 * @param <T>
 */
public class JsonPage<T> extends PageImpl {
    public JsonPage(final List<T> content, final Pageable pageable,final long total) {
        super(content, pageable, total);
    }

    public JsonPage(final List<T> content) {
        super(content);
    }

    public JsonPage(final Page<T> page, final Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    @JsonView(Base.SimpleView.class)
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(Base.SimpleView.class)
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @JsonView(Base.SimpleView.class)
    public boolean hasNext() {
        return super.hasNext();
    }

    @JsonView(Base.SimpleView.class)
    public boolean isLast() {
        return super.isLast();
    }

    @JsonView(Base.SimpleView.class)
    public boolean hasContent() {
        return super.hasContent();
    }

    @JsonView(Base.SimpleView.class)
    public List<T> getContent() {
        return super.getContent();
    }
}
