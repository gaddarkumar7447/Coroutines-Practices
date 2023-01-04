package com.example.coroutinepractices

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var text : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)

        /*CoroutineScope(Dispatchers.Main).launch {
            task1()
            task2()
        }*/

        CoroutineScope(Dispatchers.IO).launch{
            printFollower()
        }

        CoroutineScope(Dispatchers.IO).launch {
            funTion()
        }

    }

    suspend fun funTion(){
        val job = CoroutineScope(Dispatchers.IO).launch {
            if (isActive){
                logRunningTask()
            }
        }
        delay(100)
        Log.d("Tag", "Parent job cancel")
        job.cancel()
        job.join()
        Log.d("Tag", "Parent completed")
    }

    private suspend fun withCon(){
        Log.d("Tag", "Before")
        withContext(Dispatchers.IO){
            delay(1000)
            Log.d("Tag", "Inside")
        }
        Log.d("Tag", "After")
    }

    private fun logRunningTask(){
        for (i in 1 .. 1000){

        }
    }

    private suspend fun printFollower() {
        // Use launch
        /*var printFbFollower = 0
        var printFbFollower1 = 0
        val result = CoroutineScope(Dispatchers.IO).launch {
            printFbFollower = getFbFollower()
        }
        val result1 = CoroutineScope(Dispatchers.IO).launch {
            printFbFollower1 = getInstFollower()
        }
        result.join()
        result1.join()
        Log.d("TAG", "Facebook $printFbFollower, Insta - $printFbFollower1")*/

        /*val job = CoroutineScope(Dispatchers.IO).async {
            getFbFollower()
        }
        val job1 = CoroutineScope(Dispatchers.IO).async {
            getInstFollower()
        }
        Log.d("Tag", "Insta - ${job.await()} facebeeok - ${job1.await()}")*/

        CoroutineScope(Dispatchers.IO).launch {
            val job = async { getFbFollower() }
            val job1 = async { getInstFollower() }
            Log.d("Tag", "Facebook - ${job.await()}, Instragram - ${job1.await()}")
        }

        // Three way to launch the Coroutines
        /*GlobalScope.launch(Dispatchers.IO){

        }
        MainScope().launch(Dispatchers.IO){

        }*/

    }

    private suspend fun getFbFollower():Int{
        delay(1000)
        return 54
    }

    private suspend fun getInstFollower():Int{
        delay(1000)
        return 113
    }

    private suspend fun task1(){
        Log.d("TAg","Starting task 1")
        yield()
        Log.d("TAg","Ending task 1")
    }
    private suspend fun task2(){
        Log.d("TAg","Starting task 2")
        yield()
        Log.d("TAg","Ending task 2")
    }

    /*@SuppressLint("SetTextI18n")
    fun Increment(view: View) {
        Log.d("TAG", "Main Thread")
        text.text = "${text.text.toString().toInt() + 1}"
    }

    fun longRunningTask(){
        for(i in 1 .. 1000000L){

        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun LongRunning(view: View) {
        *//*thread(start = true){
            longRunningTask()
        }*//*
        CoroutineScope(Dispatchers.IO).launch {
            longRunningTask()
        }
        MainScope().launch {

        }
        GlobalScope.launch {

        }
    }*/

}