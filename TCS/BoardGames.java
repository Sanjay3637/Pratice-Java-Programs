import java.util.*;
import java.util.Queue;
import java.util.LinkedList;

public class BoardGames {
    static class State {
        int row, col;
        int x, y;
        int steps;

        State(int row, int col, int x, int y, int steps) {
            this.row = row;
            this.col = col;
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] mat = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        int str = sc.nextInt();
        int stc = sc.nextInt();
        int er = sc.nextInt();
        int ec = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();
        boolean[][][][] vis = new boolean[row][col][51][51];
        Queue<State> qu = new LinkedList<>();
        qu.add(new State(str, stc, x, y, 0));
        vis[str][stc][x + 25][y + 25] = true;
        int min = -1;
        while (!qu.isEmpty()) {
            State curr = qu.poll();
            if (curr.row == er && curr.col == ec) {
                min = curr.steps;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx, ny;
                int nr, nc;
                switch (i) {
                    case 0:
                        nx = curr.x;
                        ny = curr.y;
                        nr = curr.row + nx;
                        nc = curr.col + ny;
                        break;
                    case 1:
                        nx = curr.y;
                        ny = -curr.x;
                        nr = curr.row + nx;
                        nc = curr.col + ny;
                        break;
                    case 2:
                        nx = -curr.y;
                        ny = curr.x;
                        nr = curr.row + nx;
                        nc = curr.col + ny;
                        break;
                    case 3:
                        nx = -curr.x;
                        ny = -curr.y;
                        nr = curr.row + nx;
                        nc = curr.col + ny;
                        break;
                    default:
                        nx = 0;
                        ny = 0;
                        nr = curr.row;
                        nc = curr.col;
                }
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && mat[nr][nc] == 0) {
                    int norx = nx + 25;
                    int nory = ny + 25;
                    if (norx >= 0 && norx < 51 && nory >= 0 && nory < 51) {
                        if (!vis[nr][nc][norx][nory]) {
                            vis[nr][nc][norx][nory] = true;
                            qu.add(new State(nr, nc, nx, ny, curr.steps + 1));
                        }
                    }
                }
            }
        }
        System.out.print(min);
    }
}