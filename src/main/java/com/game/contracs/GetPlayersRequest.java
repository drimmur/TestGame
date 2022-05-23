package com.game.contracs;

import com.game.controller.PlayerOrder;


public class GetPlayersRequest extends PlayerFilters{

    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;


    public PlayerOrder getOrder() {
        return order;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
