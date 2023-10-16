// Run by Node.js
const readline = require('readline');

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
	let N = input[0];
	let S = input[1].split('').map(String);
	
	let ans = new Array(N);
	for(let i=0;i<N;i++){
		if(S[i] === S[i].toUpperCase())
			ans[i] = S[i].toLowerCase();
		else ans[i] = S[i].toUpperCase();
	}
	
	console.log(ans.join(''));
}