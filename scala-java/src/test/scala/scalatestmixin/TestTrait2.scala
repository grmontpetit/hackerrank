package scalatestmixin

import org.scalatest.{BeforeAndAfterAll, Suite}

trait TestTrait2 extends BeforeAndAfterAll with Output { this: Suite =>

  override def beforeAll(): Unit = {
    print("TestTrait2: beforeAll")
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    println("TestTrait2: afterAll")
    super.afterAll()
  }

}

