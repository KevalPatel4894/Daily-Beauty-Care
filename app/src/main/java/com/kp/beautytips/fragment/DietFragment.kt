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
import com.kp.beautytips.data.eyes.*
import com.kp.beautytips.data.face.*
import com.kp.beautytips.data.hair.*
import com.kp.beautytips.data.handsfeet.*
import com.kp.beautytips.data.skin.*

class DietFragment(var categoryName: String) : Fragment() {

    private lateinit var inflatedView: View

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
        when (categoryName) {
            getString(R.string.wrinkles_title) -> {
                rvList.adapter = ListAdapter(FaceWrinkles.diet(requireContext()),getString(R.string.diet))
            }
            getString(R.string.acne_title) -> {
                rvList.adapter = ListAdapter(Acne.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.fairskin_title) -> {
                rvList.adapter = ListAdapter(FairSkin.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.blackheads_title) -> {
                rvList.adapter = ListAdapter(BlackHeads.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.facialhair_title) -> {
                rvList.adapter = ListAdapter(FacialHairRemoval.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.detan_title) -> {
                rvList.adapter = ListAdapter(DeTanning.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.teethwhitening_title) -> {
                rvList.adapter = ListAdapter(TeethWhitening.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.darklips_title) -> {
                rvList.adapter = ListAdapter(DarkLips.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.blemishes_title) -> {
                rvList.adapter = ListAdapter(Blemishes.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.dandruff_title) -> {
                rvList.adapter = ListAdapter(Dandruff.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.silky_shiny_bouncy_title) -> {
                rvList.adapter = ListAdapter(Silky.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.straighthair_title) -> {
                rvList.adapter = ListAdapter(Straight.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.splitends_title) -> {
                rvList.adapter = ListAdapter(SplitEnds.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.greying_title) -> {
                rvList.adapter = ListAdapter(Greying.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.hairfall_title) -> {
                rvList.adapter = ListAdapter(HairFall.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.frizzy_hair_title) -> {
                rvList.adapter = ListAdapter(FrizzyHair.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.headlice_title) -> {
                rvList.adapter = ListAdapter(HeadLice.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.drydmghair_title) -> {
                rvList.adapter = ListAdapter(DryAndDamagedHair.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.glowingskin_title) -> {
                rvList.adapter = ListAdapter(GlowingSkin.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.unevenskin_title) -> {
                rvList.adapter = ListAdapter(UnevenSkin.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.warts) -> {
                rvList.adapter = ListAdapter(Warts.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.stretchmarks_title) -> {
                rvList.adapter = ListAdapter(StretchMarks.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.pricklyheat_title) -> {
                rvList.adapter = ListAdapter(PricklyHeat.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.body_scrub_title) -> {
                rvList.adapter = ListAdapter(BodyScrubs.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.bodypolish_title) -> {
                rvList.adapter = ListAdapter(BodyPolish.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.darkcircle_title) -> {
                rvList.adapter = ListAdapter(DarkCircles.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.puffyeyes_title) -> {
                rvList.adapter = ListAdapter(PuffyEyes.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.beautifuleyes_title) -> {
                rvList.adapter = ListAdapter(BeautifulEyes.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.sunkeneyes_title) -> {
                rvList.adapter = ListAdapter(SunkenEyes.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.bettereyebrows_title) -> {
                rvList.adapter = ListAdapter(BetterEyeBrows.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.thickerlongereyelashes_title) -> {
                rvList.adapter = ListAdapter(ThickerAndLongerEye.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.waxing_home_title) -> {
                rvList.adapter = ListAdapter(WaxingAtHome.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.dryroughhands_title) -> {
                rvList.adapter = ListAdapter(DryAndRoughHand.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.nailgrowth_title) -> {
                rvList.adapter = ListAdapter(NailGrowth.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.pinkynail_title) -> {
                rvList.adapter = ListAdapter(PinkyShinyNail.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.darkinnerthighs_title) -> {
                rvList.adapter = ListAdapter(DarkInnerThighs.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.darkunderarms_title) -> {
                rvList.adapter = ListAdapter(DarkUnderArms.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.lightening_vagina_title) -> {
                rvList.adapter = ListAdapter(DarkPrivateAreas.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.crackedheel_title) -> {
                rvList.adapter = ListAdapter(CrackedHeels.diet(requireContext()), getString(R.string.diet))
            }
            getString(R.string.darkhandfeet_title) -> {
                rvList.adapter = ListAdapter(DarkHandsAndFeet.diet(requireContext()), getString(R.string.diet))
            }
        }
    }
}