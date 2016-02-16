def sqr(x: Int) = x * x
def cube(x: Int) = x * x * x

def sumSimple(a: Int, b: Int): Int =
  if (a == b) a else a + sumSimple(a + 1, b)

def sumSquares(a: Int, b: Int): Int =
  if (a == b) sqr(a) else sqr(a) + sumSquares(a + 1, b)

def sumCubes(a: Int, b: Int): Int =
  if (a == b) cube(a) else cube(a) + sumCubes(a + 1, b)




def identity(x: Int) = x

def sum(f: Int=>Int, a: Int, b: Int): Int =
  if (a == b) f(a) else f(a) + sum(f, a + 1, b)


println(sum(identity, 1, 10))
println("--")
println(sumSquares(1, 10))
println(sum(sqr, 1, 10))
println("--")
println(sumCubes(1, 10))
println(sum(cube, 1, 10))
println("--")
