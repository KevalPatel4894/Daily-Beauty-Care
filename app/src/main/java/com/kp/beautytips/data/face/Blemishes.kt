package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Blemishes {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.lemon)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.eyebrows_lemon
        listModel.details = context.getString(R.string.blemishes_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.tomato_juice)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.tomato_dandruff
        listModel1.details = context.getString(R.string.blemishes_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.apple_cider_vinegar)
        listModel2.descriptionName = context.getString(R.string.fifteen_min)
        listModel2.image = R.drawable.apple_vinegar_dandruff
        listModel2.details = context.getString(R.string.blemishes_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.potato)
        listModel3.descriptionName = context.getString(R.string.twenty_min)
        listModel3.image = R.drawable.darkcrcl_potato
        listModel3.details = context.getString(R.string.blemishes_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.fuller_earth)
        listModel4.descriptionName = context.getString(R.string.twenty_min)
        listModel4.image = R.drawable.underarms_multanimitti
        listModel4.details = context.getString(R.string.blemishes_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.aloe_vera)
        listModel5.descriptionName = context.getString(R.string.fifteen_min)
        listModel5.image = R.drawable.aloevera_split
        listModel5.details = context.getString(R.string.blemishes_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.orange_peel)
        listModel6.descriptionName = context.getString(R.string.fifteen_min)
        listModel6.image = R.drawable.orangepeel_dandruff
        listModel6.details = context.getString(R.string.blemishes_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.banana_peel)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.banana_split
        listModel7.details = context.getString(R.string.blemishes_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.cucumber)
        listModel8.descriptionName = context.getString(R.string.twenty_min)
        listModel8.image = R.drawable.darkcrcl_cucumber
        listModel8.details = context.getString(R.string.blemishes_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.strawberries)
        listModel9.descriptionName = context.getString(R.string.fifteen_min)
        listModel9.image = R.drawable.puffy_strawberries
        listModel9.details = context.getString(R.string.blemishes_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_blemishes_main_one)
        listModel.descriptionName = context.getString(R.string.face_blemishes_detail_one)
        listModel.image = R.drawable.dryhands_yogurt
        listModel.details = context.getString(R.string.blemishes_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_blemishes_main_two)
        listModel1.descriptionName = context.getString(R.string.face_blemishes_detail_two)
        listModel1.image = R.drawable.walnut_dandruff
        listModel1.details = context.getString(R.string.blemishes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_blemishes_main_three)
        listModel2.descriptionName = context.getString(R.string.face_blemishes_detail_three)
        listModel2.image = R.drawable.blemishes_cruciferous
        listModel2.details = context.getString(R.string.blemishes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_blemishes_main_four)
        listModel3.descriptionName = context.getString(R.string.face_blemishes_detail_four)
        listModel3.image = R.drawable.egg_dandruff
        listModel3.details = context.getString(R.string.blemishes_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_blemishes_main_five)
        listModel4.descriptionName = context.getString(R.string.face_blemishes_detail_five)
        listModel4.image = R.drawable.avocado_split
        listModel4.details = context.getString(R.string.blemishes_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_blemishes_main_six)
        listModel5.descriptionName = context.getString(R.string.face_blemishes_detail_six)
        listModel5.image = R.drawable.blemishes_darkberries
        listModel5.details = context.getString(R.string.blemishes_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_blemishes_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_blemishes_detail_seven)
        listModel6.image = R.drawable.blemishes_legumes
        listModel6.details = context.getString(R.string.blemishes_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.face_blemishes_main_eight)
        listModel7.descriptionName = context.getString(R.string.face_blemishes_detail_eight)
        listModel7.image = R.drawable.blemishes_burdock
        listModel7.details = context.getString(R.string.blemishes_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.face_blemishes_main_nine)
        listModel8.descriptionName = context.getString(R.string.face_blemishes_detail_nine)
        listModel8.image = R.drawable.blemishes_pumpkin
        listModel8.details = context.getString(R.string.blemishes_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.face_blemishes_main_ten)
        listModel9.descriptionName = context.getString(R.string.face_blemishes_detail_ten)
        listModel9.image = R.drawable.blemishes_sweetpotato
        listModel9.details = context.getString(R.string.blemishes_diet_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.face_blemishes_main_eleven)
        listModel10.descriptionName = context.getString(R.string.face_blemishes_detail_eleven)
        listModel10.image = R.drawable.blemishes_bellpepper
        listModel10.details = context.getString(R.string.blemishes_diet_eleven)
        list.add(listModel10)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.prevention_is_better_than_cure)
        listModel.descriptionName = context.getString(R.string.prevention)
        listModel.image = R.drawable.acne_title_small
        listModel.details = context.getString(R.string.blemishes_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.day_to_day_skin_care)
        listModel1.descriptionName = context.getString(R.string.skin_care)
        listModel1.image = R.drawable.acne_toning
        listModel1.details = context.getString(R.string.blemishes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.avoid_sun)
        listModel2.descriptionName = context.getString(R.string.avoid_sun_small)
        listModel2.image = R.drawable.thighs_avoidsun
        listModel2.details = context.getString(R.string.blemishes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.ice_compressing)
        listModel3.descriptionName = context.getString(R.string.wrapped_ice_cube)
        listModel3.image = R.drawable.blemishes_icecompressing
        listModel3.details = context.getString(R.string.blemishes_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.additional_tips)
        listModel4.descriptionName = context.getString(R.string.more_tips)
        listModel4.image = R.drawable.blemishes_title
        listModel4.details = context.getString(R.string.blemishes_diet_five)
        list.add(listModel4)
        return list
    }
}