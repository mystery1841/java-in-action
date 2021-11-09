package learning.oop.structure;

public class Data {
    private String a = "a0";
    private String b = "b0";

    public Data() {
        this.b = "b1";
    }

    public Data(String s) {
        this(3);
        this.a = s;
    }

    public Data(int i) {
        this.c = "c"+i;
    }

    {
        this.a = "a2";
        this.b = "b2";
        this.c = "c2";
    }

    private String c = "c0";

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }
}
