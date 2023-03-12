package com.example.redmuriapp.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.redmuriapp.MainActivity
import com.example.redmuriapp.databinding.FragmentMainPageBinding


class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = _binding ?: throw RuntimeException("FragmentMainPageBinding == null")

    private val mainPageViewModel by lazy {
        ViewModelProvider(requireActivity())[MainPageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainPageViewModel.userData.observe(viewLifecycleOwner){
            binding.tvLocation.text = it.location
        }
    }

    private fun setUserData() {
        val firstName = getFirstNameFromPref()
        mainPageViewModel.getUser(firstName)
    }

    private fun getFirstNameFromPref(): String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return pref.getString(MainActivity.USER_FIRST_NAME, null)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
