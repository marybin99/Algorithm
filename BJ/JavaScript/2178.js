const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let [N, M] = input.shift().split(' ').map(Number);
let miro = input.map((row) => row.split('').map(Number));

let dx = [0, 0, -1, 1];
let dy = [-1, 1, 0, 0];

function BFS(x, y) {
    let q = [[x, y]];
    // visited를 방문 여부가 아닌 몇 번째 방문했는지 판단하는 용도로 사용
    let visited = { };
    visited[[x, y]] = 1;
    
    while(q.length) {
        for(let i = 0; i < q.length; i++) {
            let now = q.shift();
            for(let j = 0; j < 4; j++) {
                let nx = now[0] + dx[j];
                let ny = now[1] + dy[j];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[[nx,ny]] || miro[nx][ny] == 0) continue;

                q.push([nx, ny]);
                // 방문할 때마다 값 증가
                visited[[nx,ny]] = visited[now] + 1;
            }
        }
    }

    return visited[[N-1, M-1]];
}
console.log(BFS(0,0));
