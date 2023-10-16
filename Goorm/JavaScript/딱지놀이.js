// Run by Node.js
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
	let input = [];
	for await (const line of rl) {
		input.push(line);
	}
	
	solution(input);
    // 문제 상 오류로 끝에 공백 넣어줘야함
	console.log("");
	process.exit();
})();

function solution(input) {
    let N = Number(input[0]);

    for(let i=1;i<=N*2;i+=2) {
        let chA = input[i].split(' ').slice(1).map(Number);
        let chB = input[i+1].split(' ').slice(1).map(Number);

        let d = true;
        for(let j=4; j>=1; j--) {
            let aNum = chA.filter(num => num == j).length;
            let bNum = chB.filter(num => num == j).length;

            if(aNum != bNum) {
                console.log(aNum > bNum ? "A":"B");
                d = false;
                break;
            }
        }

        if(d) console.log("D");
    }
}
