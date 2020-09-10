package com.cwj.ssm.template.framework.redis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface ICache {

    /** 以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     * 
     * @param key
     * @return */
    long ttl(String key);
    
    /**
     * 重设过期时间
     */
    boolean expire(String key, long timeout, TimeUnit timeUnit);

    /** 查找所有符合给定模式 pattern的 key
     * 
     * @param pattern
     * @return */
    Set<String> keys(final String pattern);

    /** 写入缓存
     * <p>
     * 设置一个key-value（将value关联到 key）
     * </p>
     * 
     * @param key
     * @param value
     * @return */
    boolean set(final String key, final Object value);

    /** 写入缓存
     * <p>
     * 设置key-value和超时时间（秒）
     * </p>
     * 
     * @param key
     * @param value
     * @param expireTime （单位：秒）
     * @return */
    boolean set(final String key, final Object value, final Long expireTime);

    /** 读取缓存，返回 key所关联的值
     * 
     * @param key
     * @return */
    Object get(final String key);

    /** 读取缓存，返回Class<T>对象
     * 
     * @param key
     * @param t
     * @return */
    <T> T get(final String key, final Class<T> t);

    /** 删除key对应的value
     * 
     * @param key */
    void remove(final String key);

    /** 批量删除keys对应的value
     * 
     * @param keys */
    void remove(final String... keys);

    /** 批量删除符合给定模式 pattern的key对应value
     * 
     * @param pattern */
    void removePattern(final String pattern);

    /** 判断缓存中是否有对应的value
     * 
     * @param key
     * @return */
    boolean exists(final String key);

    // ============ Hash（哈希表）=============

    /** 将哈希表 key中的域 field的值设为 value
     * 
     * @param key
     * @param field
     * @param value */
    void hset(final String key, final String field, final Object value);

    /** 返回哈希表 key中给定域 field的值
     * 
     * @param key
     * @param field
     * @return */
    Object hget(final String key, final String field);

    /** 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * 
     * @param key
     * @param fields */
    void hdel(final String key, final Object... fields);

    /** 返回哈希表 key中，所有的域和值。
     * 
     * @param key
     * @return */
    Map<Object, Object> hgetall(final String key);

    // ===============List（列表）==============

    /** 将一个值 value插入到列表 key的表头
     * 
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。 */
    long lpush(final String key, final String value);

    /** 移除并返回列表 key的头元素。
     * 
     * @param key
     * @return 列表key的头元素。 */
    Object lpop(final String key);

    /** 将一个值 value插入到列表 key的表尾(最右边)
     * 
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。 */
    long rpush(String key, String value);

    /** 移除并返回列表 key的尾元素
     * 
     * @param key
     * @return 列表key的头元素。 */
    Object rpop(final String key);

    // =============== Set（集合）===============
    /** 将一个 member元素加入到集合 key当中，已经存在于集合的 member元素将被忽略
     * 
     * @param key
     * @param member */
    void sadd(final String key, final String member);

    /** 返回集合 key 中的所有成员
     * 
     * @param key
     * @return */
    Set<Object> smemebers(final String key);

    // ============= SortedSet（有序集合）=================
    /** 将一个 member元素及其 score值加入到有序集 key当中
     * 
     * @param key
     * @param score
     * @param member */
    void zadd(String key, double score, String member);

    /** 返回有序集 key中，指定区间内的成员
     * 
     * @param key
     * @param start
     * @param stop
     * @return */
    Set<Object> zrange(final String key, final double start, final double stop);

}
