package com.example.hizligeliyoproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hizligeliyoproject.databinding.FragmentHomeBinding
import com.example.hizligeliyoproject.network.model.Product
import com.example.hizligeliyoproject.ui.activity.CategoriesActivity
import com.example.hizligeliyoproject.ui.adapter.HomeProjectAdapter
import com.example.hizligeliyoproject.ui.viewmodel.HomeViewModel


class HomeFragment: Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    protected fun onBindViewModel() {
        homeViewModel.productsLiveData.observe(viewLifecycleOwner, {
            Log.e("ui", "adapter is run")
            setListAdapter(it)
        })

        binding.searchLayout.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                                                androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    homeViewModel.setSearchQuery(it)
                    homeViewModel.productsLiveData.observe(viewLifecycleOwner, {
                        setListAdapter(it)
                    })
                }
                return true
            }
        })

        binding.buttonBarLayout.filtreButton.setOnClickListener {
            val intent = Intent(context, CategoriesActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    private fun setListAdapter(products: List<Product>){
      val adapter = HomeProjectAdapter(products)
      binding.recyclerviewFragmentHome.layoutManager = GridLayoutManager(activity, 2)
      binding.recyclerviewFragmentHome.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
