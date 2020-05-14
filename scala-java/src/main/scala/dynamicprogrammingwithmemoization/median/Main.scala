package dynamicprogrammingwithmemoization.median

object Main {

  def main(args: Array[String]): Unit = {
    val list1 = List(4, 2, 3)
    val result1 = QuickSelect(list1).median
    assert(result1 == 3.0, s"got $result1 want 3.0")

    val list2 = List(3, 2)
    val result2 = QuickSelect(list2).median
    assert(result2 == 2.5, s"got $result1 want 2.5")

    val list3 = List(9,1,0,2,3,4,6,8,7,10,5)
    val result3 = QuickSelect(list3).median
    assert(result3 == 5.0, s"got $result1 want 5.0")
  }

}
