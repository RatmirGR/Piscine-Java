package edu.school21.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Program {

    public static void main(String[] args) {
        System.out.println("Classes:");


        try {
            Class<?> c = Class.forName("edu.school21.classes.Car");
            System.out.println("fields:");
            Field[] fields2 = c.getFields();
            for (int i = 0; i < fields2.length; i++) {
                System.out.println(i+") "+fields2[i]);
            }

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields){
                System.out.printf("    %s", field.getType().getSimpleName());
            }


//            System.out.println("Constructorв:");
//            Constructor[] constructor = c.getConstructors();
//            for (int i = 0; i < constructor.length; i++) {
//                System.out.println(i+") "+constructor[i]);
//            }
//            System.out.println("Methods:");
//            Method[] methods = c.getMethods();
//            for (int i = 0; i < methods.length; i++) {
//                System.out.println(i+") "+methods[i]);
//            }
//            Method[] marr = c.getDeclaredMethods();
//            for (int i = 0; i < marr.length; i++) {
//                int mod = marr[i].getModifiers();
//                if (Modifier.isPublic(mod)){
//                    System.out.print(" "+marr[i].getName()+",");
//                }
//            }
        }catch (Exception e){}


    }
}
