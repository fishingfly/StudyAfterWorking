package com.zy.test;

import java.util.Random;

import com.zy.test.Food.Appetizer;
import com.zy.test.Food.MainCourse;
import com.zy.test.SecurityCategory.Security;

interface Food {
    
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }
    
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
}

public class InterfaceOrganizeEnum {
    public static void main(String[] args) {
//        Food food = Appetizer.SALAD;
//        food = MainCourse.LASAGNE;
//        for (int i = 0; i < 5; i++) {
//            for (Course course : Course.values()) {
//                Food f = course.randSelection();
//                System.out.println(f);
//            }
//        }
        Random random = new Random(47);
        
        SecurityCategory[] valueTemp = SecurityCategory.class.getEnumConstants();
        for (int i = 0; i < 10; i++) {
            SecurityCategory category = valueTemp[random.nextInt(valueTemp.length)];
            System.out.println(category + ":" + category.randomSelection());
        }
    }
}

enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class);
    
    private Food[] values;
    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public Food randSelection() {
        Random random = new Random(47);
        return values[random.nextInt(values.length)];
    }
}


enum SecurityCategory {
    STOCK(Security.Stock.class), BOND(Security.Bond.class);
    Security[] values;
    SecurityCategory(Class<? extends Security> kind) {
        values = kind.getEnumConstants();
    }
    interface Security {
        enum Stock implements Security { SHORT, LONG, MARGIN}
        enum Bond implements Security {MUNICIPAL, JUNK}
    }
    
    public Security randomSelection() {
        Random random = new Random(50);
        return values[random.nextInt(values.length)];
    }
}
