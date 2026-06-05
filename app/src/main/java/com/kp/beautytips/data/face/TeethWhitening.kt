package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object TeethWhitening {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.baking_soda)
        listModel.descriptionName = context.getString(R.string.few_min)
        listModel.image = R.drawable.baking_soda_dandruff
        listModel.details = context.getString(R.string.teethwhitening_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.orange_juice)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.orangepeel_dandruff
        listModel1.details = context.getString(R.string.teethwhitening_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.strawberry_cream)
        listModel2.descriptionName = context.getString(R.string.few_min)
        listModel2.image = R.drawable.puffy_strawberries
        listModel2.details = context.getString(R.string.teethwhitening_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hydrogen_peroxide)
        listModel3.descriptionName = context.getString(R.string.few_min)
        listModel3.image = R.drawable.teeth_hydrogen
        listModel3.details = context.getString(R.string.teethwhitening_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.lemon)
        listModel4.descriptionName = context.getString(R.string.few_min)
        listModel4.image = R.drawable.eyebrows_lemon
        listModel4.details = context.getString(R.string.teethwhitening_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.salt)
        listModel5.descriptionName = context.getString(R.string.brush_with_it)
        listModel5.image = R.drawable.lice_salt
        listModel5.details = context.getString(R.string.teethwhitening_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.holy_basil)
        listModel6.descriptionName = context.getString(R.string.brush_with_it)
        listModel6.image = R.drawable.teeth_basil
        listModel6.details = context.getString(R.string.teethwhitening_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.charcoal)
        listModel7.descriptionName = context.getString(R.string.brush_with_it)
        listModel7.image = R.drawable.teeth_charcoal
        listModel7.details = context.getString(R.string.teethwhitening_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.margosa_neem)
        listModel8.descriptionName = context.getString(R.string.brush_with_it)
        listModel8.image = R.drawable.teeth_margosa
        listModel8.details = context.getString(R.string.teethwhitening_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_teethwhitening_main_one)
        listModel.descriptionName = context.getString(R.string.face_teethwhitening_detail_one)
        listModel.image = R.drawable.teeth_cauliflower
        listModel.details = context.getString(R.string.teethwhitening_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_teethwhitening_main_two)
        listModel1.descriptionName = context.getString(R.string.face_teethwhitening_detail_two)
        listModel1.image = R.drawable.puffy_strawberries
        listModel1.details = context.getString(R.string.teethwhitening_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_teethwhitening_main_three)
        listModel2.descriptionName = context.getString(R.string.face_teethwhitening_detail_three)
        listModel2.image = R.drawable.teeth_cheese
        listModel2.details = context.getString(R.string.teethwhitening_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_teethwhitening_main_four)
        listModel3.descriptionName = context.getString(R.string.face_teethwhitening_detail_four)
        listModel3.image = R.drawable.teeth_apples
        listModel3.details = context.getString(R.string.teethwhitening_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_teethwhitening_main_five)
        listModel4.descriptionName = context.getString(R.string.face_teethwhitening_detail_five)
        listModel4.image = R.drawable.teeth_celery
        listModel4.details = context.getString(R.string.teethwhitening_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_teethwhitening_main_six)
        listModel5.descriptionName = context.getString(R.string.face_teethwhitening_detail_six)
        listModel5.image = R.drawable.darkcrcl_milk
        listModel5.details = context.getString(R.string.teethwhitening_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_teethwhitening_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_teethwhitening_detail_seven)
        listModel6.image = R.drawable.ic_food_avoide
        listModel6.details = context.getString(R.string.teethwhitening_diet_seven)
        list.add(listModel6)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coconut_oil_pulling)
        listModel.descriptionName = context.getString(R.string.oil_pulling)
        listModel.image = R.drawable.coconutoil_hairfall_remedy
        listModel.details = context.getString(R.string.teethwhitening_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.brush_your_tongue)
        listModel1.descriptionName = context.getString(R.string.kill_all_bacterias)
        listModel1.image = R.drawable.teeth_tongue
        listModel1.details = context.getString(R.string.teethwhitening_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.rinse_with_apple_cider_vinegar)
        listModel2.descriptionName = context.getString(R.string.natural_antibiotic)
        listModel2.image = R.drawable.apple_vinegar_dandruff
        listModel2.details = context.getString(R.string.teethwhitening_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.floss_your_teeth)
        listModel3.descriptionName = context.getString(R.string.twice_daily)
        listModel3.image = R.drawable.teeth_floss
        listModel3.details = context.getString(R.string.teethwhitening_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.additional_tips)
        listModel4.descriptionName = context.getString(R.string.more_tips)
        listModel4.image = R.drawable.teeth_title
        listModel4.details = context.getString(R.string.teethwhitening_exercise_five)
        list.add(listModel4)
        return list
    }
}