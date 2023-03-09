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
    ): View{
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    fun observeViewModel() {
        signInViewModel.signInState.observe(viewLifecycleOwner) {
            when (it){
                is Success -> {
                    Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
                }
                is Progress -> {
                    Toast.makeText(requireContext(), "In progress...", Toast.LENGTH_SHORT).show()
                }
                is Error -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    fun showError(errorCode: Int){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() =
            SignInFragment()
    }
}
