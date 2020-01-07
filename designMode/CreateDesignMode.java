import java.util.ArrayList;
import java.util.List;

/*总体来说设计模式分为三大类：

创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。

结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。

行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。

其实还有两类：并发型模式和线程池模式。
 
 */
//1.Factory Method  工厂方法  类的创建依赖工厂类，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则
//11.普通工厂模式    tips:public修饰的接口和类要在名字对应的java文件中写
interface Sender{
	public void Send();
}

class MailSender implements Sender{
	@Override
	public void Send(){
		System.out.println("普通工厂模式--mail");
	}
}

class SmsSender implements Sender{
	@Override
	public void Send(){
		System.out.println("普通工厂模式--sms");
	}
}


class SendFactory{
	public Sender produce(String type){
		if ("mail".equals(type)){
			return new MailSender();
		}
		else if ("sms".equals(type)){
			return new SmsSender();
		}
		else{
			System.out.println("please input correct type!");
		    return null;
		}

	}
}

//12.多个工厂方法模式
class SendFactorys{
	public Sender produceMail(){
		return new MailSender();
	}
	public Sender produceSms(){
		return new SmsSender();
	}
}

//13.静态工厂方法模式   ==通常
class SendFactorysStatic{
	public static Sender produceMail(){
		return new MailSender();
	}
	public static Sender produceSms(){
		return new SmsSender();
	}
}

//2.Abstract Factory  抽象工厂
// 增加一个功能：则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，无需去改动现成的代码。拓展性较好！
interface Provider{
	public Sender produce();
}

class SendMailFactory implements Provider{
	@Override
	public Sender produce(){
		return new MailSender();
	}
}

class SendSmsFactory implements Provider{
	@Override
	public Sender produce(){
		return new SmsSender();
	}
}


//3.Singleton  单例
/*单例对象能保证在一个JVM中，该对象只有一个实例存在

1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。

2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。

3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，
系统完全乱了。（比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服务器独立控制整个流程。
*/

/*
class Singleton(){
	private static Singleton instance = null;

	private Singleton(){

	}
//未考虑线程，synchronized,内部类维护，静态方法不会被继承？？？？
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */ 
/*	public Object readResolve(){
		return instance;
	}
}

*/
//4.Builder   建造者模式
//工厂类模式提供的是创建单个类的模式，而建造者模式则是将各种产品集中起来进行管理
//用来创建复合对象，所谓复合对象就是指某个类具有不同的属性
class Builder{
	private List<Sender> list = new ArrayList<Sender>();
	public void produceMailSender(int count){
		for (int i=0;i<count;i++){
			list.add(new MailSender());
		}
	}


	public void produceSmsSender(int count){
		for(int i=0;i<count;i++){
			list.add(new SmsSender());
		}
	}
}


//5.Prototype  原型模式
//模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象

class Prototype implements Cloneable{
	public Object clone() throws CloneNotSupportedException{
		Prototype proto = (Prototype) super.clone();
		return proto;
	}
}


public class CreateDesignMode{
	public static void main(String[]args){
		System.out.println("1");
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();

		System.out.println("2");
		SendFactorys factorys = new SendFactorys();
		Sender senders = factorys.produceMail();
		senders.Send();

		System.out.println("3");
		Sender sendersstatic = SendFactorysStatic.produceSms();
		sendersstatic.Send();

		System.out.println("4");
		Provider provider = new SendMailFactory();
		Sender senderabstract = provider.produce();
		senderabstract.Send();

		System.out.println("5");
		Builder builder = new Builder();
		builder.produceSmsSender(10);
	}
}