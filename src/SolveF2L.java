public class SolveF2L {
    private Cube cube;
    private NotationCompiler nc;
    private String[] block;

    public SolveF2L(Cube pCube, NotationCompiler pNotationCompiler) {
        cube = pCube;
        nc = pNotationCompiler;
        block = new String[4];
    }

    public void solveWhiteBottom() {
        String input = null;
        String[][] pos = findWhiteBlock();
        // check top corner
        // if one of the coordinates == findTopSide
        int counter = 0;
        while (checkTopCorner(pos[0][1], pos)) {
            counter++;
            nc.executeTurn("U");
        }
        if (counter == 0) {
            input = "";
        } else if (counter == 1) {
            input = "U";
            nc.executeTurn("U'");
        } else if (counter==2) {
            input = "U2";
            nc.executeTurn("U2");
        } else if (counter == 3) {
            input = "U'";
            nc.executeTurn("U");
        }
    }

    public boolean checkTopCorner(String iaj, String[][] pos) {
        String c = null; // coordinates
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
        if (pos[3] == 0) {
            return null;
        }
        return pos;
    }
}
