import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class testHash {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加值
     */
    @Test
    public void setHashValue() {
        redisTemplate.boundHashOps("nameHash").put("a", "老大");
        redisTemplate.boundHashOps("nameHash").put("b", "老二");
        redisTemplate.boundHashOps("nameHash").put("c", "老三");
    }

    /**
     * 获取值
     */
    @Test
    public void testGetKeys() {
        Set hash = redisTemplate.boundHashOps("content").keys();
        System.out.println(hash);
        List list = redisTemplate.boundHashOps("content").values();
        System.out.println(list);
    }
    /**
     * 删除指定小keys
     */
    @Test
    public void testRemoveValueByKey(){
        redisTemplate.boundHashOps("content").delete("categoryId");
    }

}
