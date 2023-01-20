/*fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
} */

import kotlinx.coroutines.*
import kotlin.ArithmeticException
import kotlin.system.measureTimeMillis

// --------------------------------------------------------------
// first pass demo async returns Deferred<T>, launch returns Job
// --------------------------------------------------------------
/* fun main()  {
   println("Hello win on thread  ${Thread.currentThread().name}")

  val myInt: Deferred<Int> = GlobalScope.async {
       println("Hello on thread  ${Thread.currentThread().name}" )
       delay(1000)
       println("Hello on thread  ${Thread.currentThread().name}" )
       15
   }
   runBlocking {
       println(myInt.await())
   }

   val mJob: Job = GlobalScope.launch {
       println("Hello again on thread  ${Thread.currentThread().name}" )
       delay(1000)
       println("Hello again on thread  ${Thread.currentThread().name}" )
   }

   runBlocking {
       mJob.join()
   }
} */

// ----------------------------------------------------------------------------
//  without GlobalScope launch runs in runBlocking context which is main thread
// ----------------------------------------------------------------------------
/*  fun main() = runBlocking {
    println("Hello win on thread  ${Thread.currentThread().name}")

    val myInt: Deferred<Int> = async {
         println("Hello again on thread  ${Thread.currentThread().name}" )
         delay(1000)
         println("Hello again on thread  ${Thread.currentThread().name}" )
         15
     }
     //Thread.sleep(2000)
     runBlocking {
         println(myInt.await())
     }

    val mJob: Job = launch {
        println("Hello again there on thread  ${Thread.currentThread().name}" )
        delay(1000)
        println("Hello again there on thread  ${Thread.currentThread().name}" )
    }
    //Thread.sleep(2000)
    runBlocking {
        println(mJob.join())
    }
} */

// --------------------------------------------------------------------------------------------------
//  withTimeout - add exception handling - withTimeout throws TimeoutCancellationException exception
// --------------------------------------------------------------------------------------------------
/*fun main() = runBlocking {
    println("Hello win on thread  ${Thread.currentThread().name}")

    try {
        withTimeout(2000) {

            for (i in 0..500) {
                print("$i.")
                delay(500)
            }
        }
    } catch (ex: TimeoutCancellationException) {
        println("timed out with ")
    } finally {
        println("finally block")
    }

} */

// -------------------------------------------------------
//  withTimeoutOrNull  - no exception thrown, returns null
// -------------------------------------------------------
/* fun main() = runBlocking {
    println("Hello win on thread  ${Thread.currentThread().name}")

    val result: String? = withTimeoutOrNull(2000) {
        for (i in 0..500) {
            print("$i.")
            delay(500)
        }
        "I am done"
    }
    println("\nresult is $result")

} */

// -----------------------------------------------------------------------------------------------------------
// job.cancelAndJoin() - note different structure of try block - delay throws CancellationException exception
// -----------------------------------------------------------------------------------------------------------
/* fun main() = runBlocking {
    println("Hello win on thread  ${Thread.currentThread().name}")

    val job = launch(Dispatchers.Default) {
        try {
            for (i in 0..500) {
                print("$i.")
                delay(5)
            }
        } catch (ex: CancellationException) {
            println("\ntimed out with ${ex.message}")
        } finally {
            println("finally block")
        }
    }

    delay(10)
    job.cancelAndJoin()
    println("end of main thread  ${Thread.currentThread().name}" )
} */

// --------------------------------------------
// specify cancellation message in job.cancel()
// --------------------------------------------
/* fun main() = runBlocking {
    println("Hello win on thread  ${Thread.currentThread().name}")

    val job = launch(Dispatchers.Default) {
        try {
            for (i in 0..500) {
                print("$i.")
                delay(5)
            }
        } catch (ex: CancellationException) {
            println("\ntimed out with ${ex.message}")
        } finally {
            println("finally block")
        }
    }

    delay(10)
    job.cancel("This is my cancellation message")
    job.join()
    println("end of main thread  ${Thread.currentThread().name}" )
} */

// --------------------------------------------------------------------
// by default suspend functions execute sequentially time is 2 seconds
// --------------------------------------------------------------------
/*fun main() = runBlocking {
 println("main program starts thread  ${Thread.currentThread().name}")
 val time = measureTimeMillis {
   val msgOne = getMessageOne()
   val msgTwo = getMessageTwo()
   println("the entire message is   ${msgOne + msgTwo}")
 }
 println("time taken is $time")

}
suspend fun getMessageOne() : String {
 delay(1000)
 return "Hello "
}

suspend fun getMessageTwo() : String {
delay(1000)
return "World"
}*/

// --------------------------------------------------------------------
// run functions in coroutines to run concurrently, time is 1 sec
// --------------------------------------------------------------------
/* fun main() = runBlocking {
    println("main program starts thread  ${Thread.currentThread().name}")
    val time = measureTimeMillis {
        val msgOne : Deferred<String> = async { getMessageOne() }
        val msgTwo : Deferred<String> = async { getMessageTwo() }
        println("the entire message is   ${msgOne.await() + msgTwo.await()}")
    }
    println("time taken is $time")
}


suspend fun getMessageOne() : String {
  delay(1000)
  return "Hello "
}

suspend fun getMessageTwo() : String {
 delay(1000)
 return "World"
} */

// -----------------------------------------------------------------------------------------------
// use async(start = CoroutineStart.LAZY) to skip running coroutines if result not used by program
// -----------------------------------------------------------------------------------------------
/* fun main() = runBlocking {
   println("main program starts thread  ${Thread.currentThread().name}")

   val msgOne: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageOne() }
   val msgTwo: Deferred<String> = async (start = CoroutineStart.LAZY){ getMessageTwo() }
   //println("the entire message is   ${msgOne.await() + msgTwo.await()}")
   println("end of main")
}


suspend fun getMessageOne() : String {
   delay(1000)
   println("after working in getMessageOne")
   return "Hello "
}

suspend fun getMessageTwo() : String {
   delay(1000)
   println("after working in getMessageTwo")
   return "World"
} */

fun main() = runBlocking {
    println("main program starts thread  ${Thread.currentThread().name}")

    launch {
        println("C1 on  thread  ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("C2 on  thread  ${Thread.currentThread().name}")
        delay(1000)
        println("C2 on  thread after delay  ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("C3 on  thread  ${Thread.currentThread().name}")  //main thread
        delay(1000)
        println("C3 on  thread after delay  ${Thread.currentThread().name}")  //Default-Dispatcher-worker-1
    }

    launch(coroutineContext) {
        println("C4 on  thread  ${Thread.currentThread().name}")  //main thread
        delay(1000)
        println("C4 on  thread after delay  ${Thread.currentThread().name}")  //main
    }
    println("end of main")
}


