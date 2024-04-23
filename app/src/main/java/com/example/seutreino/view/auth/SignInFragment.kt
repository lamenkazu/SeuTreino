package com.example.seutreino.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentSignInBinding
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.isValidEmail
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            if(validation()){
                viewModel.login(
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString()
                )
            }
        }

        observer()

    }

    private fun observer(){
        viewModel.login.observe(viewLifecycleOwner){state->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()
                    binding.signInButton.text = ""
                }

                is UiState.Failure -> {
                    binding.signInButton.text = getString(R.string.sign_in_label)
                    binding.progressBar.hide()
                    toast(state.error!!)

                }

                is UiState.Success -> {
                    binding.signInButton.text = getString(R.string.sign_in_label)
                    binding.progressBar.hide()
                    toast(state.data)

                    findNavController().navigate(R.id.action_signInFragment_to_mainActivity)

                }
            }
        }
    }

    private fun validation(): Boolean{
        var isValid = true

        val email: String = binding.email.text.toString()
        val password: String = binding.password.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            isValid = false
            toast(getString(R.string.empty_field_warning))
        }else{
            if(!email.isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email_warning))
            }

            if(password.length < 8){
                isValid = false
                toast(getString(R.string.invalid_password_warning))
            }
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}