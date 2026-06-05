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
import com.kp.beautytips.data.eyes.*
import com.kp.beautytips.data.face.*
import com.kp.beautytips.data.hair.*
import com.kp.beautytips.data.handsfeet.*
import com.kp.beautytips.data.skin.*

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
        when (categoryName) {
            getString(R.string.wrinkles_title) -> {
                rvList.adapter = ListAdapter(FaceWrinkles.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.acne_title) -> {
                rvList.adapter = ListAdapter(Acne.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.fairskin_title) -> {
                rvList.adapter = ListAdapter(FairSkin.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.blackheads_title) -> {
                rvList.adapter = ListAdapter(BlackHeads.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.facialhair_title) -> {
                rvList.adapter =
                    ListAdapter(FacialHairRemoval.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.detan_title) -> {
                rvList.adapter = ListAdapter(DeTanning.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.teethwhitening_title) -> {
                rvList.adapter =
                    ListAdapter(TeethWhitening.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.darklips_title) -> {
                rvList.adapter = ListAdapter(DarkLips.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.blemishes_title) -> {
                rvList.adapter = ListAdapter(Blemishes.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.dandruff_title) -> {
                rvList.adapter = ListAdapter(Dandruff.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.silky_shiny_bouncy_title) -> {
                rvList.adapter = ListAdapter(Silky.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.straighthair_title) -> {
                rvList.adapter = ListAdapter(Straight.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.splitends_title) -> {
                rvList.adapter = ListAdapter(SplitEnds.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.greying_title) -> {
                rvList.adapter = ListAdapter(Greying.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.hairfall_title) -> {
                rvList.adapter = ListAdapter(HairFall.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.frizzy_hair_title) -> {
                rvList.adapter = ListAdapter(FrizzyHair.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.headlice_title) -> {
                rvList.adapter = ListAdapter(HeadLice.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.drydmghair_title) -> {
                rvList.adapter = ListAdapter(DryAndDamagedHair.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.glowingskin_title) -> {
                rvList.adapter = ListAdapter(GlowingSkin.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.unevenskin_title) -> {
                rvList.adapter = ListAdapter(UnevenSkin.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.warts) -> {
                rvList.adapter = ListAdapter(Warts.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.stretchmarks_title) -> {
                rvList.adapter = ListAdapter(StretchMarks.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.pricklyheat_title) -> {
                rvList.adapter = ListAdapter(PricklyHeat.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.body_scrub_title) -> {
                rvList.adapter = ListAdapter(BodyScrubs.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.bodypolish_title) -> {
                rvList.adapter = ListAdapter(BodyPolish.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.darkcircle_title) -> {
                rvList.adapter = ListAdapter(DarkCircles.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.puffyeyes_title) -> {
                rvList.adapter = ListAdapter(PuffyEyes.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.beautifuleyes_title) -> {
                rvList.adapter = ListAdapter(BeautifulEyes.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.sunkeneyes_title) -> {
                rvList.adapter = ListAdapter(SunkenEyes.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.bettereyebrows_title) -> {
                rvList.adapter = ListAdapter(BetterEyeBrows.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.thickerlongereyelashes_title) -> {
                rvList.adapter = ListAdapter(ThickerAndLongerEye.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.waxing_home_title) -> {
                rvList.adapter = ListAdapter(WaxingAtHome.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.dryroughhands_title) -> {
                rvList.adapter = ListAdapter(DryAndRoughHand.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.nailgrowth_title) -> {
                rvList.adapter = ListAdapter(NailGrowth.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.pinkynail_title) -> {
                rvList.adapter = ListAdapter(PinkyShinyNail.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.darkinnerthighs_title) -> {
                rvList.adapter = ListAdapter(DarkInnerThighs.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.darkunderarms_title) -> {
                rvList.adapter = ListAdapter(DarkUnderArms.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.lightening_vagina_title) -> {
                rvList.adapter = ListAdapter(DarkPrivateAreas.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.crackedheel_title) -> {
                rvList.adapter = ListAdapter(CrackedHeels.remedy(requireContext()), getString(R.string.remedy))
            }
            getString(R.string.darkhandfeet_title) -> {
                rvList.adapter = ListAdapter(DarkHandsAndFeet.remedy(requireContext()), getString(R.string.remedy))
            }
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

        val allTips = ArrayList<ListModel>()
        
        // Face
        allTips.addAll(FaceWrinkles.remedy(context))
        allTips.addAll(FaceWrinkles.diet(context))
        allTips.addAll(FaceWrinkles.exercise(context))
        allTips.addAll(Acne.remedy(context))
        allTips.addAll(Acne.diet(context))
        allTips.addAll(Acne.exercise(context))
        allTips.addAll(FairSkin.remedy(context))
        allTips.addAll(FairSkin.diet(context))
        allTips.addAll(FairSkin.exercise(context))
        allTips.addAll(BlackHeads.remedy(context))
        allTips.addAll(BlackHeads.diet(context))
        allTips.addAll(BlackHeads.exercise(context))
        allTips.addAll(FacialHairRemoval.remedy(context))
        allTips.addAll(FacialHairRemoval.diet(context))
        allTips.addAll(FacialHairRemoval.exercise(context))
        allTips.addAll(DeTanning.remedy(context))
        allTips.addAll(DeTanning.diet(context))
        allTips.addAll(DeTanning.exercise(context))
        allTips.addAll(TeethWhitening.remedy(context))
        allTips.addAll(TeethWhitening.diet(context))
        allTips.addAll(TeethWhitening.exercise(context))
        allTips.addAll(DarkLips.remedy(context))
        allTips.addAll(DarkLips.diet(context))
        allTips.addAll(DarkLips.exercise(context))
        allTips.addAll(Blemishes.remedy(context))
        allTips.addAll(Blemishes.diet(context))
        allTips.addAll(Blemishes.exercise(context))

        // Hair
        allTips.addAll(Dandruff.remedy(context))
        allTips.addAll(Dandruff.diet(context))
        allTips.addAll(Dandruff.exercise(context))
        allTips.addAll(Silky.remedy(context))
        allTips.addAll(Silky.diet(context))
        allTips.addAll(Silky.exercise(context))
        allTips.addAll(Straight.remedy(context))
        allTips.addAll(Straight.diet(context))
        allTips.addAll(Straight.exercise(context))
        allTips.addAll(SplitEnds.remedy(context))
        allTips.addAll(SplitEnds.diet(context))
        allTips.addAll(SplitEnds.exercise(context))
        allTips.addAll(Greying.remedy(context))
        allTips.addAll(Greying.diet(context))
        allTips.addAll(Greying.exercise(context))
        allTips.addAll(HairFall.remedy(context))
        allTips.addAll(HairFall.diet(context))
        allTips.addAll(HairFall.exercise(context))
        allTips.addAll(FrizzyHair.remedy(context))
        allTips.addAll(FrizzyHair.diet(context))
        allTips.addAll(FrizzyHair.exercise(context))
        allTips.addAll(HeadLice.remedy(context))
        allTips.addAll(HeadLice.diet(context))
        allTips.addAll(HeadLice.exercise(context))
        allTips.addAll(DryAndDamagedHair.remedy(context))
        allTips.addAll(DryAndDamagedHair.diet(context))
        allTips.addAll(DryAndDamagedHair.exercise(context))

        // Skin
        allTips.addAll(GlowingSkin.remedy(context))
        allTips.addAll(GlowingSkin.diet(context))
        allTips.addAll(GlowingSkin.exercise(context))
        allTips.addAll(UnevenSkin.remedy(context))
        allTips.addAll(UnevenSkin.diet(context))
        allTips.addAll(UnevenSkin.exercise(context))
        allTips.addAll(Warts.remedy(context))
        allTips.addAll(Warts.diet(context))
        allTips.addAll(Warts.exercise(context))
        allTips.addAll(StretchMarks.remedy(context))
        allTips.addAll(StretchMarks.diet(context))
        allTips.addAll(StretchMarks.exercise(context))
        allTips.addAll(PricklyHeat.remedy(context))
        allTips.addAll(PricklyHeat.diet(context))
        allTips.addAll(PricklyHeat.exercise(context))
        allTips.addAll(BodyScrubs.remedy(context))
        allTips.addAll(BodyScrubs.diet(context))
        allTips.addAll(BodyScrubs.exercise(context))
        allTips.addAll(BodyPolish.remedy(context))
        allTips.addAll(BodyPolish.diet(context))
        allTips.addAll(BodyPolish.exercise(context))

        // Eyes
        allTips.addAll(DarkCircles.remedy(context))
        allTips.addAll(DarkCircles.diet(context))
        allTips.addAll(DarkCircles.exercise(context))
        allTips.addAll(PuffyEyes.remedy(context))
        allTips.addAll(PuffyEyes.diet(context))
        allTips.addAll(PuffyEyes.exercise(context))
        allTips.addAll(BeautifulEyes.remedy(context))
        allTips.addAll(BeautifulEyes.diet(context))
        allTips.addAll(BeautifulEyes.exercise(context))
        allTips.addAll(SunkenEyes.remedy(context))
        allTips.addAll(SunkenEyes.diet(context))
        allTips.addAll(SunkenEyes.exercise(context))
        allTips.addAll(BetterEyeBrows.remedy(context))
        allTips.addAll(BetterEyeBrows.diet(context))
        allTips.addAll(BetterEyeBrows.exercise(context))
        allTips.addAll(ThickerAndLongerEye.remedy(context))
        allTips.addAll(ThickerAndLongerEye.diet(context))
        allTips.addAll(ThickerAndLongerEye.exercise(context))

        // Hands & Feet
        allTips.addAll(WaxingAtHome.remedy(context))
        allTips.addAll(WaxingAtHome.diet(context))
        allTips.addAll(WaxingAtHome.exercise(context))
        allTips.addAll(DryAndRoughHand.remedy(context))
        allTips.addAll(DryAndRoughHand.diet(context))
        allTips.addAll(DryAndRoughHand.exercise(context))
        allTips.addAll(NailGrowth.remedy(context))
        allTips.addAll(NailGrowth.diet(context))
        allTips.addAll(NailGrowth.exercise(context))
        allTips.addAll(PinkyShinyNail.remedy(context))
        allTips.addAll(PinkyShinyNail.diet(context))
        allTips.addAll(PinkyShinyNail.exercise(context))
        allTips.addAll(DarkInnerThighs.remedy(context))
        allTips.addAll(DarkInnerThighs.diet(context))
        allTips.addAll(DarkInnerThighs.exercise(context))
        allTips.addAll(DarkUnderArms.remedy(context))
        allTips.addAll(DarkUnderArms.diet(context))
        allTips.addAll(DarkUnderArms.exercise(context))
        allTips.addAll(DarkPrivateAreas.remedy(context))
        allTips.addAll(DarkPrivateAreas.diet(context))
        allTips.addAll(DarkPrivateAreas.exercise(context))
        allTips.addAll(CrackedHeels.remedy(context))
        allTips.addAll(CrackedHeels.diet(context))
        allTips.addAll(CrackedHeels.exercise(context))
        allTips.addAll(DarkHandsAndFeet.remedy(context))
        allTips.addAll(DarkHandsAndFeet.diet(context))
        allTips.addAll(DarkHandsAndFeet.exercise(context))

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