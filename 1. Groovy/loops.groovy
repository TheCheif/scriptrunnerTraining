println("While")
int count = 0
while (count < 5) {
    println(count)
    count += 1
}

println()
println("For")
for (int i = 0; i < 5; i++) {
    println(i)
}

println()
println("For in")
int[] arr = [0, 1, 2, 3, 4]
for (int i in arr) {
    println(i)
}

println()
println("For in with range")
for (int i in 0..4) {
    println(i)
}

println()
println("List forEach")
def l = ["h", "a", "l", "l", "o"]
l.forEach { print(it) }

println()
println("List map")
l.collect { it + 1 }.forEach { print(it) }