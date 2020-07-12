/*
* Java 异常处理
* 1.异常：程序在运行中发生了不正常的情况
* 2.异常信息由JVM打印输出到控制台
* 3.★异常在java中以类的形式存在 可以new对象
* 4.异常继承结构
*   Object -> Throwable(类) -> Error(不可处理!) -> VirtualMachineError -> StackOverFlowError
*                          ↓                 ↓ -> IOError
*                          ↓
*                          ↓-> Exception(可以处理!) -> RuntimeException(运行时异常，可以不处理)
*                                                  ↓-> Exception(编译时异常，必须处理)
* 常见的运行时异常:NullPointerException ClassCastException NumberFormatException(<-IllegalArgumentException)
* 常见的编译时异常:Exception的直接子类
*
* 5.★①编译时异常也是运行阶段发生的!但是编写程序期间必须处理 ②编译时异常发生的概率比较高
* 6.异常处理的两种方式 ①throws - 【异常上抛】②try catch - 【异常捕捉】
* 7.try中某语句出现异常 该行后面的代码不会执行
* 8.finally子句的使用 finally中的代码块一定会执行 常用于完成资源的释放/关闭
* 9.退出JVM finally不执行 (System.exit(0);)
* 10.重写后的方法抛出的异常不能更多(更宽泛)
*
*
* */
public class MyException {
    public static void main(String[] args) throws SelfException {
        /*
        System.out.println(100 / 0);         //Exception in thread "main" java.lang.ArithmeticException: / by zero
        System.out.println("Hello World!");  //由于上面异常 此语句不打印输出
        */
        SelfException e = new SelfException("自定义异常!");
        throw e;
        /*
        System.out.println(e.getMessage());
        e.printStackTrace();
        */
    }
}

class ExceptionTest01 {
    public static void main(String[] args) {
        /*try catch*/
        try {
            dosome();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dosome() throws ClassNotFoundException {
        System.out.println("do something!");
    }
}

class ExceptionTest02 {
    public static void main(String[] args) throws ClassNotFoundException {
        /*throws*/
        dosome();
    }

    public static void dosome() throws ClassNotFoundException {
        System.out.println("do something!");
    }
}

/*自定义异常
* 第一步：编写一个类继承Exception或RuntimeException
* 第二步：提供两个构造方法 ①无参 ②带有String参数
* */
class SelfException extends Exception {
    public SelfException() {
        super();
    }
    public  SelfException(String s) {
        super(s);
    }
}

/*
* 作业:
* 编写程序模拟用户注册:
* 1.程序开始执行时，提示用户输入"用户名"和"密码"信息
* 2.输入信息之后，后台java程序模拟用户注册
* 3.注册时用户名要求长度在[6-14]之间，否则异常
*
* 注意:
* 1.完成注册的方法放到一个单独类中
* 2.异常类自定义
* */
class Test {
    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            userService.register("jackson", "123456");
        } catch (IllegalNameException e) {
            e.printStackTrace();
        }
    }
}

class UserService {
    public void register(String username, String password) throws IllegalNameException {
        if (username == null || username.length() < 6 || username.length() > 14) {
            throw new IllegalNameException("用户名不合法!");
        }
        System.out.println("注册成功!");
    }
}

class IllegalNameException extends Exception {
    public IllegalNameException() {
        super();
    }
    public  IllegalNameException(String s) {
        super(s);
    }
}