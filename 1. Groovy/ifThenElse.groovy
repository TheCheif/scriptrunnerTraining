def a = 4

if (a == 5) {
    println("a is 5")
} else {
    println("a is not five")
}

def toTest = "123"
switch (toTest) {
    case "Hello": println("got Hello"); break
    case "test": println("i do not want to test"); break
    default: println("not recognized:  \"" + toTest + "\"")
}
