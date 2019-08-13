package dynamicprogrammingwithmemoization.median

import utest._

object QuickSelectTest extends TestSuite {
  val tests =  Tests{
    test("Median") {
      assert(QuickSelect(List(1, 2, 3)).median == 2)
      assert(QuickSelect(List(1)).median == 1)
      assert(QuickSelect(List(1, 2)).median == 1.5)
      assert(QuickSelect(List(9,1,0,2,3,4,6,8,7,10,5)).median == 5.0)
    }
  }
}
