public class SolveF2L {
    private Cube cube;
    private NotationCompiler nc;
    private String[] block;

    public SolveF2L(Cube pCube, NotationCompiler pNotationCompiler) {
        cube = pCube;
        nc = pNotationCompiler;
        block = new String[4];
    }

    public void solveF2L() {
        String input = "";
        while (solveWhiteBottom() != null) {
            System.out.println(solveWhiteBottom());
            input = input + solveWhiteBottom();
            nc.executeString(solveWhiteBottom());
        }
        while (solveWhiteTop() != null) {
            input = input + solveWhiteTop();
            nc.executeString(solveWhiteTop());
        }
        System.out.println(input);
    }

    public String solveWhiteBottom() {
        String input = null;
        String[][] pos = findWhiteBlock();
        String iaj = null;
        for (int i = 0; i < pos.length; i++) {
            if (isElementOfBlock(pos[i][1])) {
                continue;
            }
            if (pos[i][1].equals("3;3") || pos[i][1].equals("3;5") || pos[i][1].equals("5;3")
                    || pos[i][1].equals("5;5")) { // is in white
                iaj = getCornerCoordinates(pos[i][1])[0] + ";" + getCornerCoordinates(pos[i][1])[1];
            } else {
                iaj = pos[i][1];
            }
            if (turnDirectionTop(iaj) == null) { // is actually at the bottom
                continue;
            }
            int counter = 0;
            while (checkTopCorner(pos[i][1])) {
                counter++;
                nc.executeTurn("U");
            }
            if (counter == 0) {
                input = "";
            } else if (counter == 1) {
                input = "U#";
                nc.executeTurn("U'");
            } else if (counter == 2) {
                input = "U2#";
                nc.executeTurn("U2");
            } else if (counter == 3) {
                input = "U'#";
                nc.executeTurn("U");
            }
            if (getSide(iaj) == 'o') {
                input = input + "F";
            } else if (getSide(iaj) == 'g') {
                input = input + "L";
            } else if (getSide(iaj) == 'b') {
                input = input + "R";
            } else if (getSide(iaj) == 'r') {
                input = input + "B";
            }
            input = input + turnDirectionTop(iaj) + "#U" + turnDirectionTop(iaj) + "#";
            if (getSide(iaj) == 'o') {
                input = input + "F";
            } else if (getSide(iaj) == 'g') {
                input = input + "L";
            } else if (getSide(iaj) == 'b') {
                input = input + "R";
            } else if (getSide(iaj) == 'r') {
                input = input + "B";
            }
            if (!turnDirectionTop(iaj).equals("'")) {
                input = input + "'#";
            } else {
                input = input + "#";
            }
            return input;
        }
        return null;
    }

    public String solveWhiteTop() {
        String input = null;
        String[][] pos = findWhiteBlock();
        for (int i = 0; i < pos.length; i++) {
            if (isElementOfBlock(pos[i][1])) {
                continue;
            }
            if (findTopSide(pos[i][1]) != null) {
                continue;
            }
            input = lineUpCorner(pos[i][0], pos[i][1]);
            if (getSide(pos[i][1]) == 'y') { // is in yellow
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                input = input + turnDirectionTopTop(pos[i][1]) + "#U2#";
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                if (!turnDirectionTopTop(pos[i][1]).equals("'")) {
                    input = input + "'#U'#";
                } else {
                    input = input + "#U#";
                }
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                input = input + turnDirectionTopTop(pos[i][1]) + "#U" + turnDirectionTopTop(pos[i][1]) + "#";
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                if (!turnDirectionTopTop(pos[i][1]).equals("'")) {
                    input = input + "'#";
                } else {
                    input = input + "#";
                }
                return input;
            } else {
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                input = input + turnDirectionTopTop(pos[i][1]) + "#U" + turnDirectionTopTop(pos[i][1]) + "#";
                if (getSide(pos[i][1]) == 'o') {
                    input = input + "F";
                } else if (getSide(pos[i][1]) == 'g') {
                    input = input + "L";
                } else if (getSide(pos[i][1]) == 'b') {
                    input = input + "R";
                } else if (getSide(pos[i][1]) == 'r') {
                    input = input + "B";
                }
                if (!turnDirectionTopTop(pos[i][1]).equals("'")) {
                    input = input + "'#";
                } else {
                    input = input + "#";
                }
                return input;
            }
        }
        return null;
    }

    public int getNewSide(String colour) {
        String ring = "ogrb";
        int counter = 0;
        for (int i = 0; i < ring.length(); i++) {
            if (colour.contains(ring.substring(0,1))) {
                break;
            }
            ring = ring.substring(1);
            counter++;
        }
        return counter;
    }

    public String getInbetweenSide(String iaj) {
        String side = "";
        String a = iaj;
        String b = getCornerCoordinates(iaj)[0] + ";" + getCornerCoordinates(iaj)[1];
        String c = getCornerCoordinates(iaj)[2] + ";" + getCornerCoordinates(iaj)[3];
        char[] S = new char[3];
        S[0] = getSide(a);
        S[1] = getSide(b);
        S[2] = getSide(c);
        for (char i : S) {
            if (i == 'y' || i == 'w') {
            } else {
                side = side + i;
            }
        }
        return side;
    }

    // colours = wgo
    public String lineUpCorner(String colours, String side) { // side gets converted from iaj to colours
        side = getInbetweenSide(side);
        String ring = "ogrb";
        String a = null;
        String b = null;
        for (int i = 0; i < ring.length(); i++) {
            a = "" + ring.charAt(0);
            b = "" + ring.charAt(1);
            if (side.contains(a) && side.contains(b)) {
                break;
            }
            ring = ring.substring(1) + ring.charAt(0);
        }
        int counter = 0;
        for (int i = 0; i < ring.length(); i++) {
            a = "" + ring.charAt(0);
            b = "" + ring.charAt(1);
            if (colours.contains(a) && colours.contains(b)) {
                counter = i;
                break;
            }
            ring = ring.substring(1) + ring.charAt(0);
        }
        if (counter == 0) {
            return "";
        } else if (counter == 1) {
            return "U#";
        } else if (counter == 2) {
            return "U2#";
        } else if (counter == 3) {
            return "U'#";
        }
        return null;
    }

    public char getSide(String iaj) { // returns the side of the cube as a color, assuming the default position
        int one = Integer.parseInt(iaj.substring(0, iaj.indexOf(";")));
        int two = Integer.parseInt(iaj.substring(iaj.indexOf(";") + 1));
        iaj = one / 3 + ";" + two / 3;
        if (iaj.equals("0;1")) {
            return 'o';
        } else if (iaj.equals("1;0")) {
            return 'g';
        } else if (iaj.equals("1;1")) {
            return 'w';
        } else if (iaj.equals("1;2")) {
            return 'b';
        } else if (iaj.equals("2;1")) {
            return 'r';
        } else if (iaj.equals("3;1")) {
            return 'y';
        }
        return '!';
    }

    public String turnDirectionTop(String iaj) { // returns if the turn to top is ' or non modified
        String c = null;
        switch (iaj) {
            case "3;2": // green
                c = "'";
                break;
            case "5;2":
                c = "";
                break;
            case "2;3": // orange
                c = "";
                break;
            case "2;5":
                c = "'";
                break;
            case "3;6": // blue
                c = "";
                break;
            case "5;6":
                c = "'";
                break;
            case "6;3": // red
                c = "'";
                break;
            case "6;5":
                c = "";
                break;
            default:
                break;
        }
        return c;
    }

    public String turnDirectionTopTop(String iaj) { // returns if the turn to top is ' or non modified
        String c = null;
        switch (iaj) {
            case "3;0": // green
                c = "'";
                break;
            case "5;0":
                c = "";
                break;
            case "0;3": // orange
                c = "";
                break;
            case "0;5":
                c = "'";
                break;
            case "3;8": // blue
                c = "";
                break;
            case "5;8":
                c = "'";
                break;
            case "8;3": // red
                c = "'";
                break;
            case "8;5":
                c = "";
                break;
            default:
                break;
        }
        return c;
    }

    public boolean checkTopCorner(String iaj) { // actually check the top lol
        String c = null; // coordinates
        String[][] pos = findWhiteBlock();
        iaj = findTopSide(iaj)[0] + ";" + findTopSide(iaj)[1];
        for (int i = 0; i < pos.length; i++) {
            if (iaj.equals(pos[i][1])) { // is it the same as the white coordinates
                c = pos[i][1];
                break;
            } else if (iaj.equals(getCornerCoordinates(pos[i][1])[0] + ";" + getCornerCoordinates(pos[i][1])[1])) {
                c = getCornerCoordinates(pos[i][1])[0] + ";" + getCornerCoordinates(pos[i][1])[1];
                break;
            } else if (iaj.equals(getCornerCoordinates(pos[i][1])[2] + ";" + getCornerCoordinates(pos[i][1])[3])) {
                c = getCornerCoordinates(pos[i][1])[2] + ";" + getCornerCoordinates(pos[i][1])[3];
                break;
            }
        }
        if (c != null) {
            int a = Integer.parseInt(c.substring(0, c.indexOf(";")));
            int b = Integer.parseInt(c.substring(c.indexOf(";") + 1));
            if (a / 3 == 3 && b / 3 == 1) { // are the coordinates in yellow
                return true;
            }
        }
        return false;
    }

    public boolean isElementOfBlock(String iaj) {
        for (int i = 0; i < block.length; i++) {
            if (iaj.equals(block[i])) {
                return true;
            }
        }
        return false;
    }

    public int[] findTopSide(String iaj) {
        String s = null;
        switch (iaj) {
            // orange
            case "2;3":
                s = "11;3";
                break;
            case "2;5":
                s = "11;5";
                break;
            // green
            case "3;2":
                s = "11;3";
                break;
            case "5;2":
                s = "9;3";
                break;
            // white
            case "3;3":
                s = "11;3";
                break;
            case "3;5":
                s = "11;5";
                break;
            case "5;3":
                s = "9;3";
                break;
            case "5;5":
                s = "9;5";
                break;
            // blue
            case "3;6":
                s = "11;5";
                break;
            case "5;6":
                s = "9;5";
                break;
            // red
            case "6;3":
                s = "9;3";
                break;
            case "6;5":
                s = "9;5";
                break;
            default:
                break;
        }
        if (s != null) {
            int[] top = { Integer.parseInt(s.substring(0, s.indexOf(";"))),
                    Integer.parseInt(s.substring(s.indexOf(";") + 1)) };
            return top;
        }
        return null;
    }

    public void blockGood(String[][] pos) {
        int counter = 0;
        for (int i = 0; i < pos.length; i++) {
            if (pos[i][1].equals("3;3")) {
                if (pos[i][0].contains("o") && pos[i][0].contains("g")) {
                    block[counter] = pos[i][1];
                }
            } else if (pos[i][1].equals("3;5")) {
                if (pos[i][0].contains("o") && pos[i][0].contains("b")) {
                    block[counter] = pos[i][1];
                }
            } else if (pos[i][1].equals("5;3")) {
                if (pos[i][0].contains("r") && pos[i][0].contains("g")) {
                    block[counter] = pos[i][1];
                }
            } else if (pos[i][1].equals("5;5")) {
                if (pos[i][0].contains("r") && pos[i][0].contains("b")) {
                    block[counter] = pos[i][1];
                }
            }
            counter++;
        }
    }

    public String[][] findWhiteCorner() {
        char[][] array = cube.getLayout();
        String[][] pos = new String[4][2];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'w' && getCornerCoordinates(i + ";" + j) != null) { // checks if the white is an edge
                    pos[counter][0] = "w";
                    pos[counter][1] = i + ";" + j;
                    counter++;
                }
            }
        }
        return pos;
    }

    public String[][] findWhiteBlock() {
        String[][] pos = findWhiteCorner();
        char[][] layout = cube.getLayout();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < pos.length; i++) {
            a = getCornerCoordinates(pos[i][1])[0];
            b = getCornerCoordinates(pos[i][1])[1];
            c = getCornerCoordinates(pos[i][1])[2];
            d = getCornerCoordinates(pos[i][1])[3];
            pos[i][0] = pos[i][0] + layout[a][b] + layout[c][d];
        }
        blockGood(pos);
        return pos;
    }

    public int[] getCornerCoordinates(String string) {
        int[] pos = new int[4];
        // orange
        if (string.equals("0;3")) {
            int[] array = { 3, 0, 11, 3 };
            pos = array;
        } else if (string.equals("0;5")) {
            int[] array = { 3, 8, 11, 5 };
            pos = array;
        } else if (string.equals("2;3")) {
            int[] array = { 3, 2, 3, 3 };
            pos = array;
        } else if (string.equals("2;5")) {
            int[] array = { 3, 5, 3, 6 };
            pos = array;
        }
        // green
        else if (string.equals("3;0")) {
            int[] array = { 0, 3, 11, 3 };
            pos = array;
        } else if (string.equals("3;2")) {
            int[] array = { 3, 3, 2, 3 };
            pos = array;
        } else if (string.equals("5;0")) {
            int[] array = { 8, 3, 9, 3 };
            pos = array;
        } else if (string.equals("5;2")) {
            int[] array = { 5, 3, 6, 3 };
            pos = array;
        }
        // white
        else if (string.equals("3;3")) {
            int[] array = { 3, 2, 2, 3 };
            pos = array;
        } else if (string.equals("3;5")) {
            int[] array = { 2, 5, 3, 6 };
            pos = array;
        } else if (string.equals("5;3")) {
            int[] array = { 5, 2, 6, 3 };
            pos = array;
        } else if (string.equals("5;5")) {
            int[] array = { 5, 6, 6, 5 };
            pos = array;
        }
        // blue
        else if (string.equals("3;6")) {
            int[] array = { 3, 5, 2, 5 };
            pos = array;
        } else if (string.equals("3;8")) {
            int[] array = { 0, 5, 11, 5 };
            pos = array;
        } else if (string.equals("5;6")) {
            int[] array = { 5, 5, 6, 5 };
            pos = array;
        } else if (string.equals("5;8")) {
            int[] array = { 8, 5, 9, 5 };
            pos = array;
        }
        // red
        else if (string.equals("6;3")) {
            int[] array = { 5, 2, 5, 3 };
            pos = array;
        } else if (string.equals("6;5")) {
            int[] array = { 5, 5, 5, 6 };
            pos = array;
        } else if (string.equals("8;3")) {
            int[] array = { 5, 0, 9, 3 };
            pos = array;
        } else if (string.equals("8;5")) {
            int[] array = { 5, 6, 9, 5 };
            pos = array;
        }
        // yellow
        else if (string.equals("9;3")) {
            int[] array = { 5, 0, 8, 3 };
            pos = array;
        } else if (string.equals("9;5")) {
            int[] array = { 5, 6, 8, 5 };
            pos = array;
        } else if (string.equals("11;3")) {
            int[] array = { 0, 3, 3, 0 };
            pos = array;
        } else if (string.equals("11;5")) {
            int[] array = { 0, 5, 3, 8 };
            pos = array;
        }
        if (pos[2] == 0) {
            return null;
        }
        return pos;
    }
}
