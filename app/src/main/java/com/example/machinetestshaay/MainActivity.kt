package com.example.machinetestshaay

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.machinetestshaay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTodos()
    }

    private fun fetchTodos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getTodos().enqueue(object : Callback<TodoResponse> {
            override fun onResponse(call: Call<TodoResponse>, response: Response<TodoResponse>) {
                if (response.isSuccessful) {
                    val todos = response.body()?.todos ?: emptyList()
                    todoAdapter = TodoAdapter(todos) { todoItem ->
                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtra("todo", todoItem.todo)
                        intent.putExtra("userId", todoItem.userId)
                        intent.putExtra("completed", todoItem.completed)
                        startActivity(intent)
                    }
                    binding.recyclerView.adapter = todoAdapter
                }
            }

            override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                Log.e("MainActivity", "Error fetching todos", t)
            }
        })
    }
}
