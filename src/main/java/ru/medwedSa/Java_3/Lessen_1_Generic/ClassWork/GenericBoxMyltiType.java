package ru.medwedSa.Java_3.Lessen_1_Generic.ClassWork;

/**
 * Пример создания класса с большим количеством описываемых данных в дженерике.
 * @param <X>
 * @param <Y>
 * @param <Z>
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class GenericBoxMyltiType<X, Y, Z, A, B, C> {
    private X XObj;
    private Y YObj;
    private Z ZObj;
    private A AObj;
    private B BObj;
    private C CObj;

    public GenericBoxMyltiType(X XObj, Y YObj, Z ZObj, A AObj, B BObj, C CObj) {
        this.XObj = XObj;
        this.YObj = YObj;
        this.ZObj = ZObj;
        this.AObj = AObj;
        this.BObj = BObj;
        this.CObj = CObj;
    }

    //<editor-fold desc="Геттеры и сеттеры">
    public X getXObj() {
        return XObj;
    }

    public void setXObj(X XObj) {
        this.XObj = XObj;
    }

    public Y getYObj() {
        return YObj;
    }

    public void setYObj(Y YObj) {
        this.YObj = YObj;
    }

    public Z getZObj() {
        return ZObj;
    }

    public void setZObj(Z ZObj) {
        this.ZObj = ZObj;
    }

    public A getAObj() {
        return AObj;
    }

    public void setAObj(A AObj) {
        this.AObj = AObj;
    }

    public B getBObj() {
        return BObj;
    }

    public void setBObj(B BObj) {
        this.BObj = BObj;
    }

    public C getCObj() {
        return CObj;
    }

    public void setCObj(C CObj) {
        this.CObj = CObj;
    }
    //</editor-fold>
}
