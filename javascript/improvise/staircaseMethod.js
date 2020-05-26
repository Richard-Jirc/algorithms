
var array = '',
	count = 0,
	total = 12

var steps = [2, 3, 5, 7]

printways(array, total)
console.log(`${count} way(s) to climb [${total}] stair: ${steps}`)

function printways(a, n) {
	if (n == 0) {
		console.log(a)
		count++
	}
	if (n < steps[0]) return
	
	for (let i = 0; i < steps.length; i++) {
		if (n >= steps[i]) printways(a + `${steps[i]} `, n - steps[i])
	}
}