package ru.medwedSa.Java_3.Lessen_7_Reflection.HomeWork;

public class Testing {
    public static void method1(){
        System.out.println("method1");
    }
    @BeforeSuite
    public static void start(){
        System.out.println("start");
    }
    @Test(priority = 4)
    public static void method2(){
        System.out.println("method2");
    }
    @Test(priority = 3)
    public static void method3(){
        System.out.println("method3");
    }
    @Test(priority = 2)
    public static void method4(){
        System.out.println("method4");
    }
    @AfterSuite
    public static void shutdown(){
        System.out.println("shutdown");
    }
    @Test(priority = 10)
    public static void method5(){
        System.out.println("method5");
    }
//    @AfterSuite // Если раскомментировать, то будет ошибка уникальности выполнения метода.
    public static void shutdown1(){
        System.out.println("shutdown1");
    }
    @Test()
    public static void method6(){
        System.out.println("method6");
    }
}
