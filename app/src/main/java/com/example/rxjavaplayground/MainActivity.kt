package com.example.rxjavaplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import org.reactivestreams.Subscriber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        runExampleOne()
        runExampleTwo()
        runExampleThree()
    }

    private fun runExampleOne(){
        Observable.just("Hello Reactive World")
            .subscribe { value ->
                Log.d(TAG, "onCreate: $value")
            }
    }

    private fun runExampleTwo(){
        Observable.just("Apple", "Orange", "Banana")
            //.map { input -> throw RuntimeException() } //trigger error
            .subscribe(
                { value -> Log.d(TAG,"Received: $value") }, // onNext
                { error -> Log.d(TAG,"Error: $error") },    // onError
                { Log.d(TAG,"Completed") }                 // onComplete
            )
    }

    private fun runExampleThree(){
        Observable.fromIterable(listOf("Apple", "Orange", "Banana"))
            .subscribe(
                { value -> Log.d(TAG,"Received: $value") },      // onNext
                { error -> Log.d(TAG,"Error: $error") },         // onError
                { Log.d(TAG,"Completed") }                     // onComplete
            )
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}