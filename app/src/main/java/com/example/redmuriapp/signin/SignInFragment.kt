package com.example.redmuriapp.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.redmuriapp.R


class SignInFragment : Fragment() {

    private val signInViewModel = ViewModelProvider(requireActivity())[SignInViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    companion object {

        fun newInstance() =
            SignInFragment()
    }
}
