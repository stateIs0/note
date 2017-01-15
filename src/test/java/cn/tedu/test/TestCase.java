package cn.tedu.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

public class TestCase {
	
	@Test
	public void testMd5(){
		String str = "12345678你好";
		String md5 = 
			DigestUtils.md5Hex(str);
		System.out.println(md5); 
	}
	
	@Test
	public void testSaltPwd()
		throws Exception {
		String pwd = "123";
		String salt = "你吃了吗？";
		String s = 
			DigestUtils.md5Hex(pwd+salt);
		System.out.println(s);
		//2625eadfbe7fa3168f8e9cafa28aaa44
		
		//update cn_user set cn_user_password = 
		// '2625eadfbe7fa3168f8e9cafa28aaa44'
	}
}







