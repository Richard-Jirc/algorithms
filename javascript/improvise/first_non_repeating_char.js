function firstNonRepChar(string) {
	if (string.length <= 0) return -1
	
	var hashmap = construct(26)
	for (var i=0; i<string.length; i++) {
		search(string[i], i, hashmap)
	}
	
	var result = []
	for (let j=0; j<hashmap.length; j++) {
		if (hashmap[j].count == 1) {
			result.push(hashmap[j])
		}
	}
	if (result.length == 0) return '-'
	
	result.sort((a,b) => a.address - b.address)
	return string[result[0].address]
}

function hash(character) {
	return character.charCodeAt(0) - 97
}

function construct(length) {
	var obj = []
	for (var i=0; i<length; i++) {
		obj.push({
			count: 0,
			address: -1
		})
	}
	return obj
}

function search(char, address, hash_obj) {
	var index = hash(char)
	hash_obj[index].count += 1
	hash_obj[index].address = address
}

function random() {
	var source = 'abcdefghijklmnopqrstuvwxyz',
		length = 8000000,
		result = '',
		i = 0
	while(i < length) {
		var index = Math.random() * 26
		result += source[Math.floor(index)]
		i++
	}
	return result
}



var target = random()

var start = Date.now()

target.length <= 200 ? console.log(target, `length of ${target.length}`) : console.log('length:', target.length)

result = firstNonRepChar(target)

var end = Date.now()

console.log('finished:', result)
console.log('run_time:', end - start)