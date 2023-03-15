package com.example.redmuriapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.redmuriapp.ui.activities.MainActivity
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.itemLogOut.setOnClickListener {
            updateSharedPreferences()
            findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
        }
    }

    private fun updateSharedPreferences() {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(MainActivity.IS_LOGGED, false).apply()
        pref.edit().putString(MainActivity.USER_FIRST_NAME, null).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
