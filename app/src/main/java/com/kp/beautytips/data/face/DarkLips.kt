package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object DarkLips {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.lemon)
        listModel.descriptionName = context.getString(R.string.few_min)
        listModel.image = R.drawable.eyebrows_lemon
        listModel.details = context.getString(R.string.darklips_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.rose)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.darklips_rose
        listModel1.details = context.getString(R.string.darklips_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.olive_oil)
        listModel2.descriptionName = context.getString(R.string.few_min)
        listModel2.image = R.drawable.lice_oliveoil
        listModel2.details = context.getString(R.string.darklips_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.sugar)
        listModel3.descriptionName = context.getString(R.string.few_min)
        listModel3.image = R.drawable.darklips_sugar
        listModel3.details = context.getString(R.string.darklips_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.beetroot)
        listModel4.descriptionName = context.getString(R.string.few_min)
        listModel4.image = R.drawable.darkcrcl_beetroot
        listModel4.details = context.getString(R.string.darklips_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.pomegranate)
        listModel5.descriptionName = context.getString(R.string.ten_min)
        listModel5.image = R.drawable.darklips_pomegranate
        listModel5.details = context.getString(R.string.darklips_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.cucumber_juice)
        listModel6.descriptionName = context.getString(R.string.few_min)
        listModel6.image = R.drawable.darkcrcl_cucumber
        listModel6.details = context.getString(R.string.darklips_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.berries)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.puffy_strawberries
        listModel7.details = context.getString(R.string.darklips_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.honey)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.honey
        listModel8.details = context.getString(R.string.darklips_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.almond_oil)
        listModel9.descriptionName = context.getString(R.string.overnight)
        listModel9.image = R.drawable.darkcrcl_almondoil
        listModel9.details = context.getString(R.string.darklips_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.aloe_vera)
        listModel10.descriptionName = context.getString(R.string.till_it_dry)
        listModel10.image = R.drawable.aloevera_split
        listModel10.details = context.getString(R.string.darklips_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.apple_cider_vinegar)
        listModel11.descriptionName = context.getString(R.string.twelve_min)
        listModel11.image = R.drawable.apple_vinegar_dandruff
        listModel11.details = context.getString(R.string.darklips_remedy_twelve)
        list.add(listModel11)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_darklips_main_one)
        listModel.descriptionName = context.getString(R.string.face_darklips_detail_one)
        listModel.image = R.drawable.honey
        listModel.details = context.getString(R.string.darklips_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_darklips_main_two)
        listModel1.descriptionName = context.getString(R.string.face_darklips_detail_two)
        listModel1.image = R.drawable.tomato_dandruff
        listModel1.details = context.getString(R.string.darklips_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_darklips_main_three)
        listModel2.descriptionName = context.getString(R.string.face_darklips_detail_three)
        listModel2.image = R.drawable.eyelashes_greentea
        listModel2.details = context.getString(R.string.darklips_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_darklips_main_four)
        listModel3.descriptionName = context.getString(R.string.face_darklips_detail_four)
        listModel3.image = R.drawable.coconutoil_hairfall_remedy
        listModel3.details = context.getString(R.string.darklips_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_darklips_main_five)
        listModel4.descriptionName = context.getString(R.string.face_darklips_detail_five)
        listModel4.image = R.drawable.walnut_dandruff
        listModel4.details = context.getString(R.string.darklips_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_darklips_main_six)
        listModel5.descriptionName = context.getString(R.string.face_darklips_detail_six)
        listModel5.image = R.drawable.dryhands_yogurt
        listModel5.details = context.getString(R.string.darklips_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_darklips_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_darklips_detail_seven)
        listModel6.image = R.drawable.eyebrows_lemon
        listModel6.details = context.getString(R.string.darklips_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.face_darklips_main_eight)
        listModel7.descriptionName = context.getString(R.string.face_darklips_detail_eight)
        listModel7.image = R.drawable.darkcrcl_watermelon
        listModel7.details = context.getString(R.string.darklips_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.face_darklips_main_nine)
        listModel8.descriptionName = context.getString(R.string.face_darklips_detail_nine)
        listModel8.image = R.drawable.aloevera_split
        listModel8.details = context.getString(R.string.darklips_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.face_darklips_main_ten)
        listModel9.descriptionName = context.getString(R.string.face_darklips_detail_ten)
        listModel9.image = R.drawable.darkcrcl_beetroot
        listModel9.details = context.getString(R.string.darklips_diet_ten)
        list.add(listModel9)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.no_smoking_alcohal)
        listModel.descriptionName = context.getString(R.string.no_smoking)
        listModel.image = R.drawable.darklips_nosmoking
        listModel.details = context.getString(R.string.darklips_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.drink_less_coffee)
        listModel1.descriptionName = context.getString(R.string.less_tea_coffee)
        listModel1.image = R.drawable.teeth_teacoffee
        listModel1.details = context.getString(R.string.darklips_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.minimize_sun_exposure)
        listModel2.descriptionName = context.getString(R.string.avoid_sun_small)
        listModel2.image = R.drawable.thighs_avoidsun
        listModel2.details = context.getString(R.string.darklips_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.check_your_lipsticks)
        listModel3.descriptionName = context.getString(R.string.no_excessive_use)
        listModel3.image = R.drawable.darklips_lipstick
        listModel3.details = context.getString(R.string.darklips_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.dont_suck_your_lips)
        listModel4.descriptionName = context.getString(R.string.dont_bite_suck_lips)
        listModel4.image = R.drawable.darklips_sucklips
        listModel4.details = context.getString(R.string.darklips_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.additional_tips)
        listModel5.descriptionName = context.getString(R.string.more_tips)
        listModel5.image = R.drawable.darklips_title
        listModel5.details = context.getString(R.string.darklips_exercise_six)
        list.add(listModel5)
        return list
    }
}