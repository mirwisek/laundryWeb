package com.ecommerce.laundryweb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ecommerce.laundryweb.MainActivity
import com.ecommerce.laundryweb.R
import com.ecommerce.laundryweb.databinding.FragmentHomeBinding
import com.snorlux.app.home.HomeListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnDrawer.setOnClickListener {
            (requireActivity() as MainActivity).drawerToggle()
        }

        val homeList = listOf(
            HomeListItem(R.mipmap.ic_order, "Orders"),
            HomeListItem(R.mipmap.ic_workflow, "How it works"),
            HomeListItem(R.mipmap.ic_chat, "About Us"),
            HomeListItem(R.mipmap.ic_contact, "Contact Us")
        )

        homeAdapter = HomeListAdapter { homeSelectedItem ->
            (requireActivity() as MainActivity).listItemClicked(homeSelectedItem.title)
        }
        binding.rvHome.adapter = homeAdapter
        homeAdapter.updateList(homeList)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}