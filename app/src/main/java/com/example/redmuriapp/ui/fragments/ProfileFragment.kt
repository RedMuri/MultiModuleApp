package com.example.redmuriapp.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.redmuriapp.R
import com.example.redmuriapp.databinding.FragmentProfileBinding
import com.example.redmuriapp.ui.RedMuriApp
import com.example.redmuriapp.ui.activities.MainActivity
import com.example.redmuriapp.ui.view_models.ProfileViewModel
import com.example.redmuriapp.ui.view_models.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val profileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as RedMuriApp).component
    }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestLauncher: ActivityResultLauncher<String>

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
        observeViewModel()
        bindClickListeners()
        registerActivityForResultPickImage()
        registerActivityForResultRequestPermission()
    }

    private fun registerActivityForResultPickImage() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        uploadProfileImage(it.toString())
                        profileViewModel.editUserImage(it.toString())
                    }
                }
            }
    }

    private fun registerActivityForResultRequestPermission() {
        requestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                resultLauncher.launch(intent)
            }
        }
    }

    private fun observeViewModel() {
        profileViewModel.userData.observe(viewLifecycleOwner) {
            binding.tvUserName.text = it.firstName
            if (it.profileImage != null) {
                uploadProfileImage(it.profileImage!!)
            } else
                binding.ivProfileImage.setImageResource(R.drawable.default_profile_image)
        }
    }

    private fun bindClickListeners() {
        binding.itemLogOut.setOnClickListener {
            updateSharedPreferences()
            findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
        }
        binding.tvChangePhoto.setOnClickListener {
            storagePermissionRequest()
        }
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun storagePermissionRequest() {
        requestLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun uploadProfileImage(imageUrl: String) {
        Picasso.get().load(imageUrl)
            .into(binding.ivProfileImage)
    }


    private fun updateSharedPreferences() {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(MainActivity.IS_LOGGED, false).apply()
        pref.edit().putString(MainActivity.USER_FIRST_NAME, null).apply()
    }

    private fun setUserData() {
        val firstName = getFirstNameFromPref()
        profileViewModel.getUser(firstName)
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
