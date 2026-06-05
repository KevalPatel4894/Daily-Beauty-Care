package com.kp.beautytips.data.handsfeet

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object DarkHandsAndFeet {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.lemon)
        listModel.descriptionName = context.getString(R.string.fifteen_min)
        listModel.image = R.drawable.darkhandsfeet_lemon
        listModel.details = context.getString(R.string.darkhandfeet_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.yogurt)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.dryhands_yogurt
        listModel1.details = context.getString(R.string.darkhandfeet_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.cucumber)
        listModel2.descriptionName = context.getString(R.string.fifteen_min)
        listModel2.image = R.drawable.darkcrcl_cucumber
        listModel2.details = context.getString(R.string.darkhandfeet_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.aloe_vera)
        listModel3.descriptionName = context.getString(R.string.thirty_min)
        listModel3.image = R.drawable.aloevera_split
        listModel3.details = context.getString(R.string.darkhandfeet_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.gram_flour)
        listModel4.descriptionName = context.getString(R.string.till_it_dry)
        listModel4.image = R.drawable.gramflour
        listModel4.details = context.getString(R.string.darkhandfeet_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.honey_lemon)
        listModel5.descriptionName = context.getString(R.string.twenty_min)
        listModel5.image = R.drawable.honey
        listModel5.details = context.getString(R.string.darkhandfeet_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.orange_peel)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.orangepeel_dandruff
        listModel6.details = context.getString(R.string.darkhandfeet_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.tomato)
        listModel7.descriptionName = context.getString(R.string.fifteen_onehour)
        listModel7.image = R.drawable.tomato_dandruff
        listModel7.details = context.getString(R.string.darkhandfeet_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.turmeric)
        listModel8.descriptionName = context.getString(R.string.twenty_min)
        listModel8.image = R.drawable.thighs_turmeric
        listModel8.details = context.getString(R.string.darkhandfeet_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.sandalwood)
        listModel9.descriptionName = context.getString(R.string.twenty_min)
        listModel9.image = R.drawable.drydmg_sandlewood
        listModel9.details = context.getString(R.string.darkhandfeet_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.papaya)
        listModel10.descriptionName = context.getString(R.string.thirty_min)
        listModel10.image = R.drawable.darkhandsfeet_papaya
        listModel10.details = context.getString(R.string.darkhandfeet_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.rice)
        listModel11.descriptionName = context.getString(R.string.thirty_min)
        listModel11.image = R.drawable.darkhandsfeet_rice
        listModel11.details = context.getString(R.string.darkhandfeet_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.oatmeal)
        listModel12.descriptionName = context.getString(R.string.fifteen_min)
        listModel12.image = R.drawable.dryhands_oatmeal
        listModel12.details = context.getString(R.string.darkhandfeet_remedy_thirteen)
        list.add(listModel12)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.handsfeet_darkhandfeet_main_one)
        listModel.descriptionName = context.getString(R.string.handsfeet_darkhandfeet_detail_one)
        listModel.image = R.drawable.dandruff_water
        listModel.details = context.getString(R.string.darkhandfeet_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.handsfeet_darkhandfeet_main_two)
        listModel1.descriptionName = context.getString(R.string.handsfeet_darkhandfeet_detail_two)
        listModel1.image = R.drawable.underarms_balanceddiet
        listModel1.details = context.getString(R.string.darkhandfeet_diet_two)
        list.add(listModel1)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.take_care_sun)
        listModel.descriptionName = context.getString(R.string.take_care)
        listModel.image = R.drawable.thighs_avoidsun
        listModel.details = context.getString(R.string.darkhandfeet_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.exfoliate)
        listModel1.descriptionName = context.getString(R.string.remove_dead_skin)
        listModel1.image = R.drawable.darkhandsfeet_exfoliate
        listModel1.details = context.getString(R.string.darkhandfeet_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.moisturize)
        listModel2.descriptionName = context.getString(R.string.before_going_bed)
        listModel2.image = R.drawable.heels_moisturize
        listModel2.details = context.getString(R.string.darkhandfeet_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.additional_tips)
        listModel3.descriptionName = context.getString(R.string.more_tips)
        listModel3.image = R.drawable.darkhandsfeet_title
        listModel3.details = context.getString(R.string.darkhandfeet_exercise_four)
        list.add(listModel3)
        return list
    }
}