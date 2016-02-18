def fun():Int => Int = {
  def sqr(x: Int):Int = x * x
  sqr
}

val f = fun()
println(f(10))
