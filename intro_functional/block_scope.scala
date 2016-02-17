def fun(x: Int) = {
  val y = 1

  val r = {
    val y = 2
    x + y
  }

  println(r)
  println(x + y)
}

fun(10)