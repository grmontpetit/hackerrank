package time

import java.sql.Timestamp
import java.time.{LocalDateTime, ZoneOffset}

object Solution {

  def main(args: Array[String]): Unit = {
    val timeZero = new Timestamp(0).toString
    val expectedZero = "1969-12-31 19:00:00.0"
    assert(timeZero == "1969-12-31 19:00:00.0", s"got $timeZero want $expectedZero")

    val stringDateTime = "2019-10-09 14:39:51.201282"
    val expected = "2019-10-09T14:39:51.201282Z"
    val timeStamp = Timestamp.valueOf(stringDateTime)
    val localDt = timeStamp.toLocalDateTime.atZone(ZoneOffset.UTC).toString
    assert(localDt == expected, s"got $localDt want $expected")

  }

}
