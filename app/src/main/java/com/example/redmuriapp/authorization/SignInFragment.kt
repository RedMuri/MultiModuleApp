package com.example.redmuriapp.authorization

import android.os.Bundle
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
import androidx.preference.PreferenceManager
import com.example.redmuriapp.MainActivity
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

    private val signInViewModel by lazy { ViewModelProvider(requireActivity())[SignInViewModel::class.java] }

    private var errorToast: Toast? = null

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
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            signInViewModel.signIn(firstName, lastName, email)
        }
        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_logInFragment)
        }
    }

    private fun observeViewModel() {
        signInViewModel.authState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btSignIn.isEnabled = true

            when (it) {
                is AuthSuccess -> {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                    updateSharedPreferences(it.firstName)
                }
                is AuthProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btSignIn.isEnabled = false
                }
                is AuthError -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun updateSharedPreferences(firstName: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(MainActivity.IS_LOGGED, true).apply()
        pref.edit().putString(MainActivity.USER_FIRST_NAME, firstName).apply()
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            SignInViewModel.ERROR_EMPTY_FIRST_NAME -> {
                showErrorEditText(binding.etFirstName)
                showErrorToast("All fields must be filled")
            }
            SignInViewModel.ERROR_EMPTY_LAST_NAME -> {
                showErrorEditText(binding.etLastName)
                showErrorToast("All fields must be filled")
            }
            SignInViewModel.ERROR_EMPTY_EMAIL -> {
                showErrorEditText(binding.etEmail)
                showErrorToast("All fields must be filled")
            }
            SignInViewModel.ERROR_NOT_VALID_EMAIL -> {
                showErrorEditText(binding.etEmail)
                showErrorToast("Not valid email")
            }
            SignInViewModel.ERROR_SUCH_USER_EXISTS -> {
                showErrorEditText(binding.etFirstName)
                showErrorToast("User with such first name already exists")
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
                setTextColor(ContextCompat.getColor(
                    requireContext(),
                    R.color.authorization_error
                ))
            }
        }
    }

    private fun showErrorToast(errorText: String) {
        errorToast?.cancel()
        errorToast = Toast.makeText(
            requireContext(),
            errorText, Toast.LENGTH_SHORT
        )
        errorToast?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
