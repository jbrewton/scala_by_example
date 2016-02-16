def sum_overflow(n: Int): Int =
  if (n==0) 
    0
  else
    n + sum_overflow(n-1)

val m = sum_overflow(10)
println(m)

//println(sum_overflow(1000000))

def sum_tail(n: Int, accumulator: Int): Int = 
  if (n == 0)
    accumulator
  else
    sum_tail(n-1, accumulator + n)

val r = sum_tail(10, 0)
println(r)

println(sum_tail(1000000, 0))