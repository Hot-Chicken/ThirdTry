import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class mapTest {

	private static map maptest = new map();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetRandom() {
		maptest.setRandom();
		int result = 0;
		//表格密度检验
		double count = 0;
		for(int m=0;m<maptest.World.length;m++)                //二重循环，生成随机序列
			for(int n=0;n<maptest.World[0].length;n++)
				if(maptest.tempStatus[m][n] == 1)
					count++;
		double densityForEx = count/192;
		System.out.println(densityForEx);
		if(-0.1 <densityForEx - maptest.density && densityForEx - maptest.density<0.1)
			result = 1;
		
		assertEquals(1,result);	
	}

	@Test
	public void testClickCheck2() {
		fail("尚未实现");
	}

}
