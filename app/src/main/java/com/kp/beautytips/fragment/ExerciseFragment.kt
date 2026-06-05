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
import com.kp.beautytips.data.eyes.*
import com.kp.beautytips.data.face.*
import com.kp.beautytips.data.hair.*
import com.kp.beautytips.data.handsfeet.*
import com.kp.beautytips.data.skin.*

class ExerciseFragment(var categoryName: String) : Fragment() {

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
                rvList.adapter = ListAdapter(FaceWrinkles.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.acne_title) -> {
                rvList.adapter = ListAdapter(Acne.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.fairskin_title) -> {
                rvList.adapter = ListAdapter(FairSkin.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.blackheads_title) -> {
                rvList.adapter = ListAdapter(BlackHeads.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.facialhair_title) -> {
                rvList.adapter =
                    ListAdapter(FacialHairRemoval.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.detan_title) -> {
                rvList.adapter = ListAdapter(DeTanning.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.teethwhitening_title) -> {
                rvList.adapter = ListAdapter(TeethWhitening.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.darklips_title) -> {
                rvList.adapter = ListAdapter(DarkLips.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.blemishes_title) -> {
                rvList.adapter = ListAdapter(Blemishes.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.dandruff_title) -> {
                rvList.adapter = ListAdapter(Dandruff.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.silky_shiny_bouncy_title) -> {
                rvList.adapter = ListAdapter(Silky.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.straighthair_title) -> {
                rvList.adapter = ListAdapter(Straight.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.splitends_title) -> {
                rvList.adapter = ListAdapter(SplitEnds.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.greying_title) -> {
                rvList.adapter = ListAdapter(Greying.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.hairfall_title) -> {
                rvList.adapter = ListAdapter(HairFall.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.frizzy_hair_title) -> {
                rvList.adapter = ListAdapter(FrizzyHair.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.headlice_title) -> {
                rvList.adapter = ListAdapter(HeadLice.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.drydmghair_title) -> {
                rvList.adapter = ListAdapter(DryAndDamagedHair.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.glowingskin_title) -> {
                rvList.adapter = ListAdapter(GlowingSkin.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.unevenskin_title) -> {
                rvList.adapter = ListAdapter(UnevenSkin.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.warts) -> {
                rvList.adapter = ListAdapter(Warts.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.stretchmarks_title) -> {
                rvList.adapter = ListAdapter(StretchMarks.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.pricklyheat_title) -> {
                rvList.adapter = ListAdapter(PricklyHeat.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.body_scrub_title) -> {
                rvList.adapter = ListAdapter(BodyScrubs.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.bodypolish_title) -> {
                rvList.adapter = ListAdapter(BodyPolish.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.darkcircle_title) -> {
                rvList.adapter = ListAdapter(DarkCircles.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.puffyeyes_title) -> {
                rvList.adapter = ListAdapter(PuffyEyes.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.beautifuleyes_title) -> {
                rvList.adapter = ListAdapter(BeautifulEyes.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.sunkeneyes_title) -> {
                rvList.adapter = ListAdapter(SunkenEyes.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.bettereyebrows_title) -> {
                rvList.adapter = ListAdapter(BetterEyeBrows.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.thickerlongereyelashes_title) -> {
                rvList.adapter = ListAdapter(ThickerAndLongerEye.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.waxing_home_title) -> {
                rvList.adapter = ListAdapter(WaxingAtHome.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.dryroughhands_title) -> {
                rvList.adapter = ListAdapter(DryAndRoughHand.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.nailgrowth_title) -> {
                rvList.adapter = ListAdapter(NailGrowth.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.pinkynail_title) -> {
                rvList.adapter = ListAdapter(PinkyShinyNail.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.darkinnerthighs_title) -> {
                rvList.adapter = ListAdapter(DarkInnerThighs.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.darkunderarms_title) -> {
                rvList.adapter = ListAdapter(DarkUnderArms.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.lightening_vagina_title) -> {
                rvList.adapter = ListAdapter(DarkPrivateAreas.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.crackedheel_title) -> {
                rvList.adapter = ListAdapter(CrackedHeels.exercise(requireContext()), getString(R.string.exercise))
            }
            getString(R.string.darkhandfeet_title) -> {
                rvList.adapter = ListAdapter(DarkHandsAndFeet.exercise(requireContext()), getString(R.string.exercise))
            }

        }
    }
}