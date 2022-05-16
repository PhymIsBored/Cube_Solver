public class ArduinoConverter {
    private Cube cube;
    private char bottom;
    private String ring;
    private boolean handUp;

    public ArduinoConverter(Cube pCube) {
        cube = pCube;
        ring = "obrg"; // replace these with the actual position
        bottom = 'w'; // default position, white is bottom
        handUp = true;
    }

    public void testSplit() {
        String moves = "M#";
        String[] split = makeIntoSideRotations(moves);
        String[][] optimised = optimiseRotations(split);
        String turns = "";
        for (int i = 0; i < optimised.length; i++) {
            turns = turns + rotateToSide(returnSide(optimised[i][0])) + optimised[i][0]; // figure out how to use the
                                                                                         // moves
            cubeRotationsEffectImplementation(optimised[i][1]);
        }
    }

    public void cubeRotationsEffectImplementation(String pInput) { // name tentative; when cube rotations are done, the
                                                                   // position of the cube changes, but the code doesnt
                                                                   // reflect that
        switch (pInput) {
            case "1":
                ring = ring.substring(1) + ring.charAt(0);
                break;
            case "2":
                ring = ring.substring(2) + ring.substring(0, 2);
                break;
            case "3":
                ring = ring.charAt(3) + ring.substring(0, 3);
                break;
            default:
                break;
        }
    }

    public String[] makeIntoSideRotations(String moves) {
        String[] split = moves.split("#");
        String opt = "";
        for (String string : split) {
            opt = opt + breakDown(string);
        }
        split = opt.split("#");
        return split;
    }

    public String rotateToSide(char target) { // turns the side needed to perform the turn to the bottom
        String turns = null;
        String output = "";
        if (bottom != target) {
            do {
                turns = rotateRing(target);
                if (turns == null) {
                    makeX();
                    output = output + "x'#";
                }
            } while (turns == null);
            turns = output + turns;
            // move the face to the bottom
            makeX(); // this func still uses arduino notation, it has to, implement this differently!!!!
            turns = turns + "x'#";
        }
        if (turns == null) {
            return "";
        }
        return turns;
    }

    public void makeX() { // rename this
        char beforeBottom = bottom;
        bottom = ring.charAt(0);
        ring = "" + getOppositeSide(beforeBottom) + ring.charAt(1) + beforeBottom + ring.charAt(3);
    }

    public char getOppositeSide(char input) {
        switch (input) {
            case 'w':
                return 'y';
            case 'y':
                return 'w';
            case 'o':
                return 'r';
            case 'r':
                return 'o';
            case 'g':
                return 'b';
            case 'b':
                return 'g';
            default:
                System.out.println("Error: opposite Side not found");
                break;
        }
        return 0;
    }

    public String rotateRing(char target) {
        if (target == ring.charAt(0)) {
            return "";
        }
        String turns = "";
        for (int i = 0; i < 4; i++) {
            if (ring.charAt(0) == target) {
                return turns;
            }
            ring = ring.substring(1) + ring.charAt(0);
            turns = turns + "y#";
        }
        return null;
    }

    public String replaceInput(String input) { // # should not be the first char in a string
        input = input.replaceAll("##", "#");
        if (input.charAt(0) == '#') {
            input = input.substring(1);
        }
        return input;
    }

    public String[][] optimiseRotations(String[] pInput) { // make two separate Strings with turn and number
        String previous = pInput[1];
        String optRot = "";
        String optNum = "";
        int counter = 0;
        for (String string : pInput) {
            if (string.equals(previous)) {
                counter++;
            } else {
                optRot = optRot + "#" + previous;
                optNum = optNum + "#" + counter;
                previous = string;
                counter = 1;
                // slot++;
            }
        }
        optRot = optRot + "#" + previous;
        optNum = optNum + "#" + counter;
        String[] splitRot = optRot.split("#");
        String[] splitNum = optNum.split("#");
        String[][] optimised = new String[splitRot.length - 1][2];
        for (int i = 1; i < splitNum.length; i++) {
            optimised[i - 1][0] = splitRot[i];
            optimised[i - 1][1] = splitNum[i];
        }
        return optimised;
    }

    public char returnSide(String pInput) { // returns what side of the cube must be facing down to perform the turn
                                            // not functional
        char ret = 0;
        switch (pInput.charAt(0)) {
            case 'R':
                ret = 'b';
                break;
            case 'L':
                ret = 'g';
                break;
            case 'U':
                ret = 'y';
                break;
            case 'D':
                ret = 'w';
                break;
            case 'F':
                ret = 'o';
                break;
            case 'B':
                ret = 'r';
                break;
            case 'x':
                ret = 'b';
                break;
            case 'y':
                ret = 'w';
                break;
            case 'z':
                ret = 'o';
                break;
            default:
                break;
        }
        return ret;
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
