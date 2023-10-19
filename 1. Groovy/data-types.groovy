class DataTypes {
    void example(String[] args) {
        //String s = "Katzen sind cool"
        def s = "Katzen sind cool"

        //Boolean x = true
        def b = true

        // int x = 5
        def x = 5

        //long y = 100L
        def y = 100L

        //float a = 10.56f
        def a = 10.56f
        
        //double d = 10.5e40
        def d = 10.5e40
        
        //BigInteger bi = 30g
        def bi = 30g

        //BigDecimal bd = 3.5g
        def bd = 3.5g
        
        println(s)
        println(b)
        println(x)
        println(y)
        println(a)
        println(d)
        println(bi)
        println(bd)
   } 
}

def x = new DataTypes()
x.example()