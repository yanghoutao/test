import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestList {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 添加值  往右边添加数据
     */
    @Test
    public void testSetValue1(){
        redisTemplate.boundListOps("nameList1").rightPush("张嘉琪");
        redisTemplate.boundListOps("nameList1").rightPush("张宏图");
        redisTemplate.boundListOps("nameList1").rightPush("张士兵");
    }

    /**
     * 获取值
     */
    @Test
    public void testGetValue1(){
        List list1 = redisTemplate.boundListOps("nameList1").range(0, 10);
        System.out.println(list1);
    }
    /**
     * 往左边添加数据
     */
    @Test
    public void testSetValue2(){
        redisTemplate.boundListOps("nameList2").leftPush("张嘉琪");
        redisTemplate.boundListOps("nameList2").leftPush("张宏图");
        redisTemplate.boundListOps("nameList2").leftPush("张士兵");
    }

    /**
     * 获取值
     */
    @Test
    public void testGetValue2(){
        List list2 = redisTemplate.boundListOps("nameList2").range(0, 10);////第二个参数给-1，表示最后一个元素
        System.out.println(list2);
    }
    /**
     * 获取指定索引数据
     */
    @Test
    public void testSearchByIndex(){
        String s = (String) redisTemplate.boundListOps("nameList1").index(1);
        System.out.println(s);
    }
    /**
     * 删除值
     */
    @Test
    public void testRemoveByIndex(){
         redisTemplate.boundListOps("nameList1").remove(5, "张士兵");
    }
}
