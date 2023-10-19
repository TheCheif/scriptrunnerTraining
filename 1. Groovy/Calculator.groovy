class Calculator {
    int add(int a, int b) {
        return a + b
    }

    int sub(int a, int b) {
        return a - b
    }

    int mul(int a, int b) {
        return a * b
    }

    int div(int a, int b) {
        return a / b
    }
}

def c = new Calculator()
[c.add(1, 2), c.sub(2, 1), c.mul(2, 3), c.div(4, 2)]