package com.example.addingtolistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.addingtolistview.databinding.ActivityAddBinding
import com.example.addingtolistview.utils.MySharedPreference

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)

        binding.save.setOnClickListener {
            val text = binding.edt.text.toString()
            val list = MySharedPreference.list

            list.add(text)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            MySharedPreference.list = list
            finish()
        }
    }
}