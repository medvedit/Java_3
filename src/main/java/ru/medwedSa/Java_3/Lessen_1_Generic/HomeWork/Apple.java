package ru.medwedSa.Java_3.Lessen_1_Generic.HomeWork;

public class Apple extends Fruit{
    private float weight = 0.1f;

    public Apple(float apple) {
//        super();
        this.weight = apple;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
