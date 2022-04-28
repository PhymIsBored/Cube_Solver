// import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class App {
    private Cube cube;
    private NotationCompiler nc;
    private CubeSolver cs;
    private CrossSolver cr;
    private SolveF2L sf;

    public App() {
        cube = new Cube();
        nc = new NotationCompiler(cube);
        cr = new CrossSolver(cube, nc);
        sf = new SolveF2L(cube, nc);
        cs = new CubeSolver(cube, cr, nc, sf);
        this.list();
    }

    public void list() {
        String scramble = generateScramble();
        // scramble = "L#B#B#L#S#F#F#E#F#F#M#U#";
        System.out.println("Scramble: "+scramble);
        nc.executeString(scramble);
        cs.solveCube();
        System.out.println(cube.checkCompletionFinal());
        // test();
    }

    public static void main(String[] args) throws Exception {
            for (int i = 0; i < 7999; i++) {
            new App();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // new App();
    }

    public String generateScramble() {
        String turns = "RLUDFBMES";
        String scramble = "";
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(10, 20); i++) {
            scramble = scramble + Character.toString(turns.charAt(ThreadLocalRandom.current().nextInt(turns.length())))
                    + "#";
        }
        return scramble;
    }

    // public void printArray(String pInput, int pInt) {
    //     for (int i = 0; i < pInt; i++) {
    //         for (int j = 0; j < 2; j++) {
    //             System.out.println(pInput + "[" + i + "][" + j + "] = " + '"' + '"' + ';');
    //         }
    //     }
    // }

    // public String r(int i) {
    //     if (i % 4 == 0) {
    //         return "y#";
    //     } else if (i % 2 == 0) {
    //         return "y'#";
    //     } else if (((i - 1) % 4) == 0) {
    //         return "y2#";
    //     } else if (((i - 1) % 2) == 0) {
    //         return "";
    //     } else {
    //         return null;
    //     }
    // }

    // public void printF() {
    //     String[] array = new String[164];
    //     int start = 92;
    //     int stop = 100;
    //     for (int i = start; i < stop; i = i + 4) {
    //         array[i] = ("F2L[" + i + "][0] = " + '"' + create() + '"' + ';' + "\n");
    //         array[i] = array[i] + ("F2L[" + i + "][1] = " + '"' + r(i) + '"' + ';');
    //     }
    //     for (int i = start; i < stop; i++) {
    //         System.out.println(array[i]);
    //     }
    // }

    // public String create() {
    //     Scanner scanner = new Scanner(System.in);
    //     String r = null;
    //     System.out.println("Side:");
    //     r = scanner.nextLine().toUpperCase() + ":";
    //     System.out.println("Field:");
    //     r = r + scanner.nextLine().toUpperCase() + "/";
    //     System.out.println("Colour:");
    //     r = r + scanner.nextLine().toLowerCase();
    //     if (scanner.nextLine().equals("&")) {
    //         r = r + "&" + create();
    //     }
    //     return r;

    // }

}