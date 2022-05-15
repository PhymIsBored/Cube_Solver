public class CrossSolver {
    private Cube cube;
    private NotationCompiler nc;
    private boolean turnBottom;

    public CrossSolver(Cube pCube, NotationCompiler pNotationCompiler) {
        cube = pCube;
        nc = pNotationCompiler;
        turnBottom = true;

    }

    public String solveWhiteCross() {
        String input = orientCube() + "\n"; // orient cube executes the moves in the function
        while (solveWhiteBottom() != null) {
            input = input + solveWhiteBottom() + "\n";
            nc.executeString(solveWhiteBottom());
        }
        while (solveWhiteSide() != null) {
            input = input + solveWhiteSide() + "\n";
            nc.executeString(solveWhiteSide());
        }
        while (solveWhiteBottom() != null) {
            input = input + solveWhiteBottom() + "\n";
            nc.executeString(solveWhiteBottom());
        }
        while (solveWhiteTop() != null) {
            input = input + solveWhiteTop() + "\n";
            nc.executeString(solveWhiteTop());
        }
        return input;
    }

    public String orientCube() {
        String input = null;
        if (cube.getColour(1, 4) == 'y') {
            input = "x#";
        } else if (cube.getColour(4, 1) == 'y') {
            input = "z#";
        } else if (cube.getColour(4, 4) == 'y') {
            input = "x2#";
        } else if (cube.getColour(4, 7) == 'y') {
            input = "z'#";
        } else if (cube.getColour(7, 4) == 'y') {
            input = "x'#";
        } else if (cube.getColour(10, 4) == 'y') {
            input = "";
        }
        nc.executeString(input);
        if (cube.getColour(1, 4) == 'o') {
            input = input + "";
        } else if (cube.getColour(4, 1) == 'o') {
            input = input + "y'#";
            nc.executeString("y'");
        } else if (cube.getColour(4, 4) == 'o') {
            input = input + "x#";
            nc.executeString("x");
        } else if (cube.getColour(4, 7) == 'o') {
            input = input + "y#";
            nc.executeString("y");
        } else if (cube.getColour(7, 4) == 'o') {
            input = input + "y2#";
            nc.executeString("y2");
        } else if (cube.getColour(10, 4) == 'o') {
            input = input + "x'#";
            nc.executeString("x'");
        }
        return input;
    }

    public String solveWhiteTop() { // returns the input as long as there are top positions to solve
        String input = null;
        String[][] pos = findWhiteBlock();
        for (int i = 0; i < pos.length; i++) {
            if (Integer.parseInt(pos[i][1].substring(0, pos[i][1].indexOf(";"))) / 3 == 3
                    && Integer.parseInt(pos[i][1].substring(pos[i][1].indexOf(";") + 1)) / 3 == 1) { // are coordinates
                                                                                                     // in yellow
                char b = getSideColour(getEdgeCoordinates(pos[i][1]));
                input = lineUpEdgeBottom(pos[i][0].charAt(1), b);

                if (input.equals("")) {
                    return null;
                }
                return input;
            }
        }
        return null;
    }

    public String solveWhiteBottom() {
        String input = null;
        String[][] pos = findWhiteBlock();
        for (int i = 0; i < pos.length; i++) {
            if (Integer.parseInt(pos[i][1].substring(0, pos[i][1].indexOf(";"))) / 3 == 1
                    && Integer.parseInt(pos[i][1].substring(pos[i][1].indexOf(";") + 1)) / 3 == 1) { // are coordinates
                                                                                                     // in white
                if (pos[i][0].charAt(1) == getSideColour(getEdgeCoordinates(pos[i][1]))) { // is already in the correct
                                                                                           // position?
                    turnBottom = false;
                    // return null; // why the heck did I write this, this is so dumb
                    continue;
                } else {
                    if (turnBottom == true) {
                        input = lineUpEdge(pos[i][0].charAt(1), getSideColour(getEdgeCoordinates(pos[i][1])));
                        if (input.equals("U#")) {
                            input = "D'#";
                        } else if (input.equals("U2#")) {
                            input = "D2#";
                        } else if (input.equals("U'#")) {
                            input = "D#";
                        }
                        turnBottom = false;
                        return input; // change after testing
                    }
                    String c = getEdgeCoordinates(pos[i][1]);
                    int one = Integer.parseInt(c.substring(0, c.indexOf(";")));
                    int two = Integer.parseInt(c.substring(c.indexOf(";") + 1));
                    String s = findTopSide(one / 3 + ";" + two / 3);
                    int a = Integer.parseInt(s.substring(0, s.indexOf(";")));
                    int b = Integer.parseInt(s.substring(s.indexOf(";") + 1));
                    int x = 0;
                    while (cube.getColour(a, b) == 'w') {
                        cube.turnU();
                        x++;
                    }
                    if (x == 0) {
                        input = "";
                    } else if (x == 1) {
                        input = "U#";
                        cube.turnU();
                        cube.turnU();
                        cube.turnU();
                    } else if (x == 2) {
                        input = "U2#";
                        cube.turnU();
                        cube.turnU();
                    } else if (x == 3) {
                        input = "U'#";
                        cube.turnU();
                    }
                    char pSide = getSide(getEdgeCoordinates(pos[i][1]));
                    if (pSide == 'o') {
                        input = input + "F2#";
                    } else if (pSide == 'g') {
                        input = input + "L2#";
                    } else if (pSide == 'r') {
                        input = input + "B2#";
                    } else if (pSide == 'b') {
                        input = input + "R2#";
                    }
                    return input;
                }
            }
        }
        return null;
    }

    public String solveWhiteSide() {
        String input = null;
        String[][] pos = findWhiteBlock();
        for (int i = 0; i < pos.length; i++) {
            char sideColour = getSideColour(getEdgeCoordinates(pos[i][1]));
            String locationOfWhite = Integer.parseInt(pos[i][1].substring(0, pos[i][1].indexOf(";"))) / 3 + ";"
                    + Integer.parseInt(pos[i][1].substring(pos[i][1].indexOf(";") + 1)) / 3;
            if (locationOfWhite.equals("1;1") || locationOfWhite.equals("3;1")) { // if it is on the the white or
                                                                                  // yellow side
                continue;
            }
            if (pos[i][0].charAt(1) == sideColour) { // is it already on the correct side
                String edgeIAJ = getEdgeCoordinates(pos[i][1]);
                int one = Integer.parseInt(edgeIAJ.substring(0, edgeIAJ.indexOf(";")));
                int two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";") + 1));
                String topSide = findTopSide(one / 3 + ";" + two / 3);
                int a = Integer.parseInt(topSide.substring(0, topSide.indexOf(";")));
                int b = Integer.parseInt(topSide.substring(topSide.indexOf(";") + 1));
                int x = 0;
                while (cube.getColour(a, b) == 'w') { // checks if the top is free, if not, turns it to be
                                                      // free
                    cube.turnU();
                    x++;
                }
                if (x == 0) {
                    input = "";
                } else if (x == 1) {
                    input = "U#";
                    cube.turnU();
                    cube.turnU();
                    cube.turnU();
                } else if (x == 2) {
                    input = "U2#";
                    cube.turnU();
                    cube.turnU();
                } else if (x == 3) {
                    input = "U'#";
                    cube.turnU();
                }
                if (pos[i][0].charAt(1) == 'o') { // which side to turn
                    input = input + "F";
                } else if (pos[i][0].charAt(1) == 'g') {
                    input = input + "L";
                } else if (pos[i][0].charAt(1) == 'r') {
                    input = input + "B";
                } else if (pos[i][0].charAt(1) == 'b') {
                    input = input + "R";
                }
                if (!turnDirectionTop(getEdgeCoordinates(pos[i][1])).equals("'")) { // which direction to turn
                                                                                    // (normal or reverse)
                    input = input + "'#";
                } else {
                    input = input + "#";
                }
                return input;
            } else { // it is not yet on the correct side
                String edgeIAJ = getEdgeCoordinates(pos[i][1]);
                int one = Integer.parseInt(edgeIAJ.substring(0, edgeIAJ.indexOf(";")));
                int two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";") + 1));
                String topSide = findTopSide(one / 3 + ";" + two / 3);
                if (topSide == null) { // if edge is at the top or bottom of the side
                    if (getSide(getEdgeCoordinates(pos[i][1])) == 'w') { // if it is the bottom side
                        one = Integer.parseInt(pos[i][1].substring(0, pos[i][1].indexOf(";")));
                        two = Integer.parseInt(pos[i][1].substring(pos[i][1].indexOf(";") + 1));
                        topSide = findTopSide(one / 3 + ";" + two / 3);
                        int a = Integer.parseInt(topSide.substring(0, topSide.indexOf(";")));
                        int b = Integer.parseInt(topSide.substring(topSide.indexOf(";") + 1));
                        edgeIAJ = getEdgeCoordinates(topSide);
                        one = Integer.parseInt(edgeIAJ.substring(0,edgeIAJ.indexOf(";")));
                        two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";")+1));
                        int x = 0;
                        while (cube.getColour(a, b) == 'w' || cube.getColour(one, two)=='w') { // checks if the top is free, if not, turns it to be
                                                              // free
                            cube.turnU();
                            x++;
                        }
                        if (x == 0) {
                            input = "";
                        } else if (x == 1) {
                            input = "U#";
                            cube.turnU();
                            cube.turnU();
                            cube.turnU();
                        } else if (x == 2) {
                            input = "U2#";
                            cube.turnU();
                            cube.turnU();
                        } else if (x == 3) {
                            input = "U'#";
                            cube.turnU();
                        }
                        char pSide = getSide(pos[i][1]); // turns it to the side
                        if (pSide == 'o') {
                            input = input + "F#";
                        } else if (pSide == 'g') {
                            input = input + "L#";
                        } else if (pSide == 'r') {
                            input = input + "B#";
                        } else if (pSide == 'b') {
                            input = input + "R#";
                        }
                    } else { // if it is the top side
                        one = Integer.parseInt(pos[i][1].substring(0, pos[i][1].indexOf(";")));
                        two = Integer.parseInt(pos[i][1].substring(pos[i][1].indexOf(";") + 1));
                        topSide = findBottomSide(one / 3 + ";" + two / 3);
                        int a = Integer.parseInt(topSide.substring(0, topSide.indexOf(";")));
                        int b = Integer.parseInt(topSide.substring(topSide.indexOf(";") + 1));
                        edgeIAJ = getEdgeCoordinates(topSide);
                        one = Integer.parseInt(edgeIAJ.substring(0,edgeIAJ.indexOf(";")));
                        two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";")+1));
                        int x = 0;
                        while (cube.getColour(a, b) == 'w'||cube.getColour(one, two)=='w') { // checks if the bottom is free, if not, turns it to
                                                              // be free 
                            cube.turnD();
                            x++;
                        }
                        if (x == 0) {
                            input = "";
                        } else if (x == 1) {
                            input = "D#";
                            cube.turnD();
                            cube.turnD();
                            cube.turnD();
                        } else if (x == 2) {
                            input = "D2#";
                            cube.turnD();
                            cube.turnD();
                        } else if (x == 3) {
                            input = "D'#";
                            cube.turnD();
                        }
                        char pSide = getSide(pos[i][1]); // turns it to the side
                        if (pSide == 'o') {
                            input = input + "F#";
                        } else if (pSide == 'g') {
                            input = input + "L#";
                        } else if (pSide == 'r') {
                            input = input + "B#";
                        } else if (pSide == 'b') {
                            input = input + "R#";
                        }
                        if (x==1) {
                            input = input + "D'#";
                        } else if (x==2) {
                            input = input + "D2";
                        } else if (x==3) {
                            input = input + "D";
                        }
                    }
                } else { // if it's on the middle of a side
                    int a = Integer.parseInt(topSide.substring(0, topSide.indexOf(";")));
                    int b = Integer.parseInt(topSide.substring(topSide.indexOf(";") + 1));
                    int x = 0;
                    edgeIAJ = getEdgeCoordinates(a + ";" + b);
                    one = Integer.parseInt(edgeIAJ.substring(0, edgeIAJ.indexOf(";")));
                    two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";") + 1));
                    while (cube.getColour(a, b) == 'w' || cube.getColour(one, two) == 'w') { // if top
                                                                                             // is white
                                                                                             // turn so
                                                                                             // it isnt
                        cube.turnU();
                        x++;
                    }
                    if (x == 0) {
                        input = "";
                    } else if (x == 1) {
                        input = "U#";
                        cube.turnU();
                        cube.turnU();
                        cube.turnU();
                    } else if (x == 2) {
                        input = "U2#";
                        cube.turnU();
                        cube.turnU();
                    } else if (x == 3) {
                        input = "U'#";
                        cube.turnU();
                    }
                    edgeIAJ = getEdgeCoordinates(pos[i][1]);
                    a = Integer.parseInt(edgeIAJ.substring(0, edgeIAJ.indexOf(";")));
                    b = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";")+1));
                    topSide = findBottomSide(a / 3 + ";" + b / 3); // now bottom side
                    a = Integer.parseInt(topSide.substring(0, topSide.indexOf(";")));
                    b = Integer.parseInt(topSide.substring(topSide.indexOf(";")+1));
                    edgeIAJ = getEdgeCoordinates(a + ";" + b);
                    one = Integer.parseInt(edgeIAJ.substring(0, edgeIAJ.indexOf(";")));
                    two = Integer.parseInt(edgeIAJ.substring(edgeIAJ.indexOf(";")+1));
                    x = 0;
                    while (cube.getColour(a, b) == 'w' || cube.getColour(one, two) == 'w') {
                        cube.turnD();
                        if (x > 3) {
                            throw new Error("too many turns");
                        }
                        x++;
                    }
                    if (x == 1) {
                        input = input + "D#";
                        nc.executeTurn("D'");
                    } else if (x == 2) {
                        input = input + "D2#";
                        nc.executeTurn("D2");
                    } else if (x == 3) {
                        input = input + "D'#";
                        nc.executeTurn("D");
                    }
                    // turn up
                    char pSide = getSide(getEdgeCoordinates(pos[i][1]));
                    if (pSide == 'o') {
                        input = input + "F";
                    } else if (pSide == 'g') {
                        input = input + "L";
                    } else if (pSide == 'r') {
                        input = input + "B";
                    } else if (pSide == 'b') {
                        input = input + "R";
                    }
                    input = input + turnDirectionTop(getEdgeCoordinates(pos[i][1])) + "#";
                    if (x == 1) {
                        input = input + "D'#";
                    } else if (x == 2) {
                        input = input + "D2#";
                    } else if (x == 3) {
                        input = input + "D#";
                    }
                }
            }
            if (input.equals("null")) {
                return null;
            }
            return input;
        }
        return input;
    }

    public String findTopSide(String iaj) {
        String c = null;
        switch (iaj) {
            case "0;1":
                c = "11;4";
                break;
            case "1;0":
                c = "10;3";
                break;
            case "1;2":
                c = "10;5";
                break;
            case "2;1":
                c = "9;4";
                break;
            default:
                break;
        }
        return c;
    }

    public String findBottomSide(String iaj) {
        String c = null;
        switch (iaj) {
            case "0;1":
                c = "3;4";
                break;
            case "1;0":
                c = "4;3";
                break;
            case "1;2":
                c = "4;5";
                break;
            case "2;1":
                c = "5;4";
                break;
            default:
                break;
        }
        return c;
    }

    public String lineUpEdge(char pColour, char pSideColour) {
        String input = null;
        String ring = "ogrb";
        for (int i = 0; i < ring.length(); i++) {
            if (pSideColour == ring.charAt(0)) {
                break;
            }
            ring = ring.substring(1, 4) + ring.charAt(0);
        }
        int a = 0;
        for (int i = 0; i < ring.length(); i++) {
            if (pColour == ring.charAt(0)) {
                break;
            }
            ring = ring.substring(1, 4) + ring.charAt(0);
            a++;
        }
        if (a == 0) {
            input = "";
        } else if (a == 1) {
            input = "U#";
        } else if (a == 2) {
            input = "U2#";
        } else if (a == 3) {
            input = "U'#";
        }
        return input;
    }

    public String lineUpEdgeBottom(char pColour, char pSideColour) {
        String input = lineUpEdge(pColour, pSideColour); // solve this in one go, not two
        if (pColour == 'o') {
            input = input + "F2#";
        } else if (pColour == 'g') {
            input = input + "L2#";
        } else if (pColour == 'r') {
            input = input + "B2#";
        } else if (pColour == 'b') {
            input = input + "R2#";
        }
        return input;
    }

    public String[][] findWhiteEdges() {
        char[][] array = cube.getLayout();
        String[][] pos = new String[4][2];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'w' && getEdgeCoordinates(i + ";" + j) != null) { // checks if the white is an edge
                                                                                     // piece
                    pos[counter][0] = "w";
                    pos[counter][1] = i + ";" + j;
                    counter++;
                }
            }
        }
        return pos;
    }

    public String[][] findWhiteBlock() {
        String[][] pos = findWhiteEdges();
        char[][] layout = cube.getLayout();
        int a = 0;
        int b = 0;
        for (int i = 0; i < pos.length; i++) {
            String c = getEdgeCoordinates(pos[i][1]);
            a = Integer.parseInt(c.substring(0, c.indexOf(";")));
            b = Integer.parseInt(c.substring(c.indexOf(";") + 1));
            pos[i][0] = pos[i][0] + layout[a][b];
        }
        return pos;
    }

    public char getSideColour(String iaj) { // returns the side of the cube as a color, assuming the default position
        int one = Integer.parseInt(iaj.substring(0, iaj.indexOf(";")));
        int two = Integer.parseInt(iaj.substring(iaj.indexOf(";") + 1));
        iaj = one / 3 + ";" + two / 3;
        if (iaj.equals("0;1")) {
            return cube.getColour(1, 4);
        } else if (iaj.equals("1;0")) {
            return cube.getColour(4, 1);
        } else if (iaj.equals("1;1")) {
            return cube.getColour(4, 4);
        } else if (iaj.equals("1;2")) {
            return cube.getColour(4, 7);
        } else if (iaj.equals("2;1")) {
            return cube.getColour(7, 4);
        } else if (iaj.equals("3;1")) {
            return cube.getColour(10, 4);
        }
        return '!';
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
            case "3;1": // green
                c = "'";
                break;
            case "5;1":
                c = "";
                break;
            case "1;3": // orange
                c = "";
                break;
            case "1;5":
                c = "'";
                break;
            case "3;7": // blue
                c = "";
                break;
            case "5;7":
                c = "'";
                break;
            case "7;3": // red
                c = "'";
                break;
            case "7;5":
                c = "";
                break;
            default:
                break;
        }
        return c;
    }
    
    public String getEdgeCoordinates(String iaj) { // finds the coordinates of the other tile of a middle block
                                                   // (between
                                                   // two corners)
        String c = null;
        switch (iaj) {
            case "4;0": // green
                c = "10;3";
                break;
            case "3;1":
                c = "1;3";
                break;
            case "5;1":
                c = "7;3";
                break;
            case "4;2":
                c = "4;3";
                break;
            case "1;3": // orange
                c = "3;1";
                break;
            case "0;4":
                c = "11;4";
                break;
            case "1;5":
                c = "3;7";
                break;
            case "2;4":
                c = "3;4";
                break;
            case "4;3": // white
                c = "4;2";
                break;
            case "3;4":
                c = "2;4";
                break;
            case "4;5":
                c = "4;6";
                break;
            case "5;4":
                c = "6;4";
                break;
            case "4;6": // blue
                c = "4;5";
                break;
            case "3;7":
                c = "1;5";
                break;
            case "4;8":
                c = "10;5";
                break;
            case "5;7":
                c = "7;5";
                break;
            case "7;3": // red
                c = "5;1";
                break;
            case "6;4":
                c = "5;4";
                break;
            case "7;5":
                c = "5;7";
                break;
            case "8;4":
                c = "9;4";
                break;
            case "10;3": // yellow
                c = "4;0";
                break;
            case "9;4":
                c = "8;4";
                break;
            case "10;5":
                c = "4;8";
                break;
            case "11;4":
                c = "0;4";
                break;
            default:
                break;
        }
        return c;
    }
}
