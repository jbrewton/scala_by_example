def sample1() = {
  def fun1():Int => Int = {
    val y = 1
    def add(x: Int) = x + y
    add
  }

  def fun2() = {
    val y = 2
    val f = fun1()

    println(f(10))
  }

  fun2()
}

sample1()

def sample2() = {
  def sqr(x: Int) = x * x
  def cube(x: Int) = x * x * x

  def compose(f: Int=>Int, g: Int=>Int): Int=>Int =
    x => f(g(x))

  val f = compose(sqr, cube)
  println(f(2))

  val a = List(1,2,3,4)

  println(a.map(f))

  println(a.map(cube).map(sqr))
}

sample2()

def sample3() = {
  def removeLowScores(a: List[Int], threshold: Int): List[Int] = 
    a.filter(score => score >= threshold)

  val a = List(95, 87, 20, 45, 35, 66, 10, 15)

  println(removeLowScores(a, 30))
}
sample3()
