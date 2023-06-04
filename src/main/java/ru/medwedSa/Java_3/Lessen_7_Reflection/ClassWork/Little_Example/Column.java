package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

import java.lang.annotation.*;

/**
 * Над полем повесим аннотацию Column и в этом случае поле станет столбцом в таблице
 */
@Retention(RetentionPolicy.RUNTIME) // Аннотация видна во время выполнения кода.
@Target({ElementType.FIELD})// Уровень поля. Можно определять аннотации на уровне полей и ограничивать область действия.
public @interface Column {
    String name() default ""; // Можно присвоить имя при прокисании аннотации (над полем) или
                              // использовать значение по умолчанию.
}
