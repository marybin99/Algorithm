// Run by Node.js
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
	let input = [];
	for await (const line of rl) {
		input.push(line);
	}
	solution(input);
	process.exit();
})();

function solution(input) {
    let [N, D] = input[0].split(' ').map(Number);
    let p = input[1].split(' ').map(Number);
    p.sort((o1, o2) => o1-o2);
    
    // two pointer
    let [start, end] = [0, 0];
    let len = 0;
    while(start < N && end < N) {
        if(p[end] - p[start] <= D) {
            len = Math.max(len, end-start+1);
            end ++;
        } else {
            start ++;
        }
    }
    console.log(N-len);
}