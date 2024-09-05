package com.example.client.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.client.MainActivity
import com.example.client.databinding.FragmentSwitchUserBinding
import com.example.client.ui.adapter.UserAdapter
import com.example.client.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwitchUserFragment : Fragment() {

    private var _binding: FragmentSwitchUserBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by viewModels()

    private val userAdapter = UserAdapter() {
        userViewModel.switchUser(it)
        findNavController().popBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel.initService((requireActivity() as MainActivity).messageService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSwitchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUsers()
        userViewModel.getUsers()
        binding.userList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }

    private fun observeUsers() {
        userViewModel.users.observe(viewLifecycleOwner) { users ->
            userAdapter.submitList(users)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}