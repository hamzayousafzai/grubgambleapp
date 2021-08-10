package com.example.grubgamble

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userPts = view.findViewById<TextView>(R.id.user_points)
        pullProfile(userName, userPts)
        return view
    }

    private fun pullProfile(userName: TextView, userPts: TextView){

        val db = Firebase.firestore
        val uid = FirebaseAuth.getInstance().currentUser.uid
        db.collection("Users").document(uid)
            .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        userName.text= document.getData()!!.get("username").toString()
                        userPts.text = document.getData()!!.get("points").toString() + " points"
                        Log.d("tag", "DocumentSnapshot data: ${document.data}")
                    } else {
                        Log.d("tag", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("tag", "get failed with ", exception)
                }
    }
}