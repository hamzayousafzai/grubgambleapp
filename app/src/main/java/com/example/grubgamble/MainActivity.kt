package com.example.grubgamble

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(),LoginFragment.ILoginListener,RegisterFragment.IRegisterListener,
        MainFragment.IMainListener, MinigameFragment.IMiniListener, SpinFragment.ISpinListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (FirebaseAuth.getInstance().currentUser == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentView, LoginFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentView, MainFragment())
                .commit()
        }
    }
    override fun loginSuccessful() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentView, MainFragment())
            .commit()
    }

    override fun goToRegistration() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentView, RegisterFragment())
            .commit()
    }

    override fun goToLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentView, LoginFragment())
            .commit()
    }

    override fun goToTicTacToe() {
        val intent = Intent(this, TicTacToe::class.java)
        startActivity(intent)
    }

    override fun goToRandomNumberGuess() {
        val intent = Intent(this, RandomNumberGuess::class.java)
        startActivity(intent)
    }

    override fun goToFilters() {
        val intent = Intent(this, Filters::class.java)
        startActivity(intent)
    }
}