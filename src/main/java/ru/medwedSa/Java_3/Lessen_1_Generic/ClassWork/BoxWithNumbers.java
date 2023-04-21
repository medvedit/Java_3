package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Класс java.lang.Number является суперклассом классов
 * BigDecimal, BigInteger, Byte, Double, Float, Integer, Long и Short.
 * ...
 * В этом примере явно указанно, что в классе BoxWithNumbers могут храниться данные типа Number <N extends Number>
 * и все наследуемые то него, те -> int, double, float, byte, long, short. И при желании положить в него другие
 * типы данных компилятор этого не позволит. Те могут храниться любые объекты, но только числа.
 * @param <N>
 */
// Producer Extends (extends) - может отдавать данные, но не может их менять.
// Consumer Super (super) - может изменяться, но из него нельзя получить данные, кроме, как в виде Object

//public class BoxWithNumbers<N extends Number & Callable & Serializable> { // Можно добавлять в наследование и
    // дополнительные встроенные интерфейсы. Через амперсанд.
public class BoxWithNumbers<N extends Number> {
    private N[] number;

    @SafeVarargs
    public BoxWithNumbers(N... number) { // ... (три точки) означают - параметр переменного количества аргументов.
        this.number = number;
    }
//    public BoxWithNumbers(N i, N i2, N i3 number) {
//        this.number = number;
//    }

    //<editor-fold desc="Геттеры и сеттеры">
    public N[] getNumber() { // Геттер.
        return number;
    }

    public void setNumber(N[] number) { // Сеттер.
        this.number = number;
    }
    //</editor-fold>


    /**
     * Знак вопроса <Wildcard> в принимающем аргументе означает, что на вход может прилететь любой тип данных, но только
     * extends Number, тк он указан в создании класса. Если знак вопроса <Wildcard> заменить на N, что тоже допустимо,
     * то при выполнении сравнения "коробочек" будет возможность сравнивать созданные "коробочки" с одинаковыми
     * джененриками. Примеры в GenericExample...
     * ...
     * Методы вычисления и сравнение созданы прямо внутри класса, тк изначально известно, что класс BoxWithNumbers
     * может принимать только числа.
     * @param another
     * @return
     */
    //    public boolean equalsByAvg(BoxWithNumbers<? extends Number> another) {} // Эквивалент ?
    public boolean equalsByAvg(BoxWithNumbers<?> another) { // Метод сравнения по среднему арифметическому.
        return Math.abs(avg() - another.avg()) < 0.00001; // тк числа для сравнения являются типом double, а
        // у них существует погрешность, то к сравнению применяется их разность с маленьким, допустимым числом.
        // Запусти метод comparingDouble, и придет понимание.
    }

    public double avg() { // Метод среднего арифметического массива.
        return sum() / number.length;
    }

    public double sum() { // Метод суммы чисел массива.
        var sum = 0.0;
        for (N num : number) {
            sum += num.doubleValue();
        }
        return sum;
    }
}
