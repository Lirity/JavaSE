import java.util.*;

/*
* Java 集合
* 1.集合：集合就是一个容器,容纳多个对象(存储的是对象的内存地址),数组就是一个集合
* ★集合在java中本身是一个对象
* 2.不同的集合,底层对应不同的数据结构
* 3.集合类和集合接口在java.util.*;下
* 4.集合由两大类 ①单个方式存储元素 java.util.Collection; ②键值对存储元素 java.util.Map;
* 5.集合继承结构图
*   (接口)Iterator(可迭代的) - iterator();方法返回一个Iterator对象 有三个方法 ①hasNext(); ②next(); ③remove();
*            ↓
*   (接口)Collection(所有集合都是可迭代的)
*            ↓
*   (接口)List(有序可重复 存储元素有下标)     (接口)Set(无序不可重复 存储元素没有下标)
*            ↓                                       ↓
* ①ArrayList(数组结构 非线程安全)            ①HashSet(底层new了一个HashMap(哈希表))
* ②LinkedList(双向链表结构)                 ②(接口)SortedSet -> TreeSet(自动排序 底层new了一个TreeMap(二叉树))
* ③Vector(数组结构 线程安全)
*
*
*   (接口)Map<K,V>(key和value都存储内存地址) ★key无序不可重复 Map里的key相当于Set集合
*         ↓
* ①HashMap(非线程安全)
* ②HashTable(线程安全) -> Properties(K,V只支持String)
* ②(接口)SortedMap -> TreeMap
*
* 6.Collection中不使用泛型可以存储所有Object子类型 使用泛型后只能存储某个特定类型
* 7.new ArrayList(Collection c) - ArrayList的一个构造方法
* 8.JDK8对HashMap集合的改进：如果哈希表中单向链表中元素超过8个 单向链表->红黑树
*                           如果红黑树中节点数量小于6个 红黑树->单向链表
* 9.HashMap的key和value允许是null,而Hashtable不允许
* 10.比较规则不变时选择Comparable 比较规则变化时选择Comparator(写多个比较器)
* 11.java.util.Collections 是集合工具类
*
* */
public class MyCollection {
    public static void main(String[] args) {
        /*Collection接口中常用的方法*/
        Collection c = new ArrayList();

        c.add(10);
        c.add(3.14);

        System.out.println(c.size());   // 2

        c.clear();
        System.out.println(c.size());   // 0
        /*contains方法底层调用equals方法 存放在集合中的类型需要重写equals方法*/
        c.add("Hello");
        System.out.println(c.contains("Hello"));    // true
        System.out.println(c.contains("World"));    // false
        /*remove方法底层同样调用equals方法*/
        c.remove("Hello");

        System.out.println(c.isEmpty());    // true

        c.add(1);
        Object[] objects = c.toArray();
        System.out.println(objects[0]);     // 1

        /*
        迭代器迭代
        ★集合结构一旦发生改变,迭代器必须重新获取(迭代器不能自我刷新)
        所以在迭代过程中想要删除元素应该使用迭代器自带的remove方法而不是集合的remove方法
        */
        c.add(2);
        c.add(3);
        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());  // 1 2 3
        //  it.remove();
        }
        System.out.println(c.isEmpty());
    }
}

class MyList {
    public static void main(String[] args) {
        /*List接口特有方法(与index相关)*/
        ArrayList arrayList = new ArrayList();
        /*在指定位置插入 效率较低*/
        arrayList.add(0,0);
        arrayList.add(1,1);
        arrayList.add(2,2);
        arrayList.add(3,3);

        System.out.println(arrayList.get(3));   // 3 利用下标索引

        System.out.println(arrayList.lastIndexOf(3));   // 3

        arrayList.remove(0);
        System.out.println(arrayList.lastIndexOf(3));   // 2 删除了第一个元素 其余元素会往前移动噢！
        /*改指定位置元素*/
        arrayList.set(0,"change");
        System.out.println(arrayList.get(0));

        /*★将ArrayList转化为线程安全的*/
        Collections.synchronizedList(arrayList);

        /*
        Vector源码分析:
        1.底层是数组,初始化容量为10,扩容后是原容量的2倍
        2.Vector中所有方法都带有synchronized
        */

        List<Integer> mylist = new LinkedList<>();  // JDK8后的钻石表达式 JDK5后的泛型机制
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(4);
        mylist.add(5);
        /*JDK5后推出的foreach 增强for循环*/
        for (int data : mylist) {
            System.out.println(data);
        }
    }
}

class MyMap {
    public static void main(String[] args) {

        /*★要重写K部分的hashCode和equals方法*/

        /*Map和Collection没有继承关系 Map接口中常用方法如下*/
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "White");
        map.put(2, "Black");
        map.put(3, "Orange");

        System.out.println(map.containsKey(1)); // true
        System.out.println(map.containsValue("White")); // true

        System.out.println(map.get(1)); // White

        System.out.println(map.isEmpty());  // false

        /*获取Map中的所有的key Set方式存储*/
        Set<Integer> set = map.keySet();
        for (int data : set) {
            System.out.println(data);   // 1 2 3
        }

        System.out.println(map.size()); // 3

        /*获取Map中的所有的value*/
        Collection<String> collection = map.values();
        for (String str : collection) {
            System.out.println(str);   // White Black Orange
        }
        /*Map -> Set*/
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for (Map.Entry<Integer, String> element : entrySet) {
            System.out.println(element);   // 1=White 2=Black 3=Orange
        }

        // map.remove(1);
        // map.clear();    // 清空Map

        /*遍历Map集合*/
        // 1.获取所有key 通过遍历key来遍历value
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(map.get(it.next()));
        }

        for (Integer key : set) {
            System.out.println(map.get(key));
        }
        // 2.entrySet方法(省略foreach)
        Iterator<Map.Entry<Integer, String>> node = entrySet.iterator();
        while (node.hasNext()) {
            System.out.println(node.next().getValue());
        }
    }
}
/*TreeSet对自定义类排序*/
class User implements Comparable<User>{
    int age;

    public User(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }

    public static void main(String[] args) {
        //Set<User> set = new TreeSet<>();                      // 升序
        Set<User> set = new TreeSet<>(new UserComparator());    // 降序
        set.add(new User(1));
        set.add(new User(10));
        set.add(new User(5));

        for (User user : set) {
            System.out.println(user.age);
        }
    }
}

class UserComparator implements  Comparator<User> { // 降序

    @Override
    public int compare(User o1, User o2) {
        return o2.age - o1.age;
    }
}