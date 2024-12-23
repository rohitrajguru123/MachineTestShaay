package com.example.machinetestshaay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetestshaay.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: List<TodoItem>,
    private val onItemClick: (TodoItem) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: TodoItem) {
            binding.tvId.text = "ID: ${todo.id}"
            binding.tvTodo.text = todo.todo
            binding.tvCompleted.text = "Completed: ${todo.completed}"
            binding.tvUserId.text = "UserID: ${todo.userId}"

            // Handle clicks
            binding.root.setOnClickListener { onItemClick(todo) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size
}
