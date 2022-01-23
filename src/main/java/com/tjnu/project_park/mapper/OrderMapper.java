package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.Order;

/**
 * @param
 * @return
 */
public interface OrderMapper {
    public Integer insert(Order order);
    public Order findOrderByUsername(String username);
}
