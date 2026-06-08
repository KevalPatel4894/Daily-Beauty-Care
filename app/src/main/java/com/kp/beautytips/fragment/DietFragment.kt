package com.kp.beautytips.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.ListActivity
import com.kp.beautytips.adapter.ListAdapter
import com.kp.beautytips.data.TipRepository

class DietFragment : Fragment() {
    private var categoryName: String = ""
    private lateinit var inflatedView: View

    companion object {
        fun newInstance(categoryName: String): DietFragment {
            val fragment = DietFragment()
            val args = Bundle()
            args.putString("categoryName", categoryName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryName = arguments?.getString("categoryName") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_main, container, false)
        init(inflatedView)
        return inflatedView
    }

    private fun init(inflatedView: View) {
        val rvList = inflatedView.findViewById<RecyclerView>(R.id.rvList)
        rvList!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        
        val subcategoryKey = TipRepository.getSubCategoryKeyByName(requireContext(), categoryName)
        if (subcategoryKey != null) {
            val list = TipRepository.getDiets(requireContext(), subcategoryKey)
            rvList.adapter = ListAdapter(list, getString(R.string.diet))
        } else {
            rvList.adapter = ListAdapter(ArrayList(), getString(R.string.diet))
        }
    }
}