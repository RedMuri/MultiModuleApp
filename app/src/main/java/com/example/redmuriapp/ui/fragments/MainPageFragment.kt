package com.example.redmuriapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.redmuriapp.R
import com.example.redmuriapp.ui.RedMuriApp
import com.example.redmuriapp.databinding.FragmentMainPageBinding
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.ui.activities.MainActivity
import com.example.redmuriapp.ui.adapters.FlashSaleItemsAdapter
import com.example.redmuriapp.ui.adapters.LatestItemsAdapter
import com.example.redmuriapp.ui.fragments.MainPageFragmentDirections.Companion.actionMainFragmentToDetailFragment
import com.example.redmuriapp.ui.states.MainError
import com.example.redmuriapp.ui.states.MainProgress
import com.example.redmuriapp.ui.states.MainSuccess
import com.example.redmuriapp.ui.view_models.MainPageViewModel
import com.example.redmuriapp.ui.view_models.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class MainPageFragment : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = _binding ?: throw RuntimeException("FragmentMainPageBinding == null")

    private var errorToast: Toast? = null

    private val adapterLatestItems by lazy {
        LatestItemsAdapter()
    }
    private val adapterFlashSaleItems by lazy {
        FlashSaleItemsAdapter()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainPageViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainPageViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as RedMuriApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        getBothItems()
        observeViewModel()
        setupRecyclerViews()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            setUserData()
            getBothItems()
            observeViewModel()
            setupRecyclerViews()
        }
        adapterLatestItems.onItemClickListener = { latestItem ->
            findNavController().navigate(
                actionMainFragmentToDetailFragment(
                    latestItem
                )
            )
        }
        adapterFlashSaleItems.onItemClickListener = { flashSaleItem ->
            findNavController().navigate(
                actionMainFragmentToDetailFragment(
                    LatestItem(
                        flashSaleItem.category,
                        flashSaleItem.name,
                        flashSaleItem.price,
                        flashSaleItem.image_url
                    )
                )
            )
        }
    }

    private fun setupRecyclerViews() {
        binding.rvLatest.adapter = adapterLatestItems
        binding.rvFlashSale.adapter = adapterFlashSaleItems
    }

    private fun getBothItems() {
        mainPageViewModel.getBothItems()
    }

    private fun uploadProfileImage(imageUrl: String) {
        Picasso.get().load(imageUrl)
            .into(binding.ivProfileImage)
    }

    private fun observeViewModel() {
        with(mainPageViewModel) {
            userData.observe(viewLifecycleOwner) {
                binding.tvLocation.text = it.location
                if (it.profileImage != null) {
                    uploadProfileImage(it.profileImage!!)
                } else
                    binding.ivProfileImage.setImageResource(R.drawable.default_profile_image)
            }
            bothItems.observe(viewLifecycleOwner) {
                adapterLatestItems.submitList(it.first)
                adapterFlashSaleItems.submitList(it.second)
            }
            mainPageState.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
                when (it) {
                    is MainSuccess -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is MainProgress -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainError -> {
                        showError(it.errorCode)
                    }
                }
            }
        }
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            MainPageViewModel.ERROR_USER_NOT_FOUND -> {
                showErrorToast("Some error occurred. Try logging in again")
            }
            MainPageViewModel.ERROR_LOADING_ITEMS -> {
                showErrorToast("Something went wrong. Check your internet connection")
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
