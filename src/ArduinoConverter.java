public class ArduinoConverter {
    private Cube cube;
    private char down;
    private boolean handUp;

    public ArduinoConverter(Cube pCube) {
        cube = pCube;
        down = 'd';
        handUp = true;
    }

    public void returnSide(String pInput) {
        switch (pInput.charAt(0)) {
            case 'R':

                break;
            case 'L':

                break;
            case 'U':

                break;
            case 'D':

                break;
            case 'F':

                break;
            case 'B':

                break;

            default:
                break;
        }
    }

    public String breakDown(String pInput) { // breaks the notation down to the side rotations necessary to perform the
                                             // turn
        switch (pInput) {
            // cube rotations
            case "x":
                return "x#";
            // System.out.println("x");

            case "y":
                return "y#";
            // System.out.println("y");

            case "z":
                return "z#";
            // System.out.println("z");

            case "x2":
                return "x#x#";
            // System.out.println("x2");

            case "y2":
                return "y#y#";
            // System.out.println("y2");

            case "z2":
                return "z#z#";
            // System.out.println("z2");

            case "x'":
                return "x#x#x#";
            // System.out.println("x'");

            case "y'":
                return "y#y#y#";
            // System.out.println("y'");

            case "z'":
                return "z#z#z#";
            // System.out.println("z'");

            // default turns
            case "R":
                return "R#";
            // System.out.println("R");

            case "L":
                return "L#";
            // System.out.println("L");

            case "U":
                return "U#";
            // System.out.println("U");

            case "D":
                return "D#";
            // System.out.println("D");

            case "F":
                return "F#";
            // System.out.println("F");

            case "B":
                return "B#";
            // System.out.println("B");

            // reverse turns
            case "R'":
                return "R#R#R#";
            // System.out.println("R'");

            case "L'":
                return "L#L#L#";
            // System.out.println("L'");

            case "U'":
                return "U#U#U#";
            // System.out.println("U'");

            case "D'":
                return "D#D#D#";
            // System.out.println("D'");

            case "F'":
                return "F#F#F#";
            // System.out.println("F'");

            case "B'":
                return "B#B#B#";
            // System.out.println("B'");

            // double turns
            case "R2":
                return "R#R#";
            // System.out.println("R2");

            case "L2":
                return "L#L#";
            // System.out.println("L2");

            case "U2":
                return "U#U#";
            // System.out.println("U2");

            case "D2":
                return "D#D#";
            // System.out.println("D2");

            case "F2":
                return "F#F#";
            // System.out.println("F2");

            case "B2":
                return "B#B#";
            // System.out.println("B2");

            // wide turns
            case "r":
                return "x#L#";

            case "r2":
                return "x#x#L#L#";

            case "r'":
                return "x#x#x#L#L#L#";

            case "l":
                return "x#x#x#R#";
            // System.out.println("Lw");

            case "l2":
                return "x#x#R#R#";

            case "l'":
                return "x#R#R#R#";

            case "u":
                return "y#D#";
            // System.out.println("Uw");

            case "u2":
                return "y#y#D#D#";

            case "u'":
                return "y#y#y#D#D#D#";

            case "d":
                return "y#y#y#U#";
            // System.out.println("Dw");

            case "d2":
                return "y#y#U#U#";

            case "d'":
                return "y#U#U#U#";

            case "f":
                return "z#B#";
            // System.out.println("Fw");

            case "f2":
                return "z#z#B#B#";

            case "f'":
                return "z#z#z#D#D#D#";

            case "b":
                return "z#z#z#F#";
            // System.out.println("Bw");

            case "b2":
                return "z#z#F#F#";

            case "b'":
                return "z#F#F#F#";

            // slice turns
            case "M":
                return "x#x#x#R#L#L#L#";
            // System.out.println("M");

            case "E":
                return "y#y#y#F#D#D#D#";
            // System.out.println("E");

            case "S":
                return "z#F#F#F#D#";
            // System.out.println("S");

            // reverse slice turns
            case "M'":
                return "x#R#R#R#L#";
            // System.out.println("M'");

            case "E'":
                return "y#U#U#U#D#";
            // System.out.println("E'");

            case "S'":
                return "z#z#z#";
            // System.out.println("S'");

            // double slice turns
            case "M2":
                return "x#x#R#R#L#L#";
            // System.out.println("M2");

            case "E2":
                return "y#y#U#U#D#D#";
            // System.out.println("E2");

            case "S2":
                return "z#z#F#F#B#B#";
            // System.out.println("S2");

            case "":
                // this case is here to prevent errors

            default:
                System.out.println(pInput);

                // System.out.println("unknown");
                throw new Error("unknown move: " + pInput);
            //
        }
    }
}
