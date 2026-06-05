package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object FairSkin {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.apple_cream)
        listModel.descriptionName = context.getString(R.string.fifteen_min)
        listModel.image = R.drawable.teeth_apples
        listModel.details = context.getString(R.string.fairskin_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.chilled_orange_juice)
        listModel1.descriptionName = context.getString(R.string.seven_min)
        listModel1.image = R.drawable.darkcrcl_oranges
        listModel1.details = context.getString(R.string.fairskin_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.tomato_juice)
        listModel2.descriptionName = context.getString(R.string.five_min)
        listModel2.image = R.drawable.fair_tomatojuice_small
        listModel2.details = context.getString(R.string.fairskin_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.papaya_cucumber_facepack)
        listModel3.descriptionName = context.getString(R.string.twenty_min)
        listModel3.image = R.drawable.fair_papayacucumber
        listModel3.details = context.getString(R.string.fairskin_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.lemon_aloe_vera)
        listModel4.descriptionName = context.getString(R.string.two_min)
        listModel4.image = R.drawable.fair_lemonaloevera
        listModel4.details = context.getString(R.string.fairskin_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.curd_turmeric_instant)
        listModel5.descriptionName = context.getString(R.string.fifteen_min)
        listModel5.image = R.drawable.thighs_turmeric
        listModel5.details = context.getString(R.string.fairskin_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lemon_glycerine)
        listModel6.descriptionName = context.getString(R.string.overnight)
        listModel6.image = R.drawable.fair_glycerinelemon
        listModel6.details = context.getString(R.string.fairskin_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.gram_flour)
        listModel7.descriptionName = context.getString(R.string.till_it_dry)
        listModel7.image = R.drawable.underarms_gramflour
        listModel7.details = context.getString(R.string.fairskin_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.carrot_avocado)
        listModel8.descriptionName = context.getString(R.string.fifteen_min)
        listModel8.image = R.drawable.avocado_split
        listModel8.details = context.getString(R.string.fairskin_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.potato)
        listModel9.descriptionName = context.getString(R.string.twenty_min)
        listModel9.image = R.drawable.darkcrcl_potato
        listModel9.details = context.getString(R.string.fairskin_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.green_tea)
        listModel10.descriptionName = context.getString(R.string.fifteen_min)
        listModel10.image = R.drawable.eyelashes_greentea
        listModel10.details = context.getString(R.string.fairskin_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.jojoba_oil)
        listModel11.descriptionName = context.getString(R.string.overnight)
        listModel11.image = R.drawable.wrinkle_jojoba
        listModel11.details = context.getString(R.string.fairskin_remedy_twelve)
        list.add(listModel11)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_fairskin_main_one)
        listModel.descriptionName = context.getString(R.string.face_fairskin_detail_one)
        listModel.image = R.drawable.fair_balanced
        listModel.details = context.getString(R.string.fairskin_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_fairskin_main_two)
        listModel1.descriptionName = context.getString(R.string.face_fairskin_detail_two)
        listModel1.image = R.drawable.fair_lemonwater
        listModel1.details = context.getString(R.string.fairskin_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_fairskin_main_three)
        listModel2.descriptionName = context.getString(R.string.face_fairskin_detail_three)
        listModel2.image = R.drawable.grey_blacktea_remedy
        listModel2.details = context.getString(R.string.fairskin_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_fairskin_main_four)
        listModel3.descriptionName = context.getString(R.string.face_fairskin_detail_four)
        listModel3.image = R.drawable.fair_darkchocklate
        listModel3.details = context.getString(R.string.fairskin_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_fairskin_main_five)
        listModel4.descriptionName = context.getString(R.string.face_fairskin_detail_five)
        listModel4.image = R.drawable.beautifuleyes_vitaminc
        listModel4.details = context.getString(R.string.fairskin_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_fairskin_main_six)
        listModel5.descriptionName = context.getString(R.string.face_fairskin_detail_six)
        listModel5.image = R.drawable.fair_redyellow
        listModel5.details = context.getString(R.string.fairskin_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_fairskin_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_fairskin_detail_seven)
        listModel6.image = R.drawable.fair_soybean
        listModel6.details = context.getString(R.string.fairskin_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.face_fairskin_main_eight)
        listModel7.descriptionName = context.getString(R.string.face_fairskin_detail_eight)
        listModel7.image = R.drawable.darkhandsfeet_papaya
        listModel7.details = context.getString(R.string.fairskin_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.face_fairskin_main_nine)
        listModel8.descriptionName = context.getString(R.string.face_fairskin_detail_nine)
        listModel8.image = R.drawable.walnut_dandruff
        listModel8.details = context.getString(R.string.fairskin_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.face_fairskin_main_ten)
        listModel9.descriptionName = context.getString(R.string.face_fairskin_detail_ten)
        listModel9.image = R.drawable.beautyeye_fennelseed
        listModel9.details = context.getString(R.string.fairskin_diet_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.face_fairskin_main_eleven)
        listModel10.descriptionName = context.getString(R.string.face_fairskin_detail_eleven)
        listModel10.image = R.drawable.fair_beetrootpurges
        listModel10.details = context.getString(R.string.fairskin_diet_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.face_fairskin_main_twelve)
        listModel11.descriptionName = context.getString(R.string.face_fairskin_detail_twelve)
        listModel11.image = R.drawable.dandruff_water
        listModel11.details = context.getString(R.string.fairskin_diet_twelve)
        list.add(listModel11)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.shoulder_stand_yoga)
        listModel.descriptionName = context.getString(R.string.viparita_karani)
        listModel.image = R.drawable.dandruff_shoulderstand
        listModel.details = context.getString(R.string.fairskin_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.the_cobra_pose)
        listModel1.descriptionName = context.getString(R.string.bhujangasana)
        listModel1.image = R.drawable.acne_bhujangasana
        listModel1.details = context.getString(R.string.fairskin_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.the_triangle_pose)
        listModel2.descriptionName = context.getString(R.string.trikonasana)
        listModel2.image = R.drawable.acne_trikonasana
        listModel2.details = context.getString(R.string.fairskin_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.avoid_sun)
        listModel3.descriptionName = context.getString(R.string.avoid_sun_small)
        listModel3.image = R.drawable.thighs_avoidsun
        listModel3.details = context.getString(R.string.fairskin_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.sleep_properly)
        listModel4.descriptionName = context.getString(R.string.sleep)
        listModel4.image = R.drawable.ic_almond_mask
        listModel4.details = context.getString(R.string.fairskin_exercise_five)
        list.add(listModel4)
        return list
    }
}