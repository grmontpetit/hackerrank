package hackerrank.newyearchaos

import java.util.concurrent.{ForkJoinPool, ForkJoinTask, ForkJoinWorkerThread, RecursiveTask}

import scala.collection.Map
import scala.io.Source
import scala.util.{DynamicVariable, Try}

object Solution {
  type Person = Int
  type NbOfSwaps = Int
  type Swaps = Map[Person, NbOfSwaps]
  type IsChaotic = Boolean
  type HasSwapped = Boolean
  type ParResults = (Swaps, Array[Person])
  val forkJoinPool = new ForkJoinPool
  final val message = "Too chaotic"

  abstract class TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T]
    def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
      val right = task {
        taskB
      }
      val left = taskA
      (left, right.join())
    }
    def parallel(q: Array[Person], nbPar: Int): ParResults = {

      val split: Iterator[Array[Person]] = q.grouped(q.length / nbPar)
      val firstTasks: IndexedSeq[ParResults] = (0 until nbPar - 1)
        .map(_ => {
          val persons: Array[Person] = split.next
          task{
            itr(0, persons, Map.empty, true)
          }
        })
        .map(_.join())

      val lastTask = itr(0, split.next, Map.empty, true)
      val results = firstTasks :+ lastTask

      val sorted: Array[Person] = results.map(_._2).fold(Array.empty){_ ++ _}

      val swaps: Swaps = results.map(_._1).fold(Map.empty[Person, NbOfSwaps])(_ ++ _)

      (swaps, sorted)
    }
  }

  class DefaultTaskScheduler extends TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T] = {
      val t = new RecursiveTask[T] {
        def compute = body
      }
      Thread.currentThread match {
        case wt: ForkJoinWorkerThread =>
          t.fork()
        case _ =>
          forkJoinPool.execute(t)
      }
      t
    }
  }

  val scheduler = new DynamicVariable[TaskScheduler](new DefaultTaskScheduler)

  def itr(index: Int, current: Array[Person], swaps: Swaps, hasSwapped: HasSwapped): (Swaps, Array[Person]) = {
    if(index == current.length - 1) {
      if (hasSwapped) {
        itr(0, current, swaps, false)
      } else {
        (swaps, current)
      }
    }
    else if (current(index) > current(index + 1)) {
      val updatedSwaps: Swaps = incrementSwaps(index, swaps, current)
      itr(index + 1, unbribe(index, current), updatedSwaps, true)
    } else {
      itr(index + 1, current, swaps, hasSwapped)
    }
  }

  // Complete the minimumBribes function below.
  def minimumBribes(q: Array[Int]): Either[String, NbOfSwaps] = {

    val z = itr(0, q, Map.empty, true)
    z._1.exists(_._2 > 2)
    // Perform 1st split
    val first: ParResults = scheduler.value.parallel(q, 4)

    // Perform 2nd split
    val second: ParResults = scheduler.value.parallel(first._2, 5)

    // merge swaps
    val mergedSwaps = (first._1.toSeq ++ second._1.toSeq).groupBy(_._1).view.mapValues(_.map(_._2).sum)

    // check if chaotic
    val isChaotic = mergedSwaps.exists(_._2 > 2)
    if (isChaotic) Left(message)
    else Right(mergedSwaps.values.sum)

  }

  def task[T](body: => T): ForkJoinTask[T] = {
    scheduler.value.schedule(body)
  }

  def incrementSwaps(index: Int, swaps: Swaps, current: Array[Person]): Swaps = {
    val oldCount = swaps.getOrElse(current(index), 0)
    val count: Int = oldCount + 1
    val removed = swaps.toMap.removed(current(index))
    removed ++ Map(current(index) -> count)
  }

  def unbribe(index: Int, current: Array[Person]): Array[Person] = {
    val split = current.splitAt(index + 1)
    split._1.dropRight(1) ++ Array(split._2.head, split._1.last) ++ split._2.tail
  }

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block // call-by-name
    val t1 = System.currentTimeMillis()
    println("Elapsed time: " + (t1 - t0)/1000.toDouble + "s")
    result
  }

  def main(args: Array[String]): Unit = {

//    val queue1 = """2 1 5 3 4""".split(" ").map(_.trim.toInt)
//    val count1 = time(minimumBribes(queue1))
//    assert(count1 == Right(3), s"got $count1 want 3")
//
//    val queue2 = """2 5 1 3 4""".split(" ").map(_.trim.toInt)
//    val count2 = time(minimumBribes(queue2))
//    assert(count2 == Left(message), s"got $count2 want $message")
//
//    val queue3 = """1 2 5 3 4 7 8 6""".split(" ").map(_.trim.toInt)
//    val count3 = time(minimumBribes(queue3))
//    assert(count3 == Right(4), s"got ${count3.getOrElse("unknown")} want 4")
//
//    val queue4 = """1 2 5 3 7 8 6 4""".split(" ").map(_.trim.toInt)
//    val count4 = time(minimumBribes(queue4))
//    assert(count4 == Right(7), s"got ${count4.getOrElse("unknown")} want 7")
//
//    val queue5 = """5 1 2 3 7 8 6 4""".split(" ").map(_.trim.toInt)
//    val count5 = time(minimumBribes(queue5))
//    assert(count5 == Left(message), s"got $count5} want $message")

    val queues = Source.fromResource("input08.txt").getLines.toArray
    val lines = queues.tail
    val arrays: IndexedSeq[Array[Int]] = (1 to lines.length by 2)
      .map(i => lines(i).split(" ")
        .map(_.toInt))

    val expectedSource = """|119814
                            |120175
                            |119810
                            |119827
                            |Too chaotic""".stripMargin.split("\\n")

    arrays.indices.foreach { i =>
      val expected = Try(expectedSource(i).toInt)

      val q = arrays(i)
      val result: Either[String, NbOfSwaps] = time(minimumBribes(q))

      if (expected.isSuccess) {
        assert(result == Right(expected.get), s"got $result want ${expected.get}")
      } else {
        assert(result == Left("Too chaotic"), s"got $result want ${expected.get}")
      }
    }

//    val stdin = scala.io.StdIn
//
//    val t = stdin.readLine.trim.toInt
//
//    for (tItr <- 1 to t) {
//      val n = stdin.readLine.trim.toInt
//
//      val q = stdin.readLine.split(" ").map(_.trim.toInt)
//      minimumBribes(q)
//    }
  }
}
