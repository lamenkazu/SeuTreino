package com.example.seutreino

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.seutreino.databinding.ActivityMainBinding
import com.example.seutreino.exercise.ExerciseListingFragment
import com.example.seutreino.workout.WorkoutListingFragment
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(WorkoutListingFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workout -> replaceFragment(WorkoutListingFragment())
                R.id.exercise -> replaceFragment(ExerciseListingFragment())
//                R.id.settings -> replaceFragment(Settings())
                else -> {}
            }
            true
        }

        val user: MutableMap<String, Any> = HashMap()
        user["first"]= "Erick Etiene"
        user["last"]= "Simião Ferreira"
        user["born"]= 1999

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("FirebaseTag", "DocumentSnapshot adicionado com ID:" + it.id)
            }
            .addOnFailureListener{
                Log.d("FirebaseTag", "Erro adicionando documento:$it")

            }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}