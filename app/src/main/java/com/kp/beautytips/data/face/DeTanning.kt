package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object DeTanning {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.tomato)
        listModel.descriptionName = context.getString(R.string.fifteen_min)
        listModel.image = R.drawable.tomato_dandruff
        listModel.details = context.getString(R.string.detanning_tomatoes_remedy)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.aloe_vera_gel)
        listModel1.descriptionName = context.getString(R.string.twenty_min)
        listModel1.image = R.drawable.darkcrcl_aloeveragel
        listModel1.details = context.getString(R.string.detanning_aloevera_remedy)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.lemon_sugar_crysal_scrub)
        listModel2.descriptionName = context.getString(R.string.twenty_min)
        listModel2.image = R.drawable.lemon_sugar
        listModel2.details = context.getString(R.string.detanning_lemonsugar_remedy)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.potato)
        listModel3.descriptionName = context.getString(R.string.thirty_min)
        listModel3.image = R.drawable.darkcrcl_potato
        listModel3.details = context.getString(R.string.detanning_potato_remedy)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.thick_cucumber_milk_mixture)
        listModel4.descriptionName = context.getString(R.string.twenty_min)
        listModel4.image = R.drawable.cucumber_milk
        listModel4.details = context.getString(R.string.detanning_thick_cucumber_milk_mixture_remedy)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.yogurt_mask)
        listModel5.descriptionName = context.getString(R.string.thirty_min)
        listModel5.image = R.drawable.dryhands_yogurt
        listModel5.details = context.getString(R.string.detanning_yogurt_remedy)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.papaya)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.papaya
        listModel6.details = context.getString(R.string.detanning_papaya_remedy)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.gramflour_curd_paste)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.gramflour_curd
        listModel7.details = context.getString(R.string.detanning_gramflour_curd_paste_remedy)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.redlentil_aloevera_tomato)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.aloevera_split
        listModel8.details = context.getString(R.string.detanning_redlentil_aloevera_tomato_remedy)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.oatmeal_buttermilk)
        listModel9.descriptionName = context.getString(R.string.thirty_min)
        listModel9.image = R.drawable.dryhands_oatmeal
        listModel9.details = context.getString(R.string.detanning_oatmeal_buttermilk_remedy)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.orange_juice)
        listModel10.descriptionName = context.getString(R.string.thirty_min)
        listModel10.image = R.drawable.orangejuice_turmeric
        listModel10.details = context.getString(R.string.detanning_orange_juice_remedy)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.strawberry_cream)
        listModel11.descriptionName = context.getString(R.string.thirty_min)
        listModel11.image = R.drawable.puffy_strawberries
        listModel11.details = context.getString(R.string.detanning_strawberry_cream_remedy)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.sandalwood_coconut_water)
        listModel12.descriptionName = context.getString(R.string.thirty_min)
        listModel12.image = R.drawable.prickly_sandalwoodpdr
        listModel12.details = context.getString(R.string.detanning_sandalwood_coconut_water_remedy)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.turmeric_milk_honey)
        listModel13.descriptionName = context.getString(R.string.till_it_dry)
        listModel13.image = R.drawable.thighs_turmeric
        listModel13.details = context.getString(R.string.detanning_turmeric_milk_honey_remedy)
        list.add(listModel13)
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