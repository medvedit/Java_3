package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

/**
 * Интерфейсы тоже могут быть обобщенными. Ниже представлены три варианта работы с обобщёнными интерфейсами.
 * @param <M>
 */
public interface GenericInterface<M> {
    M doSomething(M request);

    // вложенный класс интерфейса - для наглядности работы с обобщёнными интерфейсами.

    class GenericClass1 implements GenericInterface { // Вариант №1

        @Override
        public Object doSomething(Object request) {
            return null;
        }
    }

    class GenericClass2<M> implements GenericInterface<M> { // Вариант №2

        @Override
        public M doSomething(M request) {
            return null;
        }
    }

    class GenericClass3 implements GenericInterface<String> { // Вариант №3

        @Override
        public String doSomething(String request) {
            return null;
        }
    }
}
