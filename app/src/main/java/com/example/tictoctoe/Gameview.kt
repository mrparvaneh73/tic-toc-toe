package com.example.tictoctoe


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.TextView


class Gameview : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnback: Button
    private lateinit var winner: TextView
    private lateinit var a1: Button
    private lateinit var a2: Button
    private lateinit var a3: Button
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var c1: Button
    private lateinit var c2: Button
    private lateinit var c3: Button
    private lateinit var reset: Button
    private val player1 = 0
    private val player2 = 1
    private var activeplayer = player1
    private lateinit var list: IntArray
    private var gameactive = true
    override fun onCreate(savedInstanceState: Bundle?) {
        list = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameview)
        reset()
        getback()
        a1 = findViewById<Button>(R.id.a1)
        a2 = findViewById<Button>(R.id.a2)
        a3 = findViewById<Button>(R.id.a3)
        b1 = findViewById<Button>(R.id.b1)
        b2 = findViewById<Button>(R.id.b2)
        b3 = findViewById<Button>(R.id.b3)
        c1 = findViewById<Button>(R.id.c1)
        c2 = findViewById<Button>(R.id.c2)
        c3 = findViewById<Button>(R.id.c3)
        winner = findViewById(R.id.winner)
        a1.setOnClickListener(this)
        a2.setOnClickListener(this)
        a3.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        c1.setOnClickListener(this)
        c2.setOnClickListener(this)
        c3.setOnClickListener(this)

    }

    private fun getback() {
        btnback = findViewById(R.id.btnback)
        btnback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun reset() {
        reset = findViewById<Button>(R.id.btnrest)
        reset.setOnClickListener {
            list = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
            gameactive = true
            winner.text = ""
            a1.text = ""
            a2.text = ""
            a3.text = ""
            b1.text = ""
            b2.text = ""
            b3.text = ""
            c1.text = ""
            c2.text = ""
            c3.text = ""
        }
    }


    override fun onClick(v: View?) {
        if (!gameactive)
            return
        val btnclicked = findViewById<Button>(v!!.id)
        val tagclicked = Integer.parseInt(btnclicked.tag.toString())
        if (list[tagclicked] != -1)
            return
        list[tagclicked] = activeplayer
        if (activeplayer == player1) {
            btnclicked.text = "O"
            activeplayer = player2
        } else {
            btnclicked.text = "X"
            activeplayer = player1
        }
        chekforwinner()

    }

    private fun chekforwinner() {
        val listwinner = arrayOf(
            arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8),
            arrayOf(0, 3, 6), arrayOf(1, 4, 7), arrayOf(2, 5, 8), arrayOf(0, 4, 8), arrayOf(2, 4, 6)
        )
        for (i in listwinner.indices) {
            val arrow0 = listwinner[i][0]
            val arrow1 = listwinner[i][1]
            val arrow2 = listwinner[i][2]
            if (list[arrow0] == list[arrow1] && list[arrow0] == list[arrow2]) {
                if (list[arrow0] != -1) {
                    gameactive = false
                    if (list[arrow0] == player1) {
                        winner.text = "Player O Is winner"
                    } else {
                        winner.text = "Player X Is Winner"
                    }
                    return
                }
            }
        }
        var counter = 0
        for (i in list.indices) {
            if (list[i] == -1) {
                counter++
            }
        }
        if (counter == 0) {
            winner.text = " IT'S DRAW"
            return
        }
    }

}


