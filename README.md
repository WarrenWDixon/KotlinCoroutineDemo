# KotlinCoroutineDemo
IntelliJ project that demonstrates the various Kotlin API features

How To Create A Kotlin Coroutine Project In IntelliJ IDEA 2022.3.1 Community Edition
To focus on the language this is a pure Kotlin project running in IntelliJ rather than an Android project.

This project is a series of main routines each of which demonstrates an aspect of the Kotlin 
coroutine API.  All but one are commented out.

The idea is to uncomment each one at a time to demo the API for that particular main.

Although this IntelliJ project is all set up, this read me will guide you through the process.

Setting up a Kotlin Coroutine project can be a little tricky in IntelliJ if you're not familar with 
the process. The key is that you need to understand how Kotlin is accessed from Java.

Here is a step by step guide so you too can amaze your friends and family.

In IntelliJ Click New Project
On the left select Kotlin Multiplatform
On the right select Console Application and Gradle Groovy
We need Groovy to have a gradle.build file so we can add the co-routine dependencies
At this point we also define the name of the project.
This demo project is called CoolCouroutineProject
Click Next, then Finish

NOTE: You MUST wait for IntelliJ to complete indexing the project before setting up the configuration.
If you try to edit the configuration (next step) before IntelliJ has finished indexing the project,
you will fail. Monitor the status in the bottom tool bar.

You should now have a new project.
The next step is to edit the configuration.
Click the drop down menu on Current File in the right side of the Tool Bar
In top left click "+" and Select Kotlin

In the Name edit box enter MainKt.  

Click on the Use classpath of module
select <project_name>.main
(Note this is the step that fails if you don't wait for IntelliJ to complete indexing the project.
If you do not see <project_name>.main in the "Use classpath of module" drop-down,
you did not wait long enough.)

Next click the ellipsis to the right of Main Class
Getting the main class configured is the key to creating the configuration

IntelliJ will search for a few moments then display
<project_name>.main
Select that then click Apply and OK

At this point you can now run the default "Hello World application"

To run Kotlin coroutines we need to add some dependencies to build.gradle.
implementation "org.jetbrains.kotlin:kotlin-stdlib"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"

After updating the gradle.build there will be a floating button that looks like
an elephant that is Load Gradle Changes.  Click that, it's similar to syncing in Android Studio.

You now have a Kotlin project that will support coroutines!



