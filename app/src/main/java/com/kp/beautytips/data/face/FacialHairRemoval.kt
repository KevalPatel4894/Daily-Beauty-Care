package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object FacialHairRemoval {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.papaya_turmeric)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.papaya_turmeric
        listModel.details = context.getString(R.string.facialhair_papaya_turmeric_remedy)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.potato_lentil)
        listModel1.descriptionName = context.getString(R.string.thirty_min)
        listModel1.image = R.drawable.darkcrcl_potato
        listModel1.details = context.getString(R.string.facialhair_potato_lentil_remedy)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.egg_cornstarch)
        listModel2.descriptionName = context.getString(R.string.twentyfive_min)
        listModel2.image = R.drawable.puffy_eggwhite
        listModel2.details = context.getString(R.string.facialhair_egg_cornstarch_remedy)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.gelatin_milk)
        listModel3.descriptionName = context.getString(R.string.five_min)
        listModel3.image = R.drawable.gileting_milk
        listModel3.details = context.getString(R.string.facialhair_gelatin_milk_remedy)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.oatmeal_banana)
        listModel4.descriptionName = context.getString(R.string.twenty_min)
        listModel4.image = R.drawable.dryhands_oatmeal
        listModel4.details = context.getString(R.string.facialhair_oatmeal_banana_remedy)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.honey_sugar)
        listModel5.descriptionName = context.getString(R.string.five_min)
        listModel5.image = R.drawable.honey
        listModel5.details = context.getString(R.string.facialhair_honey_sugar_remedy)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lavender_oil_tea_tree_oil)
        listModel6.descriptionName = context.getString(R.string.overnight)
        listModel6.image = R.drawable.teatreeoil_dandruff
        listModel6.details = context.getString(R.string.facialhair_lavender_oil_tea_tree_oil_remedy)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.onion_basil)
        listModel7.descriptionName = context.getString(R.string.twentyfive_min)
        listModel7.image = R.drawable.grey_onion_remedy
        listModel7.details = context.getString(R.string.facialhair_onion_basil_remedy)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.greengram_rosewater)
        listModel8.descriptionName = context.getString(R.string.twentyfive_min)
        listModel8.image = R.drawable.rosewater
        listModel8.details = context.getString(R.string.facialhair_greengram_rosewater_remedy)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.control_carb_diet)
        listModel.descriptionName = context.getString(R.string.control_carb_diet_detail)
        listModel.image = R.drawable.reducesugar_dandruff
        listModel.details = context.getString(R.string.facialhair_control_carb_diet)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.protein_fats_vegetables)
        listModel1.descriptionName = context.getString(R.string.protein_fats_vegetables_detail)
        listModel1.image = R.drawable.dryhands_oatmeal
        listModel1.details = context.getString(R.string.facialhair_protein_fats_vegetables_diet)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.medications_diet)
        listModel2.descriptionName = context.getString(R.string.medications_diet_detail)
        listModel2.image = R.drawable.medications
        listModel2.details = context.getString(R.string.facialhair_medications_diet)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.drink_plenty_water)
        listModel3.descriptionName = context.getString(R.string.drink_plenty_water_small)
        listModel3.image = R.drawable.dandruff_water
        listModel3.details = context.getString(R.string.facialhair_drink_plenty_water_diet)
        list.add(listModel3)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.epilate_tweeze_wax_cream_shave)
        listModel.descriptionName = context.getString(R.string.try_any_one)
        listModel.image = R.drawable.epilate_tweeze_wax_cream_shave_small
        listModel.details = context.getString(R.string.facialhair_tweeze_shave_wax)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.exercise)
        listModel1.descriptionName = context.getString(R.string.exercise_regularly)
        listModel1.image = R.drawable.yoga_title
        listModel1.details = context.getString(R.string.facialhair_regular_exercise)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.additional_tips)
        listModel2.descriptionName = context.getString(R.string.more_tips)
        listModel2.image = R.drawable.facialhair_title
        listModel2.details = context.getString(R.string.facialhair_additional_tips)
        list.add(listModel2)
        return list
    }
}