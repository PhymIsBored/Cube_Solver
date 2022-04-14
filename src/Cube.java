public class Cube {
    private char[][] layout;

    public Cube() {
        layout = new char[12][9];
        fillCubeDefault();
    }

    // cube rotations
    public void rotateX() {
        char[][] copy = this.makeCopy();
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i + 3][j];
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i + 3][j];
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i + 3][j];
            }
        }
        for (int i = 9; i < 12; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i - 9][j];
            }
        }
        // turn face R
        int i = 5;
        int j = 8;
        char field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
        // turn face L'
        j = 2;
        field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
        field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
        field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
    }

    public void rotateY() {
        char[][] copy = this.makeCopy();
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i + 3][j + 3];
            }
        }
        // turn face F'
        int x = 0;
        int y = 3;
        char field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                layout[i][j] = copy[i - 3][j + 3];
            }
        }
        // turn face L'
        x = 3;
        y = 0;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i - 3][j - 3];
            }
        }
        // turn face B'
        x = 8;
        y = 5;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                layout[i][j] = copy[i + 3][j - 3];
            }
        }
        // turn face R
        x = 5;
        y = 8;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        // turn face U
        x = 9;
        y = 3;
        // turn face U
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        // turn face D'
        x = 3;
        y = 3;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;

    }

    public void rotateZ() {
        char[][] copy = this.makeCopy();
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                layout[i][j] = copy[i][j + 3];
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i][j + 3];
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                layout[i][j] = copy[i + 6][j - 3];
            }
        }
        // turn face R2
        int x = 5;
        int y = 8;
        char field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        for (int i = 9; i < 12; i++) {
            for (int j = 3; j < 6; j++) {
                layout[i][j] = copy[i - 6][j - 3];
            }
        }
        // turn face U2
        x = 9;
        y = 3;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        // turn face F
        x = 0;
        y = 3;
        field = layout[x][y];
        layout[x][y] = layout[x + 2][y];
        layout[x + 2][y] = layout[x + 2][y + 2];
        layout[x + 2][y + 2] = layout[x][y + 2];
        layout[x][y + 2] = field;
        field = layout[x][y + 1];
        layout[x][y + 1] = layout[x + 1][y];
        layout[x + 1][y] = layout[x + 2][y + 1];
        layout[x + 2][y + 1] = layout[x + 1][y + 2];
        layout[x + 1][y + 2] = field;
        // turn face B'
        x = 8;
        y = 5;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
        field = layout[x][y];
        layout[x][y] = layout[x - 2][y];
        layout[x - 2][y] = layout[x - 2][y - 2];
        layout[x - 2][y - 2] = layout[x][y - 2];
        layout[x][y - 2] = field;
        field = layout[x][y - 1];
        layout[x][y - 1] = layout[x - 1][y];
        layout[x - 1][y] = layout[x - 2][y - 1];
        layout[x - 2][y - 1] = layout[x - 1][y - 2];
        layout[x - 1][y - 2] = field;
    }

    // methods for turning the cube
    public void turnM() {
        int i = 0;
        int j = 4;
        char field = layout[i][j];
        layout[i][j] = layout[i + 9][j];
        layout[i + 9][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 3][j];
        layout[i + 3][j] = field;
        i = 1;
        field = layout[i][j];
        layout[i][j] = layout[i + 9][j];
        layout[i + 9][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 3][j];
        layout[i + 3][j] = field;
        i = 2;
        field = layout[i][j];
        layout[i][j] = layout[i + 9][j];
        layout[i + 9][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 3][j];
        layout[i + 3][j] = field;
    }

    public void turnE() {
        int i = 1;
        int j = 3;
        char field = layout[i][j];
        layout[i][j] = layout[i + 4][j - 2];
        layout[i + 4][j - 2] = layout[i + 6][j + 2];
        layout[i + 6][j + 2] = layout[i + 2][j + 4];
        layout[i + 2][j + 4] = field;
        j = 4;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j - 3];
        layout[i + 3][j - 3] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 3][j + 3];
        layout[i + 3][j + 3] = field;
        j = 5;
        field = layout[i][j];
        layout[i][j] = layout[i + 2][j - 4];
        layout[i + 2][j - 4] = layout[i + 6][j - 2];
        layout[i + 6][j - 2] = layout[i + 4][j + 2];
        layout[i + 4][j + 2] = field;
    }

    public void turnS() {
        int i = 4;
        int j = 8;
        char field = layout[i][j];
        layout[i][j] = layout[i + 6][j - 5];
        layout[i + 6][j - 5] = layout[i][j - 6];
        layout[i][j - 6] = layout[i][j - 3];
        layout[i][j - 3] = field;
        j = 7;
        field = layout[i][j];
        layout[i][j] = layout[i + 6][j - 3];
        layout[i + 6][j - 3] = layout[i][j - 6];
        layout[i][j - 6] = layout[i][j - 3];
        layout[i][j - 3] = field;
        j = 6;
        field = layout[i][j];
        layout[i][j] = layout[i + 6][j - 1];
        layout[i + 6][j - 1] = layout[i][j - 6];
        layout[i][j - 6] = layout[i][j - 3];
        layout[i][j - 3] = field;
    }

    public void turnU() {
        int i = 9;
        int j = 3;
        // turn face U
        char field = layout[i][j];
        layout[i][j] = layout[i + 2][j];
        layout[i + 2][j] = layout[i + 2][j + 2];
        layout[i + 2][j + 2] = layout[i][j + 2];
        layout[i][j + 2] = field;
        field = layout[i][j + 1];
        layout[i][j + 1] = layout[i + 1][j];
        layout[i + 1][j] = layout[i + 2][j + 1];
        layout[i + 2][j + 1] = layout[i + 1][j + 2];
        layout[i + 1][j + 2] = field;
        // turn layer U
        i = 3;
        j = 8;
        field = layout[i][j];
        layout[i][j] = layout[i + 5][j - 3];
        layout[i + 5][j - 3] = layout[i + 2][j - 8];
        layout[i + 2][j - 8] = layout[i - 3][j - 5];
        layout[i - 3][j - 5] = field;
        i = 4;
        j = 8;
        field = layout[i][j];
        layout[i][j] = layout[i + 4][j - 4];
        layout[i + 4][j - 4] = layout[i][j - 8];
        layout[i][j - 8] = layout[i - 4][j - 4];
        layout[i - 4][j - 4] = field;
        i = 5;
        j = 8;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j - 5];
        layout[i + 3][j - 5] = layout[i - 2][j - 8];
        layout[i - 2][j - 8] = layout[i - 5][j - 3];
        layout[i - 5][j - 3] = field;
    }

    public void turnD() {
        // turn face D
        int i = 3;
        int j = 3;
        char field = layout[i][j];
        layout[i][j] = layout[i + 2][j];
        layout[i + 2][j] = layout[i + 2][j + 2];
        layout[i + 2][j + 2] = layout[i][j + 2];
        layout[i][j + 2] = field;
        field = layout[i][j + 1];
        layout[i][j + 1] = layout[i + 1][j];
        layout[i + 1][j] = layout[i + 2][j + 1];
        layout[i + 2][j + 1] = layout[i + 1][j + 2];
        layout[i + 1][j + 2] = field;
        // turn layer D
        i = 2;
        j = 3;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j - 1];
        layout[i + 3][j - 1] = layout[i + 4][j + 2];
        layout[i + 4][j + 2] = layout[i + 1][j + 3];
        layout[i + 1][j + 3] = field;
        i = 2;
        j = 4;
        field = layout[i][j];
        layout[i][j] = layout[i + 2][j - 2];
        layout[i + 2][j - 2] = layout[i + 4][j];
        layout[i + 4][j] = layout[i + 2][j + 2];
        layout[i + 2][j + 2] = field;
        i = 2;
        j = 5;
        field = layout[i][j];
        layout[i][j] = layout[i + 1][j - 3];
        layout[i + 1][j - 3] = layout[i + 4][j - 2];
        layout[i + 4][j - 2] = layout[i + 3][j + 1];
        layout[i + 3][j + 1] = field;
    }

    public void turnR() {
        int i = 5;
        int j = 8;
        // turn face R
        char field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
        // turn layer R
        i = 0;
        j = 5;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j];
        layout[i + 3][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 9][j];
        layout[i + 9][j] = field;
        i = 1;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j];
        layout[i + 3][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 9][j];
        layout[i + 9][j] = field;
        i = 2;
        field = layout[i][j];
        layout[i][j] = layout[i + 3][j];
        layout[i + 3][j] = layout[i + 6][j];
        layout[i + 6][j] = layout[i + 9][j];
        layout[i + 9][j] = field;
    }

    public void turnL() {
        int i = 3;
        int j = 0;
        // turn face L
        char field = layout[i][j];
        layout[i][j] = layout[i + 2][j];
        layout[i + 2][j] = layout[i + 2][j + 2];
        layout[i + 2][j + 2] = layout[i][j + 2];
        layout[i][j + 2] = field;
        field = layout[i][j + 1];
        layout[i][j + 1] = layout[i + 1][j];
        layout[i + 1][j] = layout[i + 2][j + 1];
        layout[i + 2][j + 1] = layout[i + 1][j + 2];
        layout[i + 1][j + 2] = field;
        // turn layer L
        i = 9;
        j = 3;
        field = layout[i][j];
        layout[i][j] = layout[i - 3][j];
        layout[i - 3][j] = layout[i - 6][j];
        layout[i - 6][j] = layout[i - 9][j];
        layout[i - 9][j] = field;
        i = 10;
        field = layout[i][j];
        layout[i][j] = layout[i - 3][j];
        layout[i - 3][j] = layout[i - 6][j];
        layout[i - 6][j] = layout[i - 9][j];
        layout[i - 9][j] = field;
        i = 11;
        field = layout[i][j];
        layout[i][j] = layout[i - 3][j];
        layout[i - 3][j] = layout[i - 6][j];
        layout[i - 6][j] = layout[i - 9][j];
        layout[i - 9][j] = field;
    }

    public void turnB() {
        int i = 8;
        int j = 5;
        // turn face B
        char field = layout[i][j];
        layout[i][j] = layout[i - 2][j];
        layout[i - 2][j] = layout[i - 2][j - 2];
        layout[i - 2][j - 2] = layout[i][j - 2];
        layout[i][j - 2] = field;
        field = layout[i][j - 1];
        layout[i][j - 1] = layout[i - 1][j];
        layout[i - 1][j] = layout[i - 2][j - 1];
        layout[i - 2][j - 1] = layout[i - 1][j - 2];
        layout[i - 1][j - 2] = field;
        // turn layer B
        i = 5;
        j = 8;
        field = layout[i][j];
        layout[i][j] = layout[i][j - 3];
        layout[i][j - 3] = layout[i][j - 6];
        layout[i][j - 6] = layout[i + 4][j - 5];
        layout[i + 4][j - 5] = field;
        j = 7;
        field = layout[i][j];
        layout[i][j] = layout[i][j - 3];
        layout[i][j - 3] = layout[i][j - 6];
        layout[i][j - 6] = layout[i + 4][j - 3];
        layout[i + 4][j - 3] = field;
        j = 6;
        field = layout[i][j];
        layout[i][j] = layout[i][j - 3];
        layout[i][j - 3] = layout[i][j - 6];
        layout[i][j - 6] = layout[i + 4][j - 1];
        layout[i + 4][j - 1] = field;
    }

    public void turnF() {
        int i = 0;
        int j = 3;
        // turn face F
        char field = layout[i][j];
        layout[i][j] = layout[i + 2][j];
        layout[i + 2][j] = layout[i + 2][j + 2];
        layout[i + 2][j + 2] = layout[i][j + 2];
        layout[i][j + 2] = field;
        field = layout[i][j + 1];
        layout[i][j + 1] = layout[i + 1][j];
        layout[i + 1][j] = layout[i + 2][j + 1];
        layout[i + 2][j + 1] = layout[i + 1][j + 2];
        layout[i + 1][j + 2] = field;
        // turn layer F
        i = 3;
        j = 0;
        field = layout[i][j];
        layout[i][j] = layout[i][j + 3];
        layout[i][j + 3] = layout[i][j + 6];
        layout[i][j + 6] = layout[i + 8][j + 5];
        layout[i + 8][j + 5] = field;
        j = 1;
        field = layout[i][j];
        layout[i][j] = layout[i][j + 3];
        layout[i][j + 3] = layout[i][j + 6];
        layout[i][j + 6] = layout[i + 8][j + 3];
        layout[i + 8][j + 3] = field;
        j = 2;
        field = layout[i][j];
        layout[i][j] = layout[i][j + 3];
        layout[i][j + 3] = layout[i][j + 6];
        layout[i][j + 6] = layout[i + 8][j + 1];
        layout[i + 8][j + 1] = field;

    }

    // get-cube
    public char[][] getLayout() {
        return layout;
    }

    // methods for editing
    public void fillCubeDefault() { /*
                                     * fills the Array "layout" with the caracters (letters) correspondign to the
                                     * colors on the cube in it's default state, when it hasn't been turned yet
                                     */

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                layout[i][j] = determineColor(i, j);
            }
        }
    }

    public boolean checkCompletion() {
        char[][] correct = new char[12][9];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                correct[i][j] = determineColor(i, j);
            }
        }
        boolean check = true;
        for (int i = 0; i < correct.length; i++) {
            for (int j = 0; j < correct[i].length; j++) {
                if (correct[i][j]!=layout[i][j]) {
                    check = false;
                    return check;
                }
            }
        }
        return check;
    }

    public char determineColor(int i, int j) { // determines what color to fill the fields with for the default position
        if (i / 3 == 0) {
            if (j / 3 == 0) {
                return 'a';
            } else if (j / 3 == 1) {
                return 'o';
            } else {
                return 'a';
            }
        } else if (i / 3 == 1) {
            if (j / 3 == 0) {
                return 'g';
            } else if (j / 3 == 1) {
                return 'w';
            } else {
                return 'b';
            }
        } else if (i / 3 == 2) {
            if (j / 3 == 0) {
                return 'a';
            } else if (j / 3 == 1) {
                return 'r';
            } else {
                return 'a';
            }
        } else {
            if (j / 3 == 0) {
                return 'a';
            } else if (j / 3 == 1) {
                return 'y';
            } else {
                return 'a';
            }
        }
    }

    public void printCubeLayout() { // prints out the layout of the cube to be easily readible
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 9; j++) {
                if (layout[i][j] == 'a') {
                    System.out.print(' ');
                } else {
                    System.out.print(layout[i][j]);
                }

            }
            System.out.println("");
        }
    }

    public void printCubeLayoutNonModified() { // prints out the layout of the cube
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                System.out.print(layout[i][j]);
            }
            System.out.println("");
        }
    }

    public void fillFaceWNumbers(int pI, int pJ) { // fills the field that corresponds to the coordinates with numbers
        char a = '1';
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                if (i / 3 == pI && j / 3 == pJ) {
                    layout[i][j] = a;
                    a++;
                }
            }
        }
    }

    private char[][] makeCopy() { /*
                                   * returns a copy of the current layout of the cube, since Object.clone would
                                   * return a reference...
                                   */
        char[][] copy = new char[12][9];
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                copy[i][j] = layout[i][j];
            }
        }
        return copy;
    }

    public String findSide(char pSide) {
        pSide = Character.toLowerCase(pSide);
        for (int i = 1; i < 11; i = i + 3) {
            for (int j = 1; j < 8; j = j + 3) {
                if (layout[i][j] == pSide) {
                    int ci = i / 3;
                    int cj = j / 3;
                    String r = Integer.toString(ci) + ";" + Integer.toString(cj);
                    return r;
                }
            }
        }
        return null;
    }

    public boolean checkRequired(String r, String pCoordinate, char pColor) {
        //String iaj = i + ";" + j;
        r = findCoordinates(r, pCoordinate);
        String[] split = r.split(";");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        if (layout[x][y] == pColor) {
            return true;
        } else {
            return false;
        }
    }
    public char getColour(int i, int j) {
        return layout[i][j];
    }

    public String findCoordinates(String iaj, String pCoordinate) {
        pCoordinate = pCoordinate.toUpperCase();
        String r = null;
        switch (iaj) {
            case "0;1":
                r = (pCoordinate.charAt(0) - 65) + ";" + (3 + pCoordinate.charAt(1) - 49);
                break;
            case "1;0":
                r = (5 - (pCoordinate.charAt(0) - 65)) + ";" + (char) (pCoordinate.charAt(1) - 1);
                break;
            case "1;1":
                r = (3 + (pCoordinate.charAt(0) - 65)) + ";" + (3 + pCoordinate.charAt(1) - 49);
                break;
            case "1;2":
                r = (3 + (pCoordinate.charAt(0) - 65)) + ";" + (6 + (51 - pCoordinate.charAt(1)));
                break;
            case "2;1":
                r = (6 + (51 - pCoordinate.charAt(1))) + ";" + (3 + (67 - pCoordinate.charAt(0)));
                break;
            case "3;1":
                r = (9 + (51 - pCoordinate.charAt(1))) + ";" + (3 + (67 - pCoordinate.charAt(0)));
                break;
            default:
                // do nothing
                break;
        }
        return r;
    }
}