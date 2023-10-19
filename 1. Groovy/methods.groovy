def add(int a, int b) {
    return a + b
}

def addStrings(String a, String b) {
    return a + b
}

def areBothTrue(Boolean a, Boolean b) {
    return a && b
}

def isFive(Float number) {
    return number == 5
}

println("Add")
println(add(5, 10))
println(addStrings("Hallo ", "Katze"))

println()
println("Are Both true?")
println(areBothTrue(true, false))
println(areBothTrue(false, false))
println(areBothTrue(true, true))

println()
println("Is Five?")
println(isFive(3))
println(isFive(4))
println(isFive(5.1))
println(isFive(5))