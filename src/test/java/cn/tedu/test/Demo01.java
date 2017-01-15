package cn.tedu.test;

public class Demo01 {
	public static void main(String[] args) {
		//Girl g1 = new Girl();
		//Girl g2 = new Girl();
		//Girl g = Girl.one;
		//Girl.one = null;
		Girl g1 = Girl.getOne();
		Girl g2 = Girl.getOne();
		
		Friend f = Friend.getFriend();
	}
}

//饱汉， 懒惰式
class Friend{
	private static Friend friend;
	private Friend() {
	}
	public synchronized static Friend getFriend() {
		if(friend==null){
			friend = new Friend();
		}
		return friend;
	}
}

//饿汉式单列模式， 非懒惰式
class Girl{
	private static Girl one = new Girl(); 
	private Girl() {
	}
	public static Girl getOne() {
		return one;
	}
}


