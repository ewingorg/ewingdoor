package com.ewing.core.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ewing.busi.resource.model.WebResource;

public class SerialTest {
    @Test
    public void test() { 
        try {
            WebResource resource2 = new WebResource();
            resource2.setId(8);
            resource2.setCreateTime(new Date());
            byte[] b = SerializingTranscoder.serialize(resource2);
            WebResource rDto = (WebResource)SerializingTranscoder.deserialize(b);
            System.out.println(rDto);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void test2() { 
        try {
            List<WebResource> list = new ArrayList<WebResource>();
            WebResource resource2 = new WebResource();
            resource2.setId(8);
            resource2.setCreateTime(new Date());
            WebResource resource1 = new WebResource();
            resource1.setId(9);
            resource1.setCreateTime(new Date());
            list.add(resource1);
            list.add(resource2);
            byte[][] bb = new byte[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                 byte[] b = SerializingTranscoder.serialize(list.get(i));
                 System.out.println(b.toString());
                 bb[i] = b;
            }
            System.out.println(1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
