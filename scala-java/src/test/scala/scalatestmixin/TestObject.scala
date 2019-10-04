package scalatestmixin

import org.scalatest.{FlatSpec, Matchers}

class TestObject extends FlatSpec with Matchers with TestTrait1 with TestTrait2 {

  "before and after all mixin" should "print 4 messages to the console" in {
    val expected = """|TestTrait2: beforeAll
                      |TestTrait1: beforeAll
                      |""".stripMargin
    stream.toString shouldBe expected

    // It is not possible to assert that the after all was run without printing to console.
  }

}
