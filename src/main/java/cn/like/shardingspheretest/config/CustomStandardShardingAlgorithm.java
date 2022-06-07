package cn.like.shardingspheretest.config;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

/**
 * 定义历史表
 *
 * @author lance
 * @date 2022/3/5 21:28
 */
public final class CustomStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> shardingValue) {
        Date date = shardingValue.getValue();
        if (Objects.isNull(date)) {
            date = new Date();
        }
        String suffix = DateFormatUtils.format(date, "yyyy");
        for (String each : collection) {
            if (each.endsWith(suffix)) {
                return each;
            }
        }
        return collection.stream().findFirst().orElse(null);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        return collection;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "ORDER_HIS";
    }
}