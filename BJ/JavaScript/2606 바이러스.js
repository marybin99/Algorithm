const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let node = Number(input[0]);
let num = Number(input[1]);

let arr = new Array(node + 1);
for(let i = 0; i < arr.length; i++) {
    arr[i] =  [];
}

for(let i = 0; i < num; i++) {
    let [a, b] = input[i+2].split(' ').map(Number);
    arr[a].push(b);
    arr[b].push(a);
}

let visited = new Array(node + 1).fill(false);
let virus = 0;
visited[1] = true;

function dfs(start) {
    for(let i = 0; i < arr[start].length; i++) {
        let next = arr[start][i];
        if(!visited[next]) {
            visited[next] = true;
            virus++;
            dfs(next);
        }
    }
}

dfs(1);
console.log(virus);