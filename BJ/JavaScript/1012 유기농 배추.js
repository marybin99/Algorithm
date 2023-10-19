const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let T = Number(input[0]);
let line = 1;
let M, N, K, map, visited;

let dx = [-1,1,0,0];
let dy = [0,0,-1,1];

for(let tc=0;tc<T;tc++) {
    [M, N, K] = input[line].split(' ').map(Number);

    map = [];
    visited = [];
    for(let i=0;i<N;i++) {
        map[i] = new Array(M).fill(0);
        visited[i] = new Array(M).fill(false);
    }

    for(let i=0;i<K;i++) {
        let [x, y] = input[line+i+1].split(' ').map(Number);

        map[y][x] = 1;
    }

    line += K+1;

    let ans = 0;
    for(let i=0;i<N;i++) {
        for(let j=0;j<M;j++) {
            if(map[i][j] == 1&& !visited[i][j]){
                dfs(i, j);
                ans++;
            }
        }
    }

    console.log(ans);
}

function dfs(x, y) {
    visited[x][y] = true;
    for(let i=0;i<4;i++) {
        let nx = x + dx[i];
        let ny = y + dy[i];

        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
        if(map[nx][ny] == 0) continue;
        if(visited[nx][ny]) continue;

        dfs(nx, ny);
    }
}
