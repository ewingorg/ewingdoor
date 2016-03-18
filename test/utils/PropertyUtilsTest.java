package utils;

import org.junit.Test;

import com.ewing.core.factory.SpringCtx;
import com.ewing.utils.PropertyUtils;

public class PropertyUtilsTest extends SpringCtx{
  @Test
  public void testGetProperty(){
    System.out.println(PropertyUtils.getProperty("redis.aopcache.open"));
  }

}
