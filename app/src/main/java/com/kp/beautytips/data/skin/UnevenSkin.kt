package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object UnevenSkin {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.goatmilk_gramflour_bakingsoda)
        listModel.descriptionName = context.getString(R.string.till_it_dry)
        listModel.image = R.drawable.goatmilk
        listModel.details = context.getString(R.string.uneven_goatmilk_gramflour_bakingsoda_remedy)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.lemon_sugar_coconutoil_scrub)
        listModel1.descriptionName = context.getString(R.string.five_min)
        listModel1.image = R.drawable.fair_lemonaloevera
        listModel1.details = context.getString(R.string.uneven_lemon_sugar_coconutoil_scrub_remedy)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.turmeric_yogurt_lemon_facepack)
        listModel2.descriptionName = context.getString(R.string.till_it_dry)
        listModel2.image = R.drawable.thighs_turmeric
        listModel2.details =
            context.getString(R.string.uneven_turmeric_yogurt_lemon_facepack_remedy)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.milkpowder)
        listModel3.descriptionName = context.getString(R.string.till_it_dry)
        listModel3.image = R.drawable.milkpowder
        listModel3.details = context.getString(R.string.uneven_milkpowder_remedy)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.tomato_lemon_honey_facepack)
        listModel4.descriptionName = context.getString(R.string.fifteen_min)
        listModel4.image = R.drawable.tomato_dandruff
        listModel4.details = context.getString(R.string.uneven_tomato_lemon_honey_facepack_remedy)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.basil_neem_multani_facepack)
        listModel5.descriptionName = context.getString(R.string.till_it_dry)
        listModel5.image = R.drawable.tulsi_neem_multani_rosewater
        listModel5.details = context.getString(R.string.uneven_basil_neem_multani_facepack_remedy)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.papaya_milk_facewash)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.papaya
        listModel6.details = context.getString(R.string.uneven_papaya_milk_facewash_remedy)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.honey_lemon_facemask)
        listModel7.descriptionName = context.getString(R.string.twenty_min)
        listModel7.image = R.drawable.honey
        listModel7.details = context.getString(R.string.uneven_honey_lemon_facemask_remedy)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.sandalwood_milk)
        listModel8.descriptionName = context.getString(R.string.till_it_dry)
        listModel8.image = R.drawable.prickly_sandalwoodpdr
        listModel8.details = context.getString(R.string.uneven_sandalwood_milk_remedy)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.orange_sugar_alovera)
        listModel9.descriptionName = context.getString(R.string.twenty_min)
        listModel9.image = R.drawable.darkcrcl_oranges
        listModel9.details = context.getString(R.string.uneven_orange_sugar_alovera_remedy)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.cucumber_lemon)
        listModel10.descriptionName = context.getString(R.string.fifteen_min)
        listModel10.image = R.drawable.darkcrcl_cucumber
        listModel10.details = context.getString(R.string.uneven_cucumber_lemon_remedy)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.honey_oatmeal)
        listModel11.descriptionName = context.getString(R.string.fifteen_min)
        listModel11.image = R.drawable.dryhands_oatmeal
        listModel11.details = context.getString(R.string.uneven_honey_oatmeal_remedy)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.orangejuice_turmeric)
        listModel12.descriptionName = context.getString(R.string.ten_min)
        listModel12.image = R.drawable.orangejuice_turmeric
        listModel12.details = context.getString(R.string.uneven_orangejuice_turmeric_remedy)
        list.add(listModel12)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.vitamin_a)
        listModel.descriptionName = context.getString(R.string.vitamin_a_content)
        listModel.image = R.drawable.drydmg_vitamina
        listModel.details = context.getString(R.string.uneven_vitamin_a_detail)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.vitamin_b_twelvle)
        listModel1.descriptionName = context.getString(R.string.vitamin_b_twelvle_content)
        listModel1.image = R.drawable.vitaminb12
        listModel1.details = context.getString(R.string.uneven_vitamin_b_twelvle_detail)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.vitamin_c)
        listModel2.descriptionName = context.getString(R.string.vitamin_c_content)
        listModel2.image = R.drawable.beautifuleyes_vitaminc
        listModel2.details = context.getString(R.string.uneven_vitamin_c_detail)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.vitamin_e)
        listModel3.descriptionName = context.getString(R.string.vitamin_e_content)
        listModel3.image = R.drawable.shinynail_vitamine
        listModel3.details = context.getString(R.string.uneven_vitamin_e_detail)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.avoid_foods)
        listModel4.descriptionName = context.getString(R.string.avoid_foods_content)
        listModel4.image = R.drawable.blackheads_avoidfood
        listModel4.details = context.getString(R.string.uneven_avoid_foods_detail)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.uneven_exercise_title_one)
        listModel.descriptionName = context.getString(R.string.exfoliate)
        listModel.image = R.drawable.blackheads_exfoliate
        listModel.details = context.getString(R.string.uneven_exercise_detail_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.uneven_exercise_title_two)
        listModel1.descriptionName = context.getString(R.string.drink_plenty_water_small)
        listModel1.image = R.drawable.dandruff_water
        listModel1.details = context.getString(R.string.uneven_exercise_detail_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.uneven_exercise_title_three)
        listModel2.descriptionName = context.getString(R.string.moisturise_whole_body)
        listModel2.image = R.drawable.moisturizer
        listModel2.details = context.getString(R.string.uneven_exercise_detail_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.uneven_exercise_title_four)
        listModel3.descriptionName = context.getString(R.string.exercise)
        listModel3.image = R.drawable.yoga_title
        listModel3.details = context.getString(R.string.uneven_exercise_detail_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.uneven_exercise_title_five)
        listModel4.descriptionName = context.getString(R.string.use_makeup)
        listModel4.image = R.drawable.makeup
        listModel4.details = context.getString(R.string.uneven_exercise_detail_five)
        list.add(listModel4)
        return list
    }
}