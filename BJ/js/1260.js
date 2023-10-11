const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let [node, line, start] = input[0].split(' ').map(Number);

let arr = new Array(node+1);
for(let i = 0; i < arr.length; i++) {
    arr[i] =  [];
}

for(let i = 0; i < line; i++) {
    let [a, b] = input[i+1].split(' ').map(Number);
    arr[a].push(b);
    arr[b].push(a);
}

arr.forEach((e) => {
    e.sort((a, b) => a-b);
});

let visited = new Array(node+1).fill(false);

// DFS
let ans_dfs = [];

function DFS(start) {
    if(visited[start]) return;
    visited[start] = true;
    ans_dfs.push(start);
    
    for(let i = 0; i < arr[start].length; i++) {
        let next = arr[start][i];
        if(!visited[next]) DFS(next);
    }
}

DFS(start);
console.log(ans_dfs.join(' '));

// BFS
visited.fill(false);
let ans_bfs = [];

function BFS(start) {
    let queue = [start];
    while(queue.length) {
        let x = queue.shift();
        if(visited[x]) continue;
        visited[x] = true;
        ans_bfs.push(x);

        for(let i = 0; i < arr[x].length; i++) {
            let next = arr[x][i];
            if(!visited[next]) queue.push(next);
        }
    }
}

BFS(start);
console.log(ans_bfs.join(' '));
