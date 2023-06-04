package ru.medwedSa.Java_3.Lessen_7_Reflection.ClassWork.Little_Example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Над классом повесим аннотацию Table с именем и тогда этот класс будет закидываться на базу данных и создаваться
 * таблица с соответствующим именем которое пропишется при прикреплении аннотации к классу.
 */
@Retention(RetentionPolicy.RUNTIME) // Аннотация видна во время выполнения кода.
@Target({ElementType.TYPE}) // Уровень класса. Можно применять ко всем классам.
public @interface Table {
    String name();
}
