package com.example.uts_waroengujang.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.uts_waroengujang.R
import com.example.uts_waroengujang.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import io.reactivex.rxjava3.core.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var viewModel:LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.login()


        btnSignIn.setOnClickListener {
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()

            val listUser = viewModel.usersLD.value

            if (listUser != null) {
                var isUserFound = false
                for (user in listUser) {
                    if (user.username == username && user.password == password) {

                        bottomNav.visibility = View.VISIBLE
                        val action = LoginFragmentDirections.actionHomeFragment()
                        Navigation.findNavController(view).navigate(action)
                        isUserFound = true
                        break
                    }
                }
                if (!isUserFound) {
                    Toast.makeText(requireContext(), "Username atau password salah",  Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(requireContext(), "Eror",  Toast.LENGTH_SHORT).show()
            }
        }

    }
}