// Run by Node.js
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	
	for await (const line of rl) {
		
		let N = Number(line);

        let arr = new Array().map(Number);
        let index = 0, tmp = 0;

        // 팩토리얼 구하기
        arr.push(1);
        for(let i = 2; i <= N; i++) {
            index = 0;
            while(!arr[index]) index++; // 성능향상 위해서 조건 추가 * 없어도 결과에는 지장없음
            for(tmp = 0; index < arr.length; index++) {
                tmp += arr[index] * i;
                arr[index] = tmp % 10; // 일의 자리 저장
                tmp = Math.floor(tmp/10); 
            }
            while(tmp) {  // r이 한 자리수가 아니면
                arr.push(tmp % 10); // arr에 추가
                tmp = Math.floor(tmp/10);
            }
        }

        // console.log(arr);
        
        // 자릿수 합 구하기
        let ans = 0;

        for(let i = 0, tmp = 0; i < arr.length; i++) {
            if(!arr[i]) continue;
            tmp +=  ans + arr[i];
            ans = tmp % 10;
            tmp = Math.floor(tmp/10);
        }
        console.log(ans);

		rl.close();
	}
	
	process.exit();
})();
