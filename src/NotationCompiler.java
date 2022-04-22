public class NotationCompiler {
    // private String currentModifiers;
    private Cube cube;

    public NotationCompiler(Cube pCube) {

        cube = pCube;
    }

    public void executeString(String pInput) { //convert r to Rw etc. 
        String[] split = pInput.split("#");
        for (int i = 0; i < split.length; i++) {
            this.executeTurn(split[i]);
        }
    }

    public void executeTurn(String pInput) { // add Rw double and reverse turns !!!! turn Rw into r
        switch (pInput) {
            case "x":
                cube.rotateX();
                // System.out.println("x");
                break;
            case "y":
                cube.rotateY();
                // System.out.println("y");
                break;
            case "z":
                cube.rotateZ();
                // System.out.println("z");
                break;
            case "x2":
                cube.rotateX();
                cube.rotateX();
                // System.out.println("x2");
                break;
            case "y2":
                cube.rotateY();
                cube.rotateY();
                // System.out.println("y2");
                break;
            case "z2":
                cube.rotateZ();
                cube.rotateZ();
                // System.out.println("z2");
                break;
            case "x'":
                cube.rotateX();
                cube.rotateX();
                cube.rotateX();
                // System.out.println("x'");
                break;
            case "y'":
                cube.rotateY();
                cube.rotateY();
                cube.rotateY();
                // System.out.println("y'");
                break;
            case "z'":
                cube.rotateZ();
                cube.rotateZ();
                cube.rotateZ();
                // System.out.println("z'");
                break;
            case "R":
                cube.turnR();
                // System.out.println("R");
                break;
            case "L":
                cube.turnL();
                // System.out.println("L");
                break;
            case "U":
                cube.turnU();
                // System.out.println("U");
                break;
            case "D":
                cube.turnD();
                // System.out.println("D");
                break;
            case "F":
                cube.turnF();
                // System.out.println("F");
                break;
            case "B":
                cube.turnB();
                // System.out.println("B");
                break;
            // reverse turns
            case "R'":
                cube.turnR();
                cube.turnR();
                cube.turnR();
                // System.out.println("R'");
                break;
            case "L'":
                cube.turnL();
                cube.turnL();
                cube.turnL();
                // System.out.println("L'");
                break;
            case "U'":
                cube.turnU();
                cube.turnU();
                cube.turnU();
                // System.out.println("U'");
                break;
            case "D'":
                cube.turnD();
                cube.turnD();
                cube.turnD();
                // System.out.println("D'");
                break;
            case "F'":
                cube.turnF();
                cube.turnF();
                cube.turnF();
                // System.out.println("F'");
                break;
            case "B'":
                cube.turnB();
                cube.turnB();
                cube.turnB();
                // System.out.println("B'");
                break;
            // double turns
            case "R2":
                cube.turnR();
                cube.turnR();
                // System.out.println("R2");
                break;
            case "L2":
                cube.turnL();
                cube.turnL();
                // System.out.println("L2");
                break;
            case "U2":
                cube.turnU();
                cube.turnU();
                // System.out.println("U2");
                break;
            case "D2":
                cube.turnD();
                cube.turnD();
                // System.out.println("D2");
                break;
            case "F2":
                cube.turnF();
                cube.turnF();
                // System.out.println("F2");
                break;
            case "B2":
                cube.turnB();
                cube.turnB();
                // System.out.println("B2");
                break;
            // wide turns
            case "r":
                cube.turnR();
                executeTurn("M'");
                break;
            case "r2":
                executeTurn("r");
                executeTurn("r");
                break;
            case "r'":
                executeTurn("r");
                executeTurn("r");
                executeTurn("r");
                break;
            case "l":
                cube.turnL();
                executeTurn("M");
                // System.out.println("Lw");
                break;
            case "l2":
                executeTurn("l");
                executeTurn("l");
                break;
            case "l'":
                executeTurn("l");
                executeTurn("l");
                executeTurn("l");
                break;
            case "u":
                cube.turnU();
                executeTurn("E'");
                // System.out.println("Uw");
                break;
            case "u2":
                executeTurn("u");
                executeTurn("u");
                break;
            case "u'":
                executeTurn("u");
                executeTurn("u");
                executeTurn("u");
                break;
            case "d":
                cube.turnD();
                executeTurn("E");
                // System.out.println("Dw");
                break;
            case "d2":
                executeTurn("d");
                executeTurn("d");
                break;
            case "d'":
                executeTurn("d");
                executeTurn("d");
                executeTurn("d");
                break;
            case "f":
                cube.turnF();
                executeTurn("S");
                // System.out.println("Fw");
                break;
            case "f2":
                executeTurn("f");
                executeTurn("f");
                break;
            case "f'":
                executeTurn("f");
                executeTurn("f");
                executeTurn("f");
                break;
            case "b":
                cube.turnB();
                executeTurn("S'");
                // System.out.println("Bw");
                break;
            case "b2":
                executeTurn("b");
                executeTurn("b");
                break;
            case "b'":
                executeTurn("b");
                executeTurn("b");
                executeTurn("b");
                break;
            // slice turns
            case "M":
                cube.turnM();
                // System.out.println("M");
                break;
            case "E":
                cube.turnE();
                // System.out.println("E");
                break;
            case "S":
                cube.turnS();
                // System.out.println("S");
                break;
            // reverse slice turns
            case "M'":
                cube.turnM();
                cube.turnM();
                cube.turnM();
                // System.out.println("M'");
                break;
            case "E'":
                cube.turnE();
                cube.turnE();
                cube.turnE();
                // System.out.println("E'");
                break;
            case "S'":
                cube.turnS();
                cube.turnS();
                cube.turnS();
                // System.out.println("S'");
                break;
            // double slice turns
            case "M2":
                cube.turnM();
                cube.turnM();
                // System.out.println("M2");
                break;
            case "E2":
                cube.turnE();
                cube.turnE();
                // System.out.println("E2");
                break;
            case "S2":
                cube.turnS();
                cube.turnS();
                // System.out.println("S2");
                break;
            default:
                System.out.println(pInput);
                System.out.println("unknown");
                throw new Error("unknown move");
                // break;
        }
    }
}