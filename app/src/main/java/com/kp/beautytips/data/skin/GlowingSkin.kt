package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object GlowingSkin {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.turmeric)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.thighs_turmeric
        listModel.details = context.getString(R.string.glowingskin_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.coconut_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.coconutoil_hairfall_remedy
        listModel1.details = context.getString(R.string.glowingskin_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.aloe_vera)
        listModel2.descriptionName = context.getString(R.string.twenty_min)
        listModel2.image = R.drawable.aloevera_split
        listModel2.details = context.getString(R.string.glowingskin_remedy_thirteen)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.baking_soda)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.baking_soda_dandruff
        listModel3.details = context.getString(R.string.glowingskin_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.lemon)
        listModel4.descriptionName = context.getString(R.string.ten_min)
        listModel4.image = R.drawable.eyebrows_lemon
        listModel4.details = context.getString(R.string.glowingskin_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.papaya)
        listModel5.descriptionName = context.getString(R.string.twenty_min)
        listModel5.image = R.drawable.darkhandsfeet_papaya
        listModel5.details = context.getString(R.string.glowingskin_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.cucumber)
        listModel6.descriptionName = context.getString(R.string.five_min)
        listModel6.image = R.drawable.darkcrcl_cucumber
        listModel6.details = context.getString(R.string.glowingskin_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.honey)
        listModel7.descriptionName = context.getString(R.string.five_min)
        listModel7.image = R.drawable.honey
        listModel7.details = context.getString(R.string.glowingskin_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.ubtan_face_pack)
        listModel8.descriptionName = context.getString(R.string.till_it_dry)
        listModel8.image = R.drawable.glowing_ubtan
        listModel8.details = context.getString(R.string.glowingskin_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.olive_oil)
        listModel9.descriptionName = context.getString(R.string.one_min)
        listModel9.image = R.drawable.lice_oliveoil
        listModel9.details = context.getString(R.string.glowingskin_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.rose_water)
        listModel10.descriptionName = context.getString(R.string.five_min)
        listModel10.image = R.drawable.rosewater
        listModel10.details = context.getString(R.string.glowingskin_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.saffron)
        listModel11.descriptionName = context.getString(R.string.ten_min)
        listModel11.image = R.drawable.glowing_safron
        listModel11.details = context.getString(R.string.glowingskin_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.milk)
        listModel12.descriptionName = context.getString(R.string.twenty_min)
        listModel12.image = R.drawable.darkcrcl_milk
        listModel12.details = context.getString(R.string.glowingskin_remedy_thirteen)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.banana)
        listModel13.descriptionName = context.getString(R.string.fifteen_min)
        listModel13.image = R.drawable.banana_split
        listModel13.details = context.getString(R.string.glowingskin_remedy_fourteen)
        list.add(listModel13)
        val listModel14 = ListModel()
        listModel14.title = context.getString(R.string.orange_peel)
        listModel14.descriptionName = context.getString(R.string.fifteen_min)
        listModel14.image = R.drawable.orangepeel_dandruff
        listModel14.details = context.getString(R.string.glowingskin_remedy_fifteen)
        list.add(listModel14)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.skin_glowingskin_main_one)
        listModel.descriptionName = context.getString(R.string.skin_glowingskin_detail_one)
        listModel.image = R.drawable.hairfall_carrot_diet
        listModel.details = context.getString(R.string.glowingskin_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.skin_glowingskin_main_two)
        listModel1.descriptionName = context.getString(R.string.skin_glowingskin_detail_two)
        listModel1.image = R.drawable.glowing_karela
        listModel1.details = context.getString(R.string.glowingskin_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.skin_glowingskin_main_three)
        listModel2.descriptionName = context.getString(R.string.skin_glowingskin_detail_three)
        listModel2.image = R.drawable.darkcrcl_oranges
        listModel2.details = context.getString(R.string.glowingskin_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.skin_glowingskin_main_four)
        listModel3.descriptionName = context.getString(R.string.skin_glowingskin_detail_four)
        listModel3.image = R.drawable.eyelashes_salmon
        listModel3.details = context.getString(R.string.glowingskin_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.skin_glowingskin_main_five)
        listModel4.descriptionName = context.getString(R.string.skin_glowingskin_detail_five)
        listModel4.image = R.drawable.broccoli
        listModel4.details = context.getString(R.string.glowingskin_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.skin_glowingskin_main_six)
        listModel5.descriptionName = context.getString(R.string.skin_glowingskin_detail_six)
        listModel5.image = R.drawable.glowing_grapefruit
        listModel5.details = context.getString(R.string.glowingskin_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.skin_glowingskin_main_seven)
        listModel6.descriptionName = context.getString(R.string.skin_glowingskin_detail_seven)
        listModel6.image = R.drawable.darkcrcl_blueberries
        listModel6.details = context.getString(R.string.glowingskin_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.skin_glowingskin_main_eight)
        listModel7.descriptionName = context.getString(R.string.skin_glowingskin_detail_eight)
        listModel7.image = R.drawable.hairfall_oyster_diet
        listModel7.details = context.getString(R.string.glowingskin_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.skin_glowingskin_main_nine)
        listModel8.descriptionName = context.getString(R.string.skin_glowingskin_detail_nine)
        listModel8.image = R.drawable.walnut_dandruff
        listModel8.details = context.getString(R.string.glowingskin_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.skin_glowingskin_main_ten)
        listModel9.descriptionName = context.getString(R.string.skin_glowingskin_detail_ten)
        listModel9.image = R.drawable.tomato_dandruff
        listModel9.details = context.getString(R.string.glowingskin_diet_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.skin_glowingskin_main_eleven)
        listModel10.descriptionName = context.getString(R.string.skin_glowingskin_detail_eleven)
        listModel10.image = R.drawable.egg_dandruff
        listModel10.details = context.getString(R.string.glowingskin_diet_eleven)
        list.add(listModel10)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.plow_pose)
        listModel.descriptionName = context.getString(R.string.halasana)
        listModel.image = R.drawable.puffy_halasana
        listModel.details = context.getString(R.string.glowingskin_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.shoulder_stand_yoga)
        listModel1.descriptionName = context.getString(R.string.sarvangasana)
        listModel1.image = R.drawable.dandruff_shoulderstand
        listModel1.details = context.getString(R.string.glowingskin_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.the_cobra_pose)
        listModel2.descriptionName = context.getString(R.string.bhujangasana)
        listModel2.image = R.drawable.acne_bhujangasana
        listModel2.details = context.getString(R.string.glowingskin_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.the_triangle_pose)
        listModel3.descriptionName = context.getString(R.string.trikonasana)
        listModel3.image = R.drawable.acne_trikonasana
        listModel3.details = context.getString(R.string.glowingskin_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.the_fish_pose)
        listModel4.descriptionName = context.getString(R.string.matasyasana)
        listModel4.image = R.drawable.glowing_matasyasana
        listModel4.details = context.getString(R.string.glowingskin_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.additional_tips)
        listModel5.descriptionName = context.getString(R.string.more_tips)
        listModel5.image = R.drawable.glowingskin_title
        listModel5.details = context.getString(R.string.glowingskin_exercise_six)
        list.add(listModel5)
        return list
    }
}