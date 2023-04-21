package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

/**
 * Клас объектов хоронящий в себе объекты типа Object. Те по факту храниться могут любые объекты, что не всегда удобно
 * без использования дженериков, тк требует дополнительных проверок на тип входящих данных в дальнейшем
 * при написании кода.
 */
public class Box {
    private Object obj;

    public Box(Object obj) { // конструктор класса.
        this.obj = obj;
    }

    public void setObj(Object obj) { // Сеттер. Заменяет объект.
        this.obj = obj;
    }

    public Object getObj() { // Геттер. Получает объект.
        return obj;
    }
}
