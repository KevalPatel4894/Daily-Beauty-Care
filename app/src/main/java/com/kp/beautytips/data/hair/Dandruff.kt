package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Dandruff {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coconut_oil_lemon_massage)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.coconutoil_lemon_dandruff
        listModel.details = context.getString(R.string.dandruff_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.rosemary_oil_treatment)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.rosemary_oil_dandruff
        listModel1.details = context.getString(R.string.dandruff_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.fenugreek_pack)
        listModel2.descriptionName = context.getString(R.string.one_hr)
        listModel2.image = R.drawable.fenugreekseeds
        listModel2.details = context.getString(R.string.dandruff_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.rosemary_teatree_pack)
        listModel3.descriptionName = context.getString(R.string.thirty_min)
        listModel3.image = R.drawable.teatreeoil_dandruff
        listModel3.details = context.getString(R.string.dandruff_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.yogurt_black_pepper)
        listModel4.descriptionName = context.getString(R.string.one_hr)
        listModel4.image = R.drawable.curd_blackpepper_dandruff
        listModel4.details = context.getString(R.string.dandruff_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.baking_soda)
        listModel5.descriptionName = context.getString(R.string.two_min)
        listModel5.image = R.drawable.baking_soda_dandruff
        listModel5.details = context.getString(R.string.dandruff_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.tea_tree_oil)
        listModel6.descriptionName = context.getString(R.string.five_min)
        listModel6.image = R.drawable.ic_tea_tree_oil
        listModel6.details = context.getString(R.string.dandruff_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.apple_cider_vinegar)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.apple_vinegar_dandruff
        listModel7.details = context.getString(R.string.dandruff_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.neem_juice)
        listModel8.descriptionName = context.getString(R.string.ten_min)
        listModel8.image = R.drawable.neemjuice_dandruff
        listModel8.details = context.getString(R.string.dandruff_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.orange_peel_pack)
        listModel9.descriptionName = context.getString(R.string.thirty_min)
        listModel9.image = R.drawable.orangepeel_dandruff
        listModel9.details = context.getString(R.string.dandruff_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_dandruff_main_one)
        listModel.descriptionName = context.getString(R.string.hair_dandruff_detail_one)
        listModel.image = R.drawable.dandruff_water
        listModel.details = context.getString(R.string.dandruff_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_dandruff_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_dandruff_detail_two)
        listModel1.image = R.drawable.omega3_dandruff
        listModel1.details = context.getString(R.string.dandruff_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_dandruff_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_dandruff_detail_three)
        listModel2.image = R.drawable.vitaminb_dandruff
        listModel2.details = context.getString(R.string.dandruff_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_dandruff_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_dandruff_detail_four)
        listModel3.image = R.drawable.allicin_dandruff
        listModel3.details = context.getString(R.string.dandruff_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_dandruff_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_dandruff_detail_five)
        listModel4.image = R.drawable.reducesugar_dandruff
        listModel4.details = context.getString(R.string.dandruff_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.hair_dandruff_main_six)
        listModel5.descriptionName = context.getString(R.string.hair_dandruff_detail_six)
        listModel5.image = R.drawable.allicin_dandruff
        listModel5.details = context.getString(R.string.dandruff_diet_six)
        list.add(listModel5)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.downward_facing_dog_pose)
        listModel.descriptionName = context.getString(R.string.adho_mukha_svanasana)
        listModel.image = R.drawable.dandruff_adhomukh
        listModel.details = context.getString(R.string.dandruff_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.lying_down_Body_twist_yoga)
        listModel1.descriptionName = context.getString(R.string.chakrasana)
        listModel1.image = R.drawable.dandruff_chakrasana
        listModel1.details = context.getString(R.string.dandruff_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.rubbing_nails)
        listModel2.descriptionName = context.getString(R.string.balyam_yoga)
        listModel2.image = R.drawable.dandruff_nailrubbing
        listModel2.details = context.getString(R.string.dandruff_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.shoulder_stand_yoga)
        listModel3.descriptionName = context.getString(R.string.sarvangasana)
        listModel3.image = R.drawable.dandruff_shoulderstand
        listModel3.details = context.getString(R.string.dandruff_exercise_four)
        list.add(listModel3)
        return list
    }
}