public class CubeSolver {
    private Cube cube;
    private String[][] F2L;
    private String[][] PLL;
    private String[][] OLL;

    public CubeSolver(Cube pCube) {
        cube = pCube;
        F2L = new String[164][2];
        PLL = new String[21][2];
        OLL = new String[57][2];
        fillF2L();
        fillPLL();
        fillOLL();

    }

    public String solveCube() {
        String input = null;
        input = solveF2L();
        input = input + "#" + solveOLL();
        input = input + "#" + solvePLL();
        if (input.equals("")) {
            return null;
        }
        return input;
    }

    public String solvePLL() {
        String input = null;
        String c = getRing();
        String s = null;
        for (int i = 0; i < PLL.length; i++) {
            s = compareRing(c, PLL[i][0]);
            if (s.charAt(0) == '1') {
                if (Character.getNumericValue(s.charAt(2)) > 0) {
                    input = "y2" + PLL[i][1];
                } else if (Character.getNumericValue(s.charAt(2)) == 3) {
                    input = "y'" + PLL[i][1];
                }
            } else {
                input = PLL[i][1];
            }
        }
        return input;
    }

    // Requirements outer;inner
    public String solveOLL() {
        String input = null;
        String a = getRing();
        String b = getInnerRing();
        String s = null;
        String[] split = new String[2];
        String rO = null;
        String rI = null;
        for (int i = 0; i < OLL.length; i++) {
            split = OLL[i][0].split(";");
            rO = split[0];
            rI = split[1];
            s = compareRing(a, rO) + "|" + compareInnerRing(b, rI);
            if (s.charAt(0) == '1' && s.charAt(4) == '1' && s.charAt(2) == s.charAt(6)) { // check if rotation is the
                                                                                          // same for both rings
                if (Character.getNumericValue(s.charAt(2)) > 0) { // this might not work, test
                                                                  // later!!!!!!!!!!!!!!!!< should work now
                    if (Character.getNumericValue(s.charAt(2)) == 1) {
                        input = "y" + OLL[i][1];
                    } else if (Character.getNumericValue(s.charAt(2)) == 2) {
                        input = "y2" + OLL[i][1];
                    } else if (Character.getNumericValue(s.charAt(2)) == 3) {
                        input = "y'" + OLL[i][1];
                    }
                } else {
                    input = OLL[i][1];
                }
            }
        }
        return input;
    }

    public String compareInnerRing(String pRing, String pRequirement) {
        for (int i = 0; i < pRing.length(); i = i + 2) {
            if (generateRing(pRing).equals(generateRing(pRequirement))) {
                return "1;" + i / 2;
            }
            pRing = pRing.substring(2) + pRing.substring(0, 2);
        }
        return "0;0";
    }

    public String compareRing(String pRing, String pRequirement) {
        for (int i = 0; i < pRing.length(); i = i + 3) {
            if (generateRing(pRing).equals(generateRing(pRequirement))) {
                return "1;" + i / 3;
            }
            pRing = pRing.substring(3) + pRing.substring(0, 3);
        }
        return "0";
    }

    public String generateRing(String pInput) { // turns the ring into a comparable ring
        int j = 2;
        pInput = pInput.replace(pInput.charAt(0), '1');
        for (int i = 1; i < pInput.length(); i++) {
            if (!Character.isDigit(pInput.charAt(i))) {
                pInput = pInput.replace(pInput.charAt(i), Integer.toString(j).charAt(0));
                j++;
            } else {

            }
        }
        return pInput;
    }

    public String getRing() { // returns the ring around the yellow face
        char[][] a = cube.getLayout();
        String ring = "";
        ring = ring + a[0][3];// orange
        ring = ring + a[0][4];
        ring = ring + a[0][5];
        ring = ring + a[3][8];// blue
        ring = ring + a[4][8];
        ring = ring + a[5][8];
        ring = ring + a[8][5];// red
        ring = ring + a[8][4];
        ring = ring + a[8][3];
        ring = ring + a[5][0];// green
        ring = ring + a[4][0];
        ring = ring + a[3][0];
        return ring;
    }

    public String getInnerRing() { // returns the ring on the yellow field, around the middle
        char[][] a = cube.getLayout();
        String ring = "";
        ring = ring + a[9][3];
        ring = ring + a[9][4];
        ring = ring + a[9][5];
        ring = ring + a[10][5];
        ring = ring + a[11][5];
        ring = ring + a[11][4];
        ring = ring + a[11][3];
        ring = ring + a[10][3];
        return ring;
    }

    public String solveF2L() {
        String input = null;
        for (int i = 0; i < F2L.length; i++) {
            if (checkRequirments(F2L[i][0])) {
                input = F2L[i][1];
            } else {
                // System.out.println("Nothing found at "+i);
            }
        }
        return input;
    }

    public boolean checkRequirments(String pInput) {
        if (pInput.length() < 5) { // checks if the input is empty; remove later
            return false;
        }
        String[] split = pInput.split("&");
        int counter = 0;
        String r = null;
        String pCoordinate = null;
        char pColor = 0;
        for (int i = 0; i < split.length; i++) {
            r = cube.findSide(split[i].charAt(0));
            pCoordinate = split[i].substring(2, 4);
            pColor = split[i].charAt(5);
            if (cube.checkRequired(r, pCoordinate, pColor) == true) {
                counter++;
            }
        }
        if (counter == split.length) {
            return true;
        }
        return false;
    }

    public void changeF2L() {
        String[] split = F2L[0][0].split("&");
        String[][] d = new String[164][2];
        fillD(d);
        String n = "";
        for (int a = 0; a < d.length; a = a + 4) {
            split = F2L[a][0].split("&");
            for (int i = 0; i < split.length; i++) {
                n = "";
                for (int j = 0; j < 4; j++) {
                    if (split[i].charAt(0) == 'B') {
                        n = "" + changeB(j) + ':';
                    } else if (split[i].charAt(0) == 'R') {
                        n = "" + changeR(j) + ':';
                    } else if (split[i].charAt(0) == 'Y') {
                        n = "Y:";
                    }
                    if (split[i].charAt(0) == 'Y') {
                        n = n + changeYellowC(split[i].substring(2, 4), j) + '/';
                    } else {
                        n = n + split[i].substring(2, 4) + '/';
                    }
                    System.out.println(a);
                    if (split[i].charAt(5) == 'b') {
                        n = n + Character.toLowerCase(changeB(j));
                    } else if (split[i].charAt(5) == 'r') {
                        n = n + Character.toLowerCase(changeR(j));
                    } else {
                        n = n + 'w';
                    }
                    d[a + j][0] = d[a + j][0] + n + "&";

                    if (j == 3) { // if orange
                        d[a + j][1] = F2L[a + j][1];
                    } else {
                        d[a + j][1] = F2L[a + j][1] + F2L[a + 3][1];
                    }
                }
            }
        }
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                System.out.println(d[i][j]);
            }
        }
    }

    public String[][] fillD(String[][] d) {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                d[i][j] = "";
            }
        }
        return d;
    }

    public char changeB(int i) {
        char r = '0';
        switch (i) {
            case 0:
                r = 'B';
                break;
            case 1:
                r = 'R';
                break;
            case 2:
                r = 'G';
                break;
            case 3:
                r = 'O';
                break;

            default:
                break;
        }
        return r;
    }

    public char changeR(int i) {
        char r = '0';
        switch (i) {
            case 0:
                r = 'R';
                break;
            case 1:
                r = 'G';
                break;
            case 2:
                r = 'O';
                break;
            case 3:
                r = 'B';
                break;

            default:
                break;
        }
        return r;
    }

    public String changeYellowC(String pC, int i) { // changes the coordinates in the yellow field based on number of y
                                                    // rotations
        String r = null;
        switch (i) {
            case 0:
                r = pC;
                break;
            case 1:
                if (pC.charAt(1) == '1') {
                    r = "A";
                } else if (pC.charAt(1) == '2') {
                    r = "B";
                } else if (pC.charAt(1) == '3') {
                    r = "C";
                }
                if (pC.charAt(0) == 'A') {
                    r = r + "3";
                } else if (pC.charAt(0) == 'B') {
                    r = r + "2";
                } else if (pC.charAt(0) == 'C') {
                    r = r + "1";
                }
                break;
            case 2:
                if (pC.charAt(0) == 'A') {
                    r = "C";
                } else if (pC.charAt(0) == 'B') {
                    r = "B";
                } else if (pC.charAt(0) == 'C') {
                    r = "A";
                }
                if (pC.charAt(1) == '1') {
                    r = r + "3";
                } else if (pC.charAt(1) == '2') {
                    r = r + "2";
                } else if (pC.charAt(1) == '3') {
                    r = r + "1";
                }
                break;
            case 3:
                if (pC.charAt(1) == '1') {
                    r = "C";
                } else if (pC.charAt(1) == '2') {
                    r = "B";
                } else if (pC.charAt(1) == '3') {
                    r = "A";
                }
                if (pC.charAt(0) == 'A') {
                    r = r + "1";
                } else if (pC.charAt(0) == 'B') {
                    r = r + "2";
                } else if (pC.charAt(0) == 'C') {
                    r = r + "3";
                }
                break;

            default:
                break;
        }
        return r;
    }

    public void fillOLL() { // outer;inner
        OLL[0][0] = "XYXYYYXYXYYY;XXXXXXXX";
        OLL[0][1] = "R#U2#R2#F#R#F'#U2#R'#F#R#F'";
        OLL[1][0] = "YYYXYYXYXYYX;XXXXXXXX";
        OLL[1][1] = "r#U#r'#U2#r#U2#R'#U2#R#U'#r'";
        OLL[2][0] = "YYXYYXYYXXYX;XXXXXXYX";
        OLL[2][1] = "r'#R2#U#R'#U#r#U2#r'#U#M'";
        OLL[3][0] = "XYYXYXXYYXYY;XXXXYXXX";
        OLL[3][1] = "M#U'#r#U2#r'#U'#R#U'#R'#M'";
        OLL[4][0] = "XXXYYXYYXYXX;YYXXXXXY";
        OLL[4][1] = "l'#U2#L#U#L'#U#l";
        OLL[5][0] = "XXXXXYXYYXYY;XYYYXXXX";
        OLL[5][1] = "r#U2#R'#U'#R#U'#r'";
        OLL[6][0] = "YXXYYXYYXXXX;XYXXXXYY";
        OLL[6][1] = "r#U#R'#U#R#U2#r'";
        OLL[7][0] = "XXYXXXXYYXYY;XYXYYXXX";
        OLL[7][1] = "l'#U'#L#U'#L'#U2#l";
        OLL[8][0] = "XXYXYXXYYXXY;XYXXYXXY";
        OLL[8][1] = "R#U#R'#U'#R'#F#R2#U#R'#U'#F'";
        OLL[9][0] = "YYXXYXYXXYXX;XXYXXYXY";
        OLL[9][1] = "R#U#R'#U#R'#F#R#F'#R#U2#R'";
        OLL[10][0] = "YXXXYXYYXYXX;XYYXXXXY";
        OLL[10][1] = "r#U#R'#U#R'#F#R#F'#R#U2#r'";
        OLL[11][0] = "XXYXXYXYYXYX;YYXYXXXX";
        OLL[11][1] = "M'#R'#U'#R#U'#R'#U2#R#U'#R#r'";
        OLL[12][0] = "YYXYXXYYXXXX;XXXYXXYY";
        OLL[12][1] = "F#U#R#U'#R2#F'#R#U#R#U'#R'";
        OLL[13][0] = "XYYXXXXYYXXY;XXXYYXXY";
        OLL[13][1] = "R'#F#R#U#R'#F'#R#F#U'#F'";
        OLL[14][0] = "XYXYXXYYXYXX;YXXYXXXY";
        OLL[14][1] = "l'#U'#l#L'#U'#L#U#l'#U#l";
        OLL[15][0] = "XYXXXYXYYXXY;XXYYXXXY";
        OLL[15][1] = "r#U#r'#R#U#R'#U'#r#U'#r'";
        OLL[16][0] = "XYXYYXXYYXYX;YXXXYXXX";
        OLL[16][1] = "F#R'#F'#R2#r'#U#R#U'#R'#U'#M'";
        OLL[17][0] = "XYXXYXYYYXYX;YXYXXXXX";
        OLL[17][1] = "r#U#R'#U#R#U2#r2#U'#R#U'#R'#U2#r";
        OLL[18][0] = "XYXXYYXYXYYX;YXYXXXXX";
        OLL[18][1] = "r'#R#U#R#U#R'#U'#M'#R'#F#R#F'";
        OLL[19][0] = "XYXXYXXYXXYX;YXYXYXYX";
        OLL[19][1] = "r#U#R'#U'#M2#U#R#U'#R'#U'#M'";
        OLL[20][0] = "YXYXXXYXYXXX;XYXYXYXY";
        OLL[20][1] = "R#U2#R'#U'#R#U#R'#U'#R#U'#R'";
        OLL[21][0] = "XXYXXXYXXYXY;XYXYXYXY";
        OLL[21][1] = "R#U2#R2#U'#R2#U'#R2#U2#R";
        OLL[22][0] = "YXYXXXXXXXXX;XYXYYYYYX";
        OLL[22][1] = "R2#D'#R#U2#R'#D#R#U2#R";
        OLL[23][0] = "XXXXXXXYXXX;XYYYXYYYX";
        OLL[23][1] = "r#U#R'#U'#r'#F#R#F'";
        OLL[24][0] = "XXXXXXYXXXXY;XYYYXYYYX";
        OLL[24][1] = "F'#r#U#R'#U'#r'#F#R";
        OLL[25][0] = "XXXXXYXXYXXY;XYYYXYXYX";
        OLL[25][1] = "R#U2#R'#U'#R#U'#R'";
        OLL[26][0] = "YXXYXXYXXXXX;XYXYXYYY";
        OLL[26][1] = "R#U#R'#U#R#U2#R'";
        OLL[27][0] = "XXXXYXXYXXXX;YYYXYXYY";
        OLL[27][1] = "r#U#R'#U'#r'#R#U#R#U'#R'";
        OLL[28][0] = "YXXXYXXYYXXX;XYYXYXXY";
        OLL[28][1] = "R#U#R'#U'#R#U'#R'#F'#U'#F#R#U#R'";
        OLL[29][0] = "XXXYYXXYXXXY;XYXXYXYY";
        OLL[29][1] = "F#R'#F#R2#U'#R'#U'#R#U#R'#F2";
        OLL[30][0] = "YXXXXXXYYXYX;XYYYYXXX";
        OLL[30][1] = "R'#U'#F#U#R#U'#R'#F'#R";
        OLL[31][0] = "XXYXYXYYXXXX;YYXXXXYY";
        OLL[31][0] = "L#U#F'#U'#L'#U#L#F#L'";
        OLL[32][0] = "YYXXXXXYYXXX;XXYYYXXY";
        OLL[32][0] = "R#U#R'#U'#R'#F#R#F'";
        OLL[33][0] = "XYXYXXXYXXXY;XXXYYXYY";
        OLL[33][0] = "R#U#R2#U'#R'#F#R#U#R#U'#F'";
        OLL[34][0] = "XYXYXXXXYXYX;YXXYYYXX";
        OLL[34][1] = "R#U2#R2#F#R#F'#R#U2#R'";
        OLL[35][0] = "XXYXXXXYXYYX;YYXYYXXX";
        OLL[35][1] = "L'#U'#L#U'#L'#U#L#U#L#F'#L'#F";
        OLL[36][0] = "XXXYYXXYYXXX;YYXXYXXY";
        OLL[36][1] = "F#R'#F'#R#U#R#U'#R'";
        OLL[37][0] = "YXXXYYXYXXXX;XYYXXXYY";
        OLL[37][1] = "R#U#R'#U#R#U'#R'#U'#R'#F#R#F'";
        OLL[38][0] = "YYXXXYXYXXXX;XXYYXXYY";
        OLL[38][1] = "L#F'#L'#U'#L#U#F#U'#L'";
        OLL[39][0] = "XYYXXXXYXYXX;YXXYYXXY";
        OLL[39][1] = "L#F'#L'#U'#L#U#F#U'#L'";
        OLL[40][0] = "YXYXYXXYXXXX;XYXXYXYY";
        OLL[40][1] = "R#U#R'#U#R#U2#R'#F#R#U#R'#U'#F";
        OLL[41][0] = "XYXXYXYXYXXX;YXYXXYXY";
        OLL[41][1] = "R'#U'#R#U'#R'#U2#R#F#R#U#R'#U'#F'";
        OLL[42][0] = "XXXXXXXYXYYY;XYYYYXXX";
        OLL[42][1] = "F'#U'#L'#U#L#F";
        OLL[43][0] = "XXXYYYXYXXXX;YYXXXXYY";
        OLL[43][1] = "F#U#R#U'#R'#F";
        OLL[44][0] = "XYXXXXXYXYXY;XXYYYXXY";
        OLL[44][1] = "F#R#U#R'#U'#F";
        OLL[45][0] = "XXXYYYXXXXYX;YYXXXYYX";
        OLL[45][1] = "R'#U'#R'#F#R#F'#U#R";
        OLL[46][0] = "YXXYXYXYYXYX;XYXYXXXX";
        OLL[46][1] = "R'#U'#R'#F#R#F'#R'#F#R#F'#U#R";
        OLL[47][0] = "XXYXYXYYXYXY;XYXXXXXY";
        OLL[47][1] = "F#R#U#R'#U'#R#U#R'#U'#F'";
        OLL[48][0] = "XXYXXXYYXYYY;XYXYXXXX";
        OLL[48][1] = "r#U'#r2#U#r2#U#r2#U'#r";
        OLL[49][0] = "XYYXXXYXXYYY;XXXYXYXX";
        OLL[49][1] = "r'#U#r2#U'#r2#U'#r2#U#r'";
        OLL[50][0] = "YYXYXYXYYXXX;XXXYXXXY";
        OLL[50][1] = "F#U#R#U'#R'#U#R#U'#R'#F";
        OLL[51][0] = "YXXYYYXXYXYX;XYXXXYXX";
        OLL[51][1] = "R#U#R'#U#R#U'#B#U'#B'#R'";
        OLL[52][0] = "YXYXXXYYYXYX;XYXYXXXX";
        OLL[52][1] = "l'#U2#L#U#L'#U'#L#U#L'#U#l";
        OLL[53][0] = "YXYXYXYYYXXX;XYXXXXXY";
        OLL[53][1] = "r#U2#R'#U'#R#U#R'#U'#R#U'#r'";
        OLL[54][0] = "YYYXXXYYYXXX;XXXYXXXY";
        OLL[54][1] = "R'#F#R#U#R#U'#R2#F'#R2#U'#R'#U#R#U#R'";
        OLL[55][0] = "XYXYXYXYXYXY;XXXYXXXY";
        OLL[55][1] = "r'#U'#r#U'#R'#U#R#U'#R'#U#R#r'#U#r";
        OLL[56][0] = "XYXXXXXYXXXX;YXYYYXYY";
        OLL[56][1] = "R#U#R'#U'#M'#U#R#U'#r'";
    }

    public void fillPLL() {
        PLL[0][0] = "RGRBROGBBOOG";
        PLL[0][1] = "R'#F#R'#B2#R#F'#R'#B2#R2";
        PLL[1][0] = "BGOGRGRBBOOR";
        PLL[1][1] = "y'#x#L#U'#L#D2#L'#U#L#D2#L2";
        PLL[2][0] = "RGOGRBOBRBOG";
        PLL[2][1] = "y#x'#R#U'#R'#D#R#U#R'#D'#R#U#R'#D#R#U'#R'#D'#x";
        PLL[3][0] = "BRGRGBOOOGBR";
        PLL[3][1] = "R'#U#R#U'#R2#F'#U'#F#U#R#F#R'#F'#R2";
        PLL[4][0] = "GBRBOGRRBOGO";
        PLL[4][1] = "R2#U#R'#U#R'#U'#R#U'#R2#D#U'#R'#U#R#D'";
        PLL[5][0] = "GORBBGRGBORO";
        PLL[5][1] = "R'#U'#R#U#D'#R2#U#R'#U#R#U'#R#U'#R2#D";
        PLL[6][0] = "GRRBOGRGBOBO";
        PLL[6][1] = "R2#U'#R#U'#R#U#R'#U#R2#D'#U#R#U'#R'#D";
        PLL[7][0] = "GBRBGGROBORO";
        PLL[7][1] = "R#U#R'#U'#D#R2#U'#R#U'#R'#U#R'#U#R2#D'";
        PLL[8][0] = "GBGRORBGBORO";
        PLL[8][1] = "R2#U2#R#U2#R2#U2#R2#U2#R#U2#R2";
        PLL[9][0] = "OGGRRRBOOGBB";
        PLL[9][1] = "y#R'#U#L'#U2#R#U'#R'#U2#R#L";
        PLL[10][0] = "GGRBBGRRBOOO";
        PLL[10][1] = "R#U2#R'#U'#R#U2#L'#U#R'#U'#L";
        PLL[11][0] = "RROGGBOORBBG";
        PLL[11][1] = "L#U'#R#U2#L'#U#R'#L#U'#R#U2#L'#U#R'";
        PLL[12][0] = "ROOGBBORRBGG";
        PLL[12][1] = "R'#U#L'#U2#R#U'#L#R'#U#L'#U2#R#U'#L";
        PLL[13][0] = "GOBOGGRBRBRO";
        PLL[13][1] = "L#U2#L'#U2#L#F'#L'#U'#L#U#L#F#L2";
        PLL[14][0] = "BRGROBOBOGGR";
        PLL[14][1] = "R'#U2#R#U2#R'#F#R#U#R'#U'#R'#F'#R2";
        PLL[15][0] = "GGRBOGRBBORO";
        PLL[15][1] = "R#U#R'#U'#R'#F#R2#U'#R'#U'#R#U#R'#F'";
        PLL[16][0] = "GOGRGRBBBORO";
        PLL[16][1] = "R2#U'#R'#U'#R#U#R#U#R#U'R";
        PLL[17][0] = "grgrorbbbogo";
        PLL[17][1] = "y2#R2#U#R#U#R'#U'#R'#U'#R'#U#R'";
        PLL[18][0] = "OBRBRGROOGGB";
        PLL[18][1] = "R'#U#R'#U'#y#R'#F'#R2#U'#R'#U#R'#F#R#F";
        PLL[19][0] = "BOGRROGBBOGR";
        PLL[19][1] = "F#R#U'#R'#U'#R#U#R'#F'#R#U#R'#U'#R'#F#R#F'";
        PLL[20][0] = "BRBOGOGOGRBR";
        PLL[20][1] = "y#R'#U'#R#U'#R#U#R#U'#R'#U#R#U#R2#U'#R";
    }

    public void fillF2L() { // 1
        F2L[0][0] = "B:C1/w&Y:A3/b&Y:B3/b&R:A1/r&R:B1/r";
        F2L[0][1] = "y#R'#F#R#F'";
        F2L[1][0] = "R:C1/w&Y:C2/r&Y:C3/r&G:A1/g&G:B1/g";
        F2L[1][1] = "y2#R'#F#R#F'";
        F2L[2][0] = "G:C1/w&Y:B1/g&Y:C1/g&O:A1/o&O:B1/o";
        F2L[2][1] = "y'#R'#F#R#F'";
        F2L[3][0] = "O:C1/w&Y:A1/o&Y:A2/o&B:A1/b&B:B1/b";
        F2L[3][1] = "R'#F#R#F'"; // 2
        F2L[4][0] = "B:B1/b&B:C1/b&Y:A2/r&Y:A3/r&R:A1/w";
        F2L[4][1] = "y#F#R'#F'#R";
        F2L[5][0] = "R:B1/r&R:C1/r&Y:B3/g&Y:C3/g&G:A1/w";
        F2L[5][1] = "y2#F#R'#F'#R";
        F2L[6][0] = "G:B1/g&G:C1/g&Y:C1/o&Y:C2/o&O:A1/w";
        F2L[6][1] = "y'#F#R'#F'#R";
        F2L[7][0] = "O:B1/o&O:C1/o&Y:B1/b&Y:A1/b&B:A1/w";
        F2L[7][1] = "F#R'#F'#R";// 3
        F2L[8][0] = "B:C1/w&Y:B1/r&Y:A3/b&R:A1/r";
        F2L[8][1] = "y#F'#U'#F";
        F2L[9][0] = "R:C1/w&Y:A2/g&Y:C3/r&G:A1/g";
        F2L[8][1] = "y2#F'#U'#F";
        F2L[10][0] = "G:C1/w&Y:B3/o&Y:C1/g&O:A1/o";
        F2L[8][1] = "y'#F'#U'#F";
        F2L[11][0] = "O:C1/w&Y:C2/b&Y:A1/o&B:A1/b";
        F2L[8][1] = "F'#U'#F"; // 4
        F2L[12][0] = "B:C1/b&Y:A3/r&Y:C2/b&R:A1/w";
        F2L[12][1] = "y#R#U#R'";
        F2L[13][0] = "R:C1/r&Y:C3/g&Y:B1/r&G:A1/w";
        F2L[12][1] = "y2#R#U#R'";
        F2L[14][0] = "G:C1/g&Y:C1/o&Y:A2/g&O:A1/w";
        F2L[12][1] = "y'#R#U#R'";
        F2L[15][0] = "O:C1/o&Y:A1/b&Y:B3/o&B:A1/w";
        F2L[12][1] = "R#U#R'"; // 5
        F2L[16][0] = "B:C1/w&Y:A3/b&Y:C2/b&R:A1/r";
        F2L[16][1] = "y#F2#L'#U'#L#U#F2";
        F2L[17][0] = "R:C1/w&Y:C3/r&Y:B1/r&G:A1/g";
        F2L[16][1] = "y2#F2#L'#U'#L#U#F2";
        F2L[18][0] = "G:C1/w&Y:C1/g&Y:A2/g&O:A1/o";
        F2L[16][1] = "y'#F2#L'#U'#L#U#F2";
        F2L[19][0] = "O:C1/w&Y:A1/o&Y:B3/o&B:A1/b";
        F2L[16][1] = "F2#L'#U'#L#U#F2"; // 6
        F2L[20][0] = "B:C1/b&Y:B1/r&Y:A3/r&R:A1/w";
        F2L[20][1] = "y#U#F'#U'#F#U2#F'#U#F";
        F2L[21][0] = "R:C1/r&Y:A2/r&Y:C3/r&G:A1/w";
        F2L[21][1] = "y2#U#F'#U'#F#U2#F'#U#F";
        F2L[22][0] = "G:C1/g&Y:B3/o&Y:C1/o&O:A1/o";
        F2L[22][1] = "y'#U#F'#U'#F#U2#F'#U#F";
        F2L[23][0] = "O:C1/o&Y:C2/b&Y:A1/b&B:A1/b";
        F2L[23][1] = "U#F'#U'#F#U2#F'#U#F";// 7
        F2L[24][0] = "B:C1/w&Y:B1/b&Y:A3/b&R:A1/r";
        F2L[24][1] = "y#U'#R#U2#R'#U2#R#U'#R'";
        F2L[25][0] = "R:C1/w&Y:A2/r&Y:C3/r&G:A1/g";
        F2L[25][1] = "y2#U'#R#U2#R'#U2#R#U'#R'";
        F2L[26][0] = "G:C1/w&Y:B3/g&Y:C1/g&O:A1/o";
        F2L[26][1] = "y'#U'#R#U2#R'#U2#R#U'#R'";
        F2L[27][0] = "O:C1/w&Y:C2/o&Y:A1/o&B:A1/b";
        F2L[27][1] = "U'#R#U2#R'#U2#R#U'#R'";// 8
        F2L[28][0] = "B:C1/b&Y:C2/r&Y:A3/r&R:A1/w";
        F2L[28][1] = "y#U#F'#U2#F#U2#F'#U#F";
        F2L[29][0] = "R:C1/r&Y:A1/g&Y:B1/g&G:A1/w";
        F2L[29][1] = "y2#U#F'#U2#F#U2#F'#U#F";
        F2L[30][0] = "G:C1/g&Y:C1/o&Y:A2/o&O:A1/w";
        F2L[30][1] = "y'#U#F'#U2#F#U2#F'#U#F";
        F2L[31][0] = "O:C1/o&Y:A1/b&Y:B3/b&B:A1/w";
        F2L[31][1] = "U#F'#U2#F#U2#F'#U#F";// 9
        F2L[32][0] = "B:C1/w&Y:A3/b&Y:C2/r&R:A1/r";
        F2L[32][1] = "y#U'#R#U'#R'#U#F'#U'#F";
        F2L[33][0] = "R:C1/w&Y:C3/r&Y:B1/g&G:A1/g";
        F2L[33][1] = "y2#U'#R#U'#R'#U#F'#U'#F";
        F2L[34][0] = "G:C1/w&Y:C1/g&Y:A2/o&O:A1/o";
        F2L[34][1] = "y'#U'#R#U'#R'#U#F'#U'#F";
        F2L[35][0] = "O:C1/w&Y:A1/o&Y:B3/b&B:A1/b";
        F2L[35][1] = "U'#R#U'#R'#U#F'#U'#F";// 10
        F2L[36][0] = "B:C1/b&Y:B1/b&Y:A3/r&R:A1/w";
        F2L[36][1] = "y#U'#R#U#R'#U#R#U#R'";
        F2L[37][0] = "R:C1/r&Y:A2/r&Y:C3/g&G:A1/w";
        F2L[37][1] = "y2#U'#R#U#R'#U#R#U#R'";
        F2L[38][0] = "G:C1/g&Y:B3/g&Y:C1/o&O:A1/w";
        F2L[38][1] = "y'#U'#R#U#R'#U#R#U#R'";
        F2L[39][0] = "O:C1/o&Y:C2/o&Y:A1/b&B:A1/w";
        F2L[39][1] = "U'#R#U#R'#U#R#U#R'";// 11
        F2L[40][0] = "B:C1/w&Y:A3/b&Y:B3/r&R:A1/r&R:B1/b";
        F2L[40][1] = "y#U'#R#U2#R'#U#F'#U'#F";
        F2L[41][0] = "R:C1/w&Y:C3/r&Y:C2/g&G:A1/g&G:B1/r";
        F2L[41][1] = "y2#U'#R#U2#R'#U#F'#U'#F";
        F2L[42][0] = "G:C1/w&Y:C1/g&Y:B1/o&O:A1/o&O:B1/g";
        F2L[42][1] = "y'#U'#R#U2#R'#U#F'#U'#F";
        F2L[43][0] = "O:C1/w&Y:A1/o&Y:A2/b&B:A1/b&B:B1/o";
        F2L[43][1] = "U'#R#U2#R'#U#F'#U'#F";// 12
        F2L[44][0] = "B:C1/b&B:B1/r&Y:A2/b&Y:A3/r&R:A1/w";
        F2L[44][1] = "y#R'#U2#R2#U#R2#U#R";
        F2L[45][0] = "R:C1/r&R:B1/g&Y:C3/g&Y:B3/r&G:A1/w";
        F2L[45][1] = "y2#R'#U2#R2#U#R2#U#R";
        F2L[46][0] = "G:C1/g&G:B1/o&Y:C1/o&Y:C2/g&O:A1/w";
        F2L[46][1] = "y'#R'#U2#R2#U#R2#U#R";
        F2L[47][0] = "O:C1/o&B:B1/r&Y:A1/b&Y:B1/o&B:A1/w";
        F2L[47][1] = "R'#U2#R2#U#R2#U#R";// 13
        F2L[48][0] = "B:C1/w&B:B1/b&Y:A3/b&Y:A2/r&R:A1/r";
        F2L[48][1] = "y#y#U#L'#U#L#U'#L'#U'#L";
        F2L[49][0] = "R:C1/w&R:B1/r&Y:C3/r&Y:B3/g&G:A1/g";
        F2L[49][1] = "y2#y#U#L'#U#L#U'#L'#U'#L";
        F2L[50][0] = "G:C1/w&G:B1/g&Y:C1/g&Y:C2/o&O:A1/o";
        F2L[50][1] = "y'#y#U#L'#U#L#U'#L'#U'#L";
        F2L[51][0] = "O:C1/w&O:B1/o&Y:A1/o&Y:B1/b&B:A1/b";
        F2L[51][1] = "y#U#L'#U#L#U'#L'#U'#L";// 14
        F2L[52][0] = "B:C1/b&Y:A3/r&Y:B3/b&R:A1/w&R:B1/r";
        F2L[52][1] = "y#U'#R#U'#R'#U#R#U#R'";
        F2L[53][0] = "R:C1/r&Y:C3/g&Y:C2/r&G:A1/w&G:B1/g";
        F2L[53][1] = "y2#U'#R#U'#R'#U#R#U#R'";
        F2L[54][0] = "G:C1/g&Y:C1/o&Y:B1/g&O:A1/w&O:B1/o";
        F2L[54][1] = "y'#U'#R#U'#R'#U#R#U#R'";
        F2L[55][0] = "O:C1/o&Y:A1/b&Y:A2/o&B:A1/w&B:B1/b";
        F2L[55][1] = "U'#R#U'#R'#U#R#U#R'";// 15
        F2L[56][0] = "B:C1/w&B:B1/r&Y:A2/b&Y:A3/b&R:A1/r";
        F2L[56][1] = "y#F'#U#F#U2#R#U#R'";
        F2L[57][0] = "R:C1/w&R:B1/g&Y:B3/r&Y:C3/r&G:A1/g";
        F2L[57][1] = "y2#F'#U#F#U2#R#U#R'";
        F2L[58][0] = "G:C1/w&G:B1/o&Y:C2/g&Y:C1/g&O:A1/o";
        F2L[58][1] = "y'#F'#U#F#U2#R#U#R'";
        F2L[59][0] = "O:C1/w&O:B1/b&Y:B1/o&Y:A1/o&B:A1/b";
        F2L[59][1] = "F'#U#F#U2#R#U#R'";// 16
        F2L[60][0] = "B:C1/b&Y:B3/r&Y:A3/r&R:A1/w&R:B1/b";
        F2L[60][1] = "y#R#U'#R'#U2#F'#U'#F";
        F2L[61][0] = "R:C1/r&Y:C2/g&Y:C3/g&G:A1/w&G:B1/r";
        F2L[61][1] = "y2#R#U'#R'#U2#F'#U'#F";
        F2L[62][0] = "G:C1/g&Y:B1/o&Y:C1/o&O:A1/w&O:B1/g";
        F2L[62][1] = "y'#R#U'#R'#U2#F'#U'#F";
        F2L[63][0] = "O:C1/o&Y:A2/b&Y:A1/b&B:A1/w&B:B1/o";
        F2L[63][1] = "R#U'#R'#U2#F'#U'#F";// 17
        F2L[64][0] = "B:C1/r&Y:A3/w&Y:B3/b&R:A1/b&R:B1/r";
        F2L[64][1] = "y#R#U2#R'#U'#R#U#R'";
        F2L[65][0] = "R:C1/g&Y:C3/w&Y:C2/r&G:A1/r&G:B1/g";
        F2L[65][1] = "y2#R#U2#R'#U'#R#U#R'";
        F2L[66][0] = "G:C1/o&Y:C1/w&Y:B1/g&O:A1/g&O:B1/o";
        F2L[66][1] = "y'#R#U2#R'#NLU'#R#U#R'";
        F2L[67][0] = "O:C1/b&Y:A1/w&Y:A2/o&B:A1/o&B:B1/b";
        F2L[67][1] = "R#U2#R'#U'#R#U#R'";// 18
        F2L[68][0] = "B:B1/b&B:C1/r&Y:A2/r&Y:A3/w&R:A1/b";
        F2L[68][1] = "y#y'#R'#U2#R#U#R'#U'#R";
        F2L[69][0] = "R:B1/r&R:C1/g&Y:B3/g&Y:C3/w&G:A1/r";
        F2L[69][1] = "y2#y'#R'#U2#R#U#R'#U'#R";
        F2L[70][0] = "G:B1/g&G:C1/o&Y:C2/o&Y:C1/w&O:A1/g";
        F2L[70][1] = "y'#y'#R'#U2#R#U#R'#U'#R";
        F2L[71][0] = "O:B1/o&O:C1/b&Y:B1/b&Y:A1/w&B:A1/o";
        F2L[71][1] = "y'#R'#U2#R#U#R'#U'#R";// 19
        F2L[72][0] = "B:C1/r&Y:A3/w&Y:C2/b&R:A1/b";
        F2L[72][1] = "y#U#R#U2#R2#F#R#F'";
        F2L[73][0] = "R:C1/g&Y:C3/w&Y:B1/r&G:A1/r";
        F2L[73][1] = "y2#U#R#U2#R2#F#R#F'";
        F2L[74][0] = "G:C1/o&Y:C1/w&Y:A2/g&O:A1/g";
        F2L[74][1] = "y'#U#R#U2#R2#F#R#F'";
        F2L[75][0] = "O:C1/b&Y:A1/w&Y:B3/o&B:A1/o";
        F2L[75][1] = "U#R#U2#R2#F#R#F'";// 20
        F2L[76][0] = "B:C1/r&Y:A3/w&Y:B1/r&R:A1/b";
        F2L[76][1] = "y#y'#U'#R'#U2#R#U'#R'#U#R";
        F2L[77][0] = "R:C1/g&Y:A1/w&Y:C2/g&G:A1/r";
        F2L[77][1] = "y2#y'#U'#R'#U2#R#U'#R'#U#R";
        F2L[78][0] = "G:C1/o&Y:C1/w&Y:B3/o&O:A1/g";
        F2L[78][1] = "y'#y'#U'#R'#U2#R#U'#R'#U#R";
        F2L[79][0] = "O:C1/b&Y:A1/w&Y:C2/b&B:A1/o";
        F2L[79][1] = "y'#U'#R'#U2#R#U'#R'#U#R";// 21
        F2L[80][0] = "B:C1/r&Y:A3/w&Y:B1/b&R:A1/b";
        F2L[80][1] = "y#U2#R#U#R'#U#R#U'#R'";
        F2L[81][0] = "R:C1/g&Y:C3/w&Y:A2/r&G:A1/r";
        F2L[81][1] = "y2#U2#R#U#R'#U#R#U'#R'";
        F2L[82][0] = "G:C1/o&Y:C1/w&Y:B3/g&O:A1/g";
        F2L[82][1] = "y'#U2#R#U#R'#U#R#U'#R'";
        F2L[83][0] = "O:C1/b&Y:A1/w&Y:C2/o&B:A1/o";
        F2L[83][1] = "U2#R#U#R'#U#R#U'#R'";// 22
        F2L[84][0] = "B:C1/r&Y:A3/w&Y:C2/r&R:A1/b";
        F2L[84][1] = "y#F'#L'#U2#L#F";
        F2L[85][0] = "R:C1/g&Y:C3/w&Y:B1/g&G:A1/r";
        F2L[85][1] = "y2#F'#L'#U2#L#F";
        F2L[86][0] = "G:C1/o&Y:C1/w&Y:A2/o&O:A1/g";
        F2L[86][1] = "y'#F'#L'#U2#L#F";
        F2L[87][0] = "O:C1/b&Y:A1/w&Y:B3/b&B:A1/o";
        F2L[87][1] = "F'#L'#U2#L#F";// 23
        F2L[88][0] = "B:B1/r&B:C1/r&Y:A2/b&Y:A3/w&R:A1/b";
        F2L[88][1] = "y#";
        F2L[89][0] = "";
        F2L[89][1] = "y2#";
        F2L[90][0] = "";
        F2L[90][1] = "y'#";
        F2L[91][0] = "";
        F2L[91][1] = "U2#R2#U2#R'#U'#R#U'#R2";// 24
        F2L[92][0] = "B:C1/r&Y:A3/w&Y:B3/r&R:A1/b&R:B1/b";
        F2L[92][1] = "y#";
        F2L[93][0] = "";
        F2L[93][1] = "y2#";
        F2L[94][0] = "";
        F2L[94][1] = "y'#";
        F2L[95][0] = "";
        F2L[95][1] = "F#U#R#U'#R'#F'#R#U'#R'";
        F2L[96][0] = "B:C3/b&Y:B3/b&R:A3/r&R:B1/r";
        F2L[96][1] = "y#";
        F2L[97][0] = "";
        F2L[97][1] = "y2#";
        F2L[98][0] = "";
        F2L[98][1] = "y'#";
        F2L[99][0] = "";
        F2L[99][1] = "R'#F'#R#U#R#U'#R'#F"; // 26
        F2L[100][0] = "B:B1/b&Y:A2/r&B:C3/b&R:A3/r";
        F2L[100][1] = "y#";
        F2L[101][0] = "";
        F2L[101][1] = "y2#";
        F2L[102][0] = "";
        F2L[102][1] = "y'#";
        F2L[103][0] = "";
        F2L[103][1] = "U#R#U'#R'#U'#F'#U#F#"; // 27
        F2L[104][0] = "B:C3/w&R:A3/b&Y:B3/b&R:B1/r";
        F2L[104][1] = "y#";
        F2L[105][0] = "";
        F2L[105][1] = "y2#";
        F2L[106][0] = "";
        F2L[106][1] = "y'#";
        F2L[107][0] = "";
        F2L[107][1] = "R#U'#R'#U#R#U'#R'#";
        // 28
        F2L[108][0] = "B:B1/b&Y:A3/r&B:C3/r&R:A3/w";
        F2L[108][1] = "y#";
        F2L[109][0] = "";
        F2L[109][1] = "y2#";
        F2L[110][0] = "";
        F2L[110][1] = "y'#";
        F2L[111][0] = "";
        F2L[111][1] = "R#U#R'#U'#F#R'#F'#R";
        // 29
        F2L[112][0] = "B:B1/b&Y:A2/r&B:C3/w&R:A3/b";
        F2L[112][1] = "y#";
        F2L[113][0] = "";
        F2L[113][1] = "y2#";
        F2L[114][0] = "";
        F2L[114][1] = "y'#";
        F2L[115][0] = "";
        F2L[115][1] = "y#L'#U'#L#U#L'#U'#L#";
        // 30
        F2L[116][0] = "B:C3/r&R:A3/w&R:B1/r&Y:B3/b";
        F2L[116][1] = "y#";
        F2L[117][0] = "";
        F2L[117][1] = "y2#";
        F2L[118][0] = "";
        F2L[118][1] = "y'#";
        F2L[119][0] = "";
        F2L[119][1] = "F#R'#F'#R#F#R'#F'#R#";
        // 31
        F2L[120][0] = "B:C2/r&R:A2/b&B:C1/r&R:A1/b&Y:A3/w";
        F2L[120][1] = "y#";
        F2L[121][0] = "";
        F2L[121][1] = "y2#";
        F2L[122][0] = "";
        F2L[122][1] = "y'#";
        F2L[123][0] = "";
        F2L[123][1] = "R#U'#R'#U2#F#R'#F'#R#";
        // 32
        F2L[124][0] = "B:C2/b&R:A2/r&B:C1/r&R:A1/b&Y:A3/w";
        F2L[124][1] = "y#";
        F2L[125][0] = "";
        F2L[125][1] = "y2#";
        F2L[126][0] = "";
        F2L[126][1] = "y'#";
        F2L[127][0] = "";
        F2L[127][1] = "R2#U#R2#U#R2#U2#R2#";
        // 33
        F2L[128][0] = "B:C2/b&R:A2/r&B:C1/w&R:A1/r&Y:A3/b";
        F2L[128][1] = "y#";
        F2L[129][0] = "";
        F2L[129][1] = "y2#";
        F2L[130][0] = "";
        F2L[130][1] = "y'#";
        F2L[131][0] = "";
        F2L[131][1] = "y#R'#D#R#U'#R'#D'#R";
        // 34
        F2L[132][0] = "B:C2/b&R:A2/r&B:C1/b&R:A1/w&Y:A3/r";
        F2L[132][1] = "y#";
        F2L[133][0] = "";
        F2L[133][1] = "y2#";
        F2L[134][0] = "";
        F2L[134][1] = "y'#";
        F2L[135][0] = "";
        F2L[135][1] = "y'#U#R'#U#R#U2#R'#U#R";
        // 35
        F2L[136][0] = "B:C2/r&B:C1/w&R:A2/b&R:A1/r&Y:A3/b";
        F2L[136][1] = "y#";
        F2L[137][0] = "";
        F2L[137][1] = "y2#";
        F2L[138][0] = "";
        F2L[138][1] = "y'#";
        F2L[139][0] = "";
        F2L[139][1] = "U'#R#U#R'#d#R'#U'#R";
        // 36
        F2L[140][0] = "B:C2/r&B:C1/b&R:A2/b&R:A1/w&Y:A3/r";
        F2L[140][1] = "y#";
        F2L[141][0] = "";
        F2L[141][1] = "y2#";
        F2L[142][0] = "";
        F2L[142][1] = "y'#";
        F2L[143][0] = "";
        F2L[143][1] = "U2#F'#U'#F#U#R#U'#R'#";
        // 37
        F2L[144][0] = "B:C3/b&B:C2/r&R:A3/r&R:A2/b";
        F2L[144][1] = "y#";
        F2L[145][0] = "";
        F2L[145][1] = "y2#";
        F2L[146][0] = "";
        F2L[146][1] = "y'#";
        F2L[147][0] = "";
        F2L[147][1] = "R#U2#R'#U#R#U2#R'#U#F'#U'#F#";
        // 38
        F2L[148][0] = "B:C3/w&B:C2/b&R:A3/b&R:A2/r";
        F2L[148][1] = "y#";
        F2L[149][0] = "";
        F2L[149][1] = "y2#";
        F2L[150][0] = "";
        F2L[150][1] = "y'#";
        F2L[151][0] = "";
        F2L[151][1] = "R2#U2#R'#U'#R#U'#R'#U2#R'#";
        // 39
        F2L[152][0] = "B:C3/r&B:C2/b&R:A3/w&R:A2/r";
        F2L[152][1] = "y#";
        F2L[153][0] = "";
        F2L[153][1] = "y2#";
        F2L[154][0] = "";
        F2L[154][1] = "y'#";
        F2L[155][0] = "";
        F2L[155][1] = "R#U2#R#U#R'#U#R#U2#R2#";
        // 40
        F2L[156][0] = "B:C3/w&B:C2/r&R:A3/b&R:A2/b";
        F2L[156][1] = "y#";
        F2L[157][0] = "";
        F2L[157][1] = "y2#";
        F2L[158][0] = "";
        F2L[158][1] = "y'#";
        F2L[159][0] = "";
        F2L[159][1] = "F'#L'#U2#L#F#R#U#R'#";
        // 41
        F2L[160][0] = "B:C3/r&B:C2/r&R:A3/w&R:A2/b";
        F2L[160][1] = "y#";
        F2L[161][0] = "";
        F2L[161][1] = "y2#";
        F2L[162][0] = "";
        F2L[162][1] = "y'#";
        F2L[163][0] = "";
        F2L[163][1] = "R#U'#R'#F'#L'#U2#L#F#";
    }
}