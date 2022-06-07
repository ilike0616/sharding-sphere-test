package cn.like.shardingspheretest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.like.shardingspheretest.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    /**
     * find orderId and city
     *
     * @param orderId  orderId
     * @param interval interval
     * @return OrderEntity
     */
    OrderEntity findByOrderIdAndIntervalTime(long orderId, Date interval);

    /**
     * 根据时间查询
     *
     * @param interval interval
     * @return list
     */
    List<OrderEntity> findByIntervalTime(Date interval);
}
