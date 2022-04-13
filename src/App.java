import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    private Cube cube;
    private NotationCompiler nc;
    private CubeSolver cs;
    private CrossSolver cr;

    public App() {
        cube = new Cube();
        nc = new NotationCompiler(cube);
        cs = new CubeSolver(cube);
        cr = new CrossSolver(cube,nc);
        this.list();
    }

    public void list() {
        cs.changeF2L();
    }

    public static void main(String[] args) throws Exception {
        new App();
    }

    public void generateScramble() {
        String turns = "RLUDFBMES";
        String scramble = "";
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(10, 20); i++) {
            scramble = scramble + Character.toString(turns.charAt(ThreadLocalRandom.current().nextInt(turns.length())))
                    + "#";
        }
        System.out.println(scramble);
    }

    public void printArray(String pInput, int pInt) {
        for (int i = 0; i < pInt; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(pInput + "[" + i + "][" + j + "] = " + '"' + '"' + ';');
            }
        }
    }

    public String r(int i) {
        if (i % 4 == 0) {
            return "y#";
        } else if (i % 2 == 0) {
            return "y'#";
        } else if (((i - 1) % 4) == 0) {
            return "y2#";
        } else if (((i - 1) % 2) == 0) {
            return "";
        } else {
            return null;
        }
    }

    public void printF() {
        String[] array = new String[164];
        int start = 92;
        int stop = 100;
        for (int i = start; i < stop; i = i + 4) {
            array[i] = ("F2L[" + i + "][0] = " + '"' + create() + '"' + ';' + "\n");
            array[i] = array[i] + ("F2L[" + i + "][1] = " + '"' + r(i) + '"' + ';');
        }
        for (int i = start; i < stop; i++) {
            System.out.println(array[i]);
        }
    }

    public String create() {
        Scanner scanner = new Scanner(System.in);
        String r = null;
        System.out.println("Side:");
        r = scanner.nextLine().toUpperCase() + ":";
        System.out.println("Field:");
        r = r + scanner.nextLine().toUpperCase() + "/";
        System.out.println("Colour:");
        r = r + scanner.nextLine().toLowerCase();
        if (scanner.nextLine().equals("&")) {
            r = r + "&" + create();
        }
        return r;

    }

}