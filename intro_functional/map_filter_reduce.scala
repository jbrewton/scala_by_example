val a = List(1,2,3,4,5,6,7)
val b = a.map(x => x * x)
val c = a.filter(x => x < 5)
val d = a.reduce((x, y)=> x+y)

println("Map Filter Reduce")
println(s"List: $a")
println(s"Map: $b")
println(s"Filter: $c")
println(s"Reduce: $d")

println("More methods on collections")

def even(x: Int) = (x % 2) == 0

val e = List(2,4,6,5,10,11,13,12)

println("For all even - are all members even?")
println(a.forall(even))

println("Exists even - at least one exists")
println(a.exists(even))

println("Take while even then stop")
println(e.takeWhile(even))

println("Partition sublists on even")
println(a.partition(even))