package com.interview.momatu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.interview.momatu.adapter.PhotoListRecyclerAdapter
import com.interview.momatu.databinding.FragmentPhotolistBinding
import com.interview.momatu.entity.OutCome
import com.interview.momatu.viewmodel.PhotoListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoListFragment : Fragment() {


    private lateinit var adapter: PhotoListRecyclerAdapter
    private var _binding: FragmentPhotolistBinding? = null

    private val viewModel: PhotoListViewModel by viewModels();

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhotolistBinding.inflate(inflater, container, false)
        setupRecyclerView();
        return binding.root

    }

    private fun setupRecyclerView() {

        adapter = PhotoListRecyclerAdapter(ArrayList());
        _binding!!.photoRecyclerview.adapter = adapter;
        _binding!!.photoRecyclerview.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData();

    }

    private fun observeData() {
        viewModel._photolist.observe(viewLifecycleOwner) {
            when (it) {
                is OutCome.Success -> {
                    _binding?.progressBar!!.visibility = View.GONE;

                    adapter.bind(it.data);

                }

                is OutCome.Loading -> _binding?.progressBar!!.visibility = View.VISIBLE;

                is OutCome.Error -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                    _binding?.progressBar!!.visibility = View.GONE;

                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}