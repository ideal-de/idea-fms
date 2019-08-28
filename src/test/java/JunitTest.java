import com.javakc.fms.file.dao.FileDao;
import com.javakc.fms.file.entity.FileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class JunitTest {

    @Autowired
    private FileDao fileDao;

    @Test
    public void  insert(){
        FileEntity entity=new FileEntity();
        entity.setId("1");
        entity.setName("name");
        entity.setCtime(new Timestamp(System.currentTimeMillis()));
        entity.setPath("G:");
        entity.setPreview(10);
        entity.setDownnum(20);
        entity.setTotal(2655444L);
        entity.setSuffix("mp4");

        fileDao.insert(entity);
    }
}
