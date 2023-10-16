// Run by Node.js
const readline = require('readline');

// 구름 IDE 사용이 너무 헷갈려서 입력받는데만 엄청 걸렸다..
(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
	let cnt = 0;
	let input = [];
	for await (const line of rl) {
		if(++cnt == 2) rl.close();
		input.push(line);
	}
	solution(input);
	process.exit();
})();

function solution(input) {
    // 개행문자 제거 "trim()"
	let [N, K] = input[0].trim().split(' ').map(String);
	let arr = input[1].split(' ').map(String);
	
	let ans = 0;
	for(let i = 0; i < Number(N); i++) {
		if(!arr[i].includes(K)) ans++;
	}
	console.log(ans);	
}