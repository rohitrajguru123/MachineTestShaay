package com.example.machinetestshaay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.machinetestshaay.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from Intent
        val todo = intent.getStringExtra("todo")
        val userId = intent.getIntExtra("userId", 0)
        val completed = intent.getBooleanExtra("completed", false)

        // Display data
        binding.tvTodo.text = "Todo: $todo"
        binding.tvUserId.text = "User ID: $userId"
        binding.tvCompleted.text = "Completed: $completed"
    }
}
