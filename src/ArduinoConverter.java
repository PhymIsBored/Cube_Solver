public class ArduinoConverter {
    private char bottom;
    private String ring;
    private boolean handUp;

    public ArduinoConverter() {
        bottom = 'w';
        ring = "ogrb"; // obrg
        handUp = false;
    }

    public void convertInput(String pInput) {
        String[] split = makeIntoSideRotations(pInput); // convert pInput to array with side rotations
        String[][] optimised = optimiseRotations(split); // convert to optimised array
        // turn optimised into arduino rotations
        String arduinoRotations = convertToArduinoRotations(optimised);
        System.out.println(arduinoRotations);
        printArduinoText(arduinoRotations);
    }

    public void printArduinoText(String pInput) {
        pInput = pInput.replaceAll("#", "");
        System.out.println(pInput);
    }

    private String convertToArduinoRotations(String[][] optimised) { // this doesnt turn to the right side yet
        String turns = "";
        for (int i = 0; i < optimised.length; i++) {
            // turn to the right side
            turns = turns + rotateToSide(returnSide(optimised[i][0]));
            // determine if hand needs to be up or down
            // then move it either up or down
            if (optimised[i][0].equals("x") || optimised[i][0].equals("y") || optimised[i][0].equals("z")) { // if it is
                                                                                                             // a cube
                                                                                                             // rotation
                // if the hand is down move it up with 4
                if (!handUp) {
                    turns = turns + "4#" + optimised[i][1] + "#";
                    handUp = true;
                } else {
                    turns = turns + optimised[i][1] + "#";
                }
            } else { // if it is a side rotation
                // if the hand is up move it down with 5
                if (handUp) {
                    turns = turns + "5#" + optimised[i][1] + "#";
                    handUp = false;
                } else {
                    turns = turns + optimised[i][1] + "#";
                }
            }
        }
        return turns;
    }

    public String rotateToSide(char pSide) {
        if (pSide == 0) {
            return null;
        }
        String turns = "";
        if (bottom != pSide) { // if the bottom is not already correct
            turns = rotateRing(pSide); // check if the correct side can be rotated to the front
            if (turns == null) { // if the correct side can not be roted to the front, rotate X
                // check if the hand is up or down
                if (handUp) { // if the hand is up, turn it down
                    turns = "5#";
                    handUp = false;
                } // if the hand is down, leave it down
                  // do X at some point here
                  // !! ^^^
                makeXrot();
                turns = "6#";
                turns = turns + rotateRing(pSide); // rotate the side to the front
            } else { // if it can be rotated turn the hand up before
                if (!handUp) {
                    turns = "4#" + turns;
                    handUp = true;
                }
            }
            if (handUp) { // if the hand is up, turn it down
                turns = turns + "5#";
                handUp = false;
            }
            makeXrot();
            turns = turns + "6#";
        }
        return turns;
    }

    public String rotateRing(char target) {
        if (target == ring.charAt(0)) {
            return "";
        }
        int turns = 0;
        for (int i = 0; i < 4; i++) {
            if (target == ring.charAt(0)) {
                return Integer.toString(turns) + "#"; // return the number of turns
            }
            ring = ring.substring(1) + ring.charAt(0);
            turns++;
        }
        return null;
    }

    public void makeXrot() { // rename this
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

    public String[] makeIntoSideRotations(String moves) {
        String[] split = moves.split("#");
        String opt = "";
        for (String string : split) {
            opt = opt + breakDown(string);
        }
        split = opt.split("#");
        return split;
    }

    public String[][] optimiseRotations(String[] pInput) { // make two separate Strings with turn and number
        String previous = pInput[0];
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
                ret = 'y';
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
        // this thing is broken rn, fix this!!!!
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
                return "z#z#z#B#B#B#";

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
                return "y#y#y#U#D#D#D#";
            // System.out.println("E");

            case "S":
                return "z#F#F#F#B#";
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
                break;
        }
        return null;
    }
}
