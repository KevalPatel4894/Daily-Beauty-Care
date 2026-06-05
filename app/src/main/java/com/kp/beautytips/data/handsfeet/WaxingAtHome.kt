package com.kp.beautytips.data.handsfeet

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object WaxingAtHome {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.waxingathome_honeysugarwax)
        listModel.descriptionName = context.getString(R.string.fifteen_min)
        listModel.image = R.drawable.sugar_honey
        listModel.details = context.getString(R.string.waxingathome_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.waxingathome_sugarlemonwax)
        listModel1.descriptionName = context.getString(R.string.twenty_min)
        listModel1.image = R.drawable.sugar_lemon
        listModel1.details = context.getString(R.string.waxingathome_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.waxingathome_sugarhoneylemonwax)
        listModel2.descriptionName = context.getString(R.string.fifteen_min)
        listModel2.image = R.drawable.sugar_honey_lemon
        listModel2.details = context.getString(R.string.waxingathome_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.waxingathome_chocolatewax)
        listModel3.descriptionName = context.getString(R.string.twenty_min)
        listModel3.image = R.drawable.chocoatewax
        listModel3.details = context.getString(R.string.waxingathome_remedy_four)
        list.add(listModel3)
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
        listModel1.image = R.drawable.dryhand_leafyveg
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
        listModel2.image = R.drawable.waxing_title
        listModel2.details = context.getString(R.string.facialhair_additional_tips)
        list.add(listModel2)
        return list
    }
}