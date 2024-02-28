import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 예술성_codetree {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int[][] map;
    static int[][] groupMap;
    static int n;
    static boolean[][] visited;
    static int groupCnt;
    static int[] combinList = new int[2];

    static int answer = 0;
    static ArrayList<Group> groups;

    static class Group {
        int myColor;
        int cnt;
        int[] arrayGroups;

        Group(int myColor) {
            this.cnt = 1;
            this.myColor = myColor;
            arrayGroups = new int[groupCnt];
        }
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        inputMap(); // map 에 값 넣기
        for (int i = 0; i < 4; i++) {
            groupCnt = 0;
            groups = new ArrayList<>();
            visited = new boolean[n][n]; // 방문 초기화
            createGroupMap(); // groupMap 만들기
            visited = new boolean[n][n]; // 방문 초기화
            createArrayGroup();
            combin(0, 0);
            matrixChange();
        }

        System.out.println(answer);
    }

    private static void inputMap() throws IOException {
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void createGroupMap() {
        groupMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) { // 방문하지 않은 곳만
                    setGroupMap(i, j); // i,j부터 그룹을 찾기.
                }
            }
        }
    }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    private static void setGroupMap(int r, int c) {
        Deque<Point> q = new ArrayDeque<>(); // deque로 구하기
        q.add(new Point(r, c)); // 포인터 추가하기
        int thisColor = map[r][c]; // 첫번째 bfs 시작위치의 색 뽑아오기
        groupMap[r][c] = groupCnt;
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.x + dr[i];
                int nc = p.y + dc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) { // 맵을 벗어난다면 그냥 넘기기
                    continue;
                }
                if (visited[nr][nc]) { // 이미 방문했다면 그냥 넘기기
                    continue;
                }
                if (map[nr][nc] == thisColor) { // 같은 색이라면
                    groupMap[nr][nc] = groupCnt;
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
        }
        groupCnt++;
    }

    private static void createArrayGroup() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    setArrayGroup(i, j);
                }
            }
        }
    }

    private static void setArrayGroup(int r, int c) {
        Deque<Point> q = new ArrayDeque<>(); // deque로 구하기
        int thisColor = map[r][c];
        int thisGroup = groupMap[r][c];
        groups.add(new Group(thisColor));
        q.add(new Point(r, c));
        boolean[][] tmpVisited = new boolean[n][n];
        tmpVisited[r][c] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.x + dr[i];
                int nc = p.y + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) { // 맵을 벗어난다면 그냥 넘기기
                    continue;
                }
                if (tmpVisited[nr][nc]) { // 이미 방문했다면 그냥 넘기기
                    continue;
                }
                if (map[nr][nc] == thisColor) { // 같은 색이라면
                    visited[nr][nc] = true;
                    tmpVisited[nr][nc] = true;
                    groups.get(thisGroup).cnt++;
                    q.add(new Point(nr, nc));
                } else if (map[nr][nc] != thisColor) { // 다른 색이라면
                    groups.get(thisGroup).arrayGroups[groupMap[nr][nc]]++; // 인접한 그룹 value ++
                }
            }
        }
    }

    /**
     * 모든 경우의 수를 구한다. 만약,
     */
    private static void combin(int idx, int val) {
        // 2개씩 비교해야 하기 때문에, n개에서 2개 조합을 뽑음
        if (idx == 2) {
            if (groups.get(combinList[0]).arrayGroups[combinList[1]] == 0) {
                return;
            }
            // (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수) * 그룹 a를 이루고 있는 숫자 값 * 그룹 b를 이루고 있는 숫자 값 * 그룹 a와 b가 서로 맞닿아 있는 변의 수
            answer += (groups.get(combinList[0]).cnt + groups.get(combinList[1]).cnt) * groups.get(combinList[0]).myColor * groups.get(combinList[1]).myColor * groups.get(combinList[0]).arrayGroups[combinList[1]];
            return;
        }

        for (int i = val; i < groupCnt; i++) {
            combinList[idx] = i;
            combin(idx + 1, i + 1);
        }
    }

    static int[][] tmpMap;

    private static void matrixChange() {
        tmpMap = new int[n][n];

        // 가운데 값 가져오기
        tmpMap[n / 2][n / 2] = map[n / 2][n / 2];

        // 십자가 반시계로
        crossRotate();

        // 왼쪽 위 돌리기
        othersRotate(0, n / 2, 0, n / 2);

        // 오른쪽 위 돌리기
        othersRotate(0, n / 2, n / 2 + 1, n);

        // 왼쪽 아래 돌리기
        othersRotate(n / 2 + 1, n, 0, n / 2);

        // 오른쪽 아래 돌리기
        othersRotate(n / 2 + 1, n, n / 2 + 1, n);

        map = tmpMap;
    }

    private static void crossRotate() {
        for (int i = 0; i < n / 2; i++) {
            tmpMap[n / 2][i] = map[i][n / 2];
            tmpMap[i][n / 2] = map[n / 2][n - 1 - i];
            tmpMap[n - 1 - i][n / 2] = map[n / 2][i];
            tmpMap[n / 2][n - 1 - i] = map[n - 1 - i][n / 2];
        }
    }

    private static void othersRotate(int startR, int endR, int startC, int endC) {
        if ((startR + endR) % 2 == 1){
            tmpMap[startR + n/4][startC + n/4] = map[startR + n/4][startC + n/4];
        }
        for (int k = 0; k < n / 4; k++) {
            // 위쪽 채우기
            for (int i = startC; i < endC; i++) {
                tmpMap[startR][i] = map[endR - 1 - i + startC][startC];
            }
            // 아래쪽 채우기
            for (int i = startC; i < endC; i++) {
                tmpMap[endR - 1][i] = map[endR - 1 - i + startC][endC - 1];
            }
            // 왼쪽 채우기
            for (int i = startR; i < endR; i++) {
                tmpMap[i][startC] = map[endR - 1][i - startR + startC];
            }
            // 오른쪽 채우기
            for (int i = startR; i < endR; i++) {
                tmpMap[i][endC - 1] = map[startR][i - startR + startC];
            }
            startR++;
            endR--;
            startC++;
            endC--;
        }
    }
}
