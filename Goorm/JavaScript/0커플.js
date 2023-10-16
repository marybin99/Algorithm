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
    let N = Number(input[0]);
    let S = input[1].split(' ').map(Number);

    // 반복문으로는 시간초과날 것 같아서 reduce 메소드 사용
    let ans = S.reduce((sum, cur) => {
        return sum + cur;
    }, 0);
	
    console.log(ans);
}