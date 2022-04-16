package com.example.androidweek6.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidweek6.MainActivity
import com.example.androidweek6.NowPlayAdapter
import com.example.androidweek6.R
import com.example.androidweek6.ViewModel.NowPlayVM
import com.example.androidweek6.databinding.FragmentNowPlayBinding

class NowPlayFragment : Fragment() {
    lateinit var  binding : FragmentNowPlayBinding
    lateinit var vm : NowPlayVM
    lateinit var  adapter : NowPlayAdapter
    lateinit var home : MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentNowPlayBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this).get(NowPlayVM::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        home = activity as MainActivity
        adapter = NowPlayAdapter(NowPlayAdapter.OnClickListener{
            home.replaceFragment(DetailFragment(it))
        })
        setUpMovieList()
        registerMovieList()
        registerErrorList()
    }
    override fun onStart() {
        super.onStart()
        vm.getNowPlaying()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.linear_layout->{
                adapter = NowPlayAdapter(NowPlayAdapter.OnClickListener{
                    home.replaceFragment(DetailFragment(it))
                })
                val lm = LinearLayoutManager(context)
                binding.dsNowplay.layoutManager = lm
                binding.dsNowplay.adapter = adapter
                vm.movieData.observe(this){
                    adapter.submitList(it)
                }
                return true
            }
            R.id.grid_layout->{
                adapter = NowPlayAdapter(NowPlayAdapter.OnClickListener{
                    home.replaceFragment(DetailFragment(it))
                })
                val grid = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false)
                binding.dsNowplay.layoutManager = grid
                binding.dsNowplay.adapter = adapter
                vm.movieData.observe(this){
                    adapter.submitList(it)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        adapter = NowPlayAdapter(NowPlayAdapter.OnClickListener{
            home.replaceFragment(DetailFragment(it))
        })
        val lm = LinearLayoutManager(context)
        binding.dsNowplay.layoutManager = lm
        binding.dsNowplay.adapter = adapter
    }
}