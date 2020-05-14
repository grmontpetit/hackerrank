package scalatestmixin

import org.scalatest.{BeforeAndAfterAll, Suite}

trait TestTrait1 extends BeforeAndAfterAll with Output { this: Suite =>

  override def beforeAll(): Unit = {
    print("TestTrait1: beforeAll")
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    println("TestTrait1: afterAll")
    super.afterAll()
  }

}
