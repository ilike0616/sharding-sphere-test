package cn.like.shardingspheretest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.like.shardingspheretest.entity.OrderEntity;
import cn.like.shardingspheretest.repository.OrderRepository;

@SpringBootTest
public class OrderRepositoryTests {
    private final static String[] CITIES = { "shanghai", "beijing" };
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Date[] dates = create();

        IntStream.range(0, 20).forEach(i -> {
            OrderEntity order = new OrderEntity();
            order.setOrderId(System.nanoTime() + i);
            order.setAddressId(i);
            order.setCity(CITIES[i % 2]);
            order.setUserId(Math.abs(random.nextInt()));
            order.setIntervalTime(dates[i % 10]);
            order.setCreator("user.0" + i);
            order.setCreateTime(new Date());
            order.setUpdater(order.getCreator());
            order.setUpdateTime(order.getCreateTime());
            orderRepository.save(order);
        });
    }

    @Test
    void findOne() {
        long orderId = 397617599063125L;
        Date[] dates = create();
        OrderEntity orderEntity = orderRepository.findByOrderIdAndIntervalTime(orderId, dates[0]);
        System.out.println("===>{}");
        System.out.println(orderEntity);
    }

    @Test
    void findByInterval() {
        Date[] dates = create();
        List<OrderEntity> list = orderRepository.findByIntervalTime(dates[0]);

        // log.info("===>{}", !list.isEmpty() ? list.size() : 0);
        // log.info("===>{}", list);
    }

    private Date[] create() {
        Date[] dates = new Date[10];

        try {
            Date date = DateUtils.parseDate("2022-02-01", "yyyy-MM-dd");
            IntStream.range(0, 10).forEach(i -> {
                dates[i] = DateUtils.addYears(date, i);
            });
        } catch (ParseException e) {
            System.out.println("date parse fail: ");
            System.out.println(e);
        }
        return dates;
    }
}
