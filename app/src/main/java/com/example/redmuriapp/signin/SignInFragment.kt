package com.example.redmuriapp.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

    private val signInViewModel by lazy { ViewModelProvider(requireActivity())[SignInViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindClickListeners()
        addTextChangeListeners()
    }

    private fun bindClickListeners() {
        binding.btSignIn.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            signInViewModel.signIn(firstName, lastName, email)
        }
    }

    private fun observeViewModel() {
        signInViewModel.signInState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btSignIn.isEnabled = true

            when (it) {
                is Success -> {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                }
                is Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btSignIn.isEnabled = false
                    Toast.makeText(requireContext(), "In progress...", Toast.LENGTH_SHORT).show()
                }
                is Error -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            1 -> {
                showErrorEditText(binding.etFirstName)
                showErrorToast("All fields must be filled")
            }
            2 -> {
                showErrorEditText(binding.etLastName)
                showErrorToast("All fields must be filled")
            }
            3 -> {
                showErrorEditText(binding.etEmail)
                showErrorToast("All fields must be filled")
            }
            4 -> {
                showErrorEditText(binding.etEmail)
                showErrorToast("Not valid email")
            }
        }
    }


    private fun addTextChangeListeners() {
        binding.etFirstName.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etFirstName)
        }
        binding.etLastName.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etLastName)
        }
        binding.etEmail.doOnTextChanged { _, _, _, _ ->
            hideErrorEditText(binding.etEmail)
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
