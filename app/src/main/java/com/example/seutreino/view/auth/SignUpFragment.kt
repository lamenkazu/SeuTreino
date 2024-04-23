package com.example.seutreino.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentSignUpBinding
import com.example.seutreino.model.entities.User
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.isValidEmail
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener{
            if(validation()){
                viewModel.register(
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString(),
                    user = getUserObj()
                )
            }
        }

        observer()

    }

    private fun observer(){

        viewModel.register.observe(viewLifecycleOwner){state->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()
                    binding.registerButton.text = ""
                }

                is UiState.Failure -> {
                    binding.registerButton.text = getString(R.string.sign_up_label)
                    binding.progressBar.hide()
                    toast(state.error!!)

                }

                is UiState.Success -> {
                    binding.registerButton.text = getString(R.string.sign_up_label)
                    binding.progressBar.hide()
                    toast(state.data)

                    findNavController().navigate(R.id.welcomeFragment)


                }


            }
        }

    }

    private fun getUserObj(): User{
        return User(
            id="",
            name=binding.name.text.toString(),
            email=binding.email.text.toString()
        )
    }

    private fun validation(): Boolean{
        var isValid = true

        if( binding.name.text.isNullOrEmpty() ||
            binding.email.text.isNullOrEmpty() ||
            binding.password.text.isNullOrEmpty() ||
            binding.confirmPassword.text.isNullOrEmpty()
            ){
            isValid = false
            toast(getString(R.string.empty_field_warning))
        }else{
            if(!binding.email.text.toString().isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email_warning))
            }

            if(binding.password.text.toString() != binding.confirmPassword.text.toString()){
                isValid = false
                toast(getString(R.string.invalid_password_match))
            }
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}