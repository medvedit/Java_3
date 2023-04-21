package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

/**
 * Принято указывать обобщенный тип данных заглавной буквой: T(ype), E(lement), V(alue) и тд. Если при создании класса
 * с объектами используется более одного описываемого типа данных, что не возбраняется, то имеет смысл более осмысленно
 * описывать для читаемости кода.
 * Для дженериков существуют ограничения:
 * 1) Нельзя объявить дженерик static в классе обобщённого типа. Пример: private static TYPE obj;
 * 2) Нельзя создать объект от обобщенного класса в самом классе.
 * 3) Нельзя напрямую создать массив обобщенного типа. Это касается так же, для создания в самом классе.
 * @param <TYPE>
 */
//public class GenericBox<T> { // одна заглавная буква, вместо слова TYPE
//public class GenericBox<Key, Value, Text> { // Допустимо несколько. Написано более осмыслено для работы с кодом.
public class GenericBox<TYPE> {
    private TYPE obj;
//    private static TYPE obj1; // Ограничение 1.

    public GenericBox(TYPE obj) {
        this.obj = obj;
    }

    public TYPE getObj() {
        return obj;
    }

    public void setObj(TYPE obj) {
        this.obj = obj;
    }

    public GenericBox() {
//        obj = new TYPE(); // Ограничение 2.
//        TYPE[] arr = new TYPE[10]; // Ограничение 3.
//        TYPE[] arr = (TYPE[]) new Object[10]; // Как вариант создать так, но работать с ним из обобщенного класса
                                              // очень не удобно. Не стоит так делать.
    }
}
