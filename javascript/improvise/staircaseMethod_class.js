class stairProblem {
	constructor(step, method) {
		this.step = step //int
		this.method = method //array of int
		this.count = 0
		this.pool = []
		this.recursive([], this.step)
	}
	ways() {
		return this.pool
	}
	counts() {
		return this.count
	}
	recursive(seq, n) {
		if (n == 0) {
			this.pool.push(seq)
			this.count++
		}
		if (n < this.method[0]) return
	
		for (let i = 0; i < this.method.length; i++) {
			if (n >= this.method[i]) {
				var clone = seq.slice(0)
				clone.push(this.method[i])
				this.recursive(clone, n - this.method[i])
			}
		}
	}
}

// function calculate(a, n, steps) {
// 	if (n == 0) {
// 		console.log(a)
// 	}
// 	if (n < steps[0]) return
//
// 	for (let i = 0; i < steps.length; i++) {
// 		if (n >= steps[i]) calculate(a + `${steps[i]} `, n - steps[i], steps)
// 	}
// }

var k = new stairProblem(21, [7, 14])

console.log(k.pool)
