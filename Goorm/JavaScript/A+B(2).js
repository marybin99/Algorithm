// Run by Node.js
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
	let input = [];
	for await (const line of rl) {
		input.push(line);
		rl.close();
	}
	solution(input);
	process.exit();
})();

function solution(input) {
	let [a, b] = input[0].split(' ').map(Number);
    // 소수점 자리수 지정 함수 "toFixed"
	let ans = (a+b).toFixed(6);
	console.log(ans);
}
