// Run by Node.js
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
    // 재귀로 풀면 재귀 깊이 한도초과가 나는지 런타임에러 발생 
	// function fac(num) {
	// 	if(num > 1) {
	// 		return BigInt(num) * BigInt(fac(num - 1));
	// 	}
	// 	return 1;
	// }
	
	let N = 0;
	for await (const line of rl) {
		N = line;
		// let ans = fac(line);
		
		rl.close();
	}
	
	solution(N);
	process.exit();
})();

function solution(N) {
	let ans = BigInt(1);
		
	for(let i = N; i >= 1; i--) {
		ans *= BigInt(i);
	}
	ans = ans % BigInt(1000000007);
	console.log(ans.toString());
}
