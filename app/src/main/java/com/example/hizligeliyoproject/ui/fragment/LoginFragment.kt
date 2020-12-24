package com.example.hizligeliyoproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hizligeliyoproject.databinding.FragmentLoginBinding
import com.example.hizligeliyoproject.ui.activity.SecondActivity
import com.example.hizligeliyoproject.ui.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    protected fun onBindViewModel(){

        binding.loginFragmentLoginButton.setOnClickListener {
            val email:String = binding.loginFragmentEmailaddress.text.toString()
            val password:String = binding.loginFragmentPassword.text.toString()
            viewModel.launchMainActivity(email, password)
        }

        viewModel.liveBooleanNextActivity.observe(viewLifecycleOwner, {
            if (it){
                val intent = Intent(context, SecondActivity::class.java)
                startActivity(intent)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}