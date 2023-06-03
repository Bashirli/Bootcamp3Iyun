package com.bashirli.bootcamp3iyun.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bashirli.bootcamp3iyun.databinding.FragmentLoginBinding
import com.bashirli.bootcamp3iyun.model.token.TokenResponse
import com.bashirli.bootcamp3iyun.service.ApiUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
   private var _binding:FragmentLoginBinding?=null
    private val binding get()=_binding!!

    private val service=ApiUtil.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val username=binding.editUsername.text.toString()
        val password=binding.editPass.text.toString()

        if(errorCheck(username,password)){
            return
        }

        service.userLogin(username,password).enqueue(object:Callback<TokenResponse>{
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                response.body()?.let {
                    val token=it.token
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                            token
                        )
                    )
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun errorCheck(username:String,password:String):Boolean{
        if(username.trim().isEmpty()||password.trim().isEmpty()){
            Toast.makeText(requireContext(),"Fill the gaps",Toast.LENGTH_SHORT).show()
            return true
        }

        return false
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}