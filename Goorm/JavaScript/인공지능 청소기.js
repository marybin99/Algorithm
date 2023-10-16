// Run by Node.js
const readline = require("readline");
const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout
});

let T = null;
let cnt = 0;
let input = [];
rl.on("line", function(line) {
	if(T == null) T = line;
	else {
		input.push(line);
		if(++cnt == T) rl.close();
	}
}).on("close", function() {
	solution(T, input);
	process.exit();
});

function solution(T, input) {
	let ans = [];
	
	for(let tc=0;tc<T;tc++) {
		let [x, y, n] = input[tc].split(' ').map(Number);
		ans[tc] = 'NO';
		
		let sum = Math.abs(x) + Math.abs(y);
		if(sum <= n && (sum - n) % 2 == 0) ans[tc] = 'YES';
		
		console.log(ans[tc]);
	}
}