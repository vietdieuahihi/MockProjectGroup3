package com.example.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.client.utils.ConnectionState
import com.example.client.databinding.ActivityMainBinding
import com.example.client.utils.SERVER_PACKAGE_NAME
import com.example.client.utils.SERVER_SERVICE_CLASS_NAME
import com.example.server.IMessageService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    var connectionState = ConnectionState.DISCONNECTED
    lateinit var messageService: IMessageService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

//        setupActionBarWithNavController(navController)
        bindService()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun bindService() {
        connectionState = ConnectionState.LOADING
        val intent = Intent().apply {
            setClassName(SERVER_PACKAGE_NAME, SERVER_SERVICE_CLASS_NAME)
        }
        val bound = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.d(TAG, "Service bind attempt: $bound")
    }

    private val serviceConnection = object : android.content.ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            connectionState = ConnectionState.CONNECTED
            messageService = IMessageService.Stub.asInterface(service)
            Log.d(TAG, "Service connected: $name")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // messageService = null
            connectionState = ConnectionState.DISCONNECTED
            Log.d(TAG, "Service disconnected: $name")
        }
    }
    
    companion object {
        const val TAG = "MainActivity"
    }
}