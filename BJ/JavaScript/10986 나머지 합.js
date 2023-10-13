const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
let input = fs.readFileSync(filePath).toString().trim().split('\n');

let [N, M]= input[0].split(' ').map(Number);
arr = input[1].split(' ').map(Number);

let ans = 0;
let sum = [arr[0]];
let mod = new Array(M).fill(0);

for(let i = 1; i < N; i++) {
    sum[i] = sum[i-1] + arr[i];
}

for(let i = 0; i < N; i++) {
    if(sum[i] % M == 0) ans++;
    mod[sum[i] % M]++;
}

for(let i = 0; i < M; i++) {
    if(mod[i]>1)  ans += (mod[i] * (mod[i] - 1) / 2);  // mode[i]개 중에 2개 뽑는 조합
}

console.log(ans);