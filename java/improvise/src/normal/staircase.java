package normal;

public class staircase {
    String buff = "h";
    public staircase() {

    }

    static void interesting() {
        System.out.print("?");
    }

    public static void main(String[] args) {
        staircase foo = new staircase();
        interesting();
        System.out.print(foo.buff);
    }
}
