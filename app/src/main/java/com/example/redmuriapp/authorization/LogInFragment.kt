package com.example.redmuriapp.authorization

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    private val logInViewModel by lazy { ViewModelProvider(requireActivity())[LogInViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindClickListeners()
        addTextChangeListeners()
    }

    private fun bindClickListeners() {
        binding.btLogIn.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val password = binding.etPassword.text.toString()
            logInViewModel.logIn(firstName, password)
        }
        binding.btHidePassword.setOnClickListener {
            with(binding.etPassword) {
                transformationMethod =
                    if (this.transformationMethod != null)
                        null
                    else
                        PasswordTransformationMethod()
            }
        }
    }

    private fun observeViewModel() {
        logInViewModel.authState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btLogIn.isEnabled = true

            when (it) {
                is Success -> {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
                }
                is Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btLogIn.isEnabled = false
                }
                is Error -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            LogInViewModel.ERROR_EMPTY_FIRST_NAME -> {
                showErrorEditText(binding.etFirstName)
                showErrorToast("All fields must be filled")
            }
            LogInViewModel.ERROR_EMPTY_PASSWORD -> {
                showErrorEditText(binding.etPassword)
                showErrorToast("All fields must be filled")
            }
            LogInViewModel.ERROR_USER_DOES_NOT_EXISTS -> {
                showErrorEditText(binding.etFirstName)
                showErrorToast("User with such first name doesn't exists")
            }
            LogInViewModel.ERROR_WRONG_PASSWORD -> {
                showErrorEditText(binding.etPassword)
                showErrorToast("Wrong password")
            }
        }
    }


    private fun addTextChangeListeners() {
        binding.etFirstName.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etFirstName)
        }
        binding.etPassword.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etPassword)
        }
    }

    private fun hideErrorEditText(editText: EditText) {
        with(editText) {
            if (background != AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.bg_sign_in_field_error
                )
            ) {
                background =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.bg_sign_in_field)
                setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.authorise_user_field
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.authorise_user_field
                    )
                )
            }
        }
    }

    private fun showErrorEditText(editText: EditText) {
        with(editText) {
            if (background != AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.bg_sign_in_field_error
                )
            ) {
                background =
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.bg_sign_in_field_error
                    )
                setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.authorization_error
                    )
                )
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.authorization_error
                    )
                )
            }
        }
    }

    private fun showErrorToast(errorText: String) {
        Toast.makeText(
            requireContext(),
            errorText, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
