package redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 *<p>Title	: RedisDataSourceImpl</p>
 * @Description	: redis连接管理类
 * @author	: admin
 * @date	: 2017年11月22日上午9:29:31
 */
public class RedisDataSourceImpl implements RedisDataSource {
	private static Log log = LogFactory.getLog(RedisDataSourceImpl.class);
	
	private ShardedJedisPool shardedJedisPool;
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}
	
	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getRedisClent error", e);
		}
		return null;
	}

	public void close(ShardedJedis shardedJedis) {
		shardedJedis.close();
	}
}
