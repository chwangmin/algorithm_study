import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프_옮기기1_17070 {
    static int mapSize;
    static int[][] map;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        mapSize= Integer.parseInt(br.readLine());

        map = new int[mapSize][mapSize];

        for (int i = 0 ; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,1,0);

        System.out.println(answer);
    }
    static void dfs(int r, int c, int state){
        if (r == mapSize-1 && c == mapSize-1){
            answer++;
            return;
        }
        switch (state){
            case 0:
                goRight(r,c+1);
                goRightDown(r+1,c+1);
                break;
            case 1:
                goRight(r,c+1);
                goRightDown(r+1,c+1);
                goDown(r+1,c);
                break;
            case 2:
                goRightDown(r+1,c+1);
                goDown(r+1,c);
                break;
        }
    }

    static void goRight(int nr, int nc){
        if (isOverMap(nr,nc)){
            return;
        }
        if (isThereWall(nr,nc)){
            return;
        }
        dfs(nr,nc,0);
    }

    static void goRightDown(int nr, int nc){
        if (isOverMap(nr,nc)){
            return;
        }
        if (isThereWall(nr-1,nc) || isThereWall(nr,nc) || isThereWall(nr,nc-1)){
            return;
        }
        dfs(nr,nc,1);
    }

    static void goDown(int nr, int nc){
        if (isOverMap(nr,nc)){
            return;
        }
        if (isThereWall(nr,nc)){
            return;
        }
        dfs(nr,nc,2);
    }

    static boolean isOverMap (int nr, int nc){
        if (nr < 0 || nr >= mapSize || nc < 0 || nc >= mapSize) {
            return true;
        }
        return false;
    }

    static boolean isThereWall (int nr, int nc){
        if (map[nr][nc] == 1){
            return true;
        }
        return false;
    }
}
