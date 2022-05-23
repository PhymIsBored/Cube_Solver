// import java.util.Scanner;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
// import java.util.concurrent.TimeUnit;

public class App {
    private Cube cube;
    private NotationCompiler nc;
    private CubeSolver cs;
    private CrossSolver cr;
    private SolveF2L sf;
    private ArduinoConverter ac;

    public App() {
        cube = new Cube();
        nc = new NotationCompiler(cube);
        cr = new CrossSolver(cube, nc);
        sf = new SolveF2L(cube, nc);
        cs = new CubeSolver(cube, cr, nc, sf);
        ac = new ArduinoConverter(cube);
        this.list();
    }

    public void list() {
        // String scramble = generateScramble();
        // // scramble = "L#B#B#L#S#F#F#E#F#F#M#U#";
        // System.out.println("Scramble: "+scramble);
        // nc.executeString(scramble);
        // cs.solveCube();
        // System.out.println(cube.checkCompletionFinal());
        // test();
        this.inputCubePosition();
        cs.solveCube();
        System.out.println(cube.checkCompletion());
    }

    public static void main(String[] args) throws Exception {
        //     for (int i = 0; i < 499; i++) {
        //     new App();
        //     try {
        //         TimeUnit.MILLISECONDS.sleep(100);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
        new App();
    }

    public void inputCubePosition() {
        Scanner scanner = new Scanner(System.in);
        char[][] layout = new char[12][9];
        //method fill faces orange; green, white, blue; red; yellow;#
        //fill orange
        for (int i = 0; i < 3; i++) {
            System.out.println("input orange:");
            String test = "aaa"+ scanner.nextLine() + "aaa";
            System.out.println(test);
            char[] d = test.toCharArray();
            layout[i] = d;
        }
        // fill green, white, blue
        for (int i = 3; i < 6; i++) {
            System.out.println("input green, white, blue:");
            String test = scanner.nextLine();
            System.out.println(test);
            char[] d = test.toCharArray();
            layout[i] = d;
        }
        // fill red
        for (int i = 6; i < 9; i++) {
            System.out.println("input red:");
            String test = "aaa"+ scanner.nextLine() + "aaa";
            System.out.println(test);
            char[] d = test.toCharArray();
            layout[i] = d;
        }
        //fill yellow
        for (int i = 9; i < 12; i++) {
            System.out.println("input yellow:");
            String test = "aaa"+ scanner.nextLine() + "aaa";
            System.out.println(test);
            char[] d = test.toCharArray();
            layout[i] = d;
        }
        cube.setCubePosition(layout);
        // System.out.println(cube.checkCompletion());
        scanner.close();
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