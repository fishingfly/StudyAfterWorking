package com.zy.test;

enum Grade {
    A("90-100"),B("80-89"),C("70-79"),D("60-69"),E("0-59");
    
    private String value;
    
    private Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


public class EnumDemo {
    public static void main(String[] args) {
//        System.out.println(Grade.A);
//        System.out.println(Grade.A.name());// 返回枚举常量的名称
//        System.out.println(Color.BLUE.getId());
//        System.out.println(Color.BLUE.getName());
//        System.out.println(Color.BLUE.ordinal());// 返回枚举常量的序数
//        System.out.println(Grade.B.getValue());// 获取封装过后的值
//        for (Grade grade : Grade.values()) {// values方法返回整个枚举类的值，以数组的形式
//            System.out.println(grade.getValue());
//        }
        System.out.println(GradeTest.B.local());// 返回实现抽象方法的结果
//        Grade g = Grade.valueOf(Grade.class, "B");// 返回带指定名称的指定枚举类型的枚举常量。有一点必须注意这个枚举常量必须是已定义的
//        System.out.println(g.getValue());// 返回枚举B中的值
    }
}

//
//enum Color {
//    BLUE,yellow,red,green,white,black;
//}

enum Color {
    BLUE("blue", 1), Green("Green", 2),RED("red",3),YELLOW("red",3);
    private String name;
    private Integer id;
    private Color(String name, Integer n) {
        this.name = name;
        this.id = n;
    }
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
}


/**
 * 带抽象方法的枚举
 * @author user
 *
 */
enum GradeTest {
    A("90-100") {
        public String local() {
            return "优";
        }
    }
    ,B("80-89") {
        public String local() {
            return "良";
        }
    }
    ,C("70-79") {
        public String local() {
            return "中";
        }
    },D("60-69"){
        public String local() {
            return "差";
        }
    },E("0-59")
    {
        public String local() {
            return "优";
        }
    };
    
    private String value;
    
    private GradeTest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    abstract public String local();
}

//class ConstantClass {
//    public static final int blue = 1;
//    public static final int yellow = 2;
//    public static final int red = 1;
//    public static final int green = 1;
//    public static final int white = 1;
//    public static final int black = 2;
//}
