package com.example.redmuriapp.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

    private val signInViewModel = ViewModelProvider(requireActivity())[SignInViewModel::class.java]

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
            1 -> binding.etFirstName.error = "This field is required"
            2 -> binding.etLastName.error = "This field is required"
            3 -> binding.etEmail.error = "This field is required"
            4 -> binding.etEmail.error = "Not valid email"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
