package ru.mif.fortunewheel.dto;

import ru.mif.fortunewheel.domain.PersistentObject;

import java.util.Collection;

public class Page<ENTITY extends PersistentObject> {

    private final long number;
    private final long size;
    private final long totalPages;
    private final long totalCount;
    private final Collection<Data<ENTITY>> items;

    public Page(long number, long size, long totalPage, long totalCount, Collection<Data<ENTITY>> items) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPage;
        this.totalCount = totalCount;
        this.items = items;
    }

    public Page(org.springframework.data.domain.Page page, Collection<Data<ENTITY>> items) {
        this(
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                items);
    }

    public long getNumber() {
        return number;
    }

    public long getSize() {
        return size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Collection<Data<ENTITY>> getItems() {
        return items;
    }
}
