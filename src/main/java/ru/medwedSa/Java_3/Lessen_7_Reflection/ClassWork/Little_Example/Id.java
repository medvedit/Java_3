package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Повесим аннотацию Id на поле, которое будет являться уникальным идентификаторам.
 */
@Retention(RetentionPolicy.RUNTIME) // Аннотация видна во время выполнения кода.
@Target({ElementType.FIELD})// Уровень поля. Можно определять аннотации на уровне полей и ограничивать область действия.
public @interface Id {
    boolean autoincrement() default false; // Позволит сделать авто инкремент, если говорим про целые числа.
}
