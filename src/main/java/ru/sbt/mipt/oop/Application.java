package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "beans.xml");


        EventObserver observer = (EventObserver) context.getBean("observer");
        observer.startExecutionCycle();

    }


}
