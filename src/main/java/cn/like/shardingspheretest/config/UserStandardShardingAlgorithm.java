package cn.like.shardingspheretest.config;

import java.util.Collection;
import java.util.Objects;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

/**
 * 定义历史表
 *
 * @author lance
 * @date 2022/3/5 21:28
 */
public final class UserStandardShardingAlgorithm implements StandardShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> shardingValue) {
        Integer birthday = shardingValue.getValue();
        if (Objects.isNull(birthday)) {
            birthday = 19700101;
        }
        String suffix = String.valueOf(birthday / 10000 / 5 * 5);
        for (String each : collection) {
            if (each.endsWith(suffix)) {
                return each;
            }
        }
        return collection.stream().findFirst().orElse(null);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection,
            RangeShardingValue<Integer> rangeShardingValue) {
        return collection;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "USER_HIS";
    }
}