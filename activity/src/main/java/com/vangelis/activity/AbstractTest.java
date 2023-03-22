package com.vangelis.activity;

/**
 * Function：abstract
 * Created on 2023/3/22.
 * Comment：
 * abstract的使用简介
 * 1.抽象类不能直接使用，必须用子类去实现抽象类，然后使用其子类的实例。
 * 【然而可以创建一个变量，其类型是一个抽象类，并让它指向具体子类的一个实例，也就是可以使用抽象类来充当形参，实际实现类作为实参，也就是多态的应用。】
 * 2.如果一个类是非抽象类却包含一个抽象方法，就会产生编译错误。
 * <p>
 * abstract 关键字和哪些关键字不能共存：
 * final：被final修饰的类不能有子类。而被abstract修饰的类一定是一个父类。
 * private: 抽象类中的私有的抽象方法，不被子类所知，就无法被复写。而抽象方法出现的就是需要被复写。
 * static：如果static可以修饰抽象方法，那么连对象都省了，直接类名调用就可以了。可是抽象方法运行没意义。
 *
 * @author Wangpei
 */
public class AbstractTest {

    public static void main(String[] args) {
        Teacher t = new Teacher();
        t.setName("小明");
        t.work();

        Doctor d = new Doctor();
        d.setName("小陈");
        d.work();
    }

    abstract static class People {
        private String name;

        public String getName() {
            return getJob() + "+" + name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //抽象方法，必须被子类实现
        public abstract void work();

        //抽象方法，可以返回给父类使用，统一代码
        public abstract String getJob();

        //并不是所有方法都需要为抽象方法
        public void test() {
            System.out.printf("我是一个人");
        }
    }

    static class Teacher extends People {
        //子类实现抽象方法
        @Override
        public void work() {
            System.out.printf("我是一名教师，我的名字是:" + getName());
        }

        @Override
        public String getJob() {
            test();//这里可以直接调用非abstract修饰的父类方法。
            return "教师";
        }
    }

    static class Doctor extends People {
        //子类实现抽象方法
        @Override
        public void work() {
            System.out.printf("我是一名医生，我的名字是:" + getName());
        }

        @Override
        public String getJob() {
            return "医生";
        }
    }
}