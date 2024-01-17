package com.example.addingtolistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.addingtolistview.databinding.ActivityMainBinding
import com.example.addingtolistview.utils.MySharedPreference

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)
        val list = MySharedPreference.list
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list)
        binding.myListView.adapter = arrayAdapter

        binding.myListView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("keyIndex", i)
            startActivity(intent)
        }
    }
}