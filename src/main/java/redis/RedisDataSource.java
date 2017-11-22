package redis;

import redis.clients.jedis.ShardedJedis;

/**
 * 
 *<p>Title	: RedisDataSource</p>
 * @Description	: redis 数据源接口
 * @author	: admin
 * @date	: 2017年11月22日上午9:29:21
 */
public interface RedisDataSource {
	
	/**
	 * 
	 * @Description : 获取redias连接
	 * @return
	 */
	public abstract ShardedJedis getRedisClient();

	/**
	 * 
	 * @Description : 关闭redis连接
	 * @param shardedJedis
	 */
	public void close(ShardedJedis shardedJedis);

}