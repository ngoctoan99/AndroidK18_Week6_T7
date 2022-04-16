package com.example.androidweek6.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidweek6.MainActivity
import com.example.androidweek6.R
import com.example.androidweek6.TopRatedAdapter
import com.example.androidweek6.ViewModel.TopRatedVM
import com.example.androidweek6.databinding.FragmentTopRateBinding

class TopRateFragment : Fragment() {
    lateinit var binding : FragmentTopRateBinding
    lateinit var vm : TopRatedVM
    lateinit var adapter : TopRatedAdapter
    lateinit var home : MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRateBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this).get(TopRatedVM::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        home = activity as MainActivity
        adapter = TopRatedAdapter(TopRatedAdapter.OnClickListener{
            home.replaceFragment(DetailFragment(it))
        })
        setUpMovieList()
        registerMovieList()
        registerErrorList()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.linear_layout->{
                adapter = TopRatedAdapter(TopRatedAdapter.OnClickListener{
                    home.replaceFragment(DetailFragment(it))
                })
                val lm = LinearLayoutManager(context)
                binding.dsToprate.layoutManager = lm
                binding.dsToprate.adapter = adapter
                vm.movieData.observe(this){
                    adapter.submitList(it)
                }
                return true
            }
            R.id.grid_layout->{
                adapter = TopRatedAdapter(TopRatedAdapter.OnClickListener{
                    home.replaceFragment(DetailFragment(it))
                })
                val grid = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false)
                binding.dsToprate.layoutManager = grid
                binding.dsToprate.adapter = adapter
                vm.movieData.observe(this){
                    adapter.submitList(it)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onStart() {
        super.onStart()
        vm.getTopRatedMovie()
    }
    private fun registerErrorList() {
        vm.errEvent.observe(this){
        }
    }
    private fun registerMovieList() {
        vm.movieData.observe(this){
            adapter.submitList(it)
        }
    }
    private fun setUpMovieList() {
        adapter = TopRatedAdapter(TopRatedAdapter.OnClickListener{
            home.replaceFragment(DetailFragment(it))
        })
        val lm = LinearLayoutManager(context)
        binding.dsToprate.layoutManager = lm
        binding.dsToprate.adapter = adapter
    }
}