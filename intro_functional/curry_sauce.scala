def addA(x: Int, y: Int): Int = x + y
def addB(x:Int):Int => Int = y => x + y

val a = addA(10, 20)
val b = addB(10)(20)

println(a)
println(b)

def addC(x: Int, y: Int, z: Int) = x + y + z
def addD(x: Int): Int => (Int => Int) = y => (z => x + y + z)

println(addC(1,2,3))
println(addD(1)(2)(3))