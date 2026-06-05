package com.kp.beautytips.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.adapter.ListAdapter
import android.content.Context
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.data.TipRepository

class RemedyFragment(var categoryName: String) : Fragment() {

    private lateinit var inflatedView: View
    private lateinit var rvList: RecyclerView

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
        rvList = inflatedView.findViewById<RecyclerView>(R.id.rvList)
        rvList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        if (categoryName == "Favorite Tips") {
            rvList.adapter = ListAdapter(getFavoriteTips(), "Favorite Tips")
            return
        }
        
        val subcategoryKey = TipRepository.getSubCategoryKeyByName(requireContext(), categoryName)
        if (subcategoryKey != null) {
            val list = TipRepository.getRemedies(requireContext(), subcategoryKey)
            rvList.adapter = ListAdapter(list, getString(R.string.remedy))
        } else {
            rvList.adapter = ListAdapter(ArrayList(), getString(R.string.remedy))
        }
    }

    override fun onResume() {
        super.onResume()
        if (categoryName == "Favorite Tips" && ::rvList.isInitialized) {
            rvList.adapter = ListAdapter(getFavoriteTips(), "Favorite Tips")
        }
    }

    private fun getFavoriteTips(): ArrayList<ListModel> {
        val context = requireContext()
        val sharedPreferences = context.getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val allEntries = sharedPreferences.all
        val favTitles = mutableSetOf<String>()
        for ((key, value) in allEntries) {
            if (key.startsWith("fav_") && value is Boolean && value) {
                favTitles.add(key.substring(4))
            }
        }

        val favorites = ArrayList<ListModel>()
        if (favTitles.isEmpty()) {
            return favorites
        }

        val allTips = TipRepository.getAllTips(context)
        val addedTitles = HashSet<String>()
        for (tip in allTips) {
            if (favTitles.contains(tip.title) && !addedTitles.contains(tip.title)) {
                favorites.add(tip)
                addedTitles.add(tip.title)
            }
        }
        return favorites
    }
}