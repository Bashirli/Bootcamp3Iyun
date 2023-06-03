package com.bashirli.bootcamp3iyun.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bashirli.bootcamp3iyun.databinding.FragmentHomeBinding
import com.bashirli.bootcamp3iyun.model.product.ProductResponse
import com.bashirli.bootcamp3iyun.service.ApiUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get()=_binding!!

    private val args: HomeFragmentArgs by navArgs()
    private val adapter=HomeAdapter()
    private val service=ApiUtil.getService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }


    private fun setup(){
        Toast.makeText(requireContext(),args.userToken,Toast.LENGTH_SHORT).show()

        binding.rvProducts.layoutManager=LinearLayoutManager(requireContext())
        binding.rvProducts.adapter=adapter

        observeData()

    }


    private fun observeData(){
        val limit=10
        val sort="asc"
        val response=service.getProducts(limit, sort)


        response.enqueue(object: Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>,
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        adapter.updateList(it)
                    }
                }else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}