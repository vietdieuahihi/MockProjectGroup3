package com.example.client.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.client.MainActivity
import com.example.client.R
import com.example.client.data.constants.ConnectionState
import com.example.client.databinding.FragmentSplashBinding
import java.util.Timer
import java.util.TimerTask

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private var timer: Timer? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer = Timer().apply {
            schedule(object : TimerTask() {
                override fun run() {
                    activity?.let { fa ->
                        (fa as? MainActivity)?.let {
                            if (it.connectionState == ConnectionState.CONNECTED) {
                                timer?.cancel()
                                timer = null

                                it.runOnUiThread {
                                    findNavController().navigate(R.id.action_splashFragment_to_listConversationFragment)
                                }
                            }
                        }
                    }
                }
            }, 2_000L)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}