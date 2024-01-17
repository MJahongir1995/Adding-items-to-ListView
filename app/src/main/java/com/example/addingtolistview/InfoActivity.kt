package com.example.addingtolistview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.addingtolistview.databinding.ActivityInfoBinding
import com.example.addingtolistview.utils.MySharedPreference
class InfoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityInfoBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        MySharedPreference.init(this)

        val index = intent.getIntExtra("keyIndex", -1)

        if (index!=-1){
            binding.txtInfo.text = MySharedPreference.list[index]
        }else{
            binding.txtInfo.text = "Not found"
        }
    }
}